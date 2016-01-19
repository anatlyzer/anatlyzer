package anatlyzer.atl.impact;

import anatlyzer.atl.impact.util.ImpactSwitch;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class ImpactCalculator extends ImpactSwitch<Void> {
	
	private ATLModel model;
	private ChangeImpact impact;

	public void compute(ATLModel model, ChangeImpact impact) {
		this.model = model;
		this.impact = impact;
	}
	
	@Override
	public Void caseModuleCallableChange(ModuleCallableChange object) {
		for (PropertyCallExp pexp : model.allObjectsOf(PropertyCallExp.class)) {
			if ( pexp.getSource() instanceof VariableExp && ((VariableExp) pexp.getSource()).getReferredVariable().getVarName().equals("thisModule") ) {
				
			}
		}
		
		
		return null;
	}
	
	
	
}
