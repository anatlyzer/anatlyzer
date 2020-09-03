package anatlyzer.atl.analyser.uncovered;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.util.ConstraintBuilder;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableExp;

public class UncoveredElementsAnalysis {

	@NonNull 
	private final IAnalyserResult result;

	public UncoveredElementsAnalysis(@NonNull IAnalyserResult result) {
		this.result = result;
	}
	
	/**
	 * This is phase 1: Detect which classes are not covered, and why
	 */
	public CoverageData getUncoveredClasses() {
		CoverageData data = new CoverageData();
		
		Set<EClass> allClasses = AnalyserUtils.getInputNamespaces(result).stream().flatMap(ns -> ns.getAllClasses().stream()).collect(Collectors.toSet());
		Set<EClass> abstractClases = allClasses.stream().filter(EClass::isAbstract).collect(Collectors.toSet());
		Set<EClass> classes = allClasses.stream().filter(c -> ! c.isAbstract()).collect(Collectors.toSet());

		Map<EClass, List<RuleWithPattern>> partialCoverage = new HashMap<>();
		Map<EClass, List<RuleWithPattern>> fullCoverage = new HashMap<>();
		
		// 1. Check which is covered by checking all rules
		List<Rule> rules = result.getATLModel().allObjectsOf(Rule.class);
		for (Rule rule : rules) {
			if (rule instanceof RuleWithPattern) {
				RuleWithPattern rwp = (RuleWithPattern) rule;
				if (ATLUtils.isOneOneRule(rwp)) {
					Metaclass inElement = ATLUtils.getInPatternType(rwp);
					EClass inputClass = inElement.getKlass();
					
					for (EClass eClass : new HashSet<>(classes)) {
						// eClass == inputClass or eClass a subtype of inputClass
						if (TypeUtils.isClassAssignableTo(eClass, inputClass)) {
							// This is covered somehow
							classes.remove(eClass);
							
							boolean partiallyCovered = rwp.getInPattern().getFilter() != null || rwp instanceof LazyRule;
							if (partiallyCovered) {
								List<RuleWithPattern> l = partialCoverage.computeIfAbsent(eClass, (k) -> new ArrayList<>());
								l.add(rwp);
							} else {
								List<RuleWithPattern> l = fullCoverage.computeIfAbsent(eClass, (k) -> new ArrayList<>());
								l.add(rwp);								
							}
						}		
					}
				}
			}
		}
		
		// Not sure if both sets should be disjunct, so just to be safe
		fullCoverage.keySet().forEach(partialCoverage::remove);		
		
		for (EClass abstractClass : abstractClases) {
			boolean covered = allClasses.stream().
					filter(subclass -> ! subclass.isAbstract()).
					filter(subclass -> subclass.getEAllSuperTypes().contains(abstractClass)).
					allMatch(subclass -> fullCoverage.containsKey(subclass));
			if (covered) {
				List<RuleWithPattern> coveringRules = allClasses.stream().
					filter(subclass -> ! subclass.isAbstract()).
					filter(subclass -> subclass.getEAllSuperTypes().contains(abstractClass)).
					flatMap(subclass -> fullCoverage.get(subclass).stream()).collect(Collectors.toList());

				data.addFullyCovered(abstractClass, coveringRules);
			} else {
				// Maybe partially covered is subclasses cover
				data.addNotCovered(abstractClass);
			}
		}
		
		fullCoverage.forEach((k, v) -> data.addFullyCovered(k, v));
		classes.forEach(v -> data.addNotCovered(v));		
		partialCoverage.forEach((k, v) -> data.addPartiallyCovered(k, v));
		
		return data;
	}
	
	
	public static class CoverageData {

		private Set<ClassCoverage> coverage = new HashSet<>();
		
		public void addFullyCovered(EClass k, List<RuleWithPattern> v) {
			coverage.add(new FullyCovered(k, v));
		}

		public void addPartiallyCovered(EClass klass, List<? extends RuleWithPattern> v) {
			coverage.add(new PossiblyUncoveredClass(klass, v));
		}

		public void addNotCovered(EClass v) {
			coverage.add(new FullyUncoveredClass(v));
		}
		
		@NonNull
		public List<UncoveredClass> getUncoveredClasses() {
			return coverage.stream().
					filter(c -> c instanceof UncoveredClass).
					map(c -> (UncoveredClass) c).
					collect(Collectors.toList());
		}
		

		public ClassCoverage getCoverage(EClass element) {
			for (ClassCoverage c : coverage) {
				if (c.getEClass() == element)
					return c;
			}
			
			return null;
		}
	}
	

	public interface ClassCoverage {
		public EClass getEClass();

		List<? extends RuleWithPattern> getRules();
	}
	
