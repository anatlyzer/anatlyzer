package anatlyzer.evaluation.popup.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.evaluation.models.ModelGenerationStrategy;
import anatlyzer.evaluation.tester.Tester;

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
	    	
	    	Tester tester = new Tester(trafo, folder, modelGenerationStrategy);
	    	tester.runEvaluation();
	    	tester.printReport(); 
	    	tester.printReport(completePath.removeLastSegments(1).toString());
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	MessageDialog.openError(null, "Evaluation", e.toString());
	    }

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		file = (IFile) ((IStructuredSelection) selection).getFirstElement();
	}

}
