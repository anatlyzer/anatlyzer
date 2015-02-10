package anatlyzer.atl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingExpectedOneAssignedMany;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.CollectionOperationOverNoCollectionError;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.errors.atl_recovery.FeatureFoundInSubclass;
import anatlyzer.atlext.ATL.Module;

public class AnalyserUtils {

	public static EPackage getSingleSourceMetamodel(Analyser analyser) {
		GlobalNamespace namespace = analyser.getNamespaces();
		
		Module mod = analyser.getATLModel().allObjectsOf(Module.class).get(0);
		String n = mod.getInModels().get(0).getMetamodel().getName();
		
		if ( namespace.getNamespace(n).getResource().getContents().size() > 1 ) {
			EPackage selected = null;
			for (EObject c : namespace.getNamespace(n).getResource().getContents()) {
				EPackage pkg = (EPackage) c;
				if ( selected == null ) selected = pkg;
				
				if ( pkg.getEClassifiers().size() > selected.getEClassifiers().size() ) 
					selected = pkg;
			}
			 
			System.out.println("TODO: AnalyserExecutor: Using a very naive stratategy to select one package among several");
			
			return selected;
		} else {
			return (EPackage) namespace.getNamespace(n).getResource().getContents().get(0);
		}
	}

	
	public static int getProblemId(Problem p) {
		if ( p instanceof NoBindingForCompulsoryFeature ) return 1;
		if ( p instanceof BindingPossiblyUnresolved     ) return 2;
		if ( p instanceof BindingWithResolvedByIncompatibleRule ) return 3;
		if ( p instanceof BindingExpectedOneAssignedMany ) return 4;
		if ( p instanceof FeatureNotFound ) {
			if ( ((FeatureNotFound) p).getRecovery() instanceof FeatureFoundInSubclass ) 
				return 6;
			else {
				return 5;
			}
		}
		// 7
		if ( p instanceof IncoherentVariableDeclaration ) return 7;
		if ( p instanceof FlattenOverNonNestedCollection ) return 8;
		if ( p instanceof CollectionOperationOverNoCollectionError ) return 9;
		
		return -1;
		// throw new UnsupportedOperationException(p.getClass().getName());
	}
	
}
