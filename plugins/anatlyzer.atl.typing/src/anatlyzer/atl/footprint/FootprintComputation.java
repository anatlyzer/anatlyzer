package anatlyzer.atl.footprint;

import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.UnknownFeature;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.footprint.CallSite;

/*
 * TODO: THIS IS REPLICATED FROM bento.componetization.atl => REORGANIZE PROJECTS!
 * 
 * The main change is that the constructor takes a metamodel namespace to know the
 * involved namespace.
 * 
 */

public abstract class FootprintComputation {
	protected MetamodelNamespace mm;

	protected HashSet<EClass> directUsedTypes   = new HashSet<EClass>();
	protected HashSet<EClass> indirectUsedTypes = new HashSet<EClass>();
	protected HashSet<EReference> usedReferences = new HashSet<EReference>();
	protected HashSet<EAttribute> usedAttributes = new HashSet<EAttribute>();
	protected HashSet<UnknownFeature> unknownFeatures = new HashSet<UnknownFeature>();
	
	protected HashSet<CallSite> callSites = new HashSet<CallSite>();
	protected ATLModel	atlModel;

	public FootprintComputation(ATLModel atlModel, MetamodelNamespace mm) {
		// this.atlTransformation = atlTransformation;
		this.mm = mm;
		this.atlModel = atlModel;
	}
	
	
	public ATLModel getATL() {
		return atlModel;
	}
	
	protected void computeFootprint() {
		// Compute direct used types
		List<Metaclass> metaclasses = atlModel.allObjectsOf(Metaclass.class);
		for (Metaclass m : metaclasses) {
			if ( m.isExplicitOcurrence() && classInMM(m.getKlass()) ) {
				directUsedTypes.add(m.getKlass());
			} else {
				// System.out.println("Not ancestor");
			}
		}
		
		// Compute indirect used types
		
		for (PropertyCallExp pcall : atlModel.allObjectsOf(PropertyCallExp.class)) {
			if ( pcall.getUsedFeature() != null && classInMM(((EStructuralFeature) pcall.getUsedFeature()).getEContainingClass()) ) {

				EStructuralFeature f = (EStructuralFeature) pcall.getUsedFeature();
				if ( f instanceof EReference ) {
					usedReferences.add((EReference) f);
					indirectUsedTypes.add((EClass) f.getEType());
				}
				else if ( f instanceof EAttribute) {  
					usedAttributes.add((EAttribute) f);
				} else {
					// TODO: Unknown features will be replicated if accessed several times!
					unknownFeatures.add((UnknownFeature) f);
				}
				
				if ( pcall.getReceptorType() instanceof Metaclass ) {
					Metaclass receptor = (Metaclass) pcall.getReceptorType();
					CallSite callSite = new CallSite(receptor.getKlass(), f);
				
					callSites.add(callSite);
				}
			}
		}		
	}
	

	/**
	 * If not meta-model is given, all classes are selected.
	 * @param klass
	 * @return
	 */
	private boolean classInMM(EClass klass) {
		return mm == null || mm.hasClass(klass);
	}


	public HashSet<EStructuralFeature> getUsedFeatures() {
		HashSet<EStructuralFeature> s = new HashSet<EStructuralFeature>();
		s.addAll(usedAttributes);
		s.addAll(usedReferences);
		return s;
	}	


}
