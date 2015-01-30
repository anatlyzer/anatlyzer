package anatlyzer.atl.analyser.namespaces;

import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.OclFeature;

public class UnresolvedTypeErrorNamespace extends TypeErrorNamespace implements IClassNamespace {
	
	public UnresolvedTypeErrorNamespace(Problem p, TypeError te) {
		super(p, te);
	}

	// Class-specific methods

	/**
	 * Returns null because an unresolved type has no attached features.
	 */
	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		return null;
	}

	/**
	 * Returns null because an unresolved type has no features.
	 */
	@Override
	public EStructuralFeature getStructuralFeatureInfo(String featureName) {
		return null;
	}
	
	@Override
	public String getMetamodelName() {
		throw new UnsupportedOperationException();
	}


	@Override
	public EClass getKlass() {
		throw new UnsupportedOperationException();
	}


	@Override
	public void attachResolvingRule(String ruleName, Type returnType,
			MatchedRule rule) {
		throw new UnsupportedOperationException();
	
	}


	/**
	 * Returns an empty list.
	 */
	@Override
	public Set<MatchedRule> getResolvingRules() {
		return Collections.emptySet();
	}


	@Override
	public List<MatchedRule> getRules() {
		throw new UnsupportedOperationException();
	}


	@Override
	public Set<ClassNamespace> getAllSubclasses() {
		return Collections.emptySet();
	}

	@Override
	public Collection<ClassNamespace> getDirectSubclasses() {
		throw new UnsupportedOperationException();
	}


	/**
	 * Returns an empty list.
	 */
	@Override
	public List<ClassNamespace> getAllSuperClasses() {
		return Collections.emptyList();
	}

}
