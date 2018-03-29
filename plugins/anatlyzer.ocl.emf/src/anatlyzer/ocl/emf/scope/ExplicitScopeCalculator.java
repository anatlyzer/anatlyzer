package anatlyzer.ocl.emf.scope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.IScopeCalculator;

public class ExplicitScopeCalculator implements IScopeCalculator {

	private HashMap<EClassifier, Bounds> bounds = new HashMap<>();
	private IMetamodelRewrite rewrite;

	public void addBound(Bounds bounds) {
		this.bounds.put(bounds.getClassifier(), bounds);
	}
	
	@Override
	public Interval getScope(EClass klass) {
		Bounds b = getBounds(klass);
		if ( b != null ) {
			return b.toInterval();
		}
		return new Interval(1, 5);
	}

	private Bounds getBounds(EClass klass) {
		Bounds b = bounds.get(klass);
		if ( b == null ) {
			EClass original = this.rewrite.getOriginal(klass);
			b = bounds.get(original);
		}
		return b;
	}

	@Override
	public Interval getScope(EReference feature) {
		return new Interval(1, 10);
	}

	@Override
	public int getDefaultMaxScope() {
		return 5;
	}

	@Override
	public boolean incrementScope() {
		// TODO Auto-generated method stub
		return false;
	}

	public ExplicitScopeCalculator withBounds(List<? extends Bounds> bounds) {
		bounds.forEach(this::addBound);
		return this;
	}
	
	@Override
	public void setMetamodelRewrite(IMetamodelRewrite rewrite) {
		this.rewrite = rewrite;
	}

	
	
}
