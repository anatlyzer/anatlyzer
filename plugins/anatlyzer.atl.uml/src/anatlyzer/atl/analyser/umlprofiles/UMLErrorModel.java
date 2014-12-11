package anatlyzer.atl.analyser.umlprofiles;

import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.umlerrors.StereotypeNotFound;
import anatlyzer.atl.umlerrors.UmlErrorsFactory;
import anatlyzer.atlext.ATL.LocatedElement;

public class UMLErrorModel {

	private ErrorModel model;

	public UMLErrorModel(ErrorModel m) {
		this.model = m;
	}

	public void signalStereotypeNotFound(String name, LocatedElement node) {
		StereotypeNotFound error = UmlErrorsFactory.eINSTANCE.createStereotypeNotFound();
		model.initProblem(error, node);
		error.setStereotypeName(name);
		model.signalError(error, "Stereotype " + name + " not found in profiles", node);
	}
	
}
