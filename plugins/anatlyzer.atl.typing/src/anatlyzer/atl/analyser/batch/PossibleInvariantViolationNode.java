package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
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
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

// Probably there is no need to inherit from AbstractDependencyNode
public class PossibleInvariantViolationNode extends AbstractDependencyNode implements IDetectedProblem {

	private OclExpression expr;
	private ATLModel model;
	private IAnalyserResult result;

	public PossibleInvariantViolationNode(StaticHelper helper, ATLModel model, IAnalyserResult result) {
		this.expr = ATLUtils.getHelperBody(helper);
		this.model = model;
		this.result = result; 
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		//
		OclExpression expr = (OclExpression) ATLCopier.copySingleElement(this.expr);
		
		List<OperationCallExp> allInstancesCalls = new ArrayList<>();
		
		expr.eAllContents().forEachRemaining(obj -> {
			if ( obj instanceof OperationCallExp ) {
				if ( ((OperationCallExp) obj).getOperationName().equals("allInstances") ) {
					allInstancesCalls.add((OperationCallExp) obj);
				}
			}
		}); 
		
		// For each occurrence of TTarget.allInstances, replace with the corresponding
		// TSource.
		for (OperationCallExp allInstances : allInstancesCalls) {
			List<MatchedRule> rules = findTargetOcurrences((OclModelElement) allInstances.getSource());
			
			// For the moment assume only one rule
			for (MatchedRule r : rules) {
				
				InPatternElement firtIPE = r.getInPattern().getElements().get(0);
				
				OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(firtIPE.getType());
				allInstances.setSource(sourceType);
				
				break; // REMOVE THIS AND CONCATENATE WITH AND
			}
			
			// concat with 'and'
		}
		
		
		
		return null;
	}

	private List<MatchedRule> findTargetOcurrences(OclModelElement targetType) {
		// TODO: Consider subtyping relationships
		return model.allObjectsOf(MatchedRule.class).stream().
			filter(r -> r.getOutPattern() != null).
			filter(r -> r.getOutPattern().getElements().stream().anyMatch(ope -> TypingModel.equalTypes(ope.getInferredType(), targetType.getInferredType()))).
			collect(Collectors.toList());
		
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
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

	public ProblemStatus getAnalysisResult() {
		throw new UnsupportedOperationException();
	}

	
}