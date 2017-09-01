package anatlyzer.atl.editor.quickfix;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.views.Images;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.ui.configuration.ConfigurationWriter;
import anatlyzer.ui.configuration.TransformationConfiguration;

public class MoveToBatchProblemQuickfix extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		Problem problem = getProblem(marker);
		return problem instanceof LocalProblem;
	}

	@Override
	public void apply(IDocument document) {
		try {
			Problem p = getProblem();
			IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getATLModel().getMainFileLocation()));
			
			TransformationConfiguration conf = AnalysisIndex.getInstance().getConfiguration(f);
			conf.getAvailableProblems().moveToBatch(p.eClass());
			
			// Same as RunConfigurationDialog
			IPath newPath = f.getLocation().removeFileExtension().addFileExtension("atlc");
			FileOutputStream os = new FileOutputStream(newPath.toOSString());
			ConfigurationWriter.write(os, conf);
			ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(newPath).refreshLocal(-1, null);				

			// AnalysisIndex.getInstance().clean(f);
			
			// Not sure if this is the cleanest way of forcing a rebuild
			f.touch(null);

		} catch (CoreException | IOException e) {
			e.printStackTrace();
		}			
	}

	@Override
	public String getDisplayString() {
		return "Move problem type to batch mode";
	}

	@Override
	public Image getImage() {
		return Images.ignore_problem_16x16.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		QuickfixApplication qa = new QuickfixApplication(this);		
		return qa;
	}

	@Override
	public void resetCache() { }
	

}
