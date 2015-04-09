package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class CollectionOperationNotFoundQuickfix extends OperationNotFoundAbstractQuickFix {
	// number of parameters X operation name
	private static HashMap<String, List<CollType>> primitiveParam = new HashMap<>();		// probably move this to superclass
	private String typeOfCollection;
	
	static {
		primitiveParam.put("append", Collections.singletonList(CollType.UserDefined));
		primitiveParam.put("at", Collections.singletonList(CollType.Integer));
		primitiveParam.put("subsequence", Arrays.asList(CollType.Integer, CollType.Integer));
		primitiveParam.put("refGetValue", Collections.singletonList(CollType.String));
	}
	
	enum CollType { 
		Integer ("0"), 
		String("''"),
		UserDefined("param");
		
		private String defaultLiteral;
		
		CollType(String dl) { this.defaultLiteral = dl; }		
		public String defaultLiteral() { return this.defaultLiteral;}
		public void setDefaultLiteral(String dl) { this.defaultLiteral = dl; }
	}
	
	public CollectionOperationNotFoundQuickfix() {
		this.populateCandidateOps();		// we can do this immediately
	}
	
	@Override protected Map<Integer, List<String>> populateCandidateOps() {
		this.candidateOps = new TreeMap<>();
		this.candidateOps.put(0, Arrays.asList("first", "flatten", "last", "asBag", "asOrderedSet", "asSequence", "asSet", "isEmpty", "notEmpty", "size", "sum", "debug", "oclIsUndefined", "oclType", "refImmediateComposite", "toString"));
		this.candidateOps.put(1, Arrays.asList("append", "at", "indexOf", "prepend", "union", "count", "excludes", "excludesAll", "excluding", "includes", "includesAll", "including", "oclIsKindOf", "oclIsTypeOf", "refGetValue"));
		this.candidateOps.put(2, Arrays.asList("insertAt", "subsequence", "refInvokeOperation", "refSetValue", "refUnsetValue"));
		
		return this.candidateOps;
	}
		
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, CollectionOperationNotFound.class);
	}
	
	private void fixParams(int numP, String closest, IDocument document, int startPos, int endPos) {  // a bit redundant that we calculate this again...
		int paramsClosest = this.getParamsClosest(closest);
		
		if (paramsClosest > numP) {
			System.out.println("You need to add "+(paramsClosest - numP )+" params");
			if (numP == 0) {	// We know how to do this: we just add a dummy var 'param' or the default literal in case of primitive types...
				try {
					int closeParIdx = document.get(startPos, endPos).indexOf(")");
					
					String param = primitiveParam.get(closest) == null ? "param" : primitiveParam.get(closest).get(0).defaultLiteral();
					
					document.replace(startPos+1, closeParIdx, param+")");
				}
				catch (BadLocationException e) {
					
				}
			}
		}
		else if (paramsClosest < numP) {
			System.out.println("You need to remove "+(numP - paramsClosest )+" params");
			if (numP == 1) {	// we know how to do this
				try {
					int closeParIdx = document.get(startPos, endPos).indexOf(")");
					document.replace(startPos+1, closeParIdx, ")");
				}
				catch (BadLocationException e) {
					
				}
			}
		}
		else System.out.println("number of params match.");
	}
	
	private CollectionOperationCallExp getOperation() {
		try {
			CollectionOperationNotFound p = (CollectionOperationNotFound) getProblem();
		
			// p.getOperationName() is null at this point 
			return (CollectionOperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	@Override
	public void apply(IDocument document) {

		try {
			
			CollectionOperationCallExp elm = this.getOperation();
								
			System.out.println("Collection operation "+elm.getOperationName()+"not found");
			System.out.println("Type of collection "+elm.getSource().getInferredType());//+"\n of: "+elm.getSource().getInferredType());
			this.typeOfCollection = "";
			if (elm.getSource().getInferredType() instanceof CollectionType) {
				CollectionType ct = (CollectionType)elm.getSource().getInferredType();				
				if (ct.getContainedType() instanceof Metaclass) {
					Metaclass mc = (Metaclass)ct.getContainedType();
					System.out.println("Type of collection "+mc.getName());
					this.typeOfCollection = mc.getName();
					String metaModelName = mc.getModel().getName();
					CollType.UserDefined.setDefaultLiteral(metaModelName+"!"+this.typeOfCollection+".newInstance()");
				}
			}
			
			String closest = this.getClosest(elm.getOperationName(), elm.getArguments().size());
			
			int[] sourceOffset = getElementOffset(elm.getSource(), document);

			int sourceOffsetEnd = sourceOffset[1];
			
			int offsetEnd   = getProblemEndOffset();
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			int parent = rest.indexOf("(");
			
			document.replace(sourceOffsetEnd, parent, "->"+closest);
			
			this.fixParams(elm.getArguments().size(), closest, document, sourceOffsetEnd+("->"+closest).length(), offsetEnd-parent+("->"+closest).length());
			
		} catch (CoreException | BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Chooses a collection operation with similar name";
	}

	@Override
	public String getDisplayString() {
		CollectionOperationCallExp ce = this.getOperation();
		String closest = this.getClosest(ce.getOperationName(), ce.getArguments().size());
		int numP = ce.getArguments().size();
		int closestPar = this.getParamsClosest(closest);
		String params = "";
		
		if (numP < closestPar) params = " (and add "+(closestPar-numP)+"params)";
		else if (numP > closestPar) params = " (and remove "+(numP-closestPar)+"params)";
		return "Collection operation name "+ce.getOperationName()+" is invalid, change to "+closest+params;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}
}
