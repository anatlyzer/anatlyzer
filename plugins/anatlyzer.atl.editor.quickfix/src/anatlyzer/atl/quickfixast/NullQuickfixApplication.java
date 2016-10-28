package anatlyzer.atl.quickfixast;

import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.AnalysisResult;

public class NullQuickfixApplication extends QuickfixApplication {

	public NullQuickfixApplication() {
		super(null);
	}

	@Override
	public void apply() {
		// do nothing
	}
	
	@Override
	public void updateWorkbench(IDocument doc) {
		// do nothing
	}
	
	@Override
	public void saveMetamodels(AnalysisResult r) {
		// do nothing
	}
	
	public static final QuickfixApplication Instance = new NullQuickfixApplication();
}
