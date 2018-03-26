package anatlyzer.experiments.featurecoverage;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.editor.builder.AnalyserExecutor.AnalyserData;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.featurecoverage.raw.CTransformation;
import anatlyzer.experiments.featurecoverage.raw.CoverageData;

public class MeasureCoverage implements IExperiment {

	private StyledText messageWindow;
	private HashMap<String, Object> options;
	private CoverageData expData = new CoverageData();
	private IFile configuration;
	
	@Override
	public void perform(IResource resource, IProgressMonitor monitor) {
		try {
			AnalyserData data = new AnalyserExecutor().exec(resource);
			
			CTransformation ctrafo = new FeatureChecker(resource.getName(), resource.getFullPath().toPortableString(), data.getATLModel()).countFeatures();
			expData.addTransformation(ctrafo);
			
			showMessage("Analysed: " + resource.getName());
			
		} catch (IOException | CoreException | CannotLoadMetamodel | PreconditionParseError e) {
			e.printStackTrace();
			showMessage("Error: " + resource.getName() + " - " + e.getMessage());
		}
		
	}

	
	@Override
	public void printResult(PrintStream out) { }

	@Override
	public boolean canExportToExcel() {
		return true;
	}

	@Override
	public void exportToExcel(String fileName) throws IOException {
		// TODO Auto-generated method stub
		// File f = folder.getFile(name).getLocation().toFile();		
		new ExcelExporter().export(expData, fileName);
		showMessage("Exported to: " + fileName);
	}

	@Override
	public void setExperimentConfiguration(IFile file) {
		this.configuration = file;
	}

	@Override
	public void setMessageWindow(StyledText text) {
		this.messageWindow = text;
	}

	@Override
	public void projectDone(IProject p) { }

	@Override
	public void finished() { 
		showMessage("Finished!");		
	}

	@Override
	public void setOptions(HashMap<String, Object> options) {
		this.options = options;
	}

	public void saveData(IFile expFile) {	
        String fname = createDataFileName(expFile, "data", "data");
        
		// http://www.ibm.com/developerworks/library/x-simplexobjs/
		// http://simple.sourceforge.net/download/stream/doc/examples/examples.php
		Serializer serializer = new Persister();
        File result = new File(fname);
        try {
			serializer.write(expData, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	protected String createDataFileName(IFile expFile, String folderName, String extension) {
		IProject project = expFile.getProject();
		IFolder folder = project.getFolder(folderName);
		return folder.getFile(expFile.getFullPath().removeFileExtension().addFileExtension(extension).lastSegment()).getLocation().toOSString();		
	}
	

	protected IFolder getFolder(IFolder baseFolder) {
		return baseFolder;
	}
	
	protected IFolder getFolder(IContainer baseFolder, String name) {
		IFolder folder = baseFolder.getFolder(new Path(name));
		if ( ! folder.exists() ) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
		}		
		return folder;
	}	

	@Override
	public void openData(IFile expFile) {
		String fname = createDataFileName(expFile, "data", "data");
		
		FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell());
		dialog.setFileName(fname);
		fname = dialog.open();
		if ( fname != null ) {
			// Read the data
			Serializer serializer = new Persister();
			try {
				this.expData = serializer.read(CoverageData.class, new File(fname));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}


	public void showMessage(String txt) {
		Display.getDefault().asyncExec(new Runnable() {			
			@Override
			public void run() {
				if ( messageWindow != null )
					messageWindow.append(txt + "\n");
			}
		});
	}
}
