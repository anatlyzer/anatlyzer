package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.quickfixast.QuickfixApplication;

public abstract class AbstractMetamodelChangeQuickfix extends AbstractAtlQuickfix {
	@Override
	public boolean isMetamodelChanging() {
		return true;
	}
	
	@Override public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			qfa.apply();
			qfa.saveMetamodels(getAnalysisResult());
			qfa.updateWorkbench(document);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
