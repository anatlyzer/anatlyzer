package anatlyzer.experiments.performance;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.quickfixast.QuickfixApplication;
import transML.exceptions.transException;

public aspect MeasureQuickfixTime {
	public TimeRecorder checkApplicability = new TimeRecorder();
	public TimeRecorder quickfixApplication = new TimeRecorder();
	public TimeRecorder speculativeAnalysis = new TimeRecorder();
	
	public static final String CHECK_APPLICABILITY      = "checkApplicability";
	public static final String SPECULATIVE_ANALYSIS = "speculativeAnalysis";
	public static final String QUICKFIX_APPLICATION = "quickfixApplication";
	
	public boolean activated = true;
	
	public void activate() {
		this.activated = true;
	}
	
	
	
    before() : execution(boolean anatlyzer.atl.editor.quickfix.AtlProblemQuickfix.isApplicable(org.eclipse.core.resources.IMarker)){
    	checkApplicability.start(CHECK_APPLICABILITY);
    }

    before() : execution(boolean anatlyzer.atl.editor.quickfix.AtlProblemQuickfix.isApplicable(org.eclipse.core.resources.IMarker)){
    	checkApplicability.stop();
    }
    
    before() : execution(boolean anatlyzer.atl.editor.quickfix.AtlProblemQuickfix.isApplicable(org.eclipse.core.resources.IMarker)){
    	checkApplicability.start(CHECK_APPLICABILITY);
    }

    before() : execution(QuickfixApplication anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix.getQuickfixApplication()){
    	quickfixApplication.start(QUICKFIX_APPLICATION);
    }

    after() : execution(QuickfixApplication anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix.getQuickfixApplication()){
    	quickfixApplication.stop();
    }

    
    	
}

