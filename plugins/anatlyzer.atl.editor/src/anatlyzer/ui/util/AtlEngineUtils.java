package anatlyzer.ui.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlEngineUtils {

	public static EMFModel loadATLFile(IFile file) {
		return loadATLFile(file, true);
	}

	public static EMFModel loadATLText(String text) {
		return loadATLFile(null, new ByteArrayInputStream(text.getBytes()), true);
	}

	public static EMFModel loadATLFile(IFile file, boolean withProblems) {
		try {
			return loadATLFile(file, file.getContents(), withProblems);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static EMFModel loadATLFile(IFile file, InputStream stream, boolean withProblems) {
		
		// ModelFactory      modelFactory = new EMFModelFactory();
		EMFModel atlEMFModel = null;
		EMFModel problems    = null;
		try {
			// EMFReferenceModel atlMetamodel = (EMFReferenceModel)modelFactory.getBuiltInResource("ATL.ecore");
			AtlParser         atlParser    = new AtlParser();
			// atlEMFModel = (EMFModel)modelFactory.newModel(atlMetamodel);
			// atlParser.inject(atlEMFModel, file.getContents(), null);
			
			IModel[] result = atlParser.inject(stream, null);
			atlEMFModel = (EMFModel) result[0];
			problems    = (EMFModel) result[1];
	
			if ( withProblems && problems != null && problems.getResource() != null ) { 
				for (EObject eObject : problems.getResource().getContents()) {
					String location = (String) eObject.eGet(eObject.eClass().getEStructuralFeature("location"));
					String description = (String) eObject.eGet(eObject.eClass().getEStructuralFeature("description"));

					String fname = file == null ? "<no-file>" : file.getFullPath().toPortableString();
					SyntaxError d = new SyntaxError(fname, location, description, eObject);
					if ( atlEMFModel.getResource() == null ) {
						// Create a dummy element to get the resource initialized
						Object metaclass = atlEMFModel.getReferenceModel().getMetaElementByName("Module"); 
						atlEMFModel.newElement(metaclass);
					}
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
		private String location;
		private int row;
		private int col;

		public SyntaxError(String name, String location, String message, EObject p) {
			this.fileName = name;
			this.message  = message;
			this.problem = p;
			
			String[] pair = location.split(":");
			if ( pair.length == 2 ) {
				try {
					this.row = Integer.parseInt(pair[0]);
					this.col = Integer.parseInt(pair[1]);
				} catch ( NumberFormatException e ) { }
			}
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
			return row;
		}

		@Override
		public int getColumn() {
			return col;
		}

		@Override
		public String toString() {
			return fileName + " " + message;
		}
		
	}


}
