package anatlyzer.atl.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.processing.AbstractDynamicToStaticCopier;

public class DynamicToStaticCopier extends AbstractDynamicToStaticCopier {
	
	private String fileLocation;

	public DynamicToStaticCopier(String location) {
		this.fileLocation = location;
	}
	
	@Override
	protected Helper createHelper(EObject original) {
		EStructuralFeature definitionF = original.eClass().getEStructuralFeature("definition");		
		EObject definition = (EObject) original.eGet(definitionF);
		EStructuralFeature contextF = definition.eClass().getEStructuralFeature("context_");		
		if ( definition.eGet(contextF) == null ) {
			// Static helper
			return ATLFactory.eINSTANCE.createStaticHelper();
		} else {
			return ATLFactory.eINSTANCE.createContextHelper();
		}
	}
	
	@Override
	protected void customCopy(EObject source, EObject target) {
		if ( target instanceof LocatedElement ) {
			((LocatedElement) target).setFileLocation(this.fileLocation);
		}
	}
	
	
}
