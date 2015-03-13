package anatlyzer.atl.optimizer;

import anatlyzer.atl.analyser.AbstractAnalyserVisitor;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Unit;

public class AtlOptimizer extends AbstractAnalyserVisitor {

	public AtlOptimizer(ATLModel model, GlobalNamespace mm) {
		super(model, mm, model.getRoot());
	}

	public void perform() {
		startVisiting(root);
	}
	
	@Override
	public void inBinding(Binding self) {
		if ( self.getResolvedBy().size() == 1 ) {
			System.out.println(self.getOutPatternElement().getOutPattern().getRule().getName());
			System.out.println("  Could be inlined" + self.getLocation() + TypeUtils.typeToString(self.getValue().getInferredType()));
		}
	
		// Two types of inlining:
		//       unique lazy rules if it must be resolved again by another rule
		//       lazy rule if not
	}
	
}
