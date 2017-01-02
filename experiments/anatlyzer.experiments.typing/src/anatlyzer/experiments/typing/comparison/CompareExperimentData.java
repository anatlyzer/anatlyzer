package anatlyzer.experiments.typing.comparison;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import anatlyzer.atl.util.Pair;
import anatlyzer.experiments.IExperimentAction;
import anatlyzer.experiments.extensions.IExperiment;
import anatlyzer.experiments.typing.AnalyseTypeErrors;
import anatlyzer.experiments.typing.raw.TEData;
import anatlyzer.experiments.typing.raw.TEProblem;
import anatlyzer.experiments.typing.raw.TETransformation;

public class CompareExperimentData implements IExperimentAction {

	private StyledText messageWindow;

	public CompareExperimentData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setMessageWindow(StyledText text) {
		this.messageWindow = text;
	}

	public void showMessage(String txt) {
		Display.getDefault().asyncExec(new Runnable() {			
			@Override
			public void run() {
				messageWindow.append(txt);
			}
		});
	}
	
	@Override
	public void execute(IExperiment experiment, IFile confFile) {
		AnalyseTypeErrors exp = (AnalyseTypeErrors) experiment;
		Shell shell = Display.getDefault().getActiveShell();
		
		String fname1 = openFileDialog(confFile, shell);
		String fname2 = null;
		if ( fname1 != null ) {
			fname2 = openFileDialog(confFile, shell);
			if ( fname2 != null ) {
				TEData data1 = readData(fname1);
				TEData data2 = readData(fname2);
				
				TEDataDiff diff = compare(data1, data2);
				if ( diff == null ) {
					MessageDialog.openInformation(null, "Info", "No differences found");
				} else {
					showDiffs(diff);
				}
			}			
		}		
	}

	private TEDataDiff compare(TEData data1, TEData data2) {
		TEDataDiff diff = null;
				
		for (TETransformation trafo1 : data1.getAllTransformations()) {
			boolean found = false;
			
			for (TETransformation trafo2 : data2.getAllTransformations()) {
				
				if ( trafo1.getPath().equals(trafo2.getPath()) ) {
					TETransformationDiff trafoDiff = compare(trafo1, trafo2);
					if ( trafoDiff != null ) {
						diff = diff == null ? new TEDataDiff() : diff;
						diff.addTransformationDiff(trafoDiff);
					}
					
					found = true;
					break;
				}				
			}
			
			if ( ! found ) {
				diff = diff == null ? new TEDataDiff() : diff;
				diff.transformationNotAnalysed(trafo1);
			}
		}
		
		return diff;
	}

	private TETransformationDiff compare(TETransformation trafo1, TETransformation trafo2) {
		TETransformationDiff diff = null;		
		
		for (TEProblem p1 : trafo1.getProblems()) {
			boolean found = false;
			
			for (TEProblem p2 : trafo2.getProblems()) {
				if ( p1.isSame(p2) ) {
					
					if ( p1.getFinalStatus() != p2.getFinalStatus() ) {
						diff = diff == null ? new TETransformationDiff(trafo1, trafo2) : diff;
						diff.addProblemWithDifferentStatus(p1, p2);
					}
					
					found = true;
					break;
				}
			}
			
			if ( ! found ) {
				diff = diff == null ? new TETransformationDiff(trafo1, trafo2) : diff;
				// Not checking the other direction...
				diff.addProblemNotFound_1(p1);
			}			
		}
		
		return diff;
	}

	private void showDiffs(TEDataDiff diff) {
		if ( diff.getNotAnalysed().size() > 0 ) {
			showMessage("Transformations not analysed by the second experiment\n");
			showMessage("=====================================================\n");
			for (TETransformation trafo : diff.getNotAnalysed()) {
				showMessage("   * " + trafo.getName() + " - " + trafo.getPath() + "\n");
			}
			showMessage("\n");
		}	
		
		if ( diff.getTrafoDiffs().size() > 0 ) {
			showMessage("Transformation differences\n");
			showMessage("==========================\n\n");
			
			for (TETransformationDiff trafo : diff.getTrafoDiffs()) {
				showMessage("* " + trafo.getTransformationName() + "\n");
				
				
				if ( trafo.getProblemsNotFound_1().size() > 0 ) {
					showMessage("  - Problems not found in the second experiment\n");
					for (TEProblem p : trafo.getProblemsNotFound_1()) {
						showMessage("    * " + p.getLocation() + ". " + p.getDescription() + "\n");
					}
					showMessage("\n");
				}
				
				if ( trafo.getProblemsWithDifferentStatus().size() > 0 ) {
					showMessage("  - Problems with different status\n");
					for (Pair<TEProblem, TEProblem> pair : trafo.getProblemsWithDifferentStatus()) {
						showMessage("    * " + pair._1.getLocation() + ". " + pair._1.getDescription() + "\n");
						showMessage("      " + pair._1.getLocation() + ". " + pair._2.getDescription() + "\n");
						showMessage("      " + pair._1.getFinalStatus() + " vs. " + pair._2.getFinalStatus() + "\n");
					}
				}				
			}
		}		
	}
	
	private TEData readData(String fname) {
		Serializer serializer = new Persister();
		try {
			return serializer.read(TEData.class, new File(fname));
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	private String openFileDialog(IFile confFile, Shell shell) {
		String fname = confFile.getLocation().toOSString();
		
		FileDialog dialog = new FileDialog(shell);
		dialog.setText("Choose first file for comparison");
		dialog.setFileName(fname);
		
		return dialog.open();		
	}

}
