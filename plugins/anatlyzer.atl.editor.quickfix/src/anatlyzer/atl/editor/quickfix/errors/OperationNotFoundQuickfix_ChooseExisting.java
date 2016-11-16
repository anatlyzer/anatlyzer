package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationNotFound;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundQuickfix_ChooseExisting extends OperationNotFoundAbstractQuickFix {
		
	private String  closest;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationNotFound.class) && this.hasHelpers();			
	}
	
	@Override public void resetCache() { 
		this.closest = null;
	}
	
	private boolean hasHelpers() {
		return !this.populateCandidateOps(ATLUtils::isOperationHelper).isEmpty();		
	}
	
	private void addPredefined(Map<Integer, List<String>> ops) {
		ops.putIfAbsent(0, new ArrayList<String>());
		ops.get(0).addAll(Arrays.asList("oclIsUndefined", "toString", "oclType", "refImmediateComposite", "debug" ));
		
		ops.putIfAbsent(1, new ArrayList<String>());
		ops.get(1).addAll(Arrays.asList("oclIsKindOf", "oclIsTypeOf", "refGetValue"));
			
		ops.putIfAbsent(2, new ArrayList<String>());
		ops.get(2).addAll(Arrays.asList("refSetValue", "refUnsetValue", "refInvokeOperation"));
		
	}
	
	@Override protected Map<Integer, List<String>> populateCandidateOps(Predicate<Helper> predicate) {
		Map<Integer, List<String>> stHelpers = new TreeMap<Integer, List<String>>();
		
		OperationCallExp op = (OperationCallExp) getProblematicElement();
		Type srcType = op.getSource().getInferredType();
		
		ATLModel model = getAnalyserData(marker).getAnalyser().getATLModel();
		
		if ( model.getRoot() instanceof Module || model.getRoot() instanceof Library) {
			stHelpers = getCompatibleContextHelpers(srcType, model, predicate).stream().
					collect(Collectors.groupingBy(e -> ATLUtils.getArgumentNames(e).length,											// group by number of args 
							Collectors.mapping( e -> ATLUtils.getHelperName(e), Collectors.toList())));		// but get name and fold into list
		}
		// Now add the predefined operations
		this.addPredefined(stHelpers);
		
		return stHelpers;
	}
	
	private String getClosest() {
		if (this.closest!=null) return this.closest;
		//if (this.candidateOps.isEmpty()) return null;
		
		OperationCallExp res = (OperationCallExp) this.getProblematicElement();		
			
		this.candidateOps = this.populateCandidateOps(ATLUtils::isOperationHelper);
		if (this.candidateOps.isEmpty()) return null;
		this.closest = this.getClosest(res.getOperationName(), res.getArguments().size());
		
		System.out.println("Closest : "+this.closest+" with "+res.getArguments().size()+" arguments.");
		
		//this.closest = Levenshtein.closest(res.getOperationName(), helpers);
		
		return this.closest;
	}
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();		
	}


	@Override
	public String getDisplayString() {
		OperationCallExp res = (OperationCallExp) this.getProblematicElement();
		return "Replace by "+this.getClosest();
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp le = (OperationCallExp)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		qfa.replace(le, (expr, trace) -> {
			trace.preserve(expr.getSource());
						
			String closest = this.getClosest();
			le.setOperationName(closest);		// TODO: Create copy... and handle parameters
			this.fixParams(closest, le);
			
			return le;
		});
		return qfa;
	}


}
