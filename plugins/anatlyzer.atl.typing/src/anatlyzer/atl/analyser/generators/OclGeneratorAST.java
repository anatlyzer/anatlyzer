package anatlyzer.atl.analyser.generators;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.CSPModel.CSPModelScope;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
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

	private ATLModel atlModel;
	private LazyRuleCallTransformationStrategy lazyRuleStrategy = new LazyRuleNotSupported();
	
	public OclGeneratorAST(ATLModel atlModel) {
		this.atlModel = atlModel;
	}

	public OclExpression gen(OclExpression expr, CSPModel.CSPModelScope vars) {
		OclExpression copied = genAux(expr, vars);
		copied.setInferredType( expr.getInferredType() );
		copied.setImplicitlyCasted( expr.isImplicitlyCasted() );
		return copied;
	}
	
	private OclExpression genAux(OclExpression expr, CSPModel.CSPModelScope vars) {
		if (expr instanceof PropertyCallExp) {
			return genPropertyCall(expr, vars);
		} else if (expr instanceof VariableExp) {
			VariableExp varT = OCLFactory.eINSTANCE.createVariableExp();
			
			varT.setReferredVariable( vars.getVar((VariableExp) expr) );
			
			return varT;
		
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
			// Enumerations are converted to integers
			EEnumLiteral literal = TypeUtils.getLiteralOf((EnumLiteralExp) expr);
			IntegerExp tgt = OCLFactory.eINSTANCE.createIntegerExp();
			tgt.setIntegerSymbol(literal.getValue());

			/*
			EnumLiteralExp enuml = (EnumLiteralExp) expr;
			EnumLiteralExp tgt = atlModel.create(EnumLiteralExp.class);
			tgt.setName(enuml.getName());
			*/
			return tgt;
		} else if ( expr instanceof OclUndefinedExp ) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
		} else if ( expr instanceof OclType ) {
			return genType((OclType) expr, vars);
		} else {
			throw new UnsupportedOperationException(expr.toString());
		}
	}


	private OclExpression genPropertyCall(OclExpression expr, CSPModel.CSPModelScope vars) {
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
			
			if ( navS.getUsedFeature() == null ) {
				navT =  OCLFactory.eINSTANCE.createOperationCallExp();
				((OperationCallExp) navT).setOperationName(navS.getName());
			
				// Pass thisModule
				VariableExp thisModuleRef = OCLFactory.eINSTANCE.createVariableExp();
				thisModuleRef.setReferredVariable(vars.getThisModuleVar());
				((OperationCallExp) navT).getArguments().add(thisModuleRef);
			} else {
				navT = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				((NavigationOrAttributeCallExp) navT).setName(navS.getName());
			}
			
			navT.setSource(receptor);
			return navT;
		} else if (expr instanceof CollectionOperationCallExp) {
			CollectionOperationCallExp navS = (CollectionOperationCallExp) expr;
			CollectionOperationCallExp navT = OCLFactory.eINSTANCE.createCollectionOperationCallExp();

			navT.setSource(receptor);
			navT.setOperationName(navS.getOperationName());
						
			for(OclExpression arg: genArgs(navS.getArguments(), vars )) navT.getArguments().add(arg);
		
			return navT;
		} else if (expr instanceof OperationCallExp) {
			OperationCallExp navS = (OperationCallExp) expr;

			// CallExprAnn ann = (CallExprAnn) typ.getAnnotation(navS.original_());
			if ( navS.getStaticResolver() instanceof StaticRule ) {
				return lazyRuleStrategy.gen(atlModel, navS);
			} 
			
			OperationCallExp navT = OCLFactory.eINSTANCE.createOperationCallExp();

			if ( navS.getStaticResolver() != null ) { // It is not a built-in function
				VariableExp thisModuleRef =  OCLFactory.eINSTANCE.createVariableExp();
				
				thisModuleRef.setReferredVariable(vars.getThisModuleVar());
				navT.getArguments().add(thisModuleRef);
			}
			
			
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
			vars = vars.derive();
			vars.put(src.getIterators().get(0), itTgt);
			tgt.setBody( gen(src.getBody(), vars));
			
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

			vars = vars.derive();
			vars.put(src.getIterators().get(0), itTgt);
			vars.put(src.getResult(), result);
			tgt.setBody( gen(src.getBody(), vars));
			
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
		public OclExpression gen(ATLModel model, OperationCallExp opCall) {
			throw new UnsupportedOperationException(opCall.getLocation());
		}
	}

	public static abstract class LazyRuleCallTransformationStrategy {
		public abstract OclExpression gen(ATLModel model, OperationCallExp opCall);
	}

	public static class LazyRuleToOclUndefined extends LazyRuleCallTransformationStrategy {
		@Override
		public OclExpression gen(ATLModel model, OperationCallExp opCall) {
			return OCLFactory.eINSTANCE.createOclUndefinedExp();
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
