package anatlyzer.atl.editor.quickfix;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public abstract class AbstractAtlQuickfix extends QuickfixUtil implements AtlProblemQuickfix {

	protected IMarker marker;

	/**
	 * The default value is false.
	 */
	@Override
	public boolean isMetamodelChanging() {
		return false;
	}
	
	@Override
	public boolean requiresUserIntervention() {
		return false;
	}
	
	@Override
	public boolean canExpectUserInteraction() {
		return canExpectUserInteraction(this.marker);
	}
		
	public boolean canExpectUserInteraction(IMarker marker) {
		try {
			return (Boolean) marker.getAttribute(AtlProblemQuickfix.GUI_MODE_ATTR);
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
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
	
	
	private QuickfixApplication cachedQfa = null;
	private HashMap<Object, Object> data;
	
	public QuickfixApplication getCachedQuickfixApplication() throws CoreException {
		if ( cachedQfa == null )
			cachedQfa = getQuickfixApplication();
		return cachedQfa;
	}
	
	public void setErrorMarker(IMarker marker) {		
		this.marker = marker;
	}
	
	@Override
	public IMarker getErrorMarker() {
		return marker;
	}
	
	
	public AnalysisResult getAnalysisResult() {
		return getAnalyserData(marker);
	}

	public ATLModel getATLModel() {
		return getAnalysisResult().getAnalyser().getATLModel();
	}

	public ATLModel getATLModel(IMarker marker) {
		return getAnalyserData(marker).getAnalyser().getATLModel();
	}
	
	protected int getProblemStartOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_START);
	}
	
	protected int getProblemEndOffset() throws CoreException {
		return (Integer) marker.getAttribute(IMarker.CHAR_END);
	}
	
	public Problem getProblem() throws CoreException {
		return getProblem(this.marker);
	}
	
	protected Problem getProblem(IMarker marker) throws CoreException {
		return (Problem) marker.getAttribute(AnATLyzerBuilder.PROBLEM);
	}
	
	public LocatedElement getProblematicElement() {
		return getProblematicElement(this.marker);
	}
	
	protected LocatedElement getProblematicElement(IMarker marker) {
		try {
			return (LocatedElement) ((LocalProblem) getProblem(marker)).getElement();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected int[] getElementOffset(LocatedElement obj, IDocument document) {
		AtlNbCharFile help = new AtlNbCharFile(new ByteArrayInputStream(document.get().getBytes()));
		return help.getIndexChar(obj.getLocation());
	}
	
	protected int getEnd(int[] elementOffset) {
		return elementOffset[1];
	}


	protected int getStart(int[] elementOffset) {
		return elementOffset[0];
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

	@Override
	public void setData(Object key, Object value) {
		if ( this.data == null ) {
			this.data = new HashMap<Object, Object>();
		}
		this.data.put(key, value);
	}
	
	public Object getData(Object key) {
		if ( this.data == null ) {
			this.data = new HashMap<Object, Object>();
		}
		return this.data.get(key);		
	}

	protected Binding getBindingFor(OperationCallExp e) {	// Can be moved to a library?
		EObject container = e.eContainer();
		
		while (!(container instanceof Binding) && (container != null)) {
			container = container.eContainer();
		}
		return (Binding)container;
	}
	
	protected Type getMetaModelType(Binding b) {		// Can be moved to a library?
		Type expected = b.getLeftType();
		if (expected instanceof SequenceType) {
			SequenceType st = (SequenceType)expected;
			return (Metaclass)st.getContainedType();
		}
		//return (Metaclass)expected;
		return expected;
	}
	
	protected EClass getClassContainer(Binding b) {		// Can be moved to a library?
		EObject feature = b.getWrittenFeature();
		EClass  owner = (EClass)feature.eContainer();
		return owner;
	}
	
	protected boolean isCompatibleWith (Type t1, Metaclass m2) {	// Can be moved to a library?
		if (t1.equals(m2)) return true;
		if (!(t1 instanceof Metaclass)) return false;
		Metaclass m1 = (Metaclass)t1;
		return (m2.getKlass().isSuperTypeOf(m1.getKlass()));
	}
	
	
	

}
