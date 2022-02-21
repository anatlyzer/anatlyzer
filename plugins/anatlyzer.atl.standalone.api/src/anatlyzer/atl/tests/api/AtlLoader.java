package anatlyzer.atl.tests.api;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.common.ATLResourceProvider;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.emf.Messages;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlLoader {

	public static class ExtEMFReferenceModel extends EMFReferenceModel {

		public ExtEMFReferenceModel(EMFReferenceModel referenceModel, EMFModelFactory mf) {
			super(referenceModel, mf);
		}

		@Override
		public void setResource(Resource resource) {
			super.setResource(resource);
		}

	}
	
	public static Resource load(String fname) throws LoadException {
		AtlParser atlParser = new AtlParser();
		EMFModelFactory modelFactory = new EMFModelFactory();
		try {
			URL url = ATLResourceProvider.class.getResource("/model/" + "ATL.ecore");
			ExtEMFReferenceModel atlMetamodel = new ExtEMFReferenceModel(modelFactory.getMetametamodel(), modelFactory);

			ResourceSetImpl resourceSet = new ResourceSetImpl();			
			Resource builtin = resourceSet.createResource(URI.createURI("ATL.ecore"));
			try {
				builtin.load(url.openStream(), Collections.EMPTY_MAP);
			} catch (IOException e) {
				throw new ATLCoreException(Messages.getString("EMFModelFactory.BUILT_IN_NOT_FOUND", "ATL.ecore"), e); //$NON-NLS-1$
			}
			
			atlMetamodel.setResource(builtin);
			atlMetamodel.register();
			
			
			// atlMetamodel = modelFactory.getBuiltInResource("ATL.ecore");
			EMFModel atlDynModel = (EMFModel) modelFactory.newModel(atlMetamodel);
			atlParser.inject(atlDynModel, fname);			
			
			Resource originalTrafo = atlDynModel.getResource();
			return originalTrafo;
		} catch (ATLCoreException e) {
			throw new LoadException(e);
		}
	}
	
	@SuppressWarnings("serial")
	public static class LoadException extends Exception {

		public LoadException(ATLCoreException e) {
			super(e);
		}
		
	}
	
}
