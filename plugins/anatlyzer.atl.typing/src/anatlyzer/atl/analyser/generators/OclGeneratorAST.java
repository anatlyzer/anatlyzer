package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.CSPModel.CSPModelScope;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.CollectionType;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.StringType;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This class provides a "gen" method that takes an OclExpression,
 * typically in the ATL/OCL format and generates an expression in
 * the USE/OCL format.
 * 
 * @author jesus
 *
 */
public class OclGeneratorAST {

	protected ATLModel atlModel;
	protected LazyRuleCallTransformationStrategy lazyRuleStrategy = new LazyRuleNotSupported();
	
	public OclGeneratorAST(ATLModel atlModel) {
		this.atlModel = atlModel;
	}

	public OclExpression gen(OclExpression expr, CSPModel.CSPModelScope vars) {
		OclExpression copied = genAux(expr, vars);
		copied.setLocation(expr.getLocation());
		copied.getProblems().addAll(expr.getProblems());

		// copied.setInferredType( expr.getInferredType() );
		CSPModel.setInferredType(copied, expr.getInferredType());
		
		copied.setImplicitlyCasted( expr.isImplicitlyCasted() );
		copied.setNoCastedType(expr.getNoCastedType());
		return copied;
	}
	
	private OclExpression genAux(OclExpression expr, CSPModel.CSPModelScope vars) {
		if (expr instanceof PropertyCallExp) {
			return genPropertyCall(expr, vars);
		} else if (expr instanceof VariableExp) {
			return resolveVariableExp((VariableExp) expr, vars);
		
		} else if (expr instanceof IntegerExp) {
			IntegerExp lit = OCLFactory.eINSTANCE.createIntegerExp();
			lit.setIntegerSymbol(((IntegerExp) expr).getIntegerSymbol());
			return lit;
		} else if (expr instanceof StringExp) {
			StringExp lit = OCLFactory.eINSTANCE.createStringExp();
			lit.setStringSymbol(((StringExp) expr).getStringSymbol());
			return lit;
		} else if ( expr instanceof BooleanExp ) {
			BooleanExp lit = OCLFactory.eINSTANCE.createBooleanExp();
			lit.setBooleanSymbol(((BooleanExp) expr).isBooleanSymbol());
			return lit;
		} else if ( expr instanceof OclModelElement ) {
			OclModelElement src = (OclModelElement) expr;
			OclModelElement tgt = OCLFactory.eINSTANCE.createOclModelElement();

			// Probably it should not always be copied... but for the moment...
			OclModel tgtModel = OCLFactory.eINSTANCE.createOclModel();
			tgtModel.setName( src.getModel().getName() );
			
			atlModel.add(tgtModel);
			
			tgt.setName( src.getName() );
			tgt.setModel( tgtModel );
			
			return tgt;
		} else if ( expr instanceof IfExp ) {
			IfExp ifexp = (IfExp) expr;
			IfExp tgt   = OCLFactory.eINSTANCE.createIfExp();
			tgt.setCondition(gen(ifexp.getCondition(), vars));
			tgt.setThenExpression(gen(ifexp.getThenExpression(), vars));
			tgt.setElseExpression(gen(ifexp.getElseExpression(), vars));
			
			return tgt;
		} else if ( expr instanceof LetExp ) {
			LetExp src = (LetExp) expr;
			LetExp tgt = OCLFactory.eINSTANCE.createLetExp();

			VariableDeclaration vd = OCLFactory.eINSTANCE.createVariableDeclaration();
			vd.setVarName(src.getVariable().getVarName());
			vd.setInitExpression(gen(src.getVariable().getInitExpression(), vars));

			tgt.setVariable(vd);

			// TODO: This is a very nasty side effect, but it is needed to make it visible
			// to subsequent calls of gen that need this variable mapped!
			vars.put(src.getVariable(), tgt.getVariable());
			
			tgt.setIn_( gen(src.getIn_(), vars) );
			
			return tgt;			
		} else if ( expr instanceof CollectionExp ) {
			CollectionExp col = (CollectionExp) expr;
			CollectionExp tgt = (CollectionExp) EcoreUtil.create(col.eClass());
			
			for(int i = 0; i < col.getElements().size(); i++) {
				tgt.getElements().add(gen( col.getElements().get(i), vars));
			}
			
			return tgt;
		} else if ( expr instanceof EnumLiteralExp ) {
			return translateEnumLiteral((EnumLiteralExp) expr);
		} else if ( expr instanceof OclUndefinedExp ) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
		} else if ( expr instanceof OclType ) {
			return genType((OclType) expr, vars);
		} else {
			if ( expr instanceof TupleExp ) {
				// I believe this is not supported by USE. Could be adapted, but meanwhile I am just going
				// to copy and let USEValidityChecker to reject the expression
				return OCLFactory.eINSTANCE.createTupleExp();
			}
			
			throw new UnsupportedOperationException(expr.toString());
		}
	}

	protected OclExpression translateEnumLiteral(EnumLiteralExp expr) {
		EnumLiteralExp enuml = (EnumLiteralExp) expr;
		EnumLiteralExp tgt = OCLFactory.eINSTANCE.createEnumLiteralExp();
		tgt.setName(enuml.getName());
		return tgt;
	}

	protected OclExpression resolveVariableExp(VariableExp expr,
			CSPModel.CSPModelScope vars) {
		VariableExp varT = OCLFactory.eINSTANCE.createVariableExp();
		
		varT.setReferredVariable( vars.getVar(expr) );
		
		return varT;
	}

	private OclExpression genPropertyCall(OclExpression expr, final CSPModel.CSPModelScope vars) {
		OclExpression receptor = gen(((PropertyCallExp) expr).getSource(), vars);
		if (expr instanceof OperatorCallExp) {
			OperatorCallExp opS  = (OperatorCallExp) expr;
			OperatorCallExp opT =  OCLFactory.eINSTANCE.createOperatorCallExp();
			
			opT.setSource(receptor);
			opT.setOperationName( opS.getOperationName() );
			for(OclExpression arg: genArgs(opS.getArguments(), vars )) opT.getArguments().add(arg);
		
			return opT;
		} else if (expr instanceof NavigationOrAttributeCallExp) {
			NavigationOrAttributeCallExp navS = (NavigationOrAttributeCallExp) expr;
			PropertyCallExp navT = null;
	
			/*
			// TODO. Is this a retyping???
			if ( navS.getUsedFeature() == null ) {
				navT =  OCLFactory.eINSTANCE.createOperationCallExp();
				((OperationCallExp) navT).setOperationName(navS.getName());
			
				// Pass thisModule
				VariableExp thisModuleRef = OCLFactory.eINSTANCE.createVariableExp();
				thisModuleRef.setReferredVariable(vars.getThisModuleVar());
				((OperationCallExp) navT).getArguments().add(thisModuleRef);
			} else {
				navT = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				navT.setUsedFeature(navS.getUsedFeature());
				((NavigationOrAttributeCallExp) navT).setName(navS.getName());
			}
			*/
			// Moved to a retyping, so just copying
			navT = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			navT.setUsedFeature(navS.getUsedFeature());
			((NavigationOrAttributeCallExp) navT).setName(navS.getName());
			
			navT.setStaticResolver(((PropertyCallExp) expr).getStaticResolver());
			navT.setSource(receptor);
			return navT;
		} else if (expr instanceof CollectionOperationCallExp) {
			CollectionOperationCallExp navS = (CollectionOperationCallExp) expr;
			CollectionOperationCallExp navT = OCLFactory.eINSTANCE.createCollectionOperationCallExp();

			navT.setSource(receptor);
			navT.setOperationName(navS.getOperationName());
						
			for(OclExpression arg: genArgs(navS.getArguments(), vars )) navT.getArguments().add(arg);
		
			// This is needed because in ATL it is valid to use "->" for normal operations
			navT.setStaticResolver(((PropertyCallExp) expr).getStaticResolver());
			
			return navT;
		} else if (expr instanceof OperationCallExp) {
			OperationCallExp navS = (OperationCallExp) expr;

			// CallExprAnn ann = (CallExprAnn) typ.getAnnotation(navS.original_());
			if ( navS.getStaticResolver() instanceof StaticRule ) {
				return lazyRuleStrategy.gen(atlModel, navS, (exp) -> gen(exp, vars));
			} 
			if ( navS.getOperationName().equals("resolveTemp") ) {
				// If it is within a collect, the collect could be removed...
				return lazyRuleStrategy.gen(atlModel, navS, (exp) -> gen(exp, vars));				
			}
			
			OperationCallExp navT = OCLFactory.eINSTANCE.createOperationCallExp();

			// Moved to a retyping
			/*
			if ( navS.getStaticResolver() != null ) { // It is not a built-in function
				VariableExp thisModuleRef =  OCLFactory.eINSTANCE.createVariableExp();
				
				thisModuleRef.setReferredVariable(vars.getThisModuleVar());
				navT.getArguments().add(thisModuleRef);
			}
			*/
			
			navT.setStaticResolver(((PropertyCallExp) expr).getStaticResolver());			
			navT.setSource(receptor);
			navT.setOperationName(navS.getOperationName());
			for(OclExpression arg: genArgs(navS.getArguments(), vars )) 
				navT.getArguments().add(arg);
			
			return navT;			
		} else if ( expr instanceof IteratorExp ) {
			IteratorExp src = (IteratorExp) expr;
			IteratorExp tgt = OCLFactory.eINSTANCE.createIteratorExp();
			
			tgt.setSource( gen(src.getSource(), vars) );
			tgt.setName(src.getName());
			Iterator itTgt = OCLFactory.eINSTANCE.createIterator();
			itTgt.setVarName( src.getIterators().get(0).getVarName() );
			if ( src.getIterators().get(0).getType() != null )
				itTgt.setType( genType(src.getIterators().get(0).getType(), vars) );
			
			tgt.getIterators().add( itTgt );

			// Create a new var for the body
			CSPModelScope bodyVars = vars.derive();
			bodyVars.put(src.getIterators().get(0), itTgt);
			tgt.setBody( gen(src.getBody(), bodyVars));
			
			return tgt;
		} else if ( expr instanceof IterateExp ) {
			IterateExp src = (IterateExp) expr;
			IterateExp tgt = OCLFactory.eINSTANCE.createIterateExp();
						
			tgt.setSource( gen(src.getSource(), vars) );
			
			Iterator itTgt = OCLFactory.eINSTANCE.createIterator();
			itTgt.setVarName( src.getIterators().get(0).getVarName() );
			if ( src.getIterators().get(0).getType() != null )
				itTgt.setType( genType(src.getIterators().get(0).getType(), vars) );
			
			tgt.getIterators().add( itTgt );
			
			// Create a new var for the body
			VariableDeclaration result = OCLFactory.eINSTANCE.createVariableDeclaration();
			result.setVarName(src.getResult().getVarName());
			result.setInitExpression( gen(src.getResult().getInitExpression(), vars) );			
			result.setType((OclType) gen(src.getResult().getType(), vars));
			tgt.setResult( result );

			CSPModelScope bodyVars = vars.derive();
			bodyVars.put(src.getIterators().get(0), itTgt);
			bodyVars.put(src.getResult(), result);
			tgt.setBody( gen(src.getBody(), bodyVars));
			
			return tgt;			
		}

		throw new UnsupportedOperationException(expr.toString());
	}

	private OclType genType(OclType src, CSPModelScope vars) {
		if ( src instanceof SetType ) {
			SetType tgt = OCLFactory.eINSTANCE.createSetType();
			tgt.setElementType((OclType) gen(((CollectionType) src).getElementType(), vars));
			return tgt;
		} else if ( src instanceof SequenceType ) {
			SequenceType tgt = OCLFactory.eINSTANCE.createSequenceType();
			tgt.setElementType((OclType) gen(((CollectionType) src).getElementType(), vars));
			return tgt;			
		} else if ( src instanceof IntegerType ) {
			return OCLFactory.eINSTANCE.createIntegerType();
		} else if ( src instanceof StringType ) {
			return OCLFactory.eINSTANCE.createStringType();
		}
		
		throw new UnsupportedOperationException(src.getClass().getName());
	}

	private String translateName(OperationCallExp call) {
		/*
		String name = call.getOperationName();
		if ( name.equals("oclIsUndefined") ) return "isUndefined";
		return name;
		*/
		throw new UnsupportedOperationException();
	}

	private List<? extends OclExpression> genArgs(List<OclExpression> arguments, CSPModel.CSPModelScope vars) {
		ArrayList<OclExpression> result = new ArrayList<OclExpression>();
		for(int i = 0; i < arguments.size(); i++) {
			result.add( gen(arguments.get(i), vars) );
		}
		return result;
	}


	public static class LazyRuleNotSupported extends LazyRuleCallTransformationStrategy {
		@Override
		public OclExpression gen(ATLModel model, OperationCallExp opCall, Function<OclExpression, OclExpression> generator) {
			throw new UnsupportedOperationException(opCall.getLocation());
		}
	}

	public static abstract class LazyRuleCallTransformationStrategy {
		public abstract OclExpression gen(ATLModel model, OperationCallExp opCall, Function<OclExpression, OclExpression> generator);
	}

	public static class LazyRuleToOclUndefined extends LazyRuleCallTransformationStrategy {
		@Override
		public OclExpression gen(ATLModel model, OperationCallExp opCall, Function<OclExpression, OclExpression> generator) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
		}
	}

	public static class LazyRuleToDummyValue extends LazyRuleCallTransformationStrategy {
		private VariableDeclaration dummyDcl;

		public LazyRuleToDummyValue(VariableDeclaration dummyDcl) {
			this.dummyDcl = dummyDcl;
		}
		
		@Override
		public OclExpression gen(ATLModel model, OperationCallExp opCall, Function<OclExpression, OclExpression> generator) {
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(dummyDcl);
			return varRef;
		}
	}

	public LazyRuleCallTransformationStrategy getLazyRuleStrategy() {
		return lazyRuleStrategy;
	}
	
	public void setLazyRuleStrategy(
			LazyRuleCallTransformationStrategy lazyRuleStrategy) {
		this.lazyRuleStrategy = lazyRuleStrategy;
	}

	

}
