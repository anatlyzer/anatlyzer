package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.IInvariantNode;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

// Probably there is no need to inherit from AbstractDependencyNode
public class PossibleInvariantViolationNode extends AbstractDependencyNode implements IDetectedProblem {

	private OclExpression expr;
	private ATLModel model;
	private IAnalyserResult result;
	private ProblemStatus status = ProblemStatus.WITNESS_REQUIRED;
	private String invName;
	private IInvariantNode invNode;
	private IWitnessModel witness;
	private ErrorSlice slice;

	public PossibleInvariantViolationNode(StaticHelper helper, ATLModel model, IAnalyserResult result) {
		this.invName = ATLUtils.getHelperName(helper);
		this.expr = ATLUtils.getHelperBody(helper);
		this.model = model;
		this.result = result; 
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
//		CSPModel cspmodel = new CSPModel();
//		cspmodel.initWithoutThisModuleContext();
//
//		return cspmodel.negateExpression( getInvariantNode().genExpr(cspmodel) );
		return getPrecondition((pre) -> {
			OperatorCallExp negate = OCLFactory.eINSTANCE.createOperatorCallExp();
			negate.setOperationName("not");
			negate.setSource(pre);
			return negate;
		});
	}

	public OclExpression getPrecondition() {
		return getPrecondition(exp -> exp);
	}
	
	protected OclExpression getPrecondition(Function<OclExpression, OclExpression> negator) {
		CSPModel2 cspmodel = new CSPModel2();
		cspmodel.initWithoutThisModuleContext();

		HashSet<OutPatternElement> targets = new HashSet<OutPatternElement>();
//		getInvariantNode().getTargetObjectsInBinding(targets);

		if ( targets.size() > 0 ) {
			IteratorExp outerExists = null;
			IteratorExp innerExists = null;
			
			for (OutPatternElement ope : targets) {
				if ( innerExists == null ) {
					innerExists = createTargetExistential(cspmodel, ope, null);
					outerExists = innerExists;
				} else {
					outerExists = createTargetExistential(cspmodel, ope, outerExists);
				}
			}
			
			OclExpression body = negator.apply(getInvariantNode().genExpr(cspmodel));
			innerExists.setBody(body);
			return outerExists;
		}
		
		return negator.apply( getInvariantNode().genExpr(cspmodel) );		
	}

	protected IteratorExp createTargetExistential(CSPModel2 cspmodel, OutPatternElement ope, OclExpression body) {
		
		OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
		OclModel model = OCLFactory.eINSTANCE.createOclModel();
		model.setName("TGT");
		m.setName(ope.getType().getName());
		m.setModel(model);
		m.setInferredType(ope.getInferredType());
		
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(m);

		IteratorExp exists = cspmodel.createExists(op, ope.getVarName());
		cspmodel.addToScope(ope, exists.getIterators().get(0));

		exists.setBody( body );
		return exists;
	}
	
	private IInvariantNode getInvariantNode() {
		if ( invNode == null)
			this.invNode = new InvariantGraphGenerator(this.result).replace(expr);		
		return this.invNode;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		HashSet<OutPatternElement> targets = new HashSet<OutPatternElement>();
		getInvariantNode().getTargetObjectsInBinding(targets);
		targets.forEach(o -> {
			slice.addTargetMetaclassNeededInError(((Metaclass) o.getInferredType()).getKlass());
		});
		
		getInvariantNode().genErrorSlice(slice);
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
		if ( slice != null )
			return slice;
		
		slice = new ErrorSlice(result, ATLUtils.getSourceMetamodelNames(result.getATLModel()), this);
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
		Set<String> srcNames = ATLUtils.getModelInfo(model).stream().filter(info -> info.isInput()).map(i -> i.getMetamodelName()).collect(Collectors.toSet());
		List<MetamodelNamespace> srcMetamodels = result.getNamespaces().getMetamodels().stream().filter(m -> srcNames.contains(m.getName())).collect(Collectors.toList());
		
		ArrayList<OclExpression> conds = new ArrayList<OclExpression>();
		for (EStructuralFeature f : getErrorSlice(result).getFeatures()) {
			if ( f.getLowerBound() == 1 ) {
				CSPModel builder = new CSPModel();
				// String s = className + ".allInstances()->forAll(c | not c." + attr.getName() + ".isUndefined())";
				EClass eClass = f.getEContainingClass();
				
				
				MetamodelNamespace ns = srcMetamodels.stream().filter(m -> m.hasClass(eClass)).findAny().orElseThrow(() -> new IllegalStateException());
				
				OperationCallExp allInstances = builder.createAllInstances(ns.getMetaclassCached(eClass));
				IteratorExp forAll = builder.createIterator(allInstances, "forAll", "o");
				
				NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				nav.setName(f.getName());
				nav.setUsedFeature(f);
				nav.setSource(builder.createVarRef(forAll.getIterators().get(0)));				
				OclExpression body = null;
				
				if ( f.isMany() ) { 
					body = builder.createCollectionOperationCall(nav, "notEmpty");
				} else {
					body = builder.negateExpression(builder.createOperationCall(nav, "oclIsUndefined"));
				}
					
				forAll.setBody(body);
				
				conds.add(forAll);
			}
		}
		return conds;
	}
	
	public ProblemStatus getAnalysisResult() {
		return status;
	}

	public void setAnalysisResult(ProblemStatus result2, IWitnessModel iWitnessModel) {
		this.status = result2;
		this.witness = iWitnessModel;
	}

	public IWitnessModel getWitness() {
		return witness;
	}
	
	public String getInvName() {
		return this.invName;
	}
	
}