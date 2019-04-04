package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.OCLHelper;
import org.eclipse.ocl.pivot.values.IntegerValue;

import anatlyzer.atl.util.UnsupportedTranslation;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.ocl.emf.PivotOCLtoATL.Visitor;

/**
 * Converts invariants in Ecore/OCL into ATL format.
 * 
 * @author jesus
 */
public class FIXOCL {

	public void tryToFix(Model m) {
		new Visitor(null).visit(m);		
	}
	
	public void tryToFix(Constraint m) {
		new Visitor(null).visit(m);		
	}
	
	public class Visitor extends AbstractExtendingVisitor<Void, Object> {

		protected Visitor(Object context) {
			super(context);
		}

		@Override
		public Void visitModel(@NonNull Model object) {
			safeVisit(object.getOwnedPackages());			
			return null;
		}
		
		private void safeVisit(@NonNull List<? extends Element> elements) {
			for (Element element : elements) {
				safeVisit(element);
			}
		}
		
		@Override
		public Void visitPackage(@NonNull Package object) {
			safeVisit(object.getOwnedClasses());
			return null;
		}
		
		@Override
		public Void visitClass(@NonNull Class object) {
			List<Constraint> cSuspicius = new ArrayList<Constraint>();
			List<Property> pSuspicius = new ArrayList<Property>();
			
			for(Constraint c : object.getOwnedInvariants()) {
				if ( c.getName() == null ) {
					cSuspicius.add(c);					
				}
			}
			
			for(Property p : object.getOwnedProperties()) {
				if ( p.getOwnedExpression() == null ) {
					pSuspicius.add(p);
				}
			}
			
			
			if ( cSuspicius.isEmpty() && pSuspicius.isEmpty() ) {
				return null;
			} if ( cSuspicius.size() == 1 && pSuspicius.size() == 1 ) {
				Property property = pSuspicius.get(0);
				Constraint constraint = cSuspicius.get(0);
				EcoreUtil.remove(constraint);
				property.setIsDerived(true);
				property.setOwnedExpression(constraint.getOwnedSpecification());
				System.err.println("Tentative fix of derived property without expression: " + property.getName());
			} else {
				System.err.println("Properties without ownedExpression, cannot be fixed. Class " + object.getName());
			}
			
			return null;
		}

		
//		@Override
//		public Void visitProperty(@NonNull Property object) {
//			if ( isDerived(object) ) { 				
//				ContextHelper helper = transform(object);
//				if ( helper != null )
//					translated.add(helper);
//			}
//			return null;
//		}
		

		public Void visiting(@NonNull Visitable visitable) {
			throw new IllegalStateException("Cannot handle: " + visitable);
		}
				
	}
	
}
