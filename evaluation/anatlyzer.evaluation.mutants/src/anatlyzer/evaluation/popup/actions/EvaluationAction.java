package anatlyzer.evaluation.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import anatlyzer.evaluation.models.ModelGenerationStrategy;
import anatlyzer.evaluation.tester.EvaluationFinishedOnRequest;
import anatlyzer.evaluation.tester.Tester;
import anatlyzer.evaluation.ui.MutantEvaluationConfigurationDialog;

public class EvaluationAction implements IObjectActionDelegate {

	private Shell shell;
	private IFile file;
	
	/**
	 * Constructor for Action1.
	 */
	public EvaluationAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IPath completePath = file.getRawLocation();
		String trafo  = completePath.toString();
		String folder = completePath.removeFileExtension().toString() + "_Evaluation";
	    try {
	    	ModelGenerationStrategy.STRATEGY modelGenerationStrategy = 
	    			action.getId().endsWith(".full")?
	    			ModelGenerationStrategy.STRATEGY.Full : 
	    			ModelGenerationStrategy.STRATEGY.Lite;
	    	
	    	MutantEvaluationConfigurationDialog dialog = new MutantEvaluationConfigurationDialog(shell);
	    	if ( dialog.open() ==  Window.OK) {
	    		Tester tester = new Tester(trafo, folder, modelGenerationStrategy);
	    		tester.setGenerateMutants( dialog.getOptionGenerateNewMutants() );
	    		tester.setGenerateTestModels( dialog.getOptionGenerateTestModels() );
	    		
	    		// new Tester(trafo, folder, modelGenerationStrategy).runEvaluation();
	    		executeOnBackground(tester);
	    	}
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	MessageDialog.openError(null, "Evaluation", e.toString());
	    }

	}

	private void executeOnBackground(Tester tester) {
		Job job = new Job("Mutant evaluation") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					tester.runEvaluation(monitor);
					return Status.OK_STATUS;
				} catch ( EvaluationFinishedOnRequest e ) {
					return Status.CANCEL_STATUS;
				} catch ( Exception e ) {
			    	e.printStackTrace();
			    	// MessageDialog.openError(null, "Evaluation", e.toString());
			    	return new Status(IStatus.ERROR, anatlyzer.evaluation.Activator.PLUGIN_ID, e.toString());
				}
			}
			
		};
		job.schedule();		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		file = (IFile) ((IStructuredSelection) selection).getFirstElement();
	}

}
