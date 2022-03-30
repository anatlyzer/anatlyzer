package anatlyzer.efinder.witness;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import efinder.core.EFinderModel;
import efinder.core.IBoundsProvider;
import efinder.ir.EFClass;

public class PerElementScopeCalculator implements IBoundsProvider {

	private PerElement perElement;
	private ErrorSlice slice;
	private EFinderModel model;
	private int maxScope;
	private int currentScope;

	public PerElementScopeCalculator(ErrorSlice slice, EFinderModel model, int maxScope) {
		this.slice = slice;
		this.model = model;
		this.maxScope = maxScope;
		this.currentScope = 1;
		computePerElement(currentScope);
	}

	private void computePerElement(int i) {
		this.perElement = new IBoundsProvider.PerElement();
		for (EClass eClass : slice.getClasses()) {
			perElement.addBound(eClass, 0, i);
		}
		List<EFClass> thisModule = model.getSpecification().getTemporary().stream().
				filter(c -> c.getKlass().getName().toLowerCase().equals("thismodule")).
				collect(Collectors.toList());
		thisModule.forEach((c) -> perElement.addBound(c.getKlass(), 1, 1));		
	}

	@Override
	public Interval getScope(EClass klass) {
		return perElement.getScope(klass);
	}

	@Override
	public Interval getScope(EReference feature) {
		return perElement.getScope(feature);
	}

	@Override
	public int getDefaultMaxScope() {
		return maxScope;
	}

	@Override
	public boolean incrementScope() {
		currentScope++;
		return currentScope > maxScope;
	}


}
