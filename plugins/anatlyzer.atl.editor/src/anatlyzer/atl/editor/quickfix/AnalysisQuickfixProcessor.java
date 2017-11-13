package anatlyzer.atl.editor.quickfix;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlCompletionDataSource;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlCompletionHelper;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlCompletionProcessor;
import org.eclipse.m2m.atl.adt.ui.text.atl.AtlModelAnalyser;
import org.eclipse.m2m.atl.common.AtlNbCharFile;
import org.eclipse.m2m.atl.engine.parser.AtlSourceManager;
import org.eclipse.ui.texteditor.MarkerAnnotation;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.builder.AnalyserExecutor;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.AnalyserUtils.CannotLoadMetamodel;
import anatlyzer.atl.util.AnalyserUtils.PreconditionParseError;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.ui.util.WorkbenchUtil;


public class AnalysisQuickfixProcessor implements IQuickAssistProcessor {

	private AtlEditor fEditor;
	private AtlSourceManager manager;

	public AnalysisQuickfixProcessor(AtlEditor editor) {
		this.fEditor = editor;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canFix(Annotation annotation) {
		if ( annotation.isMarkedDeleted() || !(annotation instanceof MarkerAnnotation) )
			return false;
		
		boolean canFix = false;
		MarkerAnnotation markerAnnotation = (MarkerAnnotation) annotation;
		try {
			 canFix = markerAnnotation.getMarker().getType().equals(AnATLyzerBuilder.MARKER_TYPE);
			 // this.annotation = markerAnnotation;
		} catch (CoreException e) { }
		
		return canFix;
	}

	/**
	 * This method is never invoked! I guess this depends on autoactivation flag.
	 * Instead computeQuickAssistProposals is invoked with CTRL+1
	 */
	@Override
	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		return false;
	}
	
	/** See: SpellingCorrectionProcessor (before was, XtextQuickAssistProcessor) */
	@Override
	public ICompletionProposal[] computeQuickAssistProposals(
			IQuickAssistInvocationContext invocationContext) {

		ISourceViewer viewer= invocationContext.getSourceViewer();
		int documentOffset= invocationContext.getOffset();

		int length= viewer != null ? viewer.getSelectedRange().y : -1;
		TextInvocationContext context= new TextInvocationContext(viewer, documentOffset, length);
		
		IAnnotationModel model = viewer.getAnnotationModel();
		if (model == null)
			return new ICompletionProposal[0]; 
		
		// From computeProposals
		int offset= context.getOffset();
		ArrayList<IMarker> annotationMarkers = new ArrayList<IMarker>();
		Iterator iter= model.getAnnotationIterator();
		while (iter.hasNext()) {
			Annotation annotation = (Annotation)iter.next();
			if (canFix(annotation)) {
				Position pos= model.getPosition(annotation);
				if (isAtPosition(offset, pos)) {
					collectMarkers(annotation, annotationMarkers);
				}
			}
		}

		List<ICompletionProposal> allProposals = new ArrayList<ICompletionProposal>();
		LinkedList<List<ICompletionProposal>> sortedProposals = new LinkedList<List<ICompletionProposal>>();
		
		for (IMarker iMarker : annotationMarkers) {
			ICompletionProposal[] qfixes = getQuickfixes(iMarker);
			
//			if ( qfixes.length > 0 && annotationMarkers.size() > 1 ) {
//				// There are quick fixes for several kinds of errors
//				allProposals.add(new ProposalCategory(iMarker));
//			}
			List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>(qfixes.length);

			if ( qfixes.length > 0 ) {
				proposals.add(new ProposalCategory(iMarker));
			}
			
			for (ICompletionProposal iCompletionProposal : qfixes) {
				proposals.add(iCompletionProposal);
			}
			
			sortedProposals.add(proposals);
		}
		sortedProposals.sort((l1, l2) -> -1 * Integer.compare(l1.size(), l2.size()));

		allProposals = sortedProposals.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
		
		if ( allProposals.isEmpty() ) {
			// If there are no errors, then try the quick assist
			return computeQuickAssist(invocationContext, invocationContext.getOffset());
		} else {		
			return (ICompletionProposal[]) allProposals.toArray(new ICompletionProposal[allProposals.size()]);
		}
	}

