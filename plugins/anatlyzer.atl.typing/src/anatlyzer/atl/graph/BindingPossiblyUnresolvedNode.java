package anatlyzer.atl.graph;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
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
	private ATLModel atlModel;

	public BindingPossiblyUnresolvedNode(BindingPossiblyUnresolved p, Binding binding, ATLModel model) {
		super(p);
		this.binding = binding;
		this.atlModel   = model;
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
		for (EClass c : problem.getProblematicClasses()) {
			slice.addMetaclassNeededInError(c);
		}
		
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
		OclExpression result = null;
		EList<RuleResolutionInfo> rules = binding.getResolvedBy();
		assert(rules.size() > 0);
		
		OclExpression value = genBindingRightPart(model, binding);		
		if ( TypeUtils.isReference(ATLUtils.getSourceType(binding)) ) {
			result = createReferenceConstraint(model, rules, value);
		} else if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {
			IteratorExp exists = model.createExists(value, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genAndRules(model, rules, varDcl);
			
			// lastIf.setElseExpression(model.createBooleanLiteral(false));
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(ATLUtils.getSourceType(binding))) {
			result = createReferenceConstraint(model, rules, value);
		} else {
			// TODO: For union types with mixed collections and mono-valued elements, Sequence { value }->flatten()
			throw new IllegalStateException(this.binding.getLocation() + " " + TypeUtils.typeToString(ATLUtils.getSourceType(binding)));
		}
		
		return result;
	}

	private LetExp createReferenceConstraint(CSPModel model,
			EList<RuleResolutionInfo> rules, OclExpression value) {
		LetExp let = model.createLetScope(value, null, "_problem_");
		VariableDeclaration varDcl = let.getVariable();
		
		OclExpression andRules = genAndRules(model, rules, varDcl);
		
		// => not varDcl.oclIsUndefined()
		VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
		varRef.setReferredVariable(varDcl);
		OperatorCallExp notUndefined = model.negateExpression(model.createOperationCall(varRef, "oclIsUndefined"));
		
		let.setIn_( model.createBinaryOperator(notUndefined, andRules, "and") );
		return let;
	}

	private OclExpression genAndRules(CSPModel model,
			EList<RuleResolutionInfo> rules, VariableDeclaration varDcl) {
		
		OclExpression lastExpr = null;
		for (RuleResolutionInfo info : rules) {
			MatchedRule r = info.getRule();
			
			// => _problem_.oclIsKindOf(ruleFrom)
			VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
			v.setReferredVariable(varDcl);				
			OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, ATLUtils.getInPatternType(info.getRule()).getName());			
			
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

				model.closeScope();
			} else {
				filter = model.createBooleanLiteral(true);
			}
			
			IfExp ifexpr = model.createIfExpression(kindOfCondition, filter, model.createBooleanLiteral(false));
			OperatorCallExp negatedIf = model.negateExpression(ifexpr);
			
			if ( lastExpr == null ) {
				lastExpr = negatedIf;
			} else {
				lastExpr = model.createBinaryOperator(lastExpr, negatedIf, "and");
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
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
		return ATLUtils.findVariableReference(binding.getValue(), v) != null;
	}
	
}
