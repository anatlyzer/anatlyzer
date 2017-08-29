package anatlyzer.atl.editor.quickfix.errors;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.NullQuickfixApplication;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoClassFoundInMetamodelQuickFix_SelectDialog extends AbstractAtlQuickfix  {

	private String  closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein(3), new LongestCommonSubstring());
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, NoClassFoundInMetamodel.class);
	}

	@Override public void resetCache() { }
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();		
	}

	@Override
	public String getDisplayString() {
		return "Select class from dialog";
	}


	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OclModelElement me = (OclModelElement) this.getProblematicElement();		
		String mmName = me.getModel().getName();
		List<EClass> allClasses = getAnalysisResult().getNamespace().getNamespace(mmName).getAllClasses();
		
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(null, new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((EClass) element).getName();
			}	
		});
		dialog.setTitle("Choose meta-class");
		dialog.setElements(allClasses.toArray(new EClass[allClasses.size()]));
		dialog.setMultipleSelection(false);
		
		if (dialog.open() != Window.OK) {
	        return NullQuickfixApplication.NullInstance;
		}
		Object[] result = dialog.getResult();
		EClass c = (EClass) result[0];
			
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(me, (expr, trace) -> {
			OclModelElement newOme = OCLFactory.eINSTANCE.createOclModelElement();
			newOme.setName(c.getName());
			newOme.setModel( (OclModel) ATLCopier.copySingleElement(me.getModel()) );
			
			return newOme;
		});
		
		return qfa;
	}

}
