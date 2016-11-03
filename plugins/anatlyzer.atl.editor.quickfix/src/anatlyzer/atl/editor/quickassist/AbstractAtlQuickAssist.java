package anatlyzer.atl.editor.quickassist;

import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.ATL.LocatedElement;

public abstract class AbstractAtlQuickAssist implements AtlQuickAssist {

	private boolean canExpectUserInteraction;
	private HashMap<Object, Object> data;
	private LocatedElement element;
	private AnalysisResult result;

	@Override
	public void setCanExpectUserInteraction(boolean b) {
		this.canExpectUserInteraction = b;
	}
	
	@Override
	public void setElement(LocatedElement elem, AnalysisResult result) {
		this.element = elem;	
		this.result  = result;
	}
	
	@Override
	public LocatedElement getElement() {
		return element;
	}
	
	/**
	 * The default value is false.
	 */
	@Override
	public boolean isMetamodelChanging() {
		return false;
	}

	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean requiresUserIntervention() {
		return false;
	}
	
	@Override
	public boolean canExpectUserInteraction() {
		return this.canExpectUserInteraction;
	}
	
	
	@Override
	public String getChangedMetamodel() {
		throw new IllegalStateException("Inconsistent 'isMetamodelChanging' for class " + this.getClass().getName());
	}
	
	/**
	 * This method implements the AST manipulation logic by using a QuickfixApplication 
	 * object to keep track of the changes.
	 * 
	 * @return The used QuickfixApplication object
	 * @throws CoreException 
	 */
	public abstract QuickfixApplication getQuickfixApplication() throws CoreException;
		
	private QuickfixApplication qfa = null;
	
	public QuickfixApplication getCachedQuickfixApplication() throws CoreException {
		if ( qfa == null )
			qfa = getQuickfixApplication();
		return qfa;
	}
	
	public AnalysisResult getAnalysisResult() {
		return result;
	}

	public ATLModel getATLModel() {
		return getAnalysisResult().getAnalyser().getATLModel();
	}

	// Default implementations
	
	@Override
	public Point getSelection(IDocument document) {
		return null;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return getDisplayString();
	}

//	@Override
//	public void setData(Object key, Object value) {
//		if ( this.data == null ) {
//			this.data = new HashMap<Object, Object>();
//		}
//		this.data.put(key, value);
//	}
//	
//	public Object getData(Object key) {
//		if ( this.data == null ) {
//			this.data = new HashMap<Object, Object>();
//		}
//		return this.data.get(key);		
//	}

	

}
