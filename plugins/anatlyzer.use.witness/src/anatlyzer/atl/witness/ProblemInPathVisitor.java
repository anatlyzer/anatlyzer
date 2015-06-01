package anatlyzer.atl.witness;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.analyser.generators.Retyping;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.processing.AbstractVisitor;

/**
 * This visitor is in charge of reporting those "extra" classes that are exposed in the path condition by 
 * {@link Retyping}, and thus needs to be included in the error slice.
 * 
 * @author jesus
 *
 */
public class ProblemInPathVisitor extends AbstractVisitor implements IObjectVisitor {

	public Set<EClass> extraClasses = new HashSet<EClass>();	
	public Set<EStructuralFeature> extraFeatures = new HashSet<EStructuralFeature>();
	
	public void perform(EObject root) {
		startVisiting(root);
	}
	
	public Set<EClass> getExtraClasses() {
		return extraClasses;
	}
	
	public Set<EStructuralFeature> getExtraFeatures() {
		return extraFeatures;
	}
	
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		FeatureFoundInSubtype p = (FeatureFoundInSubtype) AnalyserUtils.hasProblem(self, FeatureFoundInSubtype.class);
		if ( p != null ) {
			EClass klass = p.getPossibleClasses().get(0);		
			extraClasses.add(klass);

			EStructuralFeature f = klass.getEStructuralFeature(self.getName());
			if ( f != null ) {
				// The feature needs to form path of the slice
				extraFeatures.add(f);
			}
			
		}
	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		OperationFoundInSubtype p = (OperationFoundInSubtype) AnalyserUtils.hasProblem(self, OperationFoundInSubtype.class);
		if ( p != null ) {
			EClass klass = p.getPossibleClasses().get(0);
			extraClasses.add(klass);
		}
	}		
}
