package anatlyzer.atl.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.ResolvedRuleInfo;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ClassPicker;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class BindingWithResolvedByIncompatibleRuleNode extends AbstractBindingAssignmentNode<BindingWithResolvedByIncompatibleRule> implements ProblemNode {

	private Binding	binding;
	private IAnalyserResult analyser;

	public BindingWithResolvedByIncompatibleRuleNode(BindingWithResolvedByIncompatibleRule p, Binding binding, IAnalyserResult analyser) {
		super(p);
		this.analyser = analyser;
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
		
		Set<EClass> types = ClassPicker.treatOclIsKindOf_pickSubclassSibling(problem, analyser.getNamespaces());
		types.forEach(sub -> slice.addMetaclassNeededInError(sub));
	}
	
	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		return problemInExpressionCached(lp, binding.getValue()) || checkDependenciesAndConstraints(lp);
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		return expressionInExpressionCached(exp, binding.getValue()) || checkDependenciesAndConstraints(exp);
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
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		// List<RuleResolutionInfo> rules = sortRules(binding.getResolvedBy());
		EList<ResolvedRuleInfo> rules = problem.getRules();

		OclExpression bindingValue = binding.getValue();
		
		if ( isFirstSpecialCase(bindingValue) ) {
			bindingValue = ((OperationCallExp)bindingValue).getSource();
		}
		
		OclExpression value = genValueRightPart(model, bindingValue);		
		return genProblemSpecificCondition(model, rules, bindingValue, value);		
	}

	protected static OclExpression genProblemSpecificCondition(CSPModel model, List<ResolvedRuleInfo> rules, OclExpression originalValue, OclExpression genValue) {

	// protected static OclExpression genProblemSpecificCondition(CSPModel model) {
		OclExpression result = null;
		assert(rules.size() > 0);

		OclExpression value = genValue;	
		Type srcType = originalValue.getInferredType();
		if ( TypeUtils.isReference(srcType)) {
			result = createReferenceConstraint(model, rules, value, originalValue);
		} else if ( TypeUtils.isCollection(srcType) ) {
			IteratorExp exists = model.createExists(value, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl, originalValue);
			
			// lastIf.setElseExpression(model.createBooleanLiteral(false));
			// exists.setBody(lastIf);
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(srcType)) {
			result = createReferenceConstraint(model, rules, value, originalValue);	
		} else if ( TypeUtils.isUnionWithCollections(srcType) ) {
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(value, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl, originalValue);
			
			exists.setBody(lastExpr);
			result = exists;
		} else if ( srcType instanceof UnionType ) {
			// This is purely speculatively... just to try... should be signaled somehow
			SequenceExp seq = OCLFactory.eINSTANCE.createSequenceExp();
			seq.getElements().add(value);
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(seq, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genOrRules(model, rules, varDcl, originalValue);
			
			exists.setBody(lastExpr);
			result = exists;
		} else {
			throw new IllegalStateException(TypeUtils.typeToString(srcType));
		}
		
		return result;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		OclExpression result = null;
		Set<MatchedRule> guiltyRules = problem.getRules().stream().map(rri -> (MatchedRule) rri.getElement()).collect(Collectors.toSet());

		List<RuleResolutionInfo> rules = sortRules(binding.getResolvedBy()).stream().
				filter(r -> guiltyRules.contains(r.getRule()) ).collect(Collectors.toList());
		
		OclExpression originalValue = binding.getValue();	
		OclExpression genValue = model.atlCopy(originalValue);	
		assert(rules.size() > 0);
		
		if ( TypeUtils.isReference(ATLUtils.getSourceType(binding) )) {
			LetExp let = model.createLetScope(genValue, null,  model.genNiceVarName(originalValue));
			VariableDeclaration varDcl = let.getVariable();		
			varDcl.setInferredType(originalValue.getInferredType());
			varDcl.setType(ATLUtils.getOclType(originalValue.getInferredType()));
			OclExpression orRules = BindingPossiblyUnresolvedNode.genAndRules_Precondition(model, rules, varDcl, "or");

			let.setIn_( model.negateExpression(orRules) );
			result = let;
		} else if ( TypeUtils.isCollection(ATLUtils.getSourceType(binding)) ) {
			IteratorExp exists = model.createExists(genValue, model.genNiceVarName(originalValue));
			VariableDeclaration varDcl = exists.getIterators().get(0);
			varDcl.setInferredType( ((CollectionType) ATLUtils.getSourceType(binding)).getContainedType());
			
			OclExpression orRules = BindingPossiblyUnresolvedNode.genAndRules_Precondition(model, rules, varDcl, "or");
			
			exists.setBody(orRules);
			
			result = model.negateExpression(exists);
		} else if ( TypeUtils.isUnionWithReferences(ATLUtils.getSourceType(binding))) {
			throw new UnsupportedOperationException();
			// result = createReferenceConstraint(model, rules, genValue);	
		} else if ( TypeUtils.isUnionWithCollections(ATLUtils.getSourceType(binding)) ) {
			throw new UnsupportedOperationException();
		} else if ( ATLUtils.getSourceType(binding) instanceof UnionType ) {
			throw new UnsupportedOperationException();
		} else {
			throw new IllegalStateException(TypeUtils.typeToString(ATLUtils.getSourceType(binding)));
		}
		
		return result;
	}
	
	private static LetExp createReferenceConstraint(CSPModel model,
			List<ResolvedRuleInfo> rules, OclExpression genBindingValue, OclExpression srcBindingValue) {
		LetExp let = model.createLetScope(genBindingValue, null, "_problem_");
		VariableDeclaration varDcl = let.getVariable();
		let.setIn_( genOrRules(model, rules, varDcl, srcBindingValue));
		return let;
	}

	private static OclExpression genOrRules(CSPModel model, List<ResolvedRuleInfo> rules, VariableDeclaration varDcl, OclExpression srcBindingValue) {
		OclExpression lastExpr = null;
		for (ResolvedRuleInfo rule : rules) {
			MatchedRule r = (MatchedRule) rule.getElement();				
			IfExp ifexpr = genCondition(model, varDcl, rule, r, srcBindingValue);
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

	private static IfExp genCondition(CSPModel model, VariableDeclaration varDcl,
			ResolvedRuleInfo rule, MatchedRule r, OclExpression srcBindingValue) {
		// => _problem_.oclIsKindOf(ruleFrom)
		VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
		v.setReferredVariable(varDcl);
		v.setInferredType(varDcl.getInferredType());
		OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, rule.getInputType());
		
		// Generate the filter
		OclExpression filter = null;
		if ( r.getInPattern().getFilter() != null ) {
			model.openEmptyScope();
			
			SimpleInPatternElement simpleElement = (SimpleInPatternElement) r.getInPattern().getElements().get(0);
			
			// => let newVar = _problem_.oclAsType(RuleFrom) in <filter>				
			OclExpression casting = model.createCastTo(varDcl, (Metaclass) simpleElement.getInferredType(), srcBindingValue.getNoCastedType());				
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