	private ICompletionProposal[] computeQuickAssist(IQuickAssistInvocationContext invocationContext, int offset) {
		
		AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysis(fEditor.getUnderlyingResource());
		if ( analysis == null ) {
			// If it is not available yet, execute the analyser
			try {
				analysis = new AnalyserExecutor().exec(fEditor.getUnderlyingResource());
			} catch (IOException | CoreException | CannotLoadMetamodel | PreconditionParseError e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return new ICompletionProposal[0];
			}
		}
		
		IDocument document = invocationContext.getSourceViewer().getDocument();
		LocatedElement found = WorkbenchUtil.getElementFromOffset(offset, analysis.getATLModel(), document);
		
		if ( found != null ) {
			return getQuickAssists(found, analysis);
		}
		return new ICompletionProposal[0];
		
		/*
		AtlCompletionDataSource datasource = new AtlCompletionDataSource(getSourceManager());

		IDocument document = invocationContext.getSourceViewer().getDocument();
		String prefix = AtlCompletionProcessor.extractPrefix(document, offset);
		AtlModelAnalyser currentAnalyser = null;
		// if (fEditor.isDirty()) {
		try {
			currentAnalyser = new AtlCompletionHelper(document.get()).computeModelAnalyser(offset, prefix,
					datasource.getATLFileContext());

			
			
			AnalysisResult analysis = AnalysisIndex.getInstance().getAnalysis(fEditor.getUnderlyingResource());
			
			EObject locatedElement = currentAnalyser.getLocatedElement(offset);
			String location = (String) locatedElement.eGet(locatedElement.eClass().getEStructuralFeature("location"));
			
			LocatedElement found = null;
			for (LocatedElement e : analysis.getATLModel().allObjectsOf(LocatedElement.class)) {
				if ( e.getLocation() != null && e.getLocation().equals(location) ) {
					found = e;
					break;
				}
			}
			
			System.out.println(found);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}


	
	public AtlSourceManager getSourceManager() {
		if (manager == null) {
			if (fEditor != null) {
				manager = fEditor.getSourceManager();
			}
		}
		return manager;
	}

	private boolean isAtPosition(int offset, Position pos) {
		return (pos != null) && (offset >= pos.getOffset() && offset <= (pos.getOffset() +  pos.getLength()));
	}

	private void collectMarkers(Annotation annotation, List<IMarker> markers) {	
		if (annotation instanceof MarkerAnnotation)
			markers.add( ((MarkerAnnotation) annotation).getMarker());
	}
	
	public static ICompletionProposal[] getQuickfixes(IMarker iMarker) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_QUICKFIX_EXTENSION_POINT);
		ArrayList<ICompletionProposal> quickfixes = new ArrayList<ICompletionProposal>();

		try {
			iMarker.setAttribute(AtlProblemQuickfix.GUI_MODE_ATTR, true);
		} catch (CoreException e1) { 
			e1.printStackTrace();			
		}
		
		for (IConfigurationElement ce : extensions) {
			try {
				if ( ce.getName().equals("quickfix") ) {
					AtlProblemQuickfix qf = (AtlProblemQuickfix) ce.createExecutableExtension("apply");
					if ( qf.isApplicable(iMarker) ) {
						qf.setErrorMarker(iMarker);
						quickfixes.add(qf);
					}
				} 
				else if ( ce.getName().equals("quickfixset") ) {
					AtlProblemQuickfixSet detector = (AtlProblemQuickfixSet) ce.createExecutableExtension("detector");
					
					if ( detector.isApplicable(iMarker) ) {
						for(AtlProblemQuickfix q : detector.getQuickfixes(iMarker) ) {
							if ( ! q.isApplicable(iMarker) ) {
								throw new IllegalStateException();
							}
							q.setErrorMarker(iMarker);
							quickfixes.add(q);							
						}
					}
				}
			} catch (Exception e) {
				Activator.logError("Cannot retrieve quick fix", e);
				e.printStackTrace();				
			}
		}

		return (ICompletionProposal[]) quickfixes.toArray(new ICompletionProposal[quickfixes.size()]);
	}

	
	public static ICompletionProposal[] getQuickAssists(LocatedElement element, AnalysisResult result) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = registry.getConfigurationElementsFor(Activator.ATL_QUICKASSIST_EXTENSION_POINT);
		
		ArrayList<ICompletionProposal> assists = new ArrayList<ICompletionProposal>();

		for (IConfigurationElement ce : extensions) {
			try {
				if ( ce.getName().equals("quickassist") ) {
					AtlQuickAssist qa = (AtlQuickAssist) ce.createExecutableExtension("apply");
					qa.setCanExpectUserInteraction(true);
					qa.setElement(element, result);
					if ( qa.isApplicable() ) {
						assists.add(qa);
					}
				} 
				else if ( ce.getName().equals("quickassistset") ) {
					AtlQuickAssistSet detector = (AtlQuickAssistSet) ce.createExecutableExtension("detector");
					detector.setCanExpectUserInteraction(true);
					detector.setElement(element, result);
					if ( detector.isApplicable() ) {
						for(AtlQuickAssist q : detector.getQuickAssists() ) {
							q.setCanExpectUserInteraction(true);
							q.setElement(element, result);

							if ( ! q.isApplicable() ) {
								throw new IllegalStateException();
							}
							assists.add(q);							
						}
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return (ICompletionProposal[]) assists.toArray(new ICompletionProposal[assists.size()]);
	}

}
