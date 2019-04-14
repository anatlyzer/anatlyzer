package anatlyzer.atl.analyser.batch.invariants;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.VariableExp;

public class DenormalizeInvariantToUse extends Denormalizer  {


	public static final String EXTRA_CLASS = "http://extra_class";


	public DenormalizeInvariantToUse(OclExpression preNorm, ATLModel model) {
		super(preNorm, model);
	}

	public void perform() {
		// rewrite
		startVisiting(normalizedExpr);
	}

	public OclExpression getResult() {
		return normalizedExpr;
	}

	public void genErrorSlice(ErrorSlice slice) {
		eClasses.values().forEach(c -> slice.addTargetMetaclassNeededInError(c));
	}

	public Collection<EClass> getClasses() {
		return eClasses.values();
	}
	
	@Override
	public void inTupleExp(TupleExp self) {
		if ( self.getAnnotations().containsKey(LazyRulePathVisitor.TUPLE_FOR_LAZY_CALL) ) {
			String ruleName = self.getAnnotations().get(LazyRulePathVisitor.TUPLE_FOR_LAZY_CALL);
			List<OclModelElement> types = getLazyRuleTypes(self, ruleName);
			
			OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
			newElement.setName(getLazyTupleClassName(self, ruleName));
			newElement.setModel((OclModel) copy(types.get(0).getModel()));
			newElement.setInferredType(createMetaclass(self, (e) -> getLazyTupleClassName(e, ruleName), (e) -> getLazyRuleTypes(e, ruleName), (mm, i) -> self.getTuplePart().get(i).getVarName() ));
			
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(newElement);
			
			// T.allInstances()->any(o | o.prop1 = v1 and o.prop2 = v2...)
			// * For USE we cannot use "any" safely, so when possible we use "select"
			// * We check if the tuple is in a position in which the select may work
			String iteratorName = self.eContainer() instanceof LoopExp ? "select" : "any";
			
			IteratorExp any = OCLFactory.eINSTANCE.createIteratorExp();
			Iterator it = OCLFactory.eINSTANCE.createIterator();
			it.setVarName("o_");
			any.getIterators().add(it);
			// any.setName("any");
			any.setName( iteratorName );
			any.setSource(allInstances);
						
			Optional<OperatorCallExp> body = self.getTuplePart().stream().map(p -> {
				NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				nav.getAnnotations().put("TUPLE_ACCESS", "true");
				VariableExp vexp = OCLFactory.eINSTANCE.createVariableExp();
				vexp.setReferredVariable(it);
				nav.setSource(vexp);
				nav.setName(p.getVarName());
				
				OperatorCallExp eq = OCLFactory.eINSTANCE.createOperatorCallExp();
				eq.setOperationName("=");
				eq.setSource(nav);
				eq.getArguments().add(p.getInitExpression());

				return eq;
			}).collect(Collectors.reducing((l, r) -> {
				OperatorCallExp op = OCLFactory.eINSTANCE.createOperatorCallExp();
				op.setOperationName("and");
				op.setSource(l);
				op.getArguments().add(r);
				return op;
			}));
			
			any.setBody(body.orElseThrow(() -> new IllegalStateException()));
			
			EcoreUtil.replace(self, any);			
		}
	}
	
	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		if ( isProductOperation(self) ) {
			OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
			newElement.setName(getProductClassName(self));
			newElement.setModel((OclModel) copy(getTypes(self).get(0).getModel()));
			newElement.setInferredType(createMetaclass(self, this::getProductClassName, this::getTypes, this::createRefName));
			
			OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
			allInstances.setOperationName("allInstances");
			allInstances.setSource(newElement);
			
			EcoreUtil.replace(self, allInstances);
		}
	}
	
	protected HashMap<Iterator, Iterator> it2it = new HashMap<>();

	@Override
	public void beforeIteratorExp(IteratorExp self) {
		if ( self.getIterators().size() > 1 ) {		
			Iterator it = OCLFactory.eINSTANCE.createIterator();
			it.setVarName(self.getIterators().stream().map(i -> i.getVarName()).collect(Collectors.joining("_")));
			
			self.getIterators().forEach(oldIt -> it2it.put(oldIt, it));		
			
			self.getIterators().clear();
			self.getIterators().add(it);
		}
	}

	@Override
	public void inVariableExp(VariableExp self) {
		if ( it2it.containsKey(self.getReferredVariable() )) {
			Iterator newVarDcl = it2it.get(self.getReferredVariable());
			String refName = annotation.getReference(self.getReferredVariable());
			
			if ( newVarDcl == null || refName == null ) 
				throw new IllegalStateException();
			
			NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			nav.getAnnotations().put("TUPLE_ACCESS", "true");
			nav.setName(refName);
			
			VariableExp vExp = OCLFactory.eINSTANCE.createVariableExp();
			vExp.setReferredVariable(newVarDcl);
			nav.setSource(vExp);
			
			EcoreUtil.replace(self, nav);
		}
	}
	
	private <T extends OclExpression> Type createMetaclass(T self, Function<T, String> classNameGetter, Function<T, List<OclModelElement>> typeGetter, BiFunction<Metaclass, Integer, String> refNameGetter) {
		Metaclass mm = TypesFactory.eINSTANCE.createMetaclass();
		mm.setKlass(getOrCreateEClass(self, classNameGetter, typeGetter, refNameGetter));
		mm.setName(mm.getKlass().getName());
		return mm;
	}

	protected HashMap<String, EClass> eClasses = new HashMap<String, EClass>();
	
	private <T extends OclExpression> EClass getOrCreateEClass(T self, Function<T, String> classNameGetter, Function<T, List<OclModelElement>> typeGetter, BiFunction<Metaclass, Integer, String> refNameGetter) {
	//private EClass getOrCreateEClass(CollectionOperationCallExp self) {
		String name = classNameGetter.apply(self); //getProductClassName(self);
		if ( eClasses.containsKey(name) ) 
			return eClasses.get(name);
		
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(EXTRA_CLASS);
		c.getEAnnotations().add(ann);
		c.setName(name);
		
		List<OclModelElement> types = typeGetter.apply(self);
		for (int i = 0; i < types.size(); i++) {
			OclModelElement m = types.get(i);

			EReference ref = EcoreFactory.eINSTANCE.createEReference();
			ref.setName(refNameGetter.apply((Metaclass) m.getInferredType(), i));
			ref.setEType(((Metaclass) m.getInferredType()).getKlass());
			// ref.setContainment(true);
			ref.setContainment(false);
			ref.setLowerBound(1);
			ref.setUpperBound(1);
			c.getEStructuralFeatures().add(ref);
		}
		
		eClasses.put(name, c);
		return c;
	}

	

}
