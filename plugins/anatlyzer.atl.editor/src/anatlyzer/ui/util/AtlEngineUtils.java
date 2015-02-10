package anatlyzer.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlEngineUtils {
	public static EMFModel loadATLFile(IFile file) {
		ModelFactory      modelFactory = new EMFModelFactory();
		EMFModel atlEMFModel = null;
		try {
			EMFReferenceModel atlMetamodel = (EMFReferenceModel)modelFactory.getBuiltInResource("ATL.ecore");
			AtlParser         atlParser    = new AtlParser();
			atlEMFModel = (EMFModel)modelFactory.newModel(atlMetamodel);
			atlParser.inject(atlEMFModel, file.getContents(), null);
		} catch (ATLCoreException e1) {
			e1.printStackTrace();
			return null;
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		
		atlEMFModel.setIsTarget(true);
		return atlEMFModel;
	}
}
