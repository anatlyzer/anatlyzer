package anatlyzer.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import anatlyzer.atl.editor.Activator;

public class AnATLyzerPreferenceInitializer extends AbstractPreferenceInitializer {

	public static final String TIMEOUT_PREFERENCE = "TIMEOUT";
	public static final String BUILDER_ANALYSE_OPEN_ONLY = "BUILDER_ANALYSE_ONLY_OPEN";
	public static final String SPECULATIVE_QUICKFIXES_ENABLED = "SPECULATIVE_QUICKFIXES_ENABLED";
	public static final String MIN_BOUNDS = "MIN_BOUNDS";
	public static final String MAX_BOUNDS = "MAX_BOUNDS";

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(TIMEOUT_PREFERENCE, 25000L);
		store.setDefault(BUILDER_ANALYSE_OPEN_ONLY, true);
		store.setDefault(SPECULATIVE_QUICKFIXES_ENABLED, true);

		store.setDefault(MIN_BOUNDS, 0);
		store.setDefault(MAX_BOUNDS, 5);
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
	
	public static int getMinBounds() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getInt(MIN_BOUNDS);
	}
	
	public static int getMaxBounds() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getInt(MAX_BOUNDS);
	}
}
