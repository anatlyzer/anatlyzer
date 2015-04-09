package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundInThisModuleQuickfix_ChooseExisting extends OperationNotFoundAbstractQuickFix {
		
	private IMarker marker;
	private String  closest;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		this.marker = marker;
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
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
		
	@Override protected Map<Integer, List<String>> populateCandidateOps () {
		Map<Integer, List<String>> stHelpers = new TreeMap<Integer, List<String>>();
		
		ATLModel model = getAnalyserData(marker).getAnalyser().getATLModel();
		
		if ( model.getRoot() instanceof Module || model.getRoot() instanceof Library) {
			Module m = (Module) model.getRoot();
			stHelpers = m.getElements().stream().
							filter(e -> e instanceof StaticHelper).
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
		
		return stHelpers;
	}
	
	private String getClosest() {
		if (this.closest!=null) return this.closest;
		
		OperationCallExp res = this.getElement();		
			
		this.candidateOps = this.populateCandidateOps();
		this.closest = this.getClosest(res.getOperationName(), res.getArguments().size());
		
		System.out.println("Closest : "+this.closest+" with "+res.getArguments().size()+" arguments.");
		
		//this.closest = Levenshtein.closest(res.getOperationName(), helpers);
		
		return this.closest;
	}
	
	@Override
	public void apply(IDocument document) {
		OperationCallExp res = this.getElement();
		
		try {
		
			int[] sourceOffset = getElementOffset(res.getSource(), document);
	
			int sourceOffsetEnd = sourceOffset[1];
			
			int offsetEnd   = getProblemEndOffset();
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			int parent = rest.indexOf("(");
			
			document.replace(sourceOffsetEnd, parent, "."+this.getClosest());
			// TODO: take into account parameters and return value
		} catch (CoreException | BadLocationException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Operation "+res.getOperationName()+" not found in thisModule, replace by "+this.getClosest());
	}

	@Override
	public String getAdditionalProposalInfo() {
		OperationCallExp res = this.getElement();
		return "Operation "+res.getOperationName()+" not found in thisModule, replace by "+this.getClosest();
	}

	@Override
	public String getDisplayString() {
		OperationCallExp res = this.getElement();
		return "Operation "+res.getOperationName()+" not found in thisModule, replace by "+this.getClosest();
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}


}
