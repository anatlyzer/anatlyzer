package anatlyzer.atl.quickfixast;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IRegion;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlModelAnalyser;
import org.eclipse.m2m.atl.common.AtlNbCharFile;

import anatlyzer.atl.impact.ChangeImpact;
import anatlyzer.atl.quickfixast.QuickfixApplication.Action;
import anatlyzer.atl.quickfixast.QuickfixApplication.AddCommentBefore;
import anatlyzer.atl.quickfixast.QuickfixApplication.DeleteAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.InsertAfterAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.InsertBeforeAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.PutInAction;
import anatlyzer.atl.quickfixast.QuickfixApplication.ReplacementAction;
import anatlyzer.atl.simplifier.IOclSimplifier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.ui.preferences.AnATLyzerPreferenceInitializer;
import anatlyzer.ui.util.ExtensionPointUtils;

public class InDocumentSerializer extends ATLSerializer {

	private QuickfixApplication qfa;
	private IDocument document;
	private Action currentAction;
	private AtlNbCharFile help;
	private AtlModelAnalyser modelAnalyser;

	public InDocumentSerializer(QuickfixApplication qfa, IDocument document) {
		this.qfa = qfa;
		this.document = document;

		this.help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		// return help.getIndexChar(obj.getLocation());
	}

	public void serialize() {
		IDocumentExtension4 ext4 = (IDocumentExtension4) document;
		
		final DocumentRewriteSession session = ext4.startRewriteSession(DocumentRewriteSessionType.SEQUENTIAL);
		try {
			for(Action original : qfa.getActions()) {
				Action a = getActualAction(original);
	
				this.currentAction = a;
				
				// Special action that does not have a tgt
				if ( a instanceof AddCommentBefore ) {
					LocatedElement element = ((AddCommentBefore) a).getElement();
					String comment  = ((AddCommentBefore) a).getComment();
					int[] offsets = help.getIndexChar(element.getLocation());

					int start = offsets[0];
					int length = 0;
					String s = comment + "\n";									
					document.replace(start, length, s);

					qfa.updateWorkbench(document);

					return;
				}
				
				EObject targetExpression = a.getTgt();

				if ( AnATLyzerPreferenceInitializer.getUseOclSimplifier() ) {
					IOclSimplifier simplifier = ExtensionPointUtils.getOclSimplifier();
					if ( simplifier != null ) {
						System.out.println(AnalyserUtils.toTree((LocatedElement) targetExpression));
						
						EObject result = simplifier.simplify(qfa.getAnalysis(), targetExpression);
						if ( result != null ) {
							targetExpression = result;
						}
					}					
				}

				
				setInitialIndent(targetExpression);
				
				startVisiting(targetExpression);
				String s = g(targetExpression);
				
				int start  = -1;
				int length = -1;
				
				if ( a instanceof ReplacementAction ) {
					int[] offsets = help.getIndexChar(((LocatedElement) ((ReplacementAction) a).getSrc()).getLocation());
	
					start  = offsets[0];
					length = offsets[1] - offsets[0];
				} else if ( a instanceof InsertAfterAction ) {
					int[] offsets = help.getIndexChar(((LocatedElement) ((InsertAfterAction) a).getAnchor()).getLocation());
					start  = offsets[1];
					length = 0;
					if ( targetExpression instanceof Binding ) {
						// For bindings do nothing, inBinding overriding takes care
					} else {
						// By default, add a carriage return to other elements
						s = "\n\n" + s;
					}
				}  else if ( a instanceof InsertBeforeAction ) {
					int[] offsets = help.getIndexChar(((LocatedElement) ((InsertBeforeAction) a).getAnchor()).getLocation());
					start  = offsets[1];
					length = 0;
					if ( targetExpression instanceof Binding ) {
						// For bindings do nothing, inBinding overriding takes care
					} else {
						// By default, add a carriage return to other elements
						s = "\n\n" + s;
					}
				} else {
					throw new UnsupportedOperationException();
				}
				// For the moment analysing the cases, because I do not want to make Actions dependent on AtlHelp...
				
				
//				MyATLCodeFormatter formatter = new MyATLCodeFormatter();
//				s = formatter.format(new ByteArrayInputStream(s.getBytes()));
				
				//String s1 = document.get(start, length);
				//String s2 = document.get(start, length + 2);
				
				document.replace(start, length, s);
	
				/*
				AtlCompletionHelper h = new AtlCompletionHelper(document.get());

				Document document2 = new Document();
				document2.set(document.get());
				AtlSourceManager sourceManager = new AtlSourceManager();
				sourceManager.updateDataSource(document2.get());
				AtlModelAnalyser modelAnalyser = new AtlModelAnalyser(h, sourceManager
						.getModel(), 0, AtlCompletionDataSource.getATLFileContext(sourceManager));

				EObject theEObject = modelAnalyser.getLocatedElement(start);
				
				MyATLCodeFormatter formatter = new MyATLCodeFormatter();				
				formatter.format(document2, AtlCodeFormatterPreferences.getDefaultOptions(), modelAnalyser);

				System.out.println(document2.get());
				*/
				// String s2 = formatter.format(new ByteArrayInputStream(document.get().getBytes()), 0);

				// System.out.println(s2);
				
			}
			// This a kind of "post-apply"
			qfa.updateWorkbench(document);
			
			// TODO: Remove this from here, this is here just for testing
			ChangeImpact i = qfa.getImpact();
			System.out.println(i);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			ext4.stopRewriteSession(session);
		}

		
	}

