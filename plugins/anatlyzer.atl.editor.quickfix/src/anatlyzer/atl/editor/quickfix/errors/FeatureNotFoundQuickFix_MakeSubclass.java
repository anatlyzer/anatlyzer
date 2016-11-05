package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

public class FeatureNotFoundQuickFix_MakeSubclass extends AbstractMetamodelChangeQuickfix  {

	private List<EClass> result = null;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, FeatureNotFound.class) && 
				(findSuperClass().size() > 0 );
	}

	@Override public void resetCache() {
		result = null;		
	}
	
	// Similar to the FeatureNotFoundQuickfix_ChangeMetamodel
	private Metaclass getSourceType() {
		Type t = null;
		if ( getProblematicElement() instanceof NavigationOrAttributeCallExp ) {		
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
			t = nav.getSource().getInferredType();
		} else {
			Binding b = (Binding) getProblematicElement();
			t = b.getOutPatternElement().getInferredType();
		}
		
		if ( t instanceof Metaclass && ((Metaclass) t).getModel() != null ) {
			return (Metaclass) t;
		}
		return null;
	}

	private List<EClass> findSuperClass() {
		if ( result != null )
			return result;
		
		Metaclass source = getSourceType();
		String foundName = getPropertyName();
		result = ASTUtils.getMetamodelClasses(source).stream().
			filter(c -> c.getEStructuralFeatures().stream().anyMatch(f -> f.getName().equals(foundName))).
			collect(Collectors.toList());
		return result;
	}
	
	private String getPropertyName() {
		Type t = null;
		if ( getProblematicElement() instanceof NavigationOrAttributeCallExp ) {		
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
			return nav.getName();
		} else {
			Binding b = (Binding) getProblematicElement();
			return b.getPropertyName();
		}
	}
	
	
	@Override
	public String getChangedMetamodel() {
		return getSourceType().getModel().getName();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {		
		String featureName = getPropertyName();
		List<EClass> supertypes = findSuperClass();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(getSourceType().getKlass(), getSourceType().getModel().getName(), (klass) -> {
			// pick up the first one. The user could be notified
			klass.getESuperTypes().add(supertypes.get(0));
		});
		
		return qfa;
	}

	@Override
	public String getDisplayString() {
		// TODO: Explain in the long version that it contains the wanted feature
		// TODO: Could be more intelligent and take into account type compatibility to sort the result
		return "Make " + getSourceType().getName() + " inherit from " + findSuperClass().get(0).getName();
	}

	@Override
	public Image getImage() {
		return QuickfixImages.make_subclass.createImage();
	}

}
