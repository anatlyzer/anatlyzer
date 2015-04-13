package anatlyzer.atl.editor.views;

import org.eclipse.jface.action.IAction;

public interface IAnalysisViewAction extends IAction {
	void setAnalysisView(IAnalysisView analysisView);

}
