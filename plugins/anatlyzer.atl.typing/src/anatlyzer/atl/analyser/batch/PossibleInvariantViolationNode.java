package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.batch.invariants.DenormalizeInvariantToATL;
import anatlyzer.atl.analyser.batch.invariants.DenormalizeInvariantToUse;
import anatlyzer.atl.analyser.batch.invariants.IInvariantNode;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator;
import anatlyzer.atl.analyser.batch.invariants.InvariantGraphGenerator.TranslatedHelper;
import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.graph.AbstractDependencyNode;
import anatlyzer.atl.graph.GraphNode;
import anatlyzer.atl.graph.IPathVisitor;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
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

	protected OclExpression expr;
	protected ATLModel model;
	protected IAnalyserResult result;
	protected ProblemStatus status = ProblemStatus.WITNESS_REQUIRED;
	protected String invName;
	protected IInvariantNode invNode;
	protected DenormalizeInvariantToUse useDenormalizer;
	protected IWitnessModel witness;
	protected ErrorSlice slice;
	protected List<TranslatedHelper> translatedHelpers;
	protected StaticHelper helper;
	protected boolean useNorm = true;

	public PossibleInvariantViolationNode(StaticHelper helper, ATLModel model, IAnalyserResult result) {
		this.helper = helper;
		this.invName = ATLUtils.getHelperName(helper);
		this.expr = ATLUtils.getHelperBody(helper);
		this.model = model;
		this.result = result; 
	}

	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		if ( useNorm ) {
			if ( useDenormalizer != null ) {
				return useDenormalizer.getResult();
			}
			
			OclExpression preNorm = getPreconditionNorm((pre) -> {
				OperatorCallExp negate = OCLFactory.eINSTANCE.createOperatorCallExp();
				negate.setOperationName("not");
				negate.setSource(pre);
				return negate;
			});
			
			System.out.println("ATL: " + ATLSerializer.serialize(preNorm)); 
			useDenormalizer = new DenormalizeInvariantToUse(preNorm, this.model);
			useDenormalizer.perform();
			return useDenormalizer.getResult();			
		} else {
			return getPrecondition((pre) -> {
				OperatorCallExp negate = OCLFactory.eINSTANCE.createOperatorCallExp();
				negate.setOperationName("not");
				negate.setSource(pre);
				return negate;
			});
		}
	}

	public OclExpression getPreconditionATL() {
		return new DenormalizeInvariantToATL(getPreconditionNorm(), this.model).perform();
	}
	
	public OclExpression getPrecondition() {
		return getPrecondition(exp -> exp);
	}

	public OclExpression getPreconditionNorm() {
		return getPreconditionNorm(exp -> exp);
	}

	protected OclExpression getPreconditionNorm(Function<OclExpression, OclExpression> negator) {
		CSPModel2 cspmodel = new CSPModel2(result);
		cspmodel.initWithoutThisModuleContext();
		return negator.apply( getInvariantNode().genExprNormalized(cspmodel) );		
	}
	
	protected OclExpression getPrecondition(Function<OclExpression, OclExpression> negator) {
		CSPModel2 cspmodel = new CSPModel2(result);
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
	
	protected IInvariantNode getInvariantNode() {
		if ( invNode == null) {
			InvariantGraphGenerator gen = new InvariantGraphGenerator(this.result);
			this.invNode = gen.replace(expr);
			this.translatedHelpers = gen.getTranslatedHelpers();
		}
		return this.invNode;
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		getInvariantNode().genGraphviz(gv);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		HashSet<OutPatternElement> targets = new HashSet<OutPatternElement>();
		getInvariantNode().getTargetObjectsInBinding(targets);
		targets.forEach(o -> {
			slice.addTargetMetaclassNeededInError(((Metaclass) o.getInferredType()).getKlass());
		});
		
		getInvariantNode().genErrorSlice(slice);
		
		if ( useNorm ) { 
			if ( useDenormalizer == null )
				throw new IllegalStateException("Class protocol requires calling getWitnessCondition before genErrorSlice");
			
			useDenormalizer.genErrorSlice(slice);
		}
		
		translatedHelpers.forEach(h -> h.genErrorSlice(slice));
		translatedHelpers.forEach(h -> slice.addHelper(h.genSourceHelper()));
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

		// TODO: add
		// TGT_ERAttribute_Entity.allInstances()->size() = Entity.allInstances()->size() * ERAttribute.allInstances()->size()
		
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

	private Exception error;
	public void setAnalysisError(Exception e) {
		this.status = ProblemStatus.IMPL_INTERNAL_ERROR;
		this.error = e;
	}
	
	public Exception getAnalysisError() { return error; }
	
	public IWitnessModel getWitness() {
		return witness;
	}
	
	public String getInvName() {
		return this.invName;
	}

	public IAnalyserResult getAnalysis() {
		return result;
	}

}