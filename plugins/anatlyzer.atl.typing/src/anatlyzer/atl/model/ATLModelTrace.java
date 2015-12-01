package anatlyzer.atl.model;

import org.eclipse.emf.ecore.EObject;

public class ATLModelTrace {

	private DynamicToStaticCopier copier;

	public ATLModelTrace(DynamicToStaticCopier copier) {
		this.copier = copier;
	}
	
	public EObject getTarget(EObject src) {
		return this.copier.getTarget(src);
	}
}
