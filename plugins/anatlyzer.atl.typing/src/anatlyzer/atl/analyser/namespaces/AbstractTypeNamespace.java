package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.libtypes.AtlTypeDef;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableExp;

public abstract class AbstractTypeNamespace implements ITypeNamespace {

	protected HashMap<String, FeatureInfo> featureInfos = new HashMap<String, FeatureInfo>();
	
	protected FeatureInfo addFeatureInfo(String featureName, Type t) {
		FeatureInfo info = new FeatureInfo(featureName, t);
		featureInfos.put(featureName, info);
		return info;
	}
	
	protected FeatureInfo addFeatureInfo(String featureName, Type t, EStructuralFeature f) {
		FeatureInfo info = new FeatureInfo(featureName, t, f);
		featureInfos.put(featureName, info);
		return info;
	}
	
	@Override
	public boolean hasFeature(String featureName) {
		return AnalyserContext.getOclAnyInheritedNamespace().hasFeature(featureName);
	}
	
	/**
	 * Checks for common inherited operations. 
	 * Does not have any side effect.
	 * @param featureName
	 * @param node Can be null.
	 */
	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		return AnalyserContext.getOclAnyInheritedNamespace().getFeatureType(featureName, node);
	}
	
	@Override
	public Type getFeatureType(String featureName, LocatedElement node, Consumer<Type> onImplicitCasting) {
		return getFeatureType(featureName, node);
	}
	
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		if ( AnalyserContext.getOclAnyInheritedNamespace().hasOperation(operationName, arguments) )
			return true;
		
		return operationName.equals("oclIsUndefined") || operationName.equals("toString") ||
				operationName.equals("oclIsKindOf") || operationName.equals("oclIsTypeOf");
	}
	
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = AnalyserContext.getOclAnyInheritedNamespace().getOperationType(operationName, arguments, node);
		if ( t != null )
			return t;
				
		// TODO: Replace this for libtypes
		if ( operationName.equals("oclIsUndefined") ) {
			checkArguments("oclIsUndefined", new Type[] {}, new String[] { }, arguments, node);
			return AnalyserContext.getTypingModel().newBooleanType();
		} else if ( operationName.equals("toString") ) {
			checkArguments("toString", new Type[] {}, new String[] { }, arguments, node);
			return AnalyserContext.getTypingModel().newStringType();
		} else if ( operationName.equals("oclIsKindOf") || operationName.equals("oclIsTypeOf")) {
			if ( arguments.length != 1 ) {
				Type formalArgs[] = new Type[] { AnalyserContext.getTypingModel().newOclType() };
				AnalyserContext.getErrorModel().signalOperationCallInvalidNumberOfParameters("oclIsKindOf", formalArgs , arguments, node);
				return AnalyserContext.getTypingModel().newBooleanType();				
			}
			if ( ! (arguments[0] instanceof Metaclass) ) {
				AnalyserContext.getErrorModel().signalInvalidArgument(operationName, "Expected class", node);
				return AnalyserContext.getTypingModel().newBooleanType();				
			}
			
			OperationCallExp op = (OperationCallExp) node;
			if ( op.getSource() instanceof VariableExp ) {
				return AnalyserContext.getTypingModel().newBooleanType((Metaclass) arguments[0]);				
			} else {
				return AnalyserContext.getTypingModel().newBooleanType();				
			}
		} else if ( operationName.equals("debug") ) {
			return this.getType();
		}
 
		AtlTypeDef typeDef = AnalyserContext.getStdLib().getTypeDefOf(this);
		if ( typeDef != null ) {
			return typeDef.getOperationReturnType(operationName);
		}

		
		return null;
	}

	protected static void checkArguments(String operationName, Type[] formalArguments, String[] formalArgumentsNames, Type[] arguments, LocatedElement node) {
		if ( formalArguments.length != arguments.length ) {
			AnalyserContext.getErrorModel().signalOperationCallInvalidNumberOfParameters(operationName, formalArguments, arguments, node);
			return;
		}

		List<String> blamedParameters = new ArrayList<String>();
		for(int i = 0; i < formalArguments.length; i++) {
			if ( ! AnalyserContext.getTypingModel().assignableTypes(formalArguments[i], arguments[i]) ) {
				blamedParameters.add(formalArgumentsNames[i]);
			}
		}

		if ( blamedParameters.size() > 0 ) {
			AnalyserContext.getErrorModel().signalOperationCallInvalidParameter(operationName, formalArguments, arguments, blamedParameters, node);
		}
	}
	
	public static final Set<String> logicalOperators = new HashSet<String>();
	static {
		logicalOperators.add("=");
		logicalOperators.add("<>");
		logicalOperators.add(">");
		logicalOperators.add(">=");
		logicalOperators.add("<");
		logicalOperators.add("<=");
		
	}
	
	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( logicalOperators.contains(operatorSymbol) ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}
		return null;
	}

	private Type getType() {
		// This should be change for a "type" reference in the hierarchy, instead of
		// creating a type each time, then createType() replace for cloneType in case this is actually needed
		return createType(false);
	}
	
	@Override
	public ITypeConstraint newTypeConstraint() {
		throw new UnsupportedOperationException(this.getClass().getName());
	}
}
