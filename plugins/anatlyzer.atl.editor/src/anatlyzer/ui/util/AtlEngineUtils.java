package anatlyzer.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlEngineUtils {

	public static EMFModel loadATLFile(IFile file) {
		return loadATLFile(file, true);
	}
	
	public static EMFModel loadATLFile(IFile file, boolean withProblems) {
		// ModelFactory      modelFactory = new EMFModelFactory();
		EMFModel atlEMFModel = null;
		EMFModel problems    = null;
		try {
			// EMFReferenceModel atlMetamodel = (EMFReferenceModel)modelFactory.getBuiltInResource("ATL.ecore");
			AtlParser         atlParser    = new AtlParser();
			// atlEMFModel = (EMFModel)modelFactory.newModel(atlMetamodel);
			// atlParser.inject(atlEMFModel, file.getContents(), null);
			
			IModel[] result = atlParser.inject(file.getContents(), null);
			atlEMFModel = (EMFModel) result[0];
			problems    = (EMFModel) result[1];
	
			if ( withProblems && problems != null && problems.getResource() != null ) { 
				for (EObject eObject : problems.getResource().getContents()) {
					String location = (String) eObject.eGet(eObject.eClass().getEStructuralFeature("location"));
					String description = (String) eObject.eGet(eObject.eClass().getEStructuralFeature("description"));

					SyntaxError d = new SyntaxError(file.getName(), description + ", " + location, eObject);
					atlEMFModel.getResource().getErrors().add(d);
				}
			} else {
				if ( ! withProblems && ( problems != null && problems.getResource() != null && problems.getResource().getContents().size() > 0) ) {
					return null;
				}
			}
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
	
	public static boolean hasErrors(EMFModel model) {
		return model.getResource().getErrors().size() > 0;
	}
	
	public static class SyntaxError implements Diagnostic {

		private String fileName;
		private String message;
		private EObject problem;

		public SyntaxError(String name, String message, EObject p) {
			this.fileName = name;
			this.message  = message;
			this.problem = p;
		}

		@Override
		public String getMessage() {
			return message;
		}

		@Override
		public String getLocation() {
			return fileName;
		}

		@Override
		public int getLine() {
			return 0;
		}

		@Override
		public int getColumn() {
			return 0;
		}
		
	}
}
