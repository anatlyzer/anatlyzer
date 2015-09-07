package anatlyzer.atl.graph;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;


public class BindingPossiblyUnresolvedNode extends AbstractBindingAssignmentNode<BindingPossiblyUnresolved> implements ProblemNode {

	private Binding	binding;

	public BindingPossiblyUnresolvedNode(BindingPossiblyUnresolved p, Binding binding, ATLModel model) {
		super(p);
		this.binding = binding;
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpression(lp, binding.getValue()) || checkDependenciesAndConstraints(lp);
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpression(exp, binding.getValue()) || checkDependenciesAndConstraints(exp);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
		
		for(ConstraintNode n : constraints) {
			n.genErrorSlice(slice);
		}		
		
		OclSlice.slice(slice, binding.getValue());
		
		// Needed for the error
		/*
		for (EClass c : problem.getProblematicClasses()) {
			slice.addMetaclassNeededInError(c);
		}
		*/

		// New strategy to reduce the number of classes in the slice
		System.out.println(binding.getLocation());
		for (EClass c : problem.getProblematicClasses()) {
			if ( ! problem.getProblematicClassesImplicit().contains(c) ) {
				System.out.println(c.getName());
				slice.addMetaclassNeededInError(c);
			}
		}
		
		EClass c = ErrorSlice.pickClass(problem.getProblematicClassesImplicit());
		if ( c != null ) {
			slice.addMetaclassNeededInError(c);
		}
		
		// Classes whose type appear
		
		
		List<RuleResolutionInfo> resolved = binding.getResolvedBy();
		for (RuleResolutionInfo ruleInfo : resolved) {
			for (MatchedRule mr : ruleInfo.getAllInvolvedRules()) {			
				if ( mr.getInPattern().getFilter() != null ) {
					OclSlice.slice(slice, mr.getInPattern().getFilter());
				}
			}
		}
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "Problem with binding:\\n" + binding.getPropertyName() + " - possibly unresolved" + "\\n" + binding.getLocation(), leadsToExecution);
	}


	@Override
	public OclExpression genCSP(CSPModel model) {
		return genProblemSpecificCondition(model, "and");
	}

	protected OclExpression genProblemSpecificCondition(CSPModel model, String operator) {
		OclExpression result = null;
		List<RuleResolutionInfo> rules = sortRules(binding.getResolvedBy());
		assert(rules.size() > 0);

		OclExpression value = genBindingRightPart(model, binding);		

		if ( TypeUtils.isReference(ATLUtils.getSourceType(binding)) ) {
			result = createReferenceConstraint(model, rules, value, operator);
		} else if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {		
			IteratorExp exists = model.createExists(value, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genAndRules(model, rules, varDcl, operator);
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(ATLUtils.getSourceType(binding))) {
			result = createReferenceConstraint(model, rules, value, operator);
		} else if ( TypeUtils.isUnionWithCollections(ATLUtils.getSourceType(binding)) ) {
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(value, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genAndRules(model, rules, varDcl, operator);
			
			exists.setBody(lastExpr);
			result = exists;
		} else {
			// TODO: For union types with mixed collections and mono-valued elements, Sequence { value }->flatten()
			throw new IllegalStateException(this.binding.getLocation() + " " + TypeUtils.typeToString(ATLUtils.getSourceType(binding)));
		}
		
		return result;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return genProblemSpecificCondition(model, "or");
	}
	
	
	private LetExp createReferenceConstraint(CSPModel model,
			List<RuleResolutionInfo> rules, OclExpression value, String operator) {
		LetExp let = model.createLetScope(value, null, "_problem_");
		VariableDeclaration varDcl = let.getVariable();
		
		OclExpression andRules = genAndRules(model, rules, varDcl, operator);
		
		// => not varDcl.oclIsUndefined()
		// It is necessary to check for undefined because otherwise setting a reference
		// to undefined is a trivial solution to the expression
		VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
		varRef.setReferredVariable(varDcl);
		OperatorCallExp notUndefined = model.negateExpression(model.createOperationCall(varRef, "oclIsUndefined"));
		
		let.setIn_( model.createBinaryOperator(notUndefined, andRules, "and") );
		
		// let.setIn_(andRules);
		
		return let;
	}

	private OclExpression genAndRules(CSPModel model,
			List<RuleResolutionInfo> rules, VariableDeclaration varDcl, String operator) {
		
		OclExpression lastExpr = null;
		for (RuleResolutionInfo info : rules) {
			MatchedRule r = info.getRule();
			
			// => _problem_.oclIsKindOf(ruleFrom)
			VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
			v.setReferredVariable(varDcl);				
			OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, ATLUtils.getInPatternType(info.getRule()));			
			
			// Generate the filter
			OclExpression filter = null;
			if ( r.getInPattern().getFilter() != null ) {
				model.openEmptyScope();

				SimpleInPatternElement simpleElement = (SimpleInPatternElement) r.getInPattern().getElements().get(0);
				
				// => let newVar = _problem_.oclAsType(RuleFrom) in <filter>				
				OperationCallExp casting = model.createCastTo(varDcl, (Metaclass) simpleElement.getInferredType());				
				LetExp let = model.createLetScope(casting, null, simpleElement.getVarName());
					
				// Map the iterator var to the rule variable
				model.addToScope(simpleElement, let.getVariable());
				let.setIn_(model.gen(r.getInPattern().getFilter()));
				
				filter = let;

				model.closeScope();
			} else {
				filter = model.createBooleanLiteral(true);
			}
						
			// Originally it was enough with false
			// BooleanExp lastIfResult = model.createBooleanLiteral(false);
			// But there may be problems with undefined values, so it is better to check that if its undefined 
			// it will become false after the negation of the "if"
			// The alternative is to stick to "false" but check with the regular oclIsKindOf (instead of the AllInstancesStyle)
			VariableExp refToProblem = OCLFactory.eINSTANCE.createVariableExp();
			refToProblem.setReferredVariable(varDcl);
			OclExpression lastIfResult = model.createOperationCall(refToProblem, "oclIsUndefined");
			
			IfExp ifexpr = model.createIfExpression(kindOfCondition, filter, lastIfResult);
			OperatorCallExp negatedIf = model.negateExpression(ifexpr);
			
			if ( lastExpr == null ) {
				lastExpr = negatedIf;
			} else {
				lastExpr = model.createBinaryOperator(lastExpr, negatedIf, operator);
			}
		}
		return lastExpr;
	}

	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		slice.addRule(ATLUtils.getRule(binding));
		for(RuleResolutionInfo info : binding.getResolvedBy()) {
			slice.addRule(info.getRule());			
		}
		
		
		// TODO: Do I need to add the binding somehow??
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
		return ATLUtils.findVariableReference(binding.getValue(), v) != null;
	}
	
	@Override
	public void genIdentification(PathId id) {
		id.next(id.gen(binding.getValue()));
		List<MatchedRule> allRules = binding.getResolvedBy().stream().flatMap(rri -> rri.getAllInvolvedRules().stream()).collect(Collectors.toList());
		PathId.sortRules(allRules);
		
		String s = allRules.stream().map(r -> MatchedRuleExecution.ruleSignature(r, id)).collect(Collectors.joining(";"));
		id.next(s);
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
}
