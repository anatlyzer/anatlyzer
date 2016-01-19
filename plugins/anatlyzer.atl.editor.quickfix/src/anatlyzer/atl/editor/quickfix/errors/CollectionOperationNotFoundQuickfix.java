package anatlyzer.atl.editor.quickfix.errors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.errors.atl_error.CollectionOperationNotFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OperationCallExp;

/**
 * This quick fix proposes a collection operation name close to the 
 * collection operation written by the user (which does not exist).
 * 
 * @qfxName  Propose a collection operation
 * @qfxError {@link anatlyzer.atl.errors.atl_error.CollectionOperationNotFound}
 * 
 * @author jesusc
 *
 */
public class CollectionOperationNotFoundQuickfix extends OperationNotFoundAbstractQuickFix {
	// number of parameters X operation name
	private String typeOfCollection;
	
	
	public CollectionOperationNotFoundQuickfix() {
		this.populateCandidateOps();		// we can do this immediately
	}
	
	@Override protected Map<Integer, List<String>> populateCandidateOps(Predicate<Helper> predicate) {
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
	
	@Override public void resetCache() {};
	
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
	
	private OperationCallExp getOperation() {
		try {
			CollectionOperationNotFound p = (CollectionOperationNotFound) getProblem();
		
			// p.getOperationName() is null at this point 
			return (OperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	@Override
	public void apply(IDocument document) {

		OperationCallExp elm = this.getOperation();

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
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
			
//			String closest = this.getClosest(elm.getOperationName(), elm.getArguments().size());
//			
//			int[] sourceOffset = getElementOffset(elm.getSource(), document);
//
//			int sourceOffsetEnd = sourceOffset[1];
//			
//			int offsetEnd   = getProblemEndOffset();
//			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
//			int parent = rest.indexOf("(");
//			
//			document.replace(sourceOffsetEnd, parent, "->"+closest);
//			
//			this.fixParams(elm.getArguments().size(), closest, document, sourceOffsetEnd+("->"+closest).length(), offsetEnd-parent+("->"+closest).length());
			
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
		OperationCallExp ce = this.getOperation();
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
		CollectionOperationCallExp le = (CollectionOperationCallExp)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		OperationCallExp elm = this.getOperation();
		
		qfa.replace(le, (expr, trace) -> {
			trace.preserve(expr.getSource());
						
			String closest = this.getClosest(elm.getOperationName(), elm.getArguments().size());
			
			le.setOperationName(closest);		// TODO: Create copy... and handle parameters
			this.fixParams(closest, le);
			
			return le;
		});
		return qfa;
	}
	
	@Override public String neededType() {
		return this.typeOfCollection;
	}
}
