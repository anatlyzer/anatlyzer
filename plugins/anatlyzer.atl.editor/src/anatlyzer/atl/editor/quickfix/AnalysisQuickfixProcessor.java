package anatlyzer.atl.editor.quickfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.quickassist.IQuickAssistInvocationContext;
import org.eclipse.jface.text.quickassist.IQuickAssistProcessor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.TextInvocationContext;
import org.eclipse.ui.texteditor.MarkerAnnotation;

import anatlyzer.atl.editor.Activator;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.errors.Problem;


public class AnalysisQuickfixProcessor implements IQuickAssistProcessor {

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

	@Override
	public boolean canAssist(IQuickAssistInvocationContext invocationContext) {
		// TODO Auto-generated method stub
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

		IAnnotationModel model= viewer.getAnnotationModel();
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
		for (IMarker iMarker : annotationMarkers) {
			ICompletionProposal[] qfixes = getQuickfixes(iMarker);
			if ( annotationMarkers.size() > 1 ) {
				// There are quick fixes for several kinds of errors
				allProposals.add(new ProposalCategory(iMarker));
			}
			for (ICompletionProposal iCompletionProposal : qfixes) {
				allProposals.add(iCompletionProposal);
			}
		}
		
		return (ICompletionProposal[]) allProposals.toArray(new ICompletionProposal[allProposals.size()]);
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
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return (ICompletionProposal[]) quickfixes.toArray(new ICompletionProposal[quickfixes.size()]);
	}

}
