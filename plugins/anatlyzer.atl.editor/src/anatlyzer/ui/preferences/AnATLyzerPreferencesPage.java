package anatlyzer.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import anatlyzer.atl.editor.Activator;

public class AnATLyzerPreferencesPage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {
	
    public AnATLyzerPreferencesPage() {
        super(GRID);

    }

    public void createFieldEditors() {
//        addField(new DirectoryFieldEditor("PATH", "&Directory preference:",
//                getFieldEditorParent()));
//        addField(new BooleanFieldEditor("BOOLEAN_VALUE",
//                "&An example of a boolean preference", getFieldEditorParent()));
//
//        addField(new RadioGroupFieldEditor("CHOICE",
//                "An example of a multiple-choice preference", 1,
//                new String[][] { { "&Choice 1", "choice1" },
//                        { "C&hoice 2", "choice2" } }, getFieldEditorParent()));
//        addField(new StringFieldEditor("MySTRING1", "A &text preference:",
//                getFieldEditorParent()));
//        addField(new StringFieldEditor("MySTRING2", "A &text preference:",
//                getFieldEditorParent()));

    	addField(new BooleanFieldEditor(AnATLyzerPreferenceInitializer.BUILDER_ANALYSE_OPEN_ONLY, "&Analyse only open files", getFieldEditorParent()));

    	addField(new IntegerFieldEditor(AnATLyzerPreferenceInitializer.TIMEOUT_PREFERENCE, "Default time out", getFieldEditorParent()));
    	addField(new IntegerFieldEditor(AnATLyzerPreferenceInitializer.MIN_BOUNDS, "Min. bounds", getFieldEditorParent()));
    	addField(new IntegerFieldEditor(AnATLyzerPreferenceInitializer.MAX_BOUNDS, "Max. bounds", getFieldEditorParent()));
    	

    	addField(new BooleanFieldEditor(AnATLyzerPreferenceInitializer.SPECULATIVE_QUICKFIXES_ENABLED, "Enable speculative quick fixes", getFieldEditorParent()));
    	addField(new BooleanFieldEditor(AnATLyzerPreferenceInitializer.USE_OCL_SIMPLIFIER, "Simplify auto-generated OCL expressions", getFieldEditorParent()));
    	
    	addField(new ComboFieldEditor(AnATLyzerPreferenceInitializer.DEFAULT_ANALYSIS_CONFIGURATION, "Default batch configuration",
    			new String[][] { 
    				{ "Model finding on errors", DefaultAnalysisConfiguration.MODEL_FINDING_ON_ERRORS.name() },
    				{ "All errors in continous mode", DefaultAnalysisConfiguration.ALL_CONTINOUS.name() },
    				{ "No model finding", DefaultAnalysisConfiguration.NO_MODEL_FINDING.name() }    				
    			},     			
    			getFieldEditorParent()));

    }

    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("AnATLyzer preferences");
    }
    
    @Override
    protected void checkState() {
    	super.checkState();
    	
    	// Do additional validations here. Set setIsValid(false) if needed
    }
}
