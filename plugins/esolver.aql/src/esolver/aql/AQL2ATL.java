package esolver.aql;

import java.util.function.Supplier;

import org.eclipse.acceleo.query.ast.And;
import org.eclipse.acceleo.query.ast.Call;
import org.eclipse.acceleo.query.ast.CallType;
import org.eclipse.acceleo.query.ast.ErrorEClassifierTypeLiteral;
import org.eclipse.acceleo.query.ast.Expression;
import org.eclipse.acceleo.query.ast.StringLiteral;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.ast.VarRef;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.acceleo.query.validation.type.EClassifierLiteralType;
import org.eclipse.emf.common.util.EList;

import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.OCL2.OCL2Factory;
import anatlyzer.atlext.OCL2.SelectByKind;

public class AQL2ATL {

	private Supplier<OclExpression> selfReferenceGenerator;

	public AQL2ATL bindSelf(Supplier<OclExpression> selfReferenceGenerator) {
		this.selfReferenceGenerator = selfReferenceGenerator;
		return this;
	}
	
	public OclExpression toExpression(String expression) {
		if ( expression.startsWith("aql:") ) {
			expression = expression.replaceAll("^aql:", "");
		}
		
		System.out.println("Processing " + expression);
		IQueryEnvironment queryEnvironment = Query.newEnvironment();

		QueryBuilderEngine builder = new QueryBuilderEngine(queryEnvironment);
		AstResult astResult = builder.build(expression);
		return toExpression(astResult);
	}

	private OclExpression toExpression(AstResult astResult) {
		Expression ast = astResult.getAst();
		return toExpression(ast);
	}

	private OclExpression toExpression(Expression expr) {
		if ( expr instanceof Call) {
			Call call = (Call) expr;
			if ( call.getType() == CallType.CALLORAPPLY && "aqlFeatureAccess".equals(call.getServiceName())) {
				Expression source = call.getArguments().get(0);
				StringLiteral name = (StringLiteral) call.getArguments().get(1);
				
				NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				nav.setName(name.getValue());
				nav.setSource(toExpression(source));
				nav.getAnnotations().put("DO_NOT_ADD_THIS_MODULE", "true");
				
				return nav;
			} else if ( call.getType() == CallType.CALLSERVICE && "not".equals(call.getServiceName())) {
				OperatorCallExp operator = OCLFactory.eINSTANCE.createOperatorCallExp();
				operator.setOperationName("not");
				operator.setSource(toExpression(call.getArguments().get(0)));
				return operator;
			} else if ( call.getType() == CallType.COLLECTIONCALL && "filter".equals(call.getServiceName())) {
				SelectByKind selectByKind = OCL2Factory.eINSTANCE.createSelectByKind();
				
				selectByKind.getArguments().add(toExpression(call.getArguments().get(1)));
				selectByKind.setOperationName("selectByKind");
				selectByKind.setSource(toExpression(call.getArguments().get(0)));
				return selectByKind;
			} 
		} else if ( expr instanceof VarRef ) {
			VarRef ref = (VarRef) expr;
			if ( "self".equals(ref.getVariableName()) ) {
				if ( selfReferenceGenerator == null ) {
					VariableDeclaration selfVariable = OCLFactory.eINSTANCE.createVariableDeclaration();
					selfVariable.setVarName("self");
					VariableExp varExp = OCLFactory.eINSTANCE.createVariableExp();
					varExp.setReferredVariable(selfVariable);
					return varExp;
				} else {
					return selfReferenceGenerator.get();
				}
			}			
		} else if ( expr instanceof TypeLiteral ) {
			// This is an heuristic, to try to recover
			if ( expr instanceof ErrorEClassifierTypeLiteral ) {
				ErrorEClassifierTypeLiteral error = (ErrorEClassifierTypeLiteral) expr;
				EList<String> segments = error.getSegments();
				if ( segments.size() != 2 ) {
					throw new UnsupportedOperationException("Only two segments supported " + segments);					
				}
				OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
				me.setName(segments.get(1));
				OclModel m = OCLFactory.eINSTANCE.createOclModel();
				m.setName(segments.get(0));
				me.setModel(m);
				return me;
			}
		}
		
		// Could also be handled as part of Call
		if ( expr instanceof org.eclipse.acceleo.query.ast.And ) {
			// System.out.println(expr);
			OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
			op.setOperationName("and");
			op.setSource(toExpression(((And) expr).getArguments().get(0)));
			op.getArguments().add(toExpression(((And) expr).getArguments().get(1)));
			return op;
		}
		
		throw new UnsupportedOperationException("Can't handle " + expr);
	}

}
