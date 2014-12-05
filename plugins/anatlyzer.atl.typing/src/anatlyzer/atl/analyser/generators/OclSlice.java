package anatlyzer.atl.analyser.generators;

import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;

/**
 * Computes the effective meta-model of an OCL expression,
 * adding the result to given {@link ErrorSlice} object.
 * 
 * It is intended to be called by the different nodes of the error
 * graph (passing the same {@link ErrorSlice}. 
 * 
 * @author jesus
 *
 */
public class OclSlice {

	private static HashSet<Class<?>> ignore = new HashSet<Class<?>>();
	
	// In development mode only...
	static {
		ignore.add(anatlyzer.atlext.OCL.impl.VariableExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.OperatorCallExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.OclModelElementImpl.class);

		
		ignore.add(anatlyzer.atlext.OCL.impl.StringExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.IntegerExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.RealExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.BooleanExpImpl.class);
		ignore.add(anatlyzer.atlext.OCL.impl.OclUndefinedExpImpl.class);
		
	}

	public static void slice(ErrorSlice slice, OclExpression expr) {
		slice(slice, expr, false);
	}
	
	public static void slice(ErrorSlice slice, OclExpression expr, boolean isExternalDependency) {
		addExprType(slice, expr); 

		if ( expr instanceof PropertyCallExp ) {
			PropertyCallExp pc = (PropertyCallExp) expr;
			slice(slice, pc.getSource(), isExternalDependency);
		}
		
		if ( expr instanceof IteratorExp ) {
			IteratorExp it = (IteratorExp) expr; 
			slice(slice, it.getBody(), isExternalDependency);

		} else if ( expr instanceof IterateExp ) {
			IterateExp it = (IterateExp) expr; 
			slice(slice, it.getBody(), isExternalDependency);
			slice(slice, it.getResult().getInitExpression());
		} else if ( expr instanceof OperationCallExp || expr instanceof NavigationOrAttributeCallExp ) {	
			PropertyCallExp pce = null;
			if ( expr instanceof NavigationOrAttributeCallExp ) {
				EStructuralFeature f = (EStructuralFeature) ((PropertyCallExp) expr).getUsedFeature();
				if ( f != null )
					slice.addExplicitFeature(f);
				
				pce = (PropertyCallExp) expr;
			} else if ( expr instanceof OperationCallExp ) {			
				OperationCallExp op = (OperationCallExp) expr;
				for(OclExpression arg : op.getArguments()) {
					slice(slice, arg, isExternalDependency);
				}
				
				pce = op;
			}


			if ( ! pce.isIsStaticCall() ) {
				EList<ContextHelper> resolvers = pce.getDynamicResolvers();
				for (ContextHelper contextHelper : resolvers) {
					if ( slice.addHelper(contextHelper) ) {
						OclExpression body = ATLUtils.getBody(contextHelper);
						slice(slice, body, true);
					}
				}	
			} else {
				if ( pce.getStaticResolver() instanceof StaticHelper ) {
					StaticHelper moduleHelper = (StaticHelper) pce.getStaticResolver();
					if ( slice.addHelper(moduleHelper) ) {
						OclExpression body = ATLUtils.getBody(moduleHelper);
						slice(slice, body, true);						
					}
				}
				// System.out.println("OclSlice - OperationCall not generating static call");
			}
			
		} else if ( expr instanceof CollectionExp ) {
			for(OclExpression arg : ((CollectionExp) expr).getElements()) {
				slice(slice, arg, isExternalDependency);
			}			
		} else if ( expr instanceof IfExp ) {
			IfExp ifExp = (IfExp) expr;
			slice(slice, ifExp.getCondition(), isExternalDependency);
			
			if ( isExternalDependency ) {
				slice(slice, ifExp.getThenExpression(), isExternalDependency);
				slice(slice, ifExp.getElseExpression(), isExternalDependency);
			} else {				
				// Not the branches!!! because the corresponding branch should be done by the
				// corresponding node in the condition graph
				// Well... I'm doing the branches to make this easier. Spourious helpers may be generated...
				slice(slice, ifExp.getThenExpression(), true);
				slice(slice, ifExp.getElseExpression(), true);				
			}
			// slice(slice, ifExp)
		} else if ( expr instanceof LetExp ) {
			LetExp let = (LetExp) expr;
			slice(slice, let.getVariable().getInitExpression(), isExternalDependency);
			slice(slice, let.getIn_(), isExternalDependency);
		} else if ( expr instanceof EnumLiteralExp ) {
			// TODO: NOT SURE IF ENUMLITERAL SHOULD BE PART OF THE SLICE!
		} else if ( ignore.contains(expr.getClass()) ) {
			// Ignore
		} else {
			throw new UnsupportedOperationException(expr.getClass().getName());
		}
	}

	private static void addExprType(ErrorSlice slice, OclExpression expr) {
		if ( expr.getInferredType() instanceof Metaclass ) {
			slice.addExplicitMetaclass((Metaclass) expr.getInferredType());
		}
	}

}
