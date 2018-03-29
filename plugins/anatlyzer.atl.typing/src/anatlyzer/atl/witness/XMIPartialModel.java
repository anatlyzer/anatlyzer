package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Preconditions;

import anatlyzer.atl.analyser.generators.ErrorSlice;

public class XMIPartialModel implements IInputPartialModel {

	private Resource resource;

	public XMIPartialModel(Resource r) {
		this.resource = r;
	}
	
	@Override
	public Resource getResource() {
		return resource;
	}
	
	@Override
	public void extendSlice(ErrorSlice slice, IMetamodelRewrite rewrite) {
		resource.getAllContents().forEachRemaining(o -> {
			EClass orig = (EClass) rewrite.getOriginal(o.eClass(), (o1, o2) -> IMetamodelRewrite.nominalCheck(o1, o2));
			Preconditions.checkState(orig != null);
			
			slice.addMetaclassNeededInError(orig);
			
			for(EStructuralFeature f : o.eClass().getEAllStructuralFeatures()) {
				if ( ! o.eIsSet(f) )
					continue;

				EStructuralFeature origF = (EStructuralFeature) rewrite.getOriginal(f, (o1, o2) -> IMetamodelRewrite.nominalCheck(o1, o2));
				Preconditions.checkState(origF != null);		
				
				slice.addExplicitFeature(origF);
			}
		
		});
	}
	
}
