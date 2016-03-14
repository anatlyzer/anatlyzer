package anatlyzer.atl.tests.api;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlLoader {

	public static Resource load(String fname) throws ATLCoreException {
		AtlParser atlParser = new AtlParser();
		ModelFactory modelFactory = new EMFModelFactory();
		IReferenceModel atlMetamodel = modelFactory.getBuiltInResource("ATL.ecore");
		EMFModel atlDynModel = (EMFModel) modelFactory.newModel(atlMetamodel);
		atlParser.inject(atlDynModel, fname);
		
		
		Resource originalTrafo = atlDynModel.getResource();
		return originalTrafo;
	}
	
}
