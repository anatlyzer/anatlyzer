package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.ui.AskClasses;
import anatlyzer.atl.errors.atl_error.CannotInstantiateAbstractClass;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.NullQuickfixApplication;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModelElement;

public class CannotInstantiateAbstractClass_ChooseConcrete extends AbstractAtlQuickfix {

	private List<EClass> subclasses;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, CannotInstantiateAbstractClass.class) && 
				validConcreteClasses(marker).size() > 0 ;
	}

	private List<EClass> validConcreteClasses(IMarker marker) {
		if ( subclasses != null )
			return subclasses;
		
		OutPatternElement elem = (OutPatternElement) getProblematicElement(marker);
		Metaclass m = (Metaclass) elem.getInferredType();
		List<EClass> classes = ASTUtils.getMetamodelClasses(m);		
		this.subclasses = classes.stream().
				filter(c -> !c.isAbstract()).
				filter(c -> m.getKlass().isSuperTypeOf(c)).collect(Collectors.toList());
		return this.subclasses;
	}

	@Override
	public void resetCache() { 
		subclasses = null;		
	}

	@Override
	public void apply(IDocument document) {		
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();			
	}

	@Override
	public String getDisplayString() {
		return "Change abstract class by concrete class";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		EClass selectedClass = null;
		if ( canExpectUserInteraction() ) {
			AskClasses a = new AskClasses(null, "Select a non-abstract class");
			a.setClasses(this.subclasses);
			if ( a.open() == Window.OK ) {
				selectedClass = a.getSelectedClass();
			}
			if ( selectedClass == null )
				return NullQuickfixApplication.NullInstance;
		} else {
			selectedClass = subclasses.get(0); // choose one
		}

		OutPatternElement elem = (OutPatternElement) getProblematicElement(marker);

		QuickfixApplication qfa = new QuickfixApplication(this);
		
		final String className = selectedClass.getName();
		qfa.replace((OclModelElement) elem.getType(), (original, trace) -> {
			OclModelElement t = OCLFactory.eINSTANCE.createOclModelElement();
			t.setName(className);
			t.setModel(original.getModel());
			
			return t;
		});
	
		return qfa;
	}
}
