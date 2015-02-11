package anatlyzer.atl.analyser.generators;
import java.util.HashMap;
import java.util.Stack;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.generators.OclGeneratorAST.LazyRuleCallTransformationStrategy;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
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
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class CSPModel {

	private ATLModel atlModel;
	private OclGeneratorAST generator;

	public CSPModel() {
		atlModel = new ATLModel();
		generator = new OclGeneratorAST(atlModel);
	}
	

	public OperationCallExp createAllInstances(Metaclass metaclass) {
		OclModelElement classRef = OCLFactory.eINSTANCE.createOclModelElement();
		classRef.setName(metaclass.getName());

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
	
	public OperationCallExp createKindOf(OclExpression receptor, String modelName, String className) {
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("oclIsKindOf");
		op.setSource(receptor);
		
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(className);
		op.getArguments().add(m);
		
		if ( modelName != null ) {
			OclModel model = OCLFactory.eINSTANCE.createOclModel();
			model.setName(modelName);
			m.setModel(model);
		}
		
		return op;
	}

	public OperationCallExp createCastTo(VariableDeclaration varToBeCasted, String className) {
		VariableExp refToVarDcl = OCLFactory.eINSTANCE.createVariableExp();
		refToVarDcl.setReferredVariable(varToBeCasted);	
				
		OperationCallExp opCall = createOperationCall(refToVarDcl, "oclAsType");
		
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(className);
		
		opCall.getArguments().add(m);
		return opCall;
	}
	
	public IteratorExp createThisModuleContext() {
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(Analyser.USE_THIS_MODULE_CLASS);

		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(m);
		
		return createExists(op, "thisModule");
	}
	
	
	public OclExpression createKindOf_AllInstancesStyle(OclExpression receptor, String modelName, String className) {
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		m.setName(className);

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
	
	public void resetScope() {
		scope.clear();
	}
	
	public void openEmptyScope() {
		previousScopes.push(scope);
		scope = new CSPModelScope(scope.getThisModuleVar());
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
	
	public static class CSPModelScope extends HashMap<VariableDeclaration, VariableDeclaration> {
		
		private VariableDeclaration thisModule;

		public CSPModelScope(VariableDeclaration thisModule) {
			this.thisModule = thisModule;			
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
			VariableDeclaration vd = get( expr.getReferredVariable());
			if ( vd == null ) {
				if ( expr.getReferredVariable().getVarName().equals("thisModule") && thisModule != null )
					return thisModule;
				
				throw new IllegalStateException("Expected mapping for var " + expr.getReferredVariable().getVarName() + " => " + expr.getLocation());
			}
			return vd;
		}

		public VariableDeclaration getThisModuleVar() {
			if ( thisModule == null ) throw new IllegalStateException();
			return thisModule;
		}

	}

	
}
