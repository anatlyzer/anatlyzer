package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperatorCallExp;

/**
 * This class implements a wrapper over the AnATLyzer machinery intended
 * to allow the user to ask the constraint solver if a set of expressions
 * is satisfiable. 
 * 
 * @author jesus
 */
public class ConstraintSatisfactionChecker {

	private List<OclExpression> expressions = new ArrayList<OclExpression>();
	private HashMap<String, Resource> namesToResources = new HashMap<String, Resource>();
	private IWitnessFinder finder;
	private ProblemStatus finderResult;
	private ATLModel model;
	
	public ConstraintSatisfactionChecker(List<OclExpression> expressions) {
		this.expressions.addAll(expressions);
	}
	
	public static ConstraintSatisfactionChecker withExpr(OclExpression expr) {
		return new ConstraintSatisfactionChecker(Collections.singletonList(expr));
	}
	
	public ConstraintSatisfactionChecker configureMetamodel(String mmName, Resource mmResource) {
		namesToResources.put(mmName, mmResource);
		return this;
	}
	
	public ConstraintSatisfactionChecker withFinder(IWitnessFinder finder) {
		this.finder = finder;
		return this;
	}
	
	public ConstraintSatisfactionChecker check() {
		if ( this.finder == null )
			throw new IllegalStateException();
		
		Unit unit = constructTransformation();
		
		// Configure the analysis
		GlobalNamespace mm = new GlobalNamespace(namesToResources.values(), namesToResources);
		ATLModel model = new ATLModel();
		model.add(unit);
		this.model = model;
		
		Analyser analyser = new Analyser(mm, model);
		analyser.perform();
		
		// Configure the finder
		this.finderResult = finder.find(new ConstraintSatisfactionProblem(), new AnalysisResult(analyser));

		return this;
	}

	public ProblemStatus getFinderResult() {
		return finderResult;
	}

	public IWitnessModel getWitnessModel() {
		return finder.getFoundWitnessModel();
	}
	
	private Unit constructTransformation() {
		// Library module = ATLFactory.eINSTANCE.createLibrary();
		Module module = ATLFactory.eINSTANCE.createModule();
		module.setName("inMemoryModule");
		
		OclModel inModel = OCLFactory.eINSTANCE.createOclModel();
		inModel.setName("IN");
		OclModel mmModel = OCLFactory.eINSTANCE.createOclModel();
		mmModel.setName("MM");
		inModel.setMetamodel(mmModel);
		module.getInModels().add(inModel);
		
		List<StaticHelper> helpers = expressions.stream().map(e -> {
			return createOperation("exp" + expressions.indexOf(e), (op) -> {
				op.setBody((OclExpression) ATLCopier.copySingleElement(e));
			});
		}).collect(Collectors.toList());
		
		
		// module.getHelpers().addAll(helpers);
		module.getElements().addAll(helpers);

		return module;
	}

	private StaticHelper createOperation(String opName, Consumer<Operation> consumer) {
		StaticHelper h = ATLFactory.eINSTANCE.createStaticHelper();
		OclFeatureDefinition f = OCLFactory.eINSTANCE.createOclFeatureDefinition();		
		Operation op = OCLFactory.eINSTANCE.createOperation();
		op.setName(opName);
		op.setReturnType(OCLFactory.eINSTANCE.createBooleanType());
		f.setFeature(op);
		h.setDefinition(f);
		consumer.accept(op);
		return h;
	}

	private List<OclExpression> getExpressionsToBeChecked() {
		List<Helper> helpers = ATLUtils.getAllHelpers(model);
		return helpers.stream().map(h -> ATLUtils.getBody(h)).collect(Collectors.toList());
	}
	
	public class ConstraintSatisfactionProblem implements IDetectedProblem {

		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			ErrorSlice slice = new ErrorSlice(result, namesToResources.keySet(), this);
			
			getExpressionsToBeChecked().forEach(e -> OclSlice.slice(slice, e));

			return slice;
		}

		@Override
		public OclExpression getWitnessCondition() {
			List<OclExpression> exprs = getExpressionsToBeChecked();
			if ( exprs.size() == 1 ) 
				return exprs.get(0);
			
			OclExpression result = exprs.get(0);
			for(int i = 1; i < exprs.size(); i++) {
				OclExpression exp = exprs.get(i);
				
				OperatorCallExp andOp = OCLFactory.eINSTANCE.createOperatorCallExp();
				andOp.setOperationName("and");
				andOp.setSource(result);
				andOp.getArguments().add(exp);
				
				result = andOp;
			}
			
			return result;
		}

		@Override
		public boolean isExpressionInPath(OclExpression expr) {
			return false;
		}

		@Override
		public List<OclExpression> getFrameConditions() {
			return Collections.emptyList();
		}
		
	}
	
}
