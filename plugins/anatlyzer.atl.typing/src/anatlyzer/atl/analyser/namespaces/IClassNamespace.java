package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.types.Type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;

public interface IClassNamespace extends ITypeNamespace {

	public abstract String getMetamodelName();

	public abstract EClass getKlass();

	public abstract EStructuralFeature getStructuralFeatureInfo(String featureName);

	
	public abstract void attachResolvingRule(String ruleName, Type returnType,
			MatchedRule rule);

	public abstract Set<MatchedRule> getResolvingRules();

	public abstract List<MatchedRule> getRules();

	public abstract Set<ClassNamespace> getAllSubclasses();

	public abstract Collection<ClassNamespace> getDirectSubclasses();

	public abstract List<ClassNamespace> getAllSuperClasses();

	// Methods from ITypeNamespace (redeclared due to a refactoring)
	
	@Override 
	public abstract boolean hasFeature(String featureName);

	@Override
	public abstract Type getFeatureType(String featureName, LocatedElement node);

	/**
	 * Extends the meta-model 
	 * 
	 * @param class
	 * @param featureName
	 * @param attrDefinition 
	 * @param type 
	 */
	@Override
	public abstract void extendType(String featureName, Type returnType,
			Attribute attrDefinition);

	@Override
	public abstract void extendType(String operationName, Type returnType,
			Operation opDefinition);

	@Override
	public abstract boolean hasOperation(String operationName, Type[] arguments);

	@Override
	public abstract Operation getAttachedOperation(String operationName);

	@Override
	public abstract boolean hasAttachedOperation(String operationName);

	@Override
	public abstract OclFeature getAttachedOclFeature(
			String attributeOrOperationName);

	@Override
	public abstract Type createType(boolean explicitOcurrence);

	@Override
	public abstract Type getOperationType(String operationName,
			Type[] arguments, LocatedElement node);

	@Override
	public abstract Type getOperatorType(String operatorSymbol,
			Type optionalArgument, LocatedElement node);

	@Override
	public abstract void attachRule(String ruleName, Type returnType, Rule rule);


}