package anatlyzer.atl.editor.compare;

import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.internal.CompareUIPlugin;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.m2m.atl.adt.ui.AtlUIPlugin;
import org.eclipse.m2m.atl.adt.ui.text.AtlTextTools;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.editor.AtlSourceViewerConfigurationExt;
import anatlyzer.atl.util.ATLUtils;

// See SimpleTextViewer
public class ATLTextViewer extends Viewer {
	private SourceViewer fSourceViewer;
	private ICompareInput fInput;


	ATLTextViewer(Composite parent) {
		fSourceViewer= new SourceViewer(parent, null, SWT.LEFT_TO_RIGHT | SWT.H_SCROLL | SWT.V_SCROLL);
		
//		JavaTextTools tools= JavaCompareUtilities.getJavaTextTools();
//		if (tools != null) {
//			IPreferenceStore store= JavaPlugin.getDefault().getCombinedPreferenceStore();
//			fSourceViewer.configure(new JavaSourceViewerConfiguration(tools.getColorManager(), store, null, IJavaPartitions.JAVA_PARTITIONING));
//		}

		AtlEditorExt editor = new AtlEditorExt();		
		
		fSourceViewer.configure(new AtlSourceViewerConfigurationExt(AtlUIPlugin.getDefault().getTextTools(), editor));
		fSourceViewer.setEditable(false);

//		String symbolicFontName= JavaMergeViewer.class.getName();
//		Font font= JFaceResources.getFont(symbolicFontName);
//		if (font != null)
//			fSourceViewer.getTextWidget().setFont(font);

	}

	@Override
	public Control getControl() {
		return fSourceViewer.getControl();
	}

	@Override
	public void setInput(Object input) {
// This wsa from JavaTextViewer
//		if (input instanceof IStreamContentAccessor) {
//			Document document= new Document(getString(input));
////			JavaCompareUtilities.setupDocument(document);
//			fSourceViewer.setDocument(document);
//		}
//		fInput= input;
		
		
		if (input instanceof IStreamContentAccessor) {
			fSourceViewer.setDocument(new Document(getString(input)));
		} else if (input instanceof ICompareInput) {
			fInput= (ICompareInput) input;
			ITypedElement left= fInput.getLeft();
			fSourceViewer.setDocument(new Document(getString(left)));
		}
		
	}

	@Override
	public Object getInput() {
		return fInput;
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void setSelection(ISelection s, boolean reveal) {
	}

	@Override
	public void refresh() {
	}

	/**
	 * A helper method to retrieve the contents of the given object
	 * if it implements the IStreamContentAccessor interface.
	 */
//	private static String getString(Object input) {
//
////		if (input instanceof IStreamContentAccessor) {
////			IStreamContentAccessor sca= (IStreamContentAccessor) input;
////			try {
////				return JavaCompareUtilities.readString(sca);
////			} catch (CoreException ex) {
////				JavaPlugin.log(ex);
////			}
////		}
//		return ""; //$NON-NLS-1$
//	}


	private String getString(Object input) {
		
		if (input instanceof IStreamContentAccessor) {
			try {
				return Utilities.readString((IStreamContentAccessor) input);
			} catch (CoreException ex) {
				// NeedWork
				CompareUIPlugin.log(ex);
			}
		}
		return ""; //$NON-NLS-1$
	}
}
