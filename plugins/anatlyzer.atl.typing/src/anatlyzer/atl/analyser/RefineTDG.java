package anatlyzer.atl.analyser;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AtlErrorFactory;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.GenericLocalProblem;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractBindingAssignmentNode;
import anatlyzer.atl.graph.BindingPossiblyUnresolvedNode;
import anatlyzer.atl.graph.ConstraintNode;
import anatlyzer.atl.graph.DependencyNode;
import anatlyzer.atl.graph.ErrorPathGenerator;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.graph.ProblemPath;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ClassPicker;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessFinderFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleResolutionStatus;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This is an extension of the analysis to refine the original TDG using the
 * constraint solver, to have more fine grained between binding--rules and 
 * (optionally) calls -- context helpers.
 * 
 * @author jesus
 *
 */
public class RefineTDG implements AnalyserExtension {

	private IWitnessFinderFactory factory;


	public RefineTDG(IWitnessFinderFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public boolean isPreparationTask() {
		return false;
	}
	
	@Override
	public boolean isPostProcessingTask() {
		return true;
	}

	public void perform(IAnalyserResult analysis) {
		perform(analysis, analysis.getATLModel().getRoot());
	}

	@Override
	public void perform(IAnalyserResult result, Unit root) {
		ATLModel model = result.getATLModel();
		
		List<Binding> bindings = model.allObjectsOf(Binding.class);
		for (Binding binding : bindings) {
			refineBinding(binding, result);
		}
	}

	public void refineBinding(Binding b, IAnalyserResult r) {
		List<Metaclass> metaclasses = TypingModel.getInvolvedMetaclassesOfType(b.getValue().getInferredType());
		if ( ! (b.getWrittenFeature() instanceof EReference && metaclasses.size() > 0) )
			return;
		
		
		for (RuleResolutionInfo rri : b.getResolvedBy()) {
			for (Metaclass metaclass : metaclasses) {		
				// Check status of rri. If it has already been confirmed, no need to check again
				if ( isConfirmed(rri.getStatus()) )
					continue;
				
				// Filter by type/rule to avoid calling the constraint solving for combinations
				// which are not going to happen
				Metaclass ruleType = ATLUtils.getInPatternType(rri.getRule());
				if ( ! TypingModel.isCompatible(ruleType , metaclass) )
					continue;
				
				// This is a dummy object because the problem path requires an error to be passed
				GenericLocalProblem dummy = AtlErrorFactory.eINSTANCE.createGenericLocalProblem();
				dummy.setElement(b);
	
				BindingRefinementNode node = new BindingRefinementNode(r, b, rri.getRule(), metaclass, dummy);
				
				ProblemPath path = new ProblemPath(dummy, node);
				// TODO: The analysis is only need when the generatePath is used to generate all paths...
				new ErrorPathGenerator(r).generatePath(path, node, b);
				
				IWitnessFinder finder = factory.create();
				ProblemStatus witness = finder.find(path, new AnalysisResult(r));
				
				rri.setStatus(witnessStatus2ruleStatus(witness));
				
				System.out.println("RESOLVED::::: ");
				System.out.println(witness);
			}
			
			
		}
	}
	
	private boolean isConfirmed(RuleResolutionStatus status) {
		return status == RuleResolutionStatus.RESOLUTION_CONFIRMED;
	}

	protected RuleResolutionStatus witnessStatus2ruleStatus(ProblemStatus s) {
		switch (s) {
		case ERROR_CONFIRMED:
		case ERROR_CONFIRMED_SPECULATIVE:
			return RuleResolutionStatus.RESOLUTION_CONFIRMED;			
		case CANNOT_DETERMINE:
		case ERROR_DISCARDED_DUE_TO_METAMODEL:
		case IMPL_INTERNAL_ERROR:
		case NOT_SUPPORTED_BY_USE:
		case USE_INTERNAL_ERROR:
		case USE_TIME_OUT:
			return RuleResolutionStatus.RESOLUTION_UNKNOWN;
		case ERROR_DISCARDED:
			return RuleResolutionStatus.RESOLUTION_DISCARDED;
//		case STATICALLY_CONFIRMED:	
//		case INITIALLY_DISCARDED:
//		case PROBLEMS_IN_PATH:
//		case WITNESS_REQUIRED:
		default:
			throw new IllegalStateException();
		}
	}
	
	public class BindingRefinementNode extends AbstractBindingAssignmentNode<GenericLocalProblem> {

		private Binding binding;
		private MatchedRule rule;
		private Metaclass metaclass;
		private IAnalyserResult analysis;

		public BindingRefinementNode(IAnalyserResult r, Binding b, MatchedRule rule, Metaclass metaclass, GenericLocalProblem dummy) {
			super(dummy);
			this.analysis = r;
			this.rule = rule;
			this.binding = b;
			this.metaclass = metaclass;
		}

		@Override
		public boolean isStraightforward() { return false;	}

		@Override
		public OclExpression genCSP(CSPModel model, GraphNode previous) {
			OclExpression bindingValue = binding.getValue();
			Type srcType = bindingValue.getInferredType();
			
			OclExpression value = genValueRightPart(model, bindingValue);		
			
			OclExpression result = null;
			if ( TypeUtils.isReference(srcType) ) {
				result = createReferenceConstraint(model, rule, value);
			} else if ( TypeUtils.isCollection(srcType) ) {		
				IteratorExp exists = model.createExists(value, "_problem_");
				VariableDeclaration varDcl = exists.getIterators().get(0);
				
				OclExpression lastExpr = BindingPossiblyUnresolvedNode.genRuleResolutionCondition(model, value, varDcl, rule);
				
				exists.setBody(lastExpr);
				
				result = exists;
			} else if ( TypeUtils.isUnionWithReferences(srcType)) {
				result = createReferenceConstraint(model, rule, value);
			} else if ( TypeUtils.isUnionWithCollections(srcType) ) {
				CollectionOperationCallExp flattened = model.createCollectionOperationCall(value, "flatten");
				
				IteratorExp exists = model.createExists(flattened, "_problem_");
				VariableDeclaration varDcl = exists.getIterators().get(0);
				
				OclExpression lastExpr = BindingPossiblyUnresolvedNode.genRuleResolutionCondition(model, value, varDcl, rule);				
				
				exists.setBody(lastExpr);
				result = exists;
			} else {
				throw new IllegalStateException(binding.getLocation() + " " + TypeUtils.typeToString(srcType));
			}
			
			return result;
		}

		private LetExp createReferenceConstraint(CSPModel model, MatchedRule rule, OclExpression value) {
			LetExp let = model.createLetScope(value, null, "_problem_");
			VariableDeclaration varDcl = let.getVariable();		
			OclExpression andRules = BindingPossiblyUnresolvedNode.genRuleResolutionCondition(model, value, varDcl, rule);
			
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

		
		@Override
		public boolean isVarRequiredByErrorPath(VariableDeclaration v) {
			return ATLUtils.findVariableReference(binding.getValue(), v) != null;
		}

		@Override
		public OclExpression genWeakestPrecondition(CSPModel model) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void genErrorSlice(ErrorSlice slice) {
			for(DependencyNode n : dependencies) {
				n.genErrorSlice(slice);
			}		
			
			if ( constraints.size() > 0 )
				throw new IllegalStateException();

			
			OclSlice.slice(slice, binding.getValue());
			
			Metaclass ruleType = ATLUtils.getInPatternType(rule);
			slice.addExplicitMetaclass(ruleType);
			if ( ruleType.getKlass().isAbstract() ) {
				Set<EClass> subs = ClassPicker.pickSubclasses(analysis.getNamespaces(), Collections.singleton(ruleType));
				subs.forEach(sub -> slice.addMetaclassNeededInError(sub));
			}	
			
			// This is for oclIsKindOf
			// TODO: Try to adapt to just one rule
			// Set<EClass> types = ClassPicker.treatOclIsKindOf_pickSubclassSibling(problem, analysis.getNamespaces());
			// types.forEach(sub -> slice.addMetaclassNeededInError(sub));

			if ( rule.getInPattern().getFilter() != null ) {
				OclSlice.slice(slice, rule.getInPattern().getFilter());
			}
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
		public void bottomUp(IPathVisitor visitor) {
			throw new UnsupportedOperationException();
		}

		
	}
	
	
}
