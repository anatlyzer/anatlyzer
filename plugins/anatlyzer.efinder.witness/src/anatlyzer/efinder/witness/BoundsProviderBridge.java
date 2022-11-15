package anatlyzer.efinder.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.witness.IScopeCalculator;
import efinder.core.IBoundsProvider;

public class BoundsProviderBridge implements IBoundsProvider {

	private IScopeCalculator calculator;
	
	public BoundsProviderBridge(IScopeCalculator calculator) {
		this.calculator = calculator;
	}
	
	@Override
	public Interval getScope(EClass klass) {
		anatlyzer.atl.witness.IScopeCalculator.Interval iv = calculator.getScope(klass);		
		return new Interval(iv.getMin(), iv.getMax());
	}

	@Override
	public Interval getScope(EReference feature) {
		anatlyzer.atl.witness.IScopeCalculator.Interval iv = calculator.getScope(feature);
		return new Interval(iv.getMin(), iv.getMax());
	}

	@Override
	public int getDefaultMaxScope() {
		return calculator.getDefaultMaxScope();
	}

	@Override
	public boolean incrementScope() {
		return calculator.incrementScope();
	}

}
