package anatlyzer.atl.witness;

import java.util.HashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.graph.AbstractPathVisitor;
import anatlyzer.atl.graph.BindingPossiblyUnresolvedNode;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.types.Metaclass;

public class CountCompulsoryElements implements IScopeCalculator {

	private int max;
	protected HashMap<EClass, Integer> minClassBounds = new HashMap<>();
	
	public CountCompulsoryElements(int max, ProblemPath path, ErrorSlice slice) {
		this.max = max;
		new PathVisitor().bottomUp(path);
	}

	@Override
	public Interval getScope(EClass klass) {
		if ( isRootClass(klass) ) return getRootClassScope();
		
		System.out.println(klass.getName());
		return new Interval(minClassBounds.getOrDefault(klass, 0), max);
	}
	
	@Override
	public void setMetamodelRewrite(IMetamodelRewrite rewrite) {
		// IGNORED, NOT NEEDED IN THIS STRATEGY
	}

	@Override
	public Interval getScope(EReference feature) {
		return null;
	}

	@Override
	public int getDefaultMaxScope() {
		return max;
	}

	protected class PathVisitor extends AbstractPathVisitor {
		@Override
		public boolean visit(MatchedRuleExecution node) {
			node.getRule().getInPattern().getElements().stream().
				map(e -> e.getInferredType()).
				filter(t -> t instanceof Metaclass).
				map(t -> (Metaclass) t).
				forEach(m -> {
					minClassBounds.put(m.getKlass(), minClassBounds.getOrDefault(m, 0) + 1);					
				});
			return true;
		}
		
		/**
		 * We want the binding NOT to be resolved by any rule...
		 */
		@Override
		public boolean visitProblem(BindingPossiblyUnresolvedNode node) {
			return super.visitProblem(node);
		}
	}


	private Interval getRootClassScope() {
		return new Interval(1, 1);
	}

	private boolean isRootClass(EClass klass) {
		return 	klass.getName().equals("AuxiliaryClass4USE") || 
				klass.getName().equals("ThisModule");
	}

	@Override
	public boolean incrementScope() {
		return false;
	}
}
