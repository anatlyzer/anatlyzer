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
	public static final String DEFAULT_ANALYSIS_CONFIGURATION = "DEFAULT_ANALYSIS_CONFIGURATION";
	public static final String USE_OCL_SIMPLIFIER = "USE_OCL_SIMPLIFIER";

	@Override
	public void initializeDefaultPreferences() {
		// TODO Auto-generated method stub
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		// store.setDefault(TIMEOUT_PREFERENCE, 25000L);
		store.setDefault(TIMEOUT_PREFERENCE, 7500L);
		store.setDefault(BUILDER_ANALYSE_OPEN_ONLY, true);
		store.setDefault(SPECULATIVE_QUICKFIXES_ENABLED, false);
		store.setDefault(USE_OCL_SIMPLIFIER, false);
		store.setDefault(DEFAULT_ANALYSIS_CONFIGURATION, DefaultAnalysisConfiguration.MODEL_FINDING_ON_ERRORS.name());

		store.setDefault(MIN_BOUNDS, 0);
		store.setDefault(MAX_BOUNDS, 5);
	}
	
	public static boolean getUseOclSimplifier() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return store.getBoolean(USE_OCL_SIMPLIFIER);
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
	
	public static DefaultAnalysisConfiguration getDefaultAnalysisConfiguration() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		return DefaultAnalysisConfiguration.valueOf(store.getString(DEFAULT_ANALYSIS_CONFIGURATION));
	}
}
