package anatlyzer.atl.editor.problems;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.m2m.atl.adt.ui.editor.AtlEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import anatlyzer.atl.editor.quickfix.AnalysisQuickfixProcessor;

public class MarkerResolutionGenerator implements IMarkerResolutionGenerator {

	public static final String ORIGINAL_PROBLEM = "_ORIGINAL_PROBLEM_";
	
	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
		ICompletionProposal[] quickfixes = AnalysisQuickfixProcessor.getQuickfixes(marker);

		IEditorPart editor= findEditor(null, false);
		if ( ! (editor instanceof AtlEditor ) )
			return new IMarkerResolution[0];
		
		AtlEditor atlEditor = (AtlEditor) editor;
		IDocument document = atlEditor.getDocumentProvider().getDocument(atlEditor.getEditorInput());

		IMarkerResolution[] result = new IMarkerResolution[quickfixes.length];
		int i = 0;
		for (ICompletionProposal proposal : quickfixes) {
			CompletionProposalToMarkerResolutionWrapper wrapper = new CompletionProposalToMarkerResolutionWrapper(proposal, document);
			result[i++] = wrapper;
		}
		
		return result;
		// return new IMarkerResolution[] {
				// new QuickFixTestReplacement()
		// };
	}
	
	public static class CompletionProposalToMarkerResolutionWrapper implements IMarkerResolution2 {

		private ICompletionProposal proposal;
		private IDocument document;

		public CompletionProposalToMarkerResolutionWrapper(ICompletionProposal proposal, IDocument document) {
			this.proposal = proposal;
			this.document = document;
		}
		
		@Override
		public String getLabel() {
			return proposal.getDisplayString();
		}

		@Override
		public void run(IMarker marker) {
			proposal.apply(document);
		}

		@Override
		public String getDescription() {
			return proposal.getAdditionalProposalInfo();
		}

		@Override
		public Image getImage() {
			return null;
		}
		
	}
	
	public static class QuickFixTestReplacement implements IMarkerResolution2 {

		@Override
		public String getLabel() {
			return "Remove line";
		}

		@Override
		public void run(IMarker marker) {
			
			try {
				// LocalProblem lp = (LocalProblem) marker.getAttribute(ORIGINAL_PROBLEM);
				int line = (Integer) marker.getAttribute(IMarker.LINE_NUMBER);
				//int charStart = (Integer) marker.getAttribute(IMarker.CHAR_START);
				//int charEnd   = (Integer) marker.getAttribute(IMarker.CHAR_END);
				
				
				IEditorPart editor= findEditor(null, false);
				AtlEditor atlEditor = (AtlEditor) editor;
				IDocument document = atlEditor.getDocumentProvider().getDocument(atlEditor.getEditorInput());

				
				//int offset = document.getLineOffset(line) + charStart;
				//System.out.println(document.getLineOffset(line));
				// System.out.println(offset);
				int offset = (Integer) marker.getAttribute(IMarker.CHAR_START);
				int offsetEnd = (Integer) marker.getAttribute(IMarker.CHAR_END);
				
				/*
				int[] offset = atlEditor.getModelAnalyser().getHelper().getElementOffsets(lp.getElement(), atlEditor.getModelAnalyser().getModelOffset());
				int start = offset[0];
				int end   = offset[1];
				
				IDocument document = atlEditor.getDocumentProvider().getDocument(atlEditor.getEditorInput());
				*/
				document.replace(offset, offsetEnd - offset, "replacement");

			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			System.out.println(marker.getResource());
	
			
			
			// MessageDialog.openInformation(null, "Fix", "Fix");
/*
			IEditorPart editor= findEditor(null, false);
			AtlEditor atlEditor = (AtlEditor) editor;
			EObject l;
			try {
				
				atlEditor.getModelAnalyser().getHelper().getElementOffsets(element, atlEditor.getModelAnalyser().getModelOffset());
				
				atlEditor.getModelAnalyser().getModelOffset()
				l = atlEditor.getModelAnalyser().getLocatedElement(2);
				System.out.println(l);
				System.out.println(atlEditor.getDocumentProvider());
				
				IDocument document = atlEditor.getDocumentProvider().getDocument(atlEditor.getEditorInput());
				document.replace(10, 10, "adfadf");
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// atlEditor.getSourceViewerConf().
	
			*/
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "This is a description of the problem";
		}

		@Override
		public Image getImage() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	

	private static IEditorPart findEditor(Object inputElement, boolean b) {
		IWorkbenchWindow window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if ( window == null )
			return null;
		IWorkbenchPage page = window.getActivePage();
		return page.getActiveEditor();
	}

}