	public static class FullyCovered implements ClassCoverage {
		private EClass klass;
		private List<? extends RuleWithPattern> rules;

		public FullyCovered(EClass klass, List<? extends RuleWithPattern> rules) {
			this.klass = klass;
			this.rules = rules;
		}
		
		@Override
		public List<? extends RuleWithPattern> getRules() {
			return rules;
		}
		
		@Override
		public EClass getEClass() {
			return klass;
		}		
	}
	
	public static abstract class UncoveredClass implements ClassCoverage {

		private EClass klass;

		public UncoveredClass(EClass klass) {
			this.klass = klass;
		}
		
		@Override
		public EClass getEClass() {
			return klass;
		}
		
		@Override
		public List<? extends RuleWithPattern> getRules() {
			return Collections.emptyList();
		}
		
	}
	
	public static class FullyUncoveredClass extends UncoveredClass {

		public FullyUncoveredClass(EClass klass) {
			super(klass);
		}
	
	}
	
	public static class PossiblyUncoveredClass extends UncoveredClass {
		private List<? extends RuleWithPattern> rules;

		public PossiblyUncoveredClass(EClass klass, List<? extends RuleWithPattern> rules) {
			super(klass);
			this.rules = rules;
		}
		
		@Override
		public List<? extends RuleWithPattern> getRules() {
			return rules;
		}
	}
	
	public static class ExtendableTestCase {
		private final Resource resource;
		private final EClass container;
		private final EStructuralFeature feature;
		private final EClass targetEClass;

		public ExtendableTestCase(@NonNull Resource res, @NonNull EClass container, EStructuralFeature f, EClass targetEClass) {
			this.resource = res;
			this.container = container;
			this.feature = f;
			this.targetEClass = targetEClass;
		}
		
		public Resource getResource() {
			return resource;
		}
		
		public EClass getEClass() {
			return container;
		}
		
		public EClass getTargetEClass() {
			return targetEClass;
		}
		
		public EStructuralFeature getFeature() {
			return feature;
		}
				
	}

	@NonNull
	public ConstraintBuilder extendTestCase(@NonNull UncoveredClass c, @NonNull ExtendableTestCase extendable) {
		EClass rootClass = extendable.container;
		EStructuralFeature feature = extendable.feature;
		
		OperationCallExp allInstances = createAllInstances(rootClass);
		
		IteratorExp exists = OCLFactory.eINSTANCE.createIteratorExp();
		exists.setName("exists");
		exists.setSource(allInstances);
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName("it_");
		exists.getIterators().add(it);
		
		NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
		nav.setName(feature.getName());
		VariableExp varExp = OCLFactory.eINSTANCE.createVariableExp();
		varExp.setReferredVariable(it);
		nav.setSource(varExp);
		
		if (feature.isMany()) {
			OperationCallExp colNotEmpty = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			colNotEmpty.setOperationName("notEmpty");
			colNotEmpty.setSource(nav);
			
			exists.setBody(colNotEmpty);
		} else {
			OperatorCallExp operator = OCLFactory.eINSTANCE.createOperatorCallExp();
			operator.setOperationName("not");
			OperationCallExp undefined = OCLFactory.eINSTANCE.createOperationCallExp();
			undefined.setOperationName("oclIsUndefined");
			undefined.setSource(nav);
			operator.setSource(undefined);
			
			exists.setBody(operator);
		}
		
		// exists is the condition to be satisfied
	
		ConstraintBuilder builder = new ConstraintBuilder();
		builder.addExpression(exists);
		
		// At least one instance of the target class, to make sure it is instantiated
		OperationCallExp allInstances2 = createAllInstances(extendable.targetEClass);
		OperationCallExp op = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		op.setOperationName("size");
		op.setSource(allInstances2);
		OperatorCallExp greater = OCLFactory.eINSTANCE.createOperatorCallExp();
		greater.setOperationName(">");
		greater.setSource(op);
		IntegerExp integer = OCLFactory.eINSTANCE.createIntegerExp();
		integer.setIntegerSymbol(1);
		greater.getArguments().add(integer);
		
		builder.addExpression(greater);
		
		return builder;
	}

	private OperationCallExp createAllInstances(EClass rootClass) {
		Metaclass mmc = createMetaclass(rootClass);
		
		OclModel m = OCLFactory.eINSTANCE.createOclModel();
		m.setName("MM");		
		
		OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
		me.setModel(m);
		me.setName(rootClass.getName());
		me.setInferredType(mmc);
		
		OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
		allInstances.setOperationName("allInstances");
		allInstances.setSource(me);
		return allInstances;
	}

	private Metaclass createMetaclass(EClass rootClass) {
		Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
		metaclass.setKlass(rootClass);
		metaclass.setName(rootClass.getName());
		return metaclass;
	}
}
