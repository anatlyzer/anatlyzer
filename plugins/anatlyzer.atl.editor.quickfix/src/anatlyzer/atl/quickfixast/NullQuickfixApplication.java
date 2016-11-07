package anatlyzer.atl.quickfixast;

import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.AnalysisResult;

public class NullQuickfixApplication extends QuickfixApplication {

	private boolean updateWorkbench;

	public NullQuickfixApplication(boolean updateWorkbench) {
		super(null);
		this.updateWorkbench = updateWorkbench;
	}

	@Override
	public void apply() {
		// do nothing
	}
		
	@Override
	public void updateWorkbench(IDocument doc) {
		if ( updateWorkbench )
			super.updateWorkbench(doc);
	}
	
	@Override
	public void saveMetamodels(AnalysisResult r) {
		// do nothing
	}
	
	public static final QuickfixApplication NullInstance = new NullQuickfixApplication(false);
	public static final QuickfixApplication UpdatingInstance = new NullQuickfixApplication(true);

}
