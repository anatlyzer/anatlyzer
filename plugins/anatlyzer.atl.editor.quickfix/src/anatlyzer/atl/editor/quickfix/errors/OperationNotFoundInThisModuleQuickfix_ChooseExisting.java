package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundInThisModuleQuickfix_ChooseExisting extends OperationNotFoundAbstractQuickFix {
		
	private String  closest;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, OperationNotFoundInThisModule.class) && this.hasHelpers();			
	}
	
	@Override public void resetCache() { 
		closest = null;
	}
	
	private boolean hasHelpers() {
		return !this.populateCandidateOps(e -> ATLUtils.isOperationHelper(e)).isEmpty();		
	}

	private OperationCallExp getElement() {
		try {
			OperationNotFoundInThisModule p = (OperationNotFoundInThisModule) getProblem();
		
						
			// p.getOperationName() is null at this point 
			return (OperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	private void addPredefined(Map<Integer, List<String>> ops) {
		ops.putIfAbsent(2, new ArrayList<String>());
		ops.get(2).add("resolveTemp");
	}
		
	@Override protected Map<Integer, List<String>> populateCandidateOps(Predicate<Helper> predicate) {
		Map<Integer, List<String>> stHelpers = new TreeMap<Integer, List<String>>();
		
		ATLModel model = getAnalyserData(marker).getAnalyser().getATLModel();
		
		if ( model.getRoot() instanceof Module || model.getRoot() instanceof Library) {
			Module m = (Module) model.getRoot();
			stHelpers = m.getElements().stream().
							filter(e -> e instanceof StaticHelper).
							filter(e -> predicate.test((Helper) e)).
							map(e -> (StaticHelper) e).
							collect(Collectors.groupingBy(e -> ATLUtils.getArgumentNames(e).length,											// group by number of args 
									                      Collectors.mapping( e -> ATLUtils.getHelperName(e), Collectors.toList())));		// but get name and fold into list
			
			Map<Integer, List<String>> lrules =  m.getElements().stream().
							  		 filter ( e -> e instanceof LazyRule).
							  		 map ( e -> (LazyRule)e).
							  		 //map ( e -> e.getName()).
							  		 collect(Collectors.groupingBy( (LazyRule e) -> e.getCallableParameters().size(),
							  				 			             Collectors.mapping( LazyRule::getName, Collectors.toList() )));			
			
			for (Integer i : lrules.keySet())																								// merge both maps
				stHelpers.merge(i, lrules.get(i), (l1, l2) -> { l1.addAll(l2); return l1; });
		} 
		
		// Now add the predefined operations
		this.addPredefined(stHelpers);
		
		return stHelpers;
	}
	
	private String getClosest() {
		if (this.closest!=null) return this.closest;
		//if (this.candidateOps.isEmpty()) return null;
		
		OperationCallExp res = this.getElement();		
			
		this.candidateOps = this.populateCandidateOps();
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
	public String getAdditionalProposalInfo() {
		OperationCallExp res = this.getElement();
		return "Operation "+res.getOperationName()+" not found in thisModule, replace by "+this.getClosest();
	}

	@Override
	public String getDisplayString() {
		return "Replace by "+this.getClosest();
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

	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
}