	public static Action getActualAction(Action original) {
		Action a;
		if ( original instanceof PutInAction ) {
			a = ((PutInAction) original).toMockReplacement();
		} else if ( original instanceof DeleteAction ) {
			// This is only an easy way to do this, but it is not able to preserve the layout
			a = ((DeleteAction) original).toMockReplacement();					
		} else {
			a = original;
		}
		return a;
	}

	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		// A trick to deal with deletion of bindings...
		if ( currentAction.getTgt() == self ) {
			String s = self.getVarName() + " : " + g(self.getType());
			
			List<String> l = sl();
			for(Binding b : self.getBindings()) {
				l.add(g(b));
			}
			
			if ( l.size() > 0 ) {
				// s(s + "(" + cr() + join(l, "," + cr()) + cr() + ")");
				s(s + " (" + cr() + join(l, ",\n") + cr() + genTab() + ")");
			} else {
				s(s);
			}
		} else {
			super.inSimpleOutPatternElement(self);
		}
			

	}

	
	@Override
	public void inBinding(Binding self) {
		if ( currentAction.getTgt() == self ) {
			int idx = self.getOutPatternElement().getBindings().indexOf(self);
			if ( idx > 0 && idx == self.getOutPatternElement().getBindings().size() - 1 ) {
				Binding previousBinding = self.getOutPatternElement().getBindings().get(idx - 1);
				String indent = getIndent(previousBinding);
				s(", " + cr() + indent + self.getPropertyName() + " <- " + g(self.getValue()));
			} else 
				// s(cr() + genTab() + self.getPropertyName() + " <- " + g(self.getValue()) + ", ");
				// It seems that the last "," is not needed because it already exists
				s(cr() + genTab() + self.getPropertyName() + " <- " + g(self.getValue()) + "");
		} else {
			super.inBinding(self);
		}
	}
	
	/*
	@Override
	public void inModule(Module self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMatchedRule(MatchedRule self) {
		throw new UnsupportedOperationException();
	}

	// Matched and Lazy rules
	public void ruleGenerator(RuleWithPattern self, String header) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inLazyRule(LazyRule self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inCalledRule(CalledRule self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inForEachOutPatternElement(ForEachOutPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inActionBlock(ActionBlock self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBindingStat(BindingStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inInPattern(InPattern self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOutPattern(OutPattern self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBinding(Binding self) {
		throw new UnsupportedOperationException();
	}

	//
	// Helpers
	//
	@Override
	public void inContextHelper(ContextHelper self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStaticHelper(StaticHelper self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOperatorCallExp(OperatorCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOperationCallExp(OperationCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIfExp(IfExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inLetExp(LetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIteratorExp(IteratorExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIterateExp(IterateExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inVariableExp(VariableExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStringExp(StringExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIntegerExp(IntegerExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inRealExp(RealExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBooleanExp(BooleanExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclUndefinedExp(OclUndefinedExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSequenceExp(SequenceExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSetExp(SetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOrderedSetExp(OrderedSetExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBagExp(BagExp self) {
		throw new UnsupportedOperationException();
	}

	private void doCollectionExp(String atlText, CollectionExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMapExp(MapExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inTupleExp(TupleExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclModelElement(OclModelElement self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOclAnyType(OclAnyType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIntegerType(IntegerType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inStringType(StringType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inRealType(RealType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBooleanType(BooleanType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSequenceType(SequenceType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inSetType(SetType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inOrderedSetType(OrderedSetType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inBagType(BagType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inMapType(MapType self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inTupleType(TupleType self) {
		throw new UnsupportedOperationException();
	}

	//
	// Imperative
	//
	@Override
	public void inExpressionStat(ExpressionStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inIfStat(IfStat self) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void inForStat(ForStat self) {
		throw new UnsupportedOperationException();
	}
	*/
	
	private void setInitialIndent(EObject tgt) {
		EObject parent = tgt;
		while ( parent != null ) {
			parent = parent.eContainer();
			if ( parent instanceof Binding ) {
				bindingInitialIndent((Binding) parent); return;
			}
		}
	}

	private void bindingInitialIndent(Binding b) {
		int line;
		try {
			line = document.getLineOfOffset(help.getIndexChar(b.getLocation())[0]);
			IRegion lineInfo = document.getLineInformation(line);
			String lineText = document.get(lineInfo.getOffset(), lineInfo.getLength());

			Pattern p = Pattern.compile("^(\\s+)");
			Matcher m = p.matcher(lineText);
			if ( m.find() ) {
				String spacesBefore = m.group(1);
				this.indentationStack.add(spacesBefore);
				inctab();
			}
		} catch (BadLocationException e) {
			inctab(); inctab(); 
			//cindent = 2;
		}
		
	}

	protected String getIndent(LocatedElement l) {
		int[] offsets = help.getIndexChar(l.getLocation());
		IRegion r;
		try {
			r = document.getLineInformationOfOffset(offsets[0]);
			String lineInitial = document.get(r.getOffset(),offsets[0] - r.getOffset());
			if ( lineInitial.matches("\\s+") ) {
				return lineInitial;
			}
			return "";
		} catch (BadLocationException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@Override
	protected String g(EObject obj) {
		if ( currentAction.getTrace().isPreserved(obj)  ){
			LocatedElement l = (LocatedElement) obj;
			int[] offsets = help.getIndexChar(l.getLocation());
			try {
				// int line = document.getLineOfOffset(offsets[0]);
				IRegion r = document.getLineInformationOfOffset(offsets[0]);
				String lineInitial = document.get(r.getOffset(),offsets[0] - r.getOffset());
				if ( lineInitial.matches("\\s+") ) {
					// hack to avoid problems with other things that use to work
					if ( currentAction.getTgt() instanceof Binding )
						return lineInitial + document.get(offsets[0], offsets[1] - offsets[0]);
				}
				return document.get(offsets[0], offsets[1] - offsets[0]);
			} catch (BadLocationException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		return super.g(obj);
	}
}
