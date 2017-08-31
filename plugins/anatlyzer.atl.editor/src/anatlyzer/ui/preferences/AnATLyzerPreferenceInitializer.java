package anatlyzer.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import anatlyzer.atl.editor.Activator;

public class AnATLyzerPreferenceInitializer extends AbstractPreferenceInitializer {

	public static final String TIMEOUT_PREFERENCE = "TIMEOUT";
	public static final String BUILDER_ANALYSE_OPEN_ONLY = "BUILDER_ANALYSE_ONLY_OPEN";
	public static final String SPECULATIVE_QUICKFIXES_ENABLED = "SPECULATIVE_QUICKFIXES_ENABLED";

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(TIMEOUT_PREFERENCE, 25000L);
		store.setDefault(BUILDER_ANALYSE_OPEN_ONLY, true);
		store.setDefault(SPECULATIVE_QUICKFIXES_ENABLED, true);
	}
	
	public static long getTimeOut() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getLong(TIMEOUT_PREFERENCE);
	}

	public static boolean getBuilderAnalyseOnlyOpened() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(BUILDER_ANALYSE_OPEN_ONLY);
	}
	
	public static boolean getSpeculativeQuickfixesEnabled() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(SPECULATIVE_QUICKFIXES_ENABLED);
	}
}
