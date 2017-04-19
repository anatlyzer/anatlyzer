package anatlyzer.atl.analyser.batch.invariants;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class DenormalizeInvariantToATL extends Denormalizer  { 


	public DenormalizeInvariantToATL(OclExpression preNorm, ATLModel model) {
		super(preNorm, model);
	}

	public OclExpression perform() {
		// rewrite
		new ReplaceProductOperations().startVisiting(normalizedExpr);
		startVisiting(normalizedExpr);
		return normalizedExpr;
	}
	
	public class ReplaceProductOperations extends AbstractVisitor {
		@Override
		public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
			if ( isProductOperation(self) ) {
				CollectionOperationCallExp flattenedIterators = genCrossProductExpressionUsingMap(self);
				EcoreUtil.replace(self, flattenedIterators);
			}
		}
	}
	
	protected HashMap<Iterator, Iterator> it2it = new HashMap<>();

	// Similar to DenormalizeInvariantToUse
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
	
	// Similar to DenormalizeInvariantToUse
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
	
	
	private CollectionOperationCallExp genCrossProductExpressionUsingMap(CollectionOperationCallExp self) {
		// General case.
		// T1.allInstances()->map(t1 | T2.allInstances()->map(t2 | Tuple { t1 = t1, t2 = t2 }))
		// builder.openEmptyScope();
		
		List<OclModelElement> types = getTypes(self);
		List<String> varNames = getVarNames(self);
		Iterator[] iteratorVars = new Iterator[types.size()];
		
		CSPModel builder = new CSPModel();
				
		OperationCallExp op = createAllInstances(types.get(0));
		IteratorExp innerMap = builder.createIterator(op, "collect", varNames.get(0));
		iteratorVars[0] = innerMap.getIterators().get(0);
		IteratorExp externalMap = innerMap;
		
		// builder.addToScope(rule.getInPattern().getElements().get(0), innerMap.getIterators().get(0));
		
		// I do this in a separate piece of code because the other one is known to work...
		for (int i = 1; i < types.size(); i++) {
			OclModelElement e = types.get(i);
			String varName = varNames.get(i);
			
			op = createAllInstances(e);
			IteratorExp newMap = builder.createIterator(op, "collect", varName);
			iteratorVars[i] = newMap.getIterators().get(0);
			
			innerMap.setBody(newMap);
			innerMap = newMap;				
		}
		
		TupleExp t = OCLFactory.eINSTANCE.createTupleExp();
		for (int i = 0; i < types.size(); i++) {
			OclModelElement e = types.get(i);
			String partName = this.createRefName((Metaclass) e.getInferredType(), i);
			
			TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
			part.setVarName(partName);
			part.setInitExpression(builder.createVarRef( iteratorVars[i] ) );
			
			t.getTuplePart().add(part);
		}
		
		innerMap.setBody(t);
		
		return builder.createCollectionOperationCall(externalMap, "flatten");
	}

	private List<String> getVarNames(CollectionOperationCallExp self) {
		if ( self.eContainer() instanceof IteratorExp ) {
			IteratorExp iterator = (IteratorExp) self.eContainer();
			return iterator.getIterators().stream().map(it -> it.getVarName()).collect(Collectors.toList());
		}
		throw new IllegalStateException();
	}

	private OperationCallExp createAllInstances(OclModelElement type) {
		OclModelElement newElement = OCLFactory.eINSTANCE.createOclModelElement();
		newElement.setName(type.getName());
		newElement.setModel((OclModel) copy(type.getModel()));
		
		OperationCallExp allInstances = OCLFactory.eINSTANCE.createOperationCallExp();
		allInstances.setOperationName("allInstances");
		allInstances.setSource(newElement);
		
		return allInstances;
	}

}
