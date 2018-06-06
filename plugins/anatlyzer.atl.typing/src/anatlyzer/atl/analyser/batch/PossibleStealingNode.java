package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.graph.MatchedRuleExecution;
import anatlyzer.atl.graph.RuleFilterNode;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
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
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class PossibleStealingNode extends AbstractDependencyNode implements IDetectedProblem {

	private MatchedRule r1;
	private MatchedRule r2;
	private Binding binding1;
	private Binding binding2;
	private MatchedRuleExecution r1Node;
	private MatchedRuleExecution r2Node;
	private BindingCheck customNode;
	private ProblemStatus analysisResult;
	private MatchedRule resolved;

	public PossibleStealingNode(MatchedRule r1, Binding binding1, MatchedRule r2, Binding binding2, MatchedRule resolved) {
		if ( r1 == r2 ) {
			ATLCopier copier = new ATLCopier(r2);
			r2 = (MatchedRule) copier.copy();
			binding2 = (Binding) copier.get(binding2);
			r2.getInPattern().getElements().forEach(e -> e.setVarName(e.getVarName() + "_r2"));
		}
		
		this.r1 = r1;
		this.r2 = r2;
		this.binding1 = binding1;
		this.binding2 = binding2;
		this.resolved = resolved;
		
		this.r1Node = new StealingMatchedRuleExecution(r1);
		if ( r1.getInPattern().getFilter() != null ) {
			r1Node.addConstraint(new RuleFilterNode(r1.getInPattern().getFilter()));
		}

		this.r2Node = new StealingMatchedRuleExecution(r2);
		if ( r2.getInPattern().getFilter() != null ) {
			r2Node.addConstraint(new RuleFilterNode(r2.getInPattern().getFilter()));
		}

		this.customNode = new BindingCheck(binding1, binding2, resolved);
		
		customNode.addDependency(r2Node);
		r2Node.addDependency(r1Node);
	}

	public Binding getBinding1() {
		return binding1;
	}
	
	public Binding getBinding2() {
		return binding2;
	}
	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		return r1Node.genCSP(model, previous);
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// TODO: See if I have to imitate the strategies of PossibleUnresolvedbindingNode
		MatchedRuleExecution dummy = new MatchedRuleExecution(resolved);
		if ( resolved.getInPattern().getFilter() != null ) {
			dummy.addConstraint(new RuleFilterNode(resolved.getInPattern().getFilter()));
		}
		dummy.genErrorSlice(slice);
		
		r1Node.genErrorSlice(slice);
		r2Node.genErrorSlice(slice);
		customNode.genErrorSlice(slice);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isProblemInPath(LocalProblem lp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isExpressionInPath(OclExpression expr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		throw new IllegalStateException();
	}

	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}

	
	// Internal nodes
	
	public class BindingCheck extends AbstractDependencyNode {

		private Binding binding1;
		private Binding binding2;
		private MatchedRule resolved;

		public BindingCheck(Binding b1, Binding b2, MatchedRule resolved) {
			this.binding1 = b1;
			this.binding2 = b2;
			this.resolved = resolved;
		}
		
		@Override
		public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
			return false;
		}

		@Override
		public OclExpression genCSP(CSPModel model, GraphNode previous) {
			// Create the inner ifs
			OclExpression b1Value = model.atlCopy(binding1.getValue());	
			OclExpression b2Value = model.atlCopy(binding2.getValue());
			
			LetExp let1 = model.createLetScope(b1Value, null, "e1");
			LetExp let2 = model.createLetScope(b2Value, let1, "e2");
			
			Type srcType = b1Value.getInferredType();
			Type srcType2 = b2Value.getInferredType();

			OclExpression result = null;
			if ( TypeUtils.isReference(srcType) || TypeUtils.isReference(srcType2) ) {
				// TODO: This is the same as the multivalued-case but wrapping (possible) mono-valued
				// expressions into a collection. Perhaps both pieces of code can be merged.
				
				VariableExp refE1 = model.createVarRef(let1.getVariable());
				// Wrap into a collection and flatten
				CollectionOperationCallExp colE1 = model.liftToCollection(refE1);
				
				IteratorExp exists = model.createExists(colE1, "_problem_1");
				VariableDeclaration varDcl = exists.getIterators().get(0);
				
				OclExpression firstIf = genCheckFilter(model, b1Value, resolved, varDcl, () -> {
					VariableExp refE2 = model.createVarRef(let2.getVariable());
					CollectionOperationCallExp colE2 = model.liftToCollection(refE2);
					IteratorExp exists2 = model.createExists(colE2, "_problem_2");
					VariableDeclaration varDcl2 = exists2.getIterators().get(0);

					OclExpression secondIf = genCheckFilter(model, b2Value, resolved, varDcl2, () -> {
						VariableExp refe1_inner = model.createVarRef(varDcl);
						VariableExp refe2_inner = model.createVarRef(varDcl2);
						return model.createBinaryOperator(refe1_inner, refe2_inner, "=");
					});
					
					exists2.setBody(secondIf);
					
					return exists2;
				});
				
				exists.setBody(firstIf);
				
				result = exists;

			} else if ( TypeUtils.isCollection(srcType) ) {
				// I have to consider also the second binding... when checking if collection or mono-valued
				VariableExp refE1 = model.createVarRef(let1.getVariable());
				IteratorExp exists = model.createExists(refE1, "_problem_1");
				VariableDeclaration varDcl = exists.getIterators().get(0);
				
				OclExpression firstIf = genCheckFilter(model, b1Value, resolved, varDcl, () -> {
					VariableExp refE2 = model.createVarRef(let2.getVariable());
					IteratorExp exists2 = model.createExists(refE2, "_problem_2");
					VariableDeclaration varDcl2 = exists2.getIterators().get(0);

					OclExpression secondIf = genCheckFilter(model, b2Value, resolved, varDcl2, () -> {
						VariableExp refe1_inner = model.createVarRef(varDcl);
						VariableExp refe2_inner = model.createVarRef(varDcl2);
						return model.createBinaryOperator(refe1_inner, refe2_inner, "=");
					});
					
					exists2.setBody(secondIf);
					
					return exists2;
				});
				
				exists.setBody(firstIf);
				
				result = exists;
			}
			
			let1.setIn_(result);
			
			MatchedRule r1 = ATLUtils.getContainer(binding1, MatchedRule.class);
			MatchedRule r2 = ATLUtils.getContainer(binding2, MatchedRule.class);
			
			
			VariableExp refToInput1 = model.createVarRef(r1.getInPattern().getElements().get(0)); // works for 1-input rules
			VariableExp refToInput2 = model.createVarRef(r2.getInPattern().getElements().get(0)); // works for 1-input rules
			
			OclExpression comparison = model.createBinaryOperator(refToInput1, refToInput2, "<>");
			OclExpression exp = model.createIfExpression(comparison, let2, model.createBooleanLiteral(false));
			
			return exp;
		}
		
		// This is a piece of code adapted from BindingPossiblyUnresolved#genAndRules
		public OclExpression genCheckFilter(CSPModel model, OclExpression bindingValue, MatchedRule resolvedRule, VariableDeclaration varDcl, Supplier<OclExpression> genNext) {
			// => _problem_.oclIsKindOf(ruleFrom)
			VariableExp v = OCLFactory.eINSTANCE.createVariableExp();
			v.setReferredVariable(varDcl);				
			OclExpression kindOfCondition = model.createKindOf_AllInstancesStyle(v, null, ATLUtils.getInPatternType(resolvedRule));			
						
			// Generate the filter
			OclExpression filter = null;
			if ( resolvedRule.getInPattern().getFilter() != null ) {
				model.openEmptyScope();

				SimpleInPatternElement simpleElement = (SimpleInPatternElement) resolvedRule.getInPattern().getElements().get(0);
				
				// => let newVar = _problem_.oclAsType(RuleFrom) in <filter>	
				OclExpression casting = model.createCastTo(varDcl, (Metaclass) simpleElement.getInferredType(), bindingValue.getNoCastedType());				
				LetExp let = model.createLetScope(casting, null, simpleElement.getVarName());
					
				// Map the iterator var to the rule variable
				model.addToScope(simpleElement, let.getVariable());
				let.setIn_(model.gen(resolvedRule.getInPattern().getFilter()));
							
				filter = let;

				model.closeScope();
			} else {
				filter = model.createBooleanLiteral(true);
			}
								
			
			// if oclIsKindOf(...) then
			//    if filter then
			//       <next>
			//    else
			//      false
			//    endif
			// else
			//    false
			// endif
			
			IfExp ifFilter = model.createIfExpression(filter, genNext.get(), model.createBooleanLiteral(false));
			
			IfExp ifexpr = model.createIfExpression(kindOfCondition, ifFilter, model.createBooleanLiteral(false));
						
			return ifexpr;
		}

		@Override
		public OclExpression genWeakestPrecondition(CSPModel model) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void genErrorSlice(ErrorSlice slice) {
			OclSlice.slice(slice, binding1.getValue());
			OclSlice.slice(slice, binding2.getValue());
		}

		@Override
		public void genTransformationSlice(TransformationSlice slice) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isProblemInPath(LocalProblem lp) {
			return false;
		}

		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			return false;
		}

		@Override
		public void bottomUp(IPathVisitor visitor) {
			throw new UnsupportedOperationException();
		}
		
	}

	public class StealingMatchedRuleExecution extends MatchedRuleExecution {

		public StealingMatchedRuleExecution(MatchedRule atlRule) {
			super(atlRule);
		}
		
		@Override
		public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
			// to avoid the exception... because matchedruleexecution was supposed to
			// be always at the top level
			return true;
		}
		
	}

	// Implementation of IDetectedProblem
	
	@Override
	public ErrorSlice getErrorSlice(IAnalyserResult result) {
		ErrorSlice slice = new ErrorSlice(result, ATLUtils.getSourceMetamodelNames(result.getATLModel()), this);
		this.genErrorSlice(slice);
		return slice;
	}

	@Override
	public OclExpression getWitnessCondition() {
		// Similar to rule analysis
		CSPModel model = new CSPModel();
		IteratorExp ctx = model.createThisModuleContext();
		model.setThisModuleVariable(ctx.getIterators().get(0));
		
		OclExpression theCondition = this.genCSP(model, null);
		ctx.setBody(theCondition);
		return ctx;
	}

	@Override
	public List<OclExpression> getFrameConditions() {
		return Collections.emptyList();
	}
	
	public void setAnalysisResult(ProblemStatus analysisResult) {
		this.analysisResult = analysisResult;
	}
		
	public ProblemStatus getAnalysisResult() {
		return analysisResult;
	}

	@Override
	public String toString() {
		return binding1.getPropertyName() + " (" + binding1.getLocation() + ")" + " and " + binding2.getPropertyName() + " (" + binding2.getLocation() + ")"  + " and rule " + resolved.getName();
	}
	
}