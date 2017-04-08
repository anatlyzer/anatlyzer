package anatlyzer.atl.analyser.batch.invariants;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;
import anatlyzer.atlext.processing.AbstractVisitor.VisitingActions;

public class DenormalizeInvariantToUse extends Denormalizer  {


	public static final String EXTRA_CLASS = "http://extra_class";


	public DenormalizeInvariantToUse(OclExpression preNorm) {
		super(preNorm);
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
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		if ( isProductOperation(self) ) {
			OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
			newElement.setName(getProductClassName(self));
			newElement.setModel((OclModel) copy(getTypes(self).get(0).getModel()));
			newElement.setInferredType(createMetaclass(self));
			
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
			nav.setName(refName);
			
			VariableExp vExp = OCLFactory.eINSTANCE.createVariableExp();
			vExp.setReferredVariable(newVarDcl);
			nav.setSource(vExp);
			
			EcoreUtil.replace(self, nav);
		}
	}
	
	private Type createMetaclass(CollectionOperationCallExp self) {
		Metaclass mm = TypesFactory.eINSTANCE.createMetaclass();
		mm.setKlass(getOrCreateEClass(self));
		mm.setName(mm.getKlass().getName());
		return mm;
	}

	protected HashMap<String, EClass> eClasses = new HashMap<String, EClass>();
	
	private EClass getOrCreateEClass(CollectionOperationCallExp self) {
		String name = getProductClassName(self);
		if ( eClasses.containsKey(name) ) 
			return eClasses.get(name);
		
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(EXTRA_CLASS);
		c.getEAnnotations().add(ann);
		c.setName(name);
		
		for (int i = 0; i < getTypes(self).size(); i++) {
			OclModelElement m = getTypes(self).get(i);

			EReference ref = EcoreFactory.eINSTANCE.createEReference();
			ref.setName(createRefName((Metaclass) m.getInferredType(), i));
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
