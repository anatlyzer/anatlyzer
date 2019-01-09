package anatlyzer.atl.graph;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingResolution;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ClassPicker;
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
	private IAnalyserResult  analyser;

	public BindingPossiblyUnresolvedNode(BindingPossiblyUnresolved p, Binding binding, IAnalyserResult analyser) {
		super(p);
		this.binding = binding;
		this.analyser = analyser;
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
	public void genErrorSlice(ErrorSlice slice) {
		for(DependencyNode n : dependencies) {
			n.genErrorSlice(slice);
		}		
		
		for(ConstraintNode n : constraints) {
			n.genErrorSlice(slice);
		}		
		
		OclSlice.slice(slice, binding.getValue());
		

// This is not enough if the class that will raise the error is "implicit" (i.e., it 
// does not appear in the path but it is a subclass)
//		// Needed for the error
//		for (EClass c : problem.getProblematicClasses()) {
//			slice.addMetaclassNeededInError(c);
//		}


		
//		 New strategy to reduce the number of classes in the slice
		selectProblematicClassesForSlice(slice, 
				binding.getResolvedBy(), 
				problem.getProblematicClasses(), 
				problem.getProblematicClassesImplicit());
	
		// This is for oclIsKindOf
		Set<EClass> types = ClassPicker.treatOclIsKindOf_pickSubclassSibling(problem, analyser.getNamespaces());
		types.forEach(sub -> slice.addMetaclassNeededInError(sub));

	}

	public static void selectProblematicClassesForSlice(ErrorSlice slice, List<? extends RuleResolutionInfo> resolution, List<EClass> problematicClasses, List<EClass> problematicClassesImplicit) {
		// This way it does not work for Ant2Maven, missing one type in the problem:
		//		"Possibly unresolved binding Exec, Echo"
//		for (EClass c : problematicClasses) {
//			if ( ! problematicClassesImplicit.contains(c) ) {
//				System.out.println(c.getName());
//				slice.addMetaclassNeededInError(c);
//			}
//		}
		// So, always adding all problematic classes
		for (EClass c : problematicClasses) {
			slice.addMetaclassNeededInError(c);
		}
		
		EClass c = ErrorSlice.pickClass(problematicClassesImplicit);
		if ( c != null ) {
			slice.addMetaclassNeededInError(c);
		}

		
		// Classes whose type appear
		for (RuleResolutionInfo ruleInfo : resolution) {
			for (MatchedRule mr : ruleInfo.getAllInvolvedRules()) {			
				slice.addMetaclassNeededInError(ATLUtils.getInPatternType(mr).getKlass());
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
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return genProblemSpecificCondition(model, "and");
	}

	protected OclExpression genProblemSpecificCondition(CSPModel model, String operator) {
		List<RuleResolutionInfo> rules = sortRules(binding.getResolvedBy());

		OclExpression bindingValue = binding.getValue();
		
		if ( isFirstSpecialCase(bindingValue) ) {
			bindingValue = ((OperationCallExp)bindingValue).getSource();
		}
		
		OclExpression value = genValueRightPart(model, bindingValue);		
		
		// If the right-hand side is a collection it may happen that it has several types involved
		// (with mono-valued it may happen as well but it is more rare...)
		// We filter to include only the types involved in this particular notified problem
		Type srcType = bindingValue.getInferredType();		
		if ( TypeUtils.isCollection(srcType) ) {
			IteratorExp select = model.createIterator(value, "select", "binding_value");			
			OclExpression body = null;
			for (EClass c : problem.getProblematicClasses()) {
				VariableExp vr = model.createVarRef(select.getIterators().get(0));

				Metaclass mc = null;
				for (MetamodelNamespace ns : analyser.getNamespaces().getMetamodels()) {
					IClassNamespace cns = ns.getClass(c);
					if ( cns != null ) {
						// This is tricky way of doing this, but I cannot get the metaclass 
						// from the namespace because the AnalyserContext is not longer active...				
						Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
						metaclass.setKlass(c);
						metaclass.setName(c.getName());
						MetaModel mmodel = TypesFactory.eINSTANCE.createMetaModel();
						mmodel.setName(cns.getMetamodelName());					
						metaclass.setModel(mmodel);

						mc = metaclass;
						break;
					}
				}
				assert(mc != null);				
				
				OperationCallExp kindOf = model.createKindOf(vr, mc.getModel().getName(), c.getName(), mc);
				if ( body == null ) {
					body = kindOf;
				} else {
					body = model.createBinaryOperator(body, kindOf, "or");
				}
			}
			select.setBody(body);
			value = select;
		}
		
		return genProblemSpecificCondition(model, operator, rules, bindingValue, value);		
	}

	protected static OclExpression genProblemSpecificCondition(CSPModel model, String operator, List<RuleResolutionInfo> rules, OclExpression originalValue, OclExpression genValue) {
		OclExpression result = null;
		assert(rules.size() > 0);
		Type srcType = originalValue.getInferredType();

		if ( TypeUtils.isReference(srcType) ) {
			result = createReferenceConstraint(model, rules, genValue, operator);
		} else if ( TypeUtils.isCollection(srcType) ) {		
			IteratorExp exists = model.createExists(genValue, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genAndRules(model, originalValue, rules, varDcl, operator);
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(srcType)) {
			result = createReferenceConstraint(model, rules, genValue, operator);
		} else if ( TypeUtils.isUnionWithCollections(srcType) ) {
			CollectionOperationCallExp flattened = model.createCollectionOperationCall(genValue, "flatten");
			
			// Same as the previous if...
			IteratorExp exists = model.createExists(flattened, "_problem_");
			VariableDeclaration varDcl = exists.getIterators().get(0);
			
			OclExpression lastExpr = genAndRules(model, originalValue, rules, varDcl, operator);
			
			exists.setBody(lastExpr);
			result = exists;
		} else {
			// TODO: For union types with mixed collections and mono-valued elements, Sequence { value }->flatten()
			throw new IllegalStateException(originalValue.getLocation() + " " + TypeUtils.typeToString(srcType));
		}
		
		return result;
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		List<RuleResolutionInfo> rules = sortRules(binding.getResolvedBy());
		OclExpression originalValue = binding.getValue();	
		OclExpression genValue = model.atlCopy(originalValue);	
		
		// return genProblemSpecificCondition(model, "or");
		
		OclExpression result = null;
		assert(rules.size() > 0);
		Type srcType = originalValue.getInferredType();

		if ( TypeUtils.isReference(srcType) ) {
			LetExp let = model.createLetScope(genValue, null,  model.genNiceVarName(originalValue));
			VariableDeclaration varDcl = let.getVariable();	
			varDcl.setInferredType(originalValue.getInferredType());
			varDcl.setType(ATLUtils.getOclType(originalValue.getInferredType()));
			OclExpression andRules = genAndRules_Precondition(model, rules, varDcl, "or");

			let.setIn_( andRules );
			result = let;			
		} else if ( TypeUtils.isCollection(srcType) ) {		
			IteratorExp exists = model.createIterator(genValue, "forAll", model.genNiceVarName(originalValue));
			VariableDeclaration varDcl = exists.getIterators().get(0);
			varDcl.setInferredType(((CollectionType) srcType).getContainedType());
			
			OclExpression lastExpr = genAndRules_Precondition(model, rules, varDcl, "or");
			
			exists.setBody(lastExpr);
			
			result = exists;
		} else if ( TypeUtils.isUnionWithReferences(srcType)) {
			throw new UnsupportedOperationException();
		} else if ( TypeUtils.isUnionWithCollections(srcType) ) {
			throw new UnsupportedOperationException();		} else {
			// TODO: For union types with mixed collections and mono-valued elements, Sequence { value }->flatten()
			throw new IllegalStateException(originalValue.getLocation() + " " + TypeUtils.typeToString(srcType));
		}
		
		return result;
	}
	
	
	private static LetExp createReferenceConstraint(CSPModel model,
			List<RuleResolutionInfo> rules, OclExpression value, String operator) {
		LetExp let = model.createLetScope(value, null, "_problem_");
		VariableDeclaration varDcl = let.getVariable();		
		OclExpression andRules = genAndRules(model, value, rules, varDcl, operator);
		
		// => not varDcl.oclIsUndefined()
		// It is necessary to check for undefined because otherwise setting a reference
		// to undefined is a trivial solution to the expression
		VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
		varRef.setReferredVariable(varDcl);
		varDcl.setInferredType(varDcl.getInferredType());
		OperatorCallExp notUndefined = model.negateExpression(model.createOperationCall(varRef, "oclIsUndefined"));
		
		let.setIn_( model.createBinaryOperator(notUndefined, andRules, "and") );
		
		// let.setIn_(andRules);
		
		return let;
	}

	/*
	 * This is the default generator that it is used for witness generation
	 */
	private static OclExpression genAndRules(CSPModel model,
			OclExpression bindingValue, List<RuleResolutionInfo> rules, VariableDeclaration varDcl, String operator) {
				
		OclExpression lastExpr = null;
		for (RuleResolutionInfo info : rules) {
			OclExpression ifexpr = genRuleResolutionCondition(model, bindingValue, varDcl, info.getRule());
			OperatorCallExp negatedIf = model.negateExpression(ifexpr);
			
			if ( lastExpr == null ) {
				lastExpr = negatedIf;
			} else {
				lastExpr = model.createBinaryOperator(lastExpr, negatedIf, operator);
			}
		}
		return lastExpr;
	}

	public static OclExpression genRuleResolutionCondition(CSPModel model,
			OclExpression bindingValue, VariableDeclaration varDcl,
			MatchedRule r) {
		
		// => _problem_.oclIsKindOf(ruleFrom)
		VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
		v.setReferredVariable(varDcl);
		v.setInferredType(varDcl.getInferredType());
		OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, ATLUtils.getInPatternType(r));			
		
		// Generate the filter
		OclExpression filter = null;
		if ( r.getInPattern().getFilter() != null ) {
			model.openEmptyScope();

			SimpleInPatternElement simpleElement = (SimpleInPatternElement) r.getInPattern().getElements().get(0);
			
			// => let newVar = _problem_.oclAsType(RuleFrom) in <filter>	
			OclExpression casting = model.createCastTo(varDcl, (Metaclass) simpleElement.getInferredType(), bindingValue.getNoCastedType());				
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
		refToProblem.setInferredType(varDcl.getInferredType());
		OclExpression lastIfResult = model.createOperationCall(refToProblem, "oclIsUndefined");
		
		IfExp ifexpr = model.createIfExpression(kindOfCondition, filter, lastIfResult);
		return ifexpr;
	}

	public static OclExpression genAndRules_Precondition(CSPModel model, List<RuleResolutionInfo> rules, VariableDeclaration varDcl, String operator) {
		OclExpression lastExpr = null;
		for (RuleResolutionInfo info : rules) {
			MatchedRule r = info.getRule();
			
			// => _problem_.oclIsKindOf(ruleFrom)
			VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
			v.setReferredVariable(varDcl);				
			v.setInferredType(varDcl.getInferredType());
			Metaclass srcType = ATLUtils.getInPatternType(info.getRule());
			OclExpression kindOfCondition = model.createKindOf(v, srcType.getModel().getName(), srcType.getName(), srcType);			

			// Generate the filter
			OclExpression fulfilledExpr = kindOfCondition;
			if ( r.getInPattern().getFilter() != null ) {
				model.openEmptyScope();
					// Map the iterator var to the rule variable
					SimpleInPatternElement simpleElement = (SimpleInPatternElement) r.getInPattern().getElements().get(0);
					model.addToScope(simpleElement, varDcl);				
					OclExpression filter = model.atlCopy(r.getInPattern().getFilter());
				model.closeScope();
				
				fulfilledExpr = model.createIfExpression(kindOfCondition, filter, model.createBooleanLiteral(false)); 
				// This does not work because if the filter is not fullfilled I get a true, so the whole forAll is true
				// fulfilledExpr = model.createBinaryOperator(kindOfCondition, filter, "implies");
			}
									
			if ( lastExpr == null ) {
				lastExpr = fulfilledExpr;
			} else {
				lastExpr = model.createBinaryOperator(lastExpr, fulfilledExpr, operator);
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
