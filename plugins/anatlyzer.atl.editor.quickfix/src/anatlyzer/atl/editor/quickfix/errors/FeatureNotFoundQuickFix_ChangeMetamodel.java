package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.ui.AskFeature;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.NullQuickfixApplication;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;

public class FeatureNotFoundQuickFix_ChangeMetamodel extends AbstractMetamodelChangeQuickfix  {

	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, FeatureNotFound.class) && 
				(canExpectUserInteraction(marker) || findExpectedType() != null);
	}

	@Override public void resetCache() {}
	
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
	public boolean requiresUserIntervention() {
		return findExpectedType() != null;
	}
	
	/**
	 * Returns the type that will be used as the new feature type and
	 * the best upper bound.
	 * @return
	 */
	protected Pair<Type, Integer> findExpectedType() {
		// The binding case
		if ( getProblematicElement() instanceof Binding ) {
			Binding b = (Binding) getProblematicElement();
			int upperBound = TypeUtils.isCollection(b.getValue().getInferredType()) ? -1 : 1;
			if ( b.getLeftType() instanceof PrimitiveType ) {
				return new Pair<Type, Integer>(b.getLeftType(), upperBound);
			}
			// This could be smarter and look for compatible rules...
			return null;			
		}
		
		// The navigation case
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();		
		Type t = ASTUtils.findExpectedTypeInExpressionPosition(nav, false);
		return new Pair<Type, Integer>(t, TypeUtils.isCollection(t) ? -1 : 1);		
	}
	
	@Override
	public String getChangedMetamodel() {
		return getSourceType().getModel().getName();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		Pair<Type, Integer> pair = findExpectedType();
		
		if ( canExpectUserInteraction() ) {
			return askUser(pair);
		}
		
		String featureName = getPropertyName();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(getSourceType().getKlass(), getSourceType().getModel().getName(), (klass) -> {
			Type featureType = TypeUtils.getUnderlyingType(pair._1);
			
			EStructuralFeature feat = null;
			if ( featureType instanceof PrimitiveType ) {
				feat = EcoreFactory.eINSTANCE.createEAttribute();
				if ( featureType instanceof BooleanType ) {
					feat.setEType(EcorePackage.Literals.EBOOLEAN);
				} else if ( featureType instanceof StringType ) {
					feat.setEType(EcorePackage.Literals.ESTRING);
				} else if ( featureType instanceof IntegerType ) {
					feat.setEType(EcorePackage.Literals.EINT);
				} else if ( featureType instanceof FloatType ) {
					feat.setEType(EcorePackage.Literals.EDOUBLE);
				} else {
					throw new UnsupportedOperationException();
				}
			} else if ( featureType instanceof Metaclass ) {
				feat = EcoreFactory.eINSTANCE.createEReference();
				feat.setEType(((Metaclass) featureType).getKlass());
			} else {
				throw new UnsupportedOperationException();
			}
			
			feat.setName(featureName);
			feat.setLowerBound(1);
			feat.setUpperBound(pair._2);
			
			klass.getEStructuralFeatures().add(feat);
		});
		
		return qfa;
	}

	private QuickfixApplication askUser(Pair<Type, Integer> pair) {
		AskFeature a = new AskFeature(null);
		a.setFeatureName(getPropertyName());
		a.setEnclosingType(getSourceType());
		if ( pair != null ) {
			a.setBounds(1, pair._2);
			a.setFeatureType(pair._1);
		}
		
		if ( a.open() == Window.OK ) {
			EClassifier c = null;
			if      ( a.getFeatureType().equals("EBoolean") ) c = EcorePackage.Literals.EBOOLEAN;
			else if ( a.getFeatureType().equals("EString") )  c = EcorePackage.Literals.ESTRING;
			else if ( a.getFeatureType().equals("EInteger") ) c = EcorePackage.Literals.EINT;
			else if ( a.getFeatureType().equals("EFloat") )   c = EcorePackage.Literals.EFLOAT;
			else if ( a.getFeatureType().equals("EDouble") )  c = EcorePackage.Literals.EDOUBLE;
			else {
				c = ASTUtils.getMetamodelClasses(getSourceType()).stream().
					filter(cl -> cl.getName().equals(a.getFeatureType())).
					findAny().
					orElse(null);
			}
			
			if ( c == null ) {
				// TODO: Notify the user
				return NullQuickfixApplication.Instance;
			}
			
			String featureName = a.getFeatureName();

			final EClassifier actualFeatureType = c;
			
			// Be careful to keep this in sync with the automatic inference
			QuickfixApplication qfa = new QuickfixApplication(this);			
			qfa.mmModify(getSourceType().getKlass(), getSourceType().getModel().getName(), (klass) -> {
				EStructuralFeature feat = null;
				if ( actualFeatureType instanceof EClass ) {
					feat = EcoreFactory.eINSTANCE.createEReference();										
				} else {
					feat = EcoreFactory.eINSTANCE.createEAttribute();					
				}
				feat.setEType(actualFeatureType);
				feat.setName(featureName);
				feat.setLowerBound(a.getLowerBound());
				feat.setUpperBound(a.getUpperBound());
				
				klass.getEStructuralFeatures().add(feat);
			});
			
			return qfa;
		}
		
		return NullQuickfixApplication.Instance;
	}

	@Override
	public String getDisplayString() {
		return "Create new feature (in the meta-model)";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_feature.createImage();
	}
	
}
