package anatlyzer.atl.analyser;

import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.Unit;

public class RefiningAnalysisTraversal extends TypeAnalysisTraversal {

	public RefiningAnalysisTraversal(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}

	@Override
	protected void explicitTypeTraversal() {
		new ExplicitTypeTraversal(model, mm, root) {
			protected void checkReadingTargetModel(anatlyzer.atlext.OCL.OclModelElement self) { /* Do nothing */ };
		}.perform(attr);
	}
	
	@Override
	protected void ruleAnalysis() {
		new RefiningRuleAnalysis(model, mm, root).perform(attr);
	}
	
	public class RefiningRuleAnalysis extends RuleAnalysis {
		public RefiningRuleAnalysis(ATLModel model, GlobalNamespace mm, Unit root) {
			super(model, mm, root);
		}
		
		@Override
		protected void checkCompulsoryFeature(OutPatternElement self,
				NoBindingForCompulsoryFeatureKind kind, RuleWithPattern rule) {
			// Do nothing
		}

		@Override
		protected void analyseRuleResolution(Binding self, Type rightType, EReference f) {
			Type t = ATLUtils.getUnderlyingBindingLeftType(self);			
			if ( ! typ().assignableTypes(t, TypeUtils.getUnderlyingType(rightType) ) ) {
				errors().signalBindingInplaceInvalid(self, rightType, f);
			}			
		}
	}
}
