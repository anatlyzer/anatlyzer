package anatlyzer.atl.graph;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class BindingWithResolvedByIncompatibleRuleNode extends AbstractBindingAssignmentNode<BindingWithResolvedByIncompatibleRule> implements ProblemNode {

	private Binding	binding;

	public BindingWithResolvedByIncompatibleRuleNode(BindingWithResolvedByIncompatibleRule p, Binding binding, ATLModel model) {
		super(p);
		this.binding = binding;
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
		for(ResolvedRuleInfo rinfo : problem.getRules()) {
			for(EObject rule : rinfo.getAllInvolvedRules()) {
				MatchedRule mr = (MatchedRule) rule;
				if ( mr.getInPattern().getFilter() != null ) {
					OclSlice.slice(slice, mr.getInPattern().getFilter());
				}
			}
		}
		
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
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, "Problem with binding:\\n" + binding.getPropertyName() + " - resolved with invalid target" + "\\n" + binding.getLocation(), leadsToExecution);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genTransformationSlice(slice);
		}		
		
		this.getConstraint().genTransformationSlice(slice);
	}
	
	@Override
	public OclExpression genCSP(CSPModel model) {
		return genProblemSpecificCondition(model);
	}

	protected OclExpression genProblemSpecificCondition(CSPModel model) {
		OclExpression result = null;
		EList<ResolvedRuleInfo> rules = problem.getRules();
		assert(rules.size() > 0);
		
		OclExpression value = genBindingRightPart(model, binding);		
		if ( TypeUtils.isReference(ATLUtils.getSourceType(binding) )) {
			result = createReferenceConstraint(model, rules, value);
		} else if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {
			IteratorExp exists = model.createExists(value, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl);
			
			// lastIf.setElseExpression(model.createBooleanLiteral(false));
			// exists.setBody(lastIf);
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(ATLUtils.getSourceType(binding))) {
			result = createReferenceConstraint(model, rules, value);	
		} else if ( TypeUtils.isUnionWithCollections(ATLUtils.getSourceType(binding)) ) {
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(value, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl);
			
			exists.setBody(lastExpr);
			result = exists;
		} else if ( ATLUtils.getSourceType(binding) instanceof UnionType ) {
			// This is purely speculatively... just to try... should be signaled somehow
			SequenceExp seq = OCLFactory.eINSTANCE.createSequenceExp();
			seq.getElements().add(value);
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(seq, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl);
			
			exists.setBody(lastExpr);
			result = exists;
		} else {
			throw new IllegalStateException(TypeUtils.typeToString(ATLUtils.getSourceType(binding)));
		}
		
		return result;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return genProblemSpecificCondition(model);
	}
	
	private LetExp createReferenceConstraint(CSPModel model,
			EList<ResolvedRuleInfo> rules, OclExpression value) {
		LetExp let = model.createLetScope(value, null, "_problem_");
		VariableDeclaration varDcl = let.getVariable();
		let.setIn_( genOrRules(model, rules, varDcl));
		return let;
	}

	private OclExpression genOrRules(CSPModel model, EList<ResolvedRuleInfo> rules, VariableDeclaration varDcl) {
		OclExpression lastExpr = null;
		for (ResolvedRuleInfo rule : rules) {
			MatchedRule r = (MatchedRule) rule.getElement();				
			IfExp ifexpr = genCondition(model, varDcl, rule, r);
			if ( lastExpr == null ) {
				lastExpr = ifexpr;
			} else {
				lastExpr = model.createBinaryOperator(lastExpr, ifexpr, "or");
			}

			/*
			if ( lastIf == null ) {
				lastIf = ifexpr;
			} else {
				ifexpr.setElseExpression(lastIf);
				lastIf = ifexpr;
			}
			*/
		}
		return lastExpr;
	}

	private IfExp genCondition(CSPModel model, VariableDeclaration varDcl,
			ResolvedRuleInfo rule, MatchedRule r) {
		// => _problem_.oclIsKindOf(ruleFrom)
		VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
		v.setReferredVariable(varDcl);				
		OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, rule.getInputType().getName());
		
		// Generate the filter
		OclExpression filter = null;
		if ( r.getInPattern().getFilter() != null ) {
			model.openEmptyScope();
			
			SimpleInPatternElement simpleElement = (SimpleInPatternElement) r.getInPattern().getElements().get(0);
			
			// => let newVar = _problem_.oclAsType(RuleFrom) in <filter>				
			OperationCallExp casting = model.createCastTo(varDcl, simpleElement.getType().getName());				
			LetExp let = model.createLetScope(casting, null, simpleElement.getVarName());
				
			// Map the iterator var to the rule variable
			model.addToScope(simpleElement, let.getVariable());
			let.setIn_(model.gen(r.getInPattern().getFilter()));
			
			filter = let;
			
			/*
			// Map the iterator var to the rule variable
			model.addToScope((SimpleInPatternElement) r.getInPattern().getElements().get(0), varDcl);
			
			filter = model.gen(r.getInPattern().getFilter());
			*/
			
			model.closeScope();
		} else {
			filter = model.createBooleanLiteral(true);
		}
		
		IfExp ifexpr = model.createIfExpression(kindOfCondition, filter, model.createBooleanLiteral(false));
		return ifexpr;
	}

	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
		return ATLUtils.findVariableReference(binding.getValue(), v) != null;
	}
		
	
}
