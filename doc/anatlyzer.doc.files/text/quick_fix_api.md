
# Quick-fixes

This page describes how to add new quick fixes by means of a dedicated extension
point and the provided API. Many examples of concrete quick fixes already bundled
with anATLyzer are available in the `anatlyzer.atl.editor.quickfix` project. 

## Extension points

A quick-fix can be added to anATLyzer by instantiating the `anatlyzer.atl.editor.quickfix`
extension point. There are two ways to declare a new quickfix:

 *  Using a **quickfix** element. 
    The class that implements the quick fix is indicated in the `apply` property. It must implement 
    the `AtlProblemQuickfix` class. For instance, this is a piece of XML written in `plugin.xml`
    to declare a quick fix that changes navigations over collections using a dot with an arrow.
    
	  <extension
         id="anatlyzer.atl.editor.quickfix.warnings.OperationOverCollectionQuickfix"
         name="Quickfix: Change dot for arrow"
         point="anatlyzer.atl.editor.quickfix">
        <quickfix
            apply="anatlyzer.atl.editor.quickfix.warnings.CollectionOperationOverNoCollectionQuickfix">
        </quickfix>
      </extension>
       
 *  Using a **quickfixset** element.
    This alternative is useful for a) package a set of quick fixes for the same type of problems, or
    b) compute quick fixes dynamically.

## Implementing a quick fix using the quick fix API

The default way to create quick fixes in Eclipse is generating strings that replace other strings
in the original ATL file, performing the replacement directly on the document buffer. 
However, this approach is too low-level, hindering other usages of the quick fix such as speculative analysis, incremental retyping of the transformation, and automated evaluation of quick fixes (i.e, applying quick fixes 
in batch mode).

To tackle this, the recommended way to implement quick fixes in anATLyzer is to create fragments of 
the abstract syntax tree (AST) and replace the corresponding elements with such fragments. 
Thus, a quick fix works at the AST level allowing manipulating the resulting transformation
before making the changes directly available to the editor.
Although this poses the problem of correctly serializing the new fragments, maintaining the layout of 
the existing text, anATLyzer tries to handle this issue transparently.

The `anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix` class provides facility methods to
access properties of the quick fix, namely, the problem object, the offsets of the problem, etc.
In particular, it contains the `QuickfixApplication getQuickfixApplication()` abstract method,
which is intended to contain the implementation of the quick fix.  

The following code shows an implementation of a quick fix:

	/**
	 * Collection operations must be accessed with "->", but ATL supports ".".
	 * This quickfix makes the code OCL compliant.
	 * 
	 * Example:
	 * <pre>
	 * 		Sequence { }.operation()   =>  Sequence { }->operation()
	 * </pre>
	 */
	public class OperationOverCollectionTypeQuickfix extends AbstractAtlQuickfix {
	
		@Override
		public boolean isApplicable(IMarker marker) {
			return checkProblemType(marker, OperationOverCollectionType.class);		
		}
	
		@Override
		public QuickfixApplication getQuickfixApplication() {
			OperationCallExp call = (OperationCallExp) getProblematicElement();
			QuickfixApplication qfa = new QuickfixApplication();
			
			qfa.replace(call, (expr, trace) -> {
				trace.preserve(expr.getSource());
				trace.preserve(expr.getArguments());
				
				CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
				colOp.setOperationName(expr.getOperationName());
				colOp.getArguments().addAll(expr.getArguments());
				colOp.setSource(expr.getSource());
				
				return colOp;
			});
			
			return qfa;
		}
	
		@Override
		public void apply(IDocument document) {
			QuickfixApplication qfa = getQuickfixApplication();			
			new InDocumentSerializer(qfa, document).serialize();	
		}
	
		@Override
		public String getDisplayString() {
			return "Replace '.' with '->'";
		}
	}

The `QuickfixApplication` class provides simple refactoring operations, providing mechanisms to
keep the traceability relationships. Internally it convert refactorings operations into commands.
So far, there are two operations:

*   replace. Replaces the given element with the generated one, keeping the trace relationship.
    It is important to use the `preserve` method to indicate which elements of the replaced
    elements are moved into the new element.
    
*   insertAfter. Given an anchor element (e.g., a helper) it inserts the generated element in the
    same reference, following it.

The rationale for the `QuickfixApplication` is to decouple the specification of the refactoring
from its actual application. **For the moment this is only true for serialization, because commands
are applied eagerly in the current implementation**.

The `anatlyzer.atl.quickfixast.InDocumentSerializer` uses a `QuickfixApplication` object
to modify the Eclipse document with the applied refactoring.

Some useful classes when working at the AST level are `anatlyzer.atl.quickfixast.ASTUtils`,
`anatlyzer.atl.util.ATLUtils` and `anatlyzer.atl.model.TypeUtils`.

