package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;

import analyser.atl.problems.IDetectedProblem;
import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.ExtendTransformation.IEOperationHandler;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.analyser.libtypes.IOclStandardLibrary;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
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
	private Set<Helper> requiredHelpers = new HashSet<Helper>();
	private HashMap<String, Resource> namesToResources = new HashMap<String, Resource>();
	private IWitnessFinder finder;
	private ProblemStatus finderResult;
	private ATLModel model;
	private Library library;
	private List<IOCLDialectTransformer> preAdapters = new ArrayList<ConstraintSatisfactionChecker.IOCLDialectTransformer>();
	private List<IOCLDialectTransformer> postAdapters = new ArrayList<ConstraintSatisfactionChecker.IOCLDialectTransformer>();
	private IOclStandardLibrary stdLibrary;
	private String globalAccessVariableName;
	
	public ConstraintSatisfactionChecker(Collection<? extends OclExpression> expressions) {
		this.expressions.addAll(expressions);
	}
	
	public ConstraintSatisfactionChecker(Library lib) {
		this.library = lib;
	}

	public static ConstraintSatisfactionChecker withExpr(OclExpression expr) {
		return new ConstraintSatisfactionChecker(Collections.singletonList(expr));
	}
	

	public static ConstraintSatisfactionChecker withLibrary(Library lib) {
		return new ConstraintSatisfactionChecker(lib);
	}

	public static ConstraintSatisfactionChecker withExpr(List<OclExpression> expr) {
		return new ConstraintSatisfactionChecker(expr);
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
		GlobalNamespace mm = new GlobalNamespace(namesToResources.values(), namesToResources, false);
		ATLModel model = new ATLModel();
		model.add(unit);
		this.model = model;
		
		for (IOCLDialectTransformer t : preAdapters) {
			t.adapt(model, null);
		}
		
		Analyser analyser = new Analyser(mm, model);
		// This is to make sure that we do not inline eOperations which have already been handled by the OCL translator
		analyser.withEOperationHandler(new IEOperationHandler() {			
			@Override
			public boolean handle(Unit unit, EClass c, EOperation op) { return true; }		
			@Override
			public boolean canHandle(EClass c, EOperation op) { return true; }
		});
		
		if ( stdLibrary != null )
			analyser.withOclLibrary(this.stdLibrary);
		
		analyser.perform();

		for (IOCLDialectTransformer t : postAdapters) {
			t.adapt(model, analyser);
		}

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
	
	public IScrollingIterator getScrollingIterator() {
		return finder.getScrollingIterator();
	}
	
	private Unit constructTransformation() {
		Module module = AnalyserUtils.constructTransformation(Collections.emptyList(), expressions, namesToResources);
		
		if ( library != null )
			module.getElements().addAll(library.getHelpers());
		
		module.getElements().addAll(requiredHelpers);
		
		return module;
	}

	private List<OclExpression> getExpressionsToBeChecked() {
		List<Helper> helpers = ATLUtils.getAllHelpers(model).stream().
				filter(h -> ! AnalyserUtils.isAddedEOperation(h)).
				filter(h -> ! isPropertyRepresentation(h)).
				filter(h -> ! isOperationImplementation(h)).
				map(h -> {
					if ( h instanceof ContextHelper ) {
						return AnalyserUtils.convertContextInvariant((ContextHelper) h);
					} else {
						return h;
					}
				}).
				collect(Collectors.toList());
		return helpers.stream().map(h -> ATLUtils.getBody(h)).collect(Collectors.toList());
	}

	private List<ContextHelper> getDerivedPropertiesOrOperations() {
		return ATLUtils.getAllHelpers(model).stream().			
				filter(h -> isPropertyRepresentation(h) || isOperationImplementation(h)).
				map(h -> (ContextHelper) h).
				collect(Collectors.toList());
	}
	
	
	private boolean isPropertyRepresentation(Helper h) {
		return h.getAnnotations().containsKey("DERIVED_PROPERTY");
	}

	private boolean isOperationImplementation(Helper h) {
		return h.getAnnotations().containsKey("OPERATION_IMPLEMENTATION");
	}

	public class ConstraintSatisfactionProblem implements IDetectedProblem {

		@Override
		public ErrorSlice getErrorSlice(IAnalyserResult result) {
			ErrorSlice slice = new ErrorSlice(result, result.getNamespaces().getLogicalNamesToMetamodels().keySet(), this);
			
			getExpressionsToBeChecked().forEach(e -> OclSlice.slice(slice, e));
			getDerivedPropertiesOrOperations().forEach(h -> {
				OclSlice.slice(slice, ATLUtils.getHelperBody(h));
				slice.addHelper(h);	
			});
			
			return slice;
		}

		
		
		@Override
		public OclExpression getWitnessCondition() {
			OclExpression witness = getWitnessConditionRaw(); 
			if ( globalAccessVariableName != null ) {		
				OclModelElement m = OCLFactory.eINSTANCE.createOclModelElement();
				m.setName(Analyser.USE_THIS_MODULE_CLASS);
	
				OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
				op.setOperationName("allInstances");
				op.setSource(m);
				
				IteratorExp exists = OCLFactory.eINSTANCE.createIteratorExp();
				exists.setName("exists");
				exists.setSource(op);
				Iterator it = OCLFactory.eINSTANCE.createIterator();
				it.setVarName("thisModule");
				exists.getIterators().add(it);
				
				// Copy needed because the OCL slice wants to refer to the original element which
				// used to be allocated into a helper, but if we don't copy it is no longer the case
				exists.setBody((OclExpression) ATLCopier.copySingleElement(witness));
				
				witness = exists;
			}
			
			return witness;
		}
		
		public OclExpression getWitnessConditionRaw() {
			List<OclExpression> exprs = getExpressionsToBeChecked();
			if ( exprs.size() == 1 ) 
				return exprs.get(0);
			
			
			OclExpression result;
			if ( exprs.size() == 0 ) {
				result = OCLFactory.eINSTANCE.createBooleanExp();
				((BooleanExp) result).setBooleanSymbol(true);
			} else {
				result = (OclExpression) ATLCopier.copySingleElement(exprs.get(0));
			}
			for(int i = 1; i < exprs.size(); i++) {
				OclExpression exp = exprs.get(i);
				
				OperatorCallExp andOp = OCLFactory.eINSTANCE.createOperatorCallExp();
				andOp.setOperationName("and");
				andOp.setSource(result);
				// Needs to be copied because there will be other calls to getExpressionsToBeChecked
				// and adding exp to the "and" will change the helper body
				andOp.getArguments().add( (OclExpression) ATLCopier.copySingleElement(exp) );
				
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

	// This adapts the ATL representation for one OCL dialect to fit another OCL representation
	// e.g., EMF/OCL to USE
	public static interface IOCLDialectTransformer {
		/** If it is a a post transformer, it will receive an analysis result */
		public void adapt(ATLModel model, IAnalyserResult result);			
	}

	public ConstraintSatisfactionChecker withPreAnalysisAdapter(IOCLDialectTransformer fixer) {
		preAdapters.add(fixer);
		return this;
	}

	public ConstraintSatisfactionChecker withPostAnalysisAdapter(IOCLDialectTransformer fixer) {
		postAdapters.add(fixer);
		return this;
	}

	public ConstraintSatisfactionChecker withOclStdLibrary(IOclStandardLibrary stdLibrary) {
		this.stdLibrary = stdLibrary;
		return this;
	}

	public ConstraintSatisfactionChecker withRequiredHelpers(Set<Helper> helpers) {
		this.requiredHelpers.addAll(helpers);
		return this;
	}

	public ConstraintSatisfactionChecker withGlobal(String globalAccessVariableName) {
		this.globalAccessVariableName = globalAccessVariableName;
		return this;
	}


}
