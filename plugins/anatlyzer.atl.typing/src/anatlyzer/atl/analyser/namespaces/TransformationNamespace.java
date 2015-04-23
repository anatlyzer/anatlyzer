package anatlyzer.atl.analyser.namespaces;

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;

public class TransformationNamespace implements ITypeNamespace {

	private HashMap<String, VirtualFeature<TransformationNamespace, Operation>> operations = new HashMap<String, VirtualFeature<TransformationNamespace, Operation>>();
	private HashMap<String, VirtualFeature<TransformationNamespace, Attribute>> features   = new HashMap<String, VirtualFeature<TransformationNamespace, Attribute>>();
	private HashMap<String, VirtualFeature<TransformationNamespace, CalledRule>> calledRules  = new HashMap<String, VirtualFeature<TransformationNamespace, CalledRule>>();
	private HashMap<String, VirtualFeature<TransformationNamespace, LazyRule>> lazyRules  = new HashMap<String, VirtualFeature<TransformationNamespace, LazyRule>>();

	private ThisModuleType	theType;
	
	public TransformationNamespace() {
		theType = AnalyserContext.getTypingModel().createThisModuleType();
		theType.setMetamodelRef(this);
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		VirtualFeature<TransformationNamespace, Attribute> op = features.get(featureName);
		if ( op == null ) {
			return AnalyserContext.getErrorModel().signalNoThisModuleFeature(featureName, node);
		}
		return op.returnType;
	}


	@Override
	public boolean hasFeature(String featureName) {
		return features.containsKey(featureName);
	}


	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		if ( operationName.equals("refSetValue") ) {
			return theType; // // returns itself
		}
		
		VirtualFeature<TransformationNamespace, ?> op = operations.get(operationName);
		if ( op == null ) {
			op = calledRules.get(operationName);
			if ( op == null ) 
				op = lazyRules.get(operationName);
			
			if ( op == null ) {
				return AnalyserContext.getErrorModel().signalNoThisModuleOperation(operationName, node);
			}
		}

		if ( arguments != null )
			checkHelperArguments(op, op.definition, arguments, node);
		return op.returnType;
	}

	private void checkHelperArguments(VirtualFeature<TransformationNamespace, ?> op, Object definition, Type[] arguments, LocatedElement node) {
		if ( definition instanceof Operation ) {
			ClassNamespace.checkHelperArguments(op.featureName, ((Operation) definition).getParameters(), arguments, node);
		} else if ( definition instanceof LazyRule ) {
			LazyRule r = (LazyRule) op.definition;
			
			Type[] formalArguments       = new Type[r.getInPattern().getElements().size()];
			String[] formalArgumentNames = new String[r.getInPattern().getElements().size()];
			int i = 0;
			for(InPatternElement ipe : r.getInPattern().getElements()) {
				formalArgumentNames[i] = ipe.getVarName();
				formalArguments[i] = ipe.getInferredType();
				i++;
			}
			
			AbstractTypeNamespace.checkArguments(op.featureName, formalArguments, formalArgumentNames, arguments, node);	
		} else if ( definition instanceof CalledRule ) {
			CalledRule r = (CalledRule) op.definition;
			ClassNamespace.checkHelperArguments(op.featureName, r.getParameters(), arguments, node);
		}
	}

	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		features.put(featureName, new VirtualFeature<TransformationNamespace, Attribute>(this, featureName, returnType, attrDefinition));
	}

	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		operations.put(operationName, new VirtualFeature<TransformationNamespace, Operation>(this, operationName, returnType, opDefinition));
	}

	public void attachRule(String ruleName, Type returnType, Rule r) {
		if ( r instanceof CalledRule ) {
			calledRules.put(ruleName, new VirtualFeature<TransformationNamespace, CalledRule>(this, ruleName, returnType, (CalledRule) r));
		} else if ( r instanceof LazyRule ) {
			lazyRules.put(ruleName, new VirtualFeature<TransformationNamespace, LazyRule>(this, ruleName, returnType, (LazyRule) r));
		} else {
			throw new IllegalArgumentException("Only called rulres and lazy rules can be attached to thisModule");
		}
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		return operations.containsKey(operationName) ||
			   calledRules.containsKey(operationName) ||
			   lazyRules.containsKey(operationName);
	}

	public boolean hasLazyRule(String ruleName) {
		return lazyRules.containsKey(ruleName);
 	}
	
	public boolean hasCalledRule(String ruleName) {
		return calledRules.containsKey(ruleName);
 	}
	
	public LazyRule getLazyRule(String ruleName) {
		return lazyRules.get(ruleName).definition;
 	}
	
	public CalledRule getCalledRule(String ruleName) {
		return calledRules.get(ruleName).definition;
 	}
	
	public Operation getAttachedOperation(String operationName) {
		return operations.get(operationName).definition;
	}

	public boolean hasAttachedOperation(String operationName) {
		return operations.containsKey(operationName);
	}

	
	@Override
	public Type createType(boolean explicitOcurrence) {
		return theType;
	}

	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		throw new UnsupportedOperationException("No symbol " + operatorSymbol + " supported");
	}

	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		VirtualFeature<?, ? extends OclFeature> vf = operations.get(attributeOrOperationName);
		if ( vf == null ) {
			vf = features.get(attributeOrOperationName);
		}
		return vf == null ? null : vf.definition;
	}
	
	/**
	 * It always raises UnsupportedOperationException, since it is not possible to
	 * pass "the transformation type" explicitly to e.g., oclIsKindOf
	 */
	@Override
	public ITypeConstraint newTypeConstraint() {
		throw new UnsupportedOperationException();
	}
}
