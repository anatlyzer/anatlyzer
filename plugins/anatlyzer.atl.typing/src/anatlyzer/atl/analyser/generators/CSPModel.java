package anatlyzer.atl.analyser.generators;
import java.util.HashMap;
import java.util.Stack;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.OclGeneratorAST.LazyRuleCallTransformationStrategy;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class CSPModel {

	public static final String THIS_MODULE_CONTEXT_VAR = "thisModule";
	protected ATLModel atlModel;
	protected OclGeneratorAST generator;

	public CSPModel() {
		atlModel = new ATLModel();
		generator = new OclGeneratorAST(atlModel);
	}
	

	public OperationCallExp createAllInstances(Metaclass metaclass) {
		OclModelElement classRef = OCLFactory.eINSTANCE.createOclModelElement();
		classRef.setName(metaclass.getName());
		setInferredType(classRef, metaclass);
		
		OperationCallExp allInstancesCall = OCLFactory.eINSTANCE.createOperationCallExp();
		allInstancesCall.setOperationName("allInstances");
		allInstancesCall.setSource(classRef);
		
		return allInstancesCall;
	}

	public IteratorExp createExists(OclExpression source, String iteratorVarName) {
		IteratorExp exists = OCLFactory.eINSTANCE.createIteratorExp();
		exists.setName("exists");
		exists.setSource(source);
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName(iteratorVarName);
		exists.getIterators().add(it);
		
		return exists;
	}


	public IteratorExp createIterator(OclExpression source, String iteratorName) {
		String iteratorVarName = "i" + genId();
		IteratorExp iterator = OCLFactory.eINSTANCE.createIteratorExp();
		iterator.setName(iteratorName);
		if ( source != null )
			iterator.setSource(source);
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName(iteratorVarName);
		iterator.getIterators().add(it);
		
		return iterator;		
	}
	
	public BooleanExp createBooleanLiteral(boolean b) {
		BooleanExp exp = OCLFactory.eINSTANCE.createBooleanExp();
		exp.setBooleanSymbol(b);
		return exp;
	}

	public IfExp createIfExpression(OclExpression condition, OclExpression trueBranch, OclExpression falseBranch) {
		IfExp exp = OCLFactory.eINSTANCE.createIfExp();
		if ( trueBranch != null )
			exp.setThenExpression(trueBranch);
		if ( falseBranch != null )
			exp.setElseExpression(falseBranch);
		exp.setCondition(condition);
		return exp;
	}


	public OperationCallExp createOperationCall(OclExpression source, String opName) {
		OperationCallExp exp = OCLFactory.eINSTANCE.createOperationCallExp();
		exp.setOperationName(opName);
		exp.setSource(source);
		return exp;
	}
	
	public OperatorCallExp negateExpression(OclExpression source) {
		OperatorCallExp exp = OCLFactory.eINSTANCE.createOperatorCallExp();
		exp.setOperationName("not");
		exp.setSource(source);
		return exp;
	}

	public CollectionOperationCallExp createCollectionOperationCall(OclExpression source, String opName) {
		CollectionOperationCallExp exp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		exp.setOperationName(opName);
		exp.setSource(source);
		return exp;
	}
	
	private int nextId = 1;
	public LetExp createLetScope(OclExpression newVarExpr, OclExpression result, String varName) {
		LetExp let = OCLFactory.eINSTANCE.createLetExp();
		VariableDeclaration vd = OCLFactory.eINSTANCE.createVariableDeclaration();
		
		// This is needed because later I will create a cast over _problem_, and I need to know the type
		// to decide whether to cast
		vd.setInferredType(newVarExpr.getInferredType());
		
		// No type : vd.setType(?)
		vd.setVarName(varName + genId());
		vd.setInitExpression(newVarExpr);
		let.setVariable(vd);
		if ( result != null )
			let.setIn_(result);
		        
		return let;
	}

	private int genId() {
		return nextId++;
	}

	public OperatorCallExp createBinaryOperator(OclExpression expr1, OclExpression expr2, String opName) {
		OperatorCallExp exp = OCLFactory.eINSTANCE.createOperatorCallExp();
		exp.setOperationName(opName);
		exp.setSource(expr1);
		exp.getArguments().add(expr2);
		return exp;		
	}
	
	public OperationCallExp createKindOf(OclExpression receptor, String modelName, String className, Metaclass klass) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("oclIsKindOf");
		op.setSource(receptor);
		
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(className);
		op.getArguments().add(m);

		if ( klass != null ) {
			setInferredType(m, klass);
		}
		
		if ( modelName != null ) {
			OclModel model = OCLFactory.eINSTANCE.createOclModel();
			model.setName(modelName);
			m.setModel(model);
		}
		
		return op;
	}

	public OclExpression createCastTo(VariableDeclaration varToBeCasted, Metaclass castTo, Type originalNoCastedType) {
		VariableExp refToVarDcl = OCLFactory.eINSTANCE.createVariableExp();
		refToVarDcl.setReferredVariable(varToBeCasted);	
		if ( varToBeCasted.getInferredType() != null  ) {
			Metaclass originalClass = (Metaclass) varToBeCasted.getInferredType();
			// This is needed to avoid problems such as the following (in workflow2pn)
			//   binding: input  <- e.input (but in this rule the filter is "e.input.oclIsKindOf(WF!Task)")
			//   resolving rule: "from t  : WF!Task ( t.isInitial )"
			// e.input is implicitly casted to Task, and since t:WF!Task is the same type
			// no cast will be needed
			// In practice we need the cast because USE does not have implicit casting
			if ( originalNoCastedType instanceof Metaclass ) {
				originalClass = (Metaclass) originalNoCastedType;
			}
			
			if ( castTo.getKlass().isSuperTypeOf(originalClass.getKlass())) {
				// No need to cast, and we are not going to cast because USE complains
				return refToVarDcl;
			}
		}
		
		OperationCallExp opCall = createOperationCall(refToVarDcl, "oclAsType");
		
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(castTo.getName());
		setInferredType(m, castTo);
		
		opCall.getArguments().add(m);
		return opCall;
	}
	
	public IteratorExp createThisModuleContext() {
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(Analyser.USE_THIS_MODULE_CLASS);

		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(m);
		
		return createExists(op, THIS_MODULE_CONTEXT_VAR);
	}

	// This is not the ideal way of doing this, because the actual model cannot be set...
	public OclExpression createKindOf_AllInstancesStyle(OclExpression receptor, String modelName, EClass klass) {
		Metaclass m = TypesFactory.eINSTANCE.createMetaclass();
		m.setKlass(klass);
		m.setName(klass.getName());
		return createKindOf_AllInstancesStyle(receptor, modelName, m);
	}
	
	public OclExpression createKindOf_AllInstancesStyle(OclExpression receptor, String modelName, Metaclass klass) {
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(klass.getName());
		setInferredType(m, klass);

		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(m);
		
		CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		colOp.setOperationName("includes");
		colOp.setSource(op);
		colOp.getArguments().add(receptor);
		
		if ( modelName != null ) {
			OclModel model = OCLFactory.eINSTANCE.createOclModel();
			model.setName(modelName);
			m.setModel(model);
		}
		
		return colOp;
	}
	
	public OclExpression gen(OclExpression expr) {
		return generator.gen(expr, scope);
	}
	

	public OclExpression gen(OclExpression expr, OclGeneratorAST.LazyRuleCallTransformationStrategy strategy) {
		LazyRuleCallTransformationStrategy oldStrategy = generator.getLazyRuleStrategy();
		generator.setLazyRuleStrategy(strategy);
		OclExpression value = generator.gen(expr, scope);		
		generator.setLazyRuleStrategy(oldStrategy);
		return value;
	}


	private CSPModelScope scope = null; //new CSPModelScope();
	private Stack<CSPModelScope> previousScopes = new Stack<CSPModelScope>();

	public void addToScope(VariableDeclaration varDcl, VariableDeclaration newVar) {
		if ( scope.containsKey(varDcl) ) 
			throw new IllegalArgumentException("Variable already bound: " + varDcl.getVarName() + " - " + varDcl.getLocation());
		
		scope.put(varDcl, newVar);
	}
	
	public LetExp rebind(VariableDeclaration srcVarDcl) {
		VariableDeclaration tgtVarDcl = scope.getVar(srcVarDcl);		
		LetExp let = createLetScope(createVarRef(tgtVarDcl), null, tgtVarDcl.getVarName() + "_rebind");
		// Assumes that a copy of the scope has been made, so that we can redefine
		scope.put(srcVarDcl, let.getVariable());
		return let;		
	}

	
	public void resetScope() {
		scope.clear();
	}
	
	public void openEmptyScope() {
		previousScopes.push(scope);
		scope = new CSPModelScope(scope.getThisModuleVar());
		// scope.setThisModuleVariable(scope.getThisModuleVar());
	}

	public void copyScope() {
		previousScopes.push(scope);
		scope = new CSPModelScope(scope.getThisModuleVar(), scope);
		// scope.setThisModuleVariable(scope.getThisModuleVar());
	}

	public void closeScope() {
		scope = previousScopes.pop();
	}

	public void setThisModuleVariable(VariableDeclaration thisModule) {
		if ( scope != null )
			throw new IllegalStateException();
		scope = new CSPModelScope(thisModule);
		// scope.setThisModuleVariable(thisModule);
	}

	public VariableDeclaration getThisModuleVariable() {
		return scope.getThisModuleVar();
	}

	/**
	 * For the moment, it uses the thisModule variable...
	 * @return
	 */
	public VariableDeclaration getTargetDummyVariable() {
		return this.getThisModuleVariable();
	}

	public String getTargetDummyClass() {
		return Analyser.USE_THIS_MODULE_CLASS;
	}
	
	public static class CSPModelScope extends HashMap<VariableDeclaration, VariableDeclaration> {
		
		private VariableDeclaration thisModule;

		public CSPModelScope(VariableDeclaration thisModule) {
			this.thisModule = thisModule;			
		}
		
		public CSPModelScope(VariableDeclaration thisModule, CSPModelScope previousScope) {
			this(thisModule);
			this.putAll(previousScope);
		}
		
		/*
		public void setThisModuleVariable(VariableDeclaration thisModule) {
			this.thisModule = thisModule;
		}
		*/

		public CSPModelScope derive() {
			CSPModelScope r = new CSPModel.CSPModelScope(thisModule);
			r.putAll(this);
			// r.setThisModuleVariable(thisModule);
			return r;
		}
		
		public VariableDeclaration getVar(VariableExp expr) {
			return getVar(expr.getReferredVariable(), expr);
		}

		public VariableDeclaration getVar(VariableDeclaration aVar) {
			return getVar(aVar, aVar);
		}

		protected VariableDeclaration getVar(VariableDeclaration aVar, LocatedElement context) {
			VariableDeclaration vd = get( aVar );
			if ( vd == null ) {
				if ( aVar.getVarName().equals("thisModule") && thisModule != null )
					return thisModule;
				
				throw new IllegalStateException("Expected mapping for var " + aVar.getVarName() + " => " + context.getLocation());
			}
			return vd;
		}

		

		public VariableDeclaration getThisModuleVar() {
			if ( thisModule == null ) throw new IllegalStateException();
			return thisModule;
		}

	}

	public static void setInferredType(OclExpression expr, Type t) {
		expr.setInferredType(copy(t));
	}

	public static  Type copy(Type t) {
		return (Type) ATLCopier.copySingleElement(t, true);
	}


	public OclExpression createVarRef(VariableDeclaration vd) {
		VariableExp varref = OCLFactory.eINSTANCE.createVariableExp();
		varref.setReferredVariable(vd);
		return varref;
	}
	
}
