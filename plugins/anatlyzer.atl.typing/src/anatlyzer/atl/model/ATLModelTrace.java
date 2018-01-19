package anatlyzer.atl.model;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;

public class ATLModelTrace {

	private DynamicToStaticCopier copier;

	public ATLModelTrace(DynamicToStaticCopier copier) {
		this.copier = copier;
	}
	
	public EObject getTarget(EObject src) {
		return this.copier.getTarget(src);
	}

	public EObject getOriginalATLObject(EObject object) {
		EObject x = copier.getOriginalATLObject(object);
		if ( x == null )
			throw new IllegalArgumentException("Object " + object + " is not an element of the model");
		return x;
	}
		
}
