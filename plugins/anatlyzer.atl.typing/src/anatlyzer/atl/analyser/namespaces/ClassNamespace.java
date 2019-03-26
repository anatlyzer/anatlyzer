package anatlyzer.atl.analyser.namespaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.recovery.RecoverOperationNotFound;
import anatlyzer.atl.analyser.typeconstraints.ITypeConstraint;
import anatlyzer.atl.analyser.typeconstraints.MetaclassTypeConstraint;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;

public class ClassNamespace extends AbstractTypeNamespace implements IClassNamespace {
	protected EClass	eClass;
	protected MetamodelNamespace	metamodel;
	protected HashMap<String, VirtualFeature<ClassNamespace, Attribute>> features = 
				new HashMap<String, VirtualFeature<ClassNamespace, Attribute>>();
	protected HashMap<String, VirtualFeature<ClassNamespace, Operation>> operations = 
				new HashMap<String, VirtualFeature<ClassNamespace, Operation>>();
	protected Metaclass	theType;

	
	public ClassNamespace(MetamodelNamespace metamodel, EClass eclass) {
		this.eClass = eclass;
		this.metamodel = metamodel;
	}		

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getMetamodelName()
	 */
	@Override
	public String getMetamodelName() {
		return this.metamodel.getName();
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getKlass()
	 */
	@Override
	public EClass getKlass() {
		return eClass;
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#hasFeature(java.lang.String)
	 */	
	@Override
	public boolean hasFeature(String featureName) {
		return getExtendedFeature(featureName) != null ||
				 eClass.getEStructuralFeature(featureName) != null || 
				 super.hasFeature(featureName);
	}	


	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getFeatureInfo(java.lang.String)
	 */
	@Override
	public EStructuralFeature getStructuralFeatureInfo(String featureName) {
		return eClass.getEStructuralFeature(featureName);
	}

	@Override
	public Type getFeatureType(String featureName, LocatedElement node) {
		return getFeatureType(featureName, node, null);
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getFeatureType(java.lang.String, anatlyzer.atlext.ATL.LocatedElement)
	 */
	@Override
	public Type getFeatureType(String featureName, LocatedElement node, Consumer<Type> onImplicitCasting) {
		FeatureInfo info = getFeatureInfo(featureName);
		// This check is needed because getFeatureType has the side effect of signalling
		// an error is the feature is finally not found. Thus, if in a previous execution
		// of getFeatureInfo that was the case, then subsequent errors (in other nodes) must
		// be signalled as well.
		if ( info == null ) {
			return tryRecovery(featureName, node, onImplicitCasting);
		}		
		return info.getType();
	}

	/**
	 * Return information about a feature.
	 *  
	 * This method does not have any side effect, w.r.t the error model. If the
	 * feature cannot be found it just returns null.
	 * 
	 * @param featureName The name of the feature.
	 * @param node
	 * @return the information about the feature or null if it cannot be found.
	 */
	public FeatureInfo getFeatureInfo(String featureName) {
		FeatureInfo info = featureInfos.get(featureInfos);
		if ( info != null ) {
			return info;
		}
		
		Type t = getExtendedFeature(featureName);
		if ( t != null) {
			return addFeatureInfo(featureName, t);
		}
		
		t = super.getFeatureType(featureName, null);
		if ( t != null ) {
			return addFeatureInfo(featureName, t);
		}
		
		EStructuralFeature f = eClass.getEStructuralFeature(featureName);
		if ( f == null ) {
			return null;
		}
		
		return addFeatureInfo(featureName, AnalyserContext.getConverter().convert(f, metamodel), f);
	}

	private Type getExtendedFeature(String featureName) {
		if ( metamodel.featureNames.contains(featureName) ) {
			if ( features.containsKey(featureName) ) {
				return features.get(featureName).returnType;
			}

			for (EClass supertype : eClass.getEAllSuperTypes()) {
				if ( supertype.eIsProxy() ) {
					continue; // TODO: Don't know how to deal with proxies...
				}
				
				ClassNamespace cn = (ClassNamespace) metamodel.getClassifier(supertype.getName());
				if ( cn.features.containsKey(featureName) ) {
					return cn.features.get(featureName).returnType;
				}
			}
		}
		return null;
	}
	
	private Type tryRecovery(String featureName, LocatedElement node, Consumer<Type> onImplicitCasting) {			
		if ( ! this.getKlass().isAbstract() ) {
			return AnalyserContext.getErrorModel().signalNoFeature(eClass, featureName, node);
		}
		
		HashSet<Metaclass> foundClasses = new HashSet<Metaclass>();
		Type returnedType = null;
		
		LinkedList<ClassNamespace> pending = new LinkedList<ClassNamespace>();
		HashSet<EClass> withoutOperation = new HashSet<EClass>();
		pending.add(this);

		while ( ! pending.isEmpty() ) {
			ClassNamespace ns = pending.pop();
			EClass subtype = ns.eClass;
			boolean operationExistsForType = false;
			
			EStructuralFeature f = subtype.getEStructuralFeature(featureName);
			if (f != null) {
				foundClasses.add(ns.getType());
				if ( returnedType == null ) {
					returnedType = AnalyserContext.getConverter().convert(f, metamodel);					
				}				
				operationExistsForType = true;
			} else {			
				if ( ns.hasFeature(featureName)) {
					foundClasses.add(ns.getType());
					if ( returnedType == null ) {
						returnedType = ns.getFeatureType(featureName, node);
					}
					operationExistsForType = true;
				}		
			}

			if ( ! operationExistsForType ) {
				Collection<ClassNamespace> direct = ns.getDirectSubclasses();
				if ( direct.size() > 0 ) {
					pending.addAll(direct);
				} else {
					withoutOperation.add(ns.eClass);
				}
			}			
		}
		
		if ( foundClasses.size() > 0 && withoutOperation.size() > 0 ) {			
			AnalyserContext.getErrorModel().warningFeatureFoundInSubType(getType(), foundClasses.stream().map(m -> m.getKlass()).collect(Collectors.toSet()), withoutOperation, featureName, node);
			return returnedType;
		} else if ( foundClasses.size() > 0 && returnedType != null ) {
			// This is basically a duck typed operation, so that the receptor type is basically casted
			// to all compatible subtypes
			Type castedType = AnalyserContext.getTypingModel().getCommonType(new ArrayList<Metaclass>(foundClasses));
			onImplicitCasting.accept(castedType);
			return returnedType;
		} else {
			// TODO: Check that the return type of all operations is compatible, actually the 
			// same type
			if ( returnedType != null )	{
				return returnedType;
			} else {
				return AnalyserContext.getErrorModel().signalNoFeature(eClass, featureName, node);
			}
		}

		
		/*
		for(ClassNamespace c : getAllSubclasses()) {
			EClass subtype = c.eClass;
			
			EStructuralFeature f = subtype.getEStructuralFeature(featureName);
			if (f != null) {
				Metaclass t = (Metaclass) metamodel.getClassifier(subtype.getName()).createType(false);
				foundClasses.add(t.getKlass());
				if ( returnedType == null ) {
					returnedType = AnalyserContext.getConverter().convert(f, metamodel);					
				}
			} else {			
				// Check extended features
				ClassNamespace cn = (ClassNamespace) metamodel.getClassifier(subtype.getName());
				if ( cn.hasFeature(featureName)) {
					foundClasses.add(cn.getType().getKlass());
					if ( returnedType == null ) {
						returnedType = cn.getFeatureType(featureName, node);
					}
				}		
			}
		}
		
		if ( foundClasses.size() > 0 ) {			
			AnalyserContext.getErrorModel().warningFeatureFoundInSubType(getType(), TypeUtils.getUpperInHierarchy(foundClasses), featureName, node);
			return returnedType;
		}
		*/
		
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#extendType(java.lang.String, anatlyzer.atl.types.Type, anatlyzer.atlext.OCL.Attribute)
	 */
	@Override
	public void extendType(String featureName, Type returnType, Attribute attrDefinition) {
		features.put(featureName, new VirtualFeature<ClassNamespace, Attribute>(this, featureName, returnType, attrDefinition));
		metamodel.featureNames.add(featureName);
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#extendType(java.lang.String, anatlyzer.atl.types.Type, anatlyzer.atlext.OCL.Operation)
	 */
	@Override
	public void extendType(String operationName, Type returnType, Operation opDefinition) {
		operations.put(operationName, new VirtualFeature<ClassNamespace, Operation>(this, operationName, returnType, opDefinition));
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#hasOperation(java.lang.String, anatlyzer.atl.types.Type[])
	 */
	@Override
	public boolean hasOperation(String operationName, Type[] arguments) {
		if ( super.hasOperation(operationName, arguments) )
			return true;
		
		// TODO: Synchronize well with the operations defined by getOperation
		if ( operationName.equals("allInstances") || operationName.equals("allInstancesFrom") || operationName.equals("refImmediateComposite") || operationName.equals("oclType")) 
			return true;

		// Reflective
		if ( operationName.equals("newInstance") || operationName.equals("refSetValue") ) 
			return true;

		boolean hasOperation = operations.containsKey(operationName);
		
		if ( ! hasOperation ) {
			// Similar to getOperation
			// Not sure this is totally valid because of the traversal order
			EList<EClass> supertypes = eClass.getESuperTypes();			
			for (EClass c : supertypes) {
				if ( c.eIsProxy() ) continue; // proxies again...
				ITypeNamespace n = metamodel.getClass(c);
				if ( n.hasOperation(operationName, arguments) ) {
					hasOperation = true;
					break;
				}			
			}
		}
		
		return hasOperation;
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getAttachedOperation(java.lang.String)
	 */
	@Override
	public Operation getAttachedOperation(String operationName) {
		return operations.get(operationName).definition;
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#hasAttachedOperation(java.lang.String)
	 */
	@Override
	public boolean hasAttachedOperation(String operationName) {
		return operations.containsKey(operationName);
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getAttachedOclFeature(java.lang.String)
	 */
	@Override
	public OclFeature getAttachedOclFeature(String attributeOrOperationName) {
		VirtualFeature<ClassNamespace, ? extends OclFeature> vf = operations.get(attributeOrOperationName);
		if ( vf == null ) {
			vf = features.get(attributeOrOperationName);
		}
		return vf == null ? null : vf.definition;
	}
	
	public Metaclass getType() {
		if ( this.theType == null )
			this.theType = (Metaclass) createType(true);
		return this.theType;
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#createType(boolean)
	 */
	@Override
	public Type createType(boolean explicitOcurrence) {
		// TODO: Not sure if this should cache the result... to have only one type
		// per metaclass
		// To enable homogenous access to getType, do always a getType to make sure it is assigned
		if ( this.theType == null ) {		
			Metaclass m = AnalyserContext.getConverter().convertEClass(eClass, this);
			m.setExplicitOcurrence(explicitOcurrence);
			theType = m;
			// See getFrameConditions in PossibleInvariantNode
		}
		
		Metaclass m = AnalyserContext.getConverter().convertEClass(eClass, this);
		m.setExplicitOcurrence(explicitOcurrence);
		return m;
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getOperationType(java.lang.String, anatlyzer.atl.types.Type[], anatlyzer.atlext.ATL.LocatedElement)
	 */
	@Override
	public Type getOperationType(String operationName, Type[] arguments, LocatedElement node) {
		Type t = super.getOperationType(operationName, arguments, node);
		if ( t != null ) {
			return t;
		}
		
		if ( operationName.equals("allInstances") || operationName.equals("allInstancesFrom") ) {
			return AnalyserContext.getConverter().convertAllInstancesOf(eClass, this);
		} else if ( operationName.equals("newInstance") || operationName.equals("refSetValue") ) {
			return AnalyserContext.getConverter().convertEClass(eClass, this);
		} else if ( operationName.equals("oclIsUndefined") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		} else if ( operationName.equals("oclType") ) {
			return AnalyserContext.getTypingModel().newOclType(createType(true));
		} else if ( operationName.equals("refImmediateComposite") ) {
			return findTypeOfContainer(node);
		} else if ( operationName.equals("refSetValue") ) {
			return getType(); // returns itself
		}

		VirtualFeature<ClassNamespace, Operation> op = operations.get(operationName);			
		if ( op != null ) {
			checkHelperArguments(op.featureName, op.definition.getParameters(), arguments, node);
			return op.returnType;
		}
		
		
		// Not sure this is totally valid because of the traversal order
		EList<EClass> supertypes = eClass.getEAllSuperTypes();
		for (EClass c : supertypes) {
			if ( c.eIsProxy() ) continue; // proxies again...
			ITypeNamespace n = metamodel.getClassifier(c.getName());
			if ( n.hasOperation(operationName, arguments) ){
				return n.getOperationType(operationName, arguments, node);
			}			
		}

		t = tryRecoveryGetOperation(operationName, arguments, node);
		if ( t != null ) {
			return t;
		}

		return AnalyserContext.getErrorModel().signalNoOperationFound(getType(), operationName, node, 
				new RecoverOperationNotFound(this, operationName, node));
	}
	
	protected static void checkHelperArguments(String opName, List<Parameter> params, Type[] arguments, LocatedElement node) {
		Type[] formalArguments = new Type[params.size()];
		String[] formalArgumentNames = new String[params.size()];
		for(int i = 0; i < params.size(); i++) {
			formalArguments[i] = params.get(i).getStaticType();
			formalArgumentNames[i] = params.get(i).getVarName();
		}
		
		checkArguments(opName, formalArguments, formalArgumentNames, arguments, node);	
	}

	private Type tryRecoveryGetOperation(String operationName, Type[] arguments, LocatedElement node) {
		HashSet<EClass> foundClasses = new HashSet<EClass>();
		Type returnedType = null;

		LinkedList<ClassNamespace> pending = new LinkedList<ClassNamespace>();
		HashSet<EClass> withoutOperation = new HashSet<EClass>();
		pending.add(this);

		while ( ! pending.isEmpty() ) {
			ClassNamespace ns = pending.pop();
			if ( ns.hasOperation(operationName, arguments)) {
				// Fine. This branch is covered
				foundClasses.add(ns.eClass);
				if ( returnedType == null) {
					returnedType = ns.getOperationType(operationName, arguments, node);
				}
			} else {
				Collection<ClassNamespace> direct = ns.getDirectSubclasses();
				if ( direct.size() > 0 ) {
					pending.addAll(direct);
				} else {
					withoutOperation.add(ns.eClass);
				}
			}
		}
		
		if ( foundClasses.size() > 0 && withoutOperation.size() > 0 ) {			
			AnalyserContext.getErrorModel().warningOperationFoundInSubType(getType(), foundClasses, withoutOperation, operationName, node);
			return returnedType;			
		} else {
			// TODO: Check that the return type of all operations is compatible, actually the 
			// same type
			return returnedType;
		}
		
		/*
		for(ClassNamespace c : getAllSubclasses()) {
			EClass subtype = c.eClass;
			ClassNamespace cn = (ClassNamespace) metamodel.getClassifier(subtype.getName());
			if ( cn.hasOperation(operationName, arguments)) {
				foundClasses.add(subtype);
				if ( returnedType == null) {
					returnedType = cn.getOperationType(operationName, arguments, node);
				}
			}		
		}
		
		if ( foundClasses.size() > 0 ) {			
			AnalyserContext.getErrorModel().warningOperationFoundInSubType(getType(), TypeUtils.getUpperInHierarchy(foundClasses), operationName, node);
			return returnedType;
		}
		return null;
		 */
		
		
	}


	public List<EReference> getContainerReferences() {
		ArrayList<EReference> result = new ArrayList<EReference>();
		List<EClass> classes = metamodel.getAllClasses();
		for (EClass c : classes) {
			for(EReference r : c.getEReferences()) {
				if ( r.isContainment() ) {
					if ( r.getEReferenceType() == eClass || 
						 eClass.isSuperTypeOf(r.getEReferenceType()) || // This is to select every possible "pointed" subclass
						 r.getEReferenceType().isSuperTypeOf(eClass)    // This is the normaly polymorphic checking
					) {
						
						result.add(r);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Implements the algorithm to find the container(s) that may hold
	 * the an object of the class when invoking "refImmediateComposite".
	 * 
	 * @param node
	 * @return
	 */
	private Type findTypeOfContainer(LocatedElement node) {
//		ArrayList<EClass> possibleContainers = new ArrayList<EClass>();
//		
//		List<EClass> classes = metamodel.getAllClasses();
//		for (EClass c : classes) {
//			for(EReference r : c.getEReferences()) {
//				if ( r.isContainment() ) {
//					if ( r.getEReferenceType() == eClass || 
//						 eClass.isSuperTypeOf(r.getEReferenceType()) || // This is to select every possible "pointed" subclass
//						 r.getEReferenceType().isSuperTypeOf(eClass)    // This is the normaly polymorphic checking
//					) {
//						
//						possibleContainers.add(r.getEContainingClass());
//					}
//				}
//			}
//		}
		
		List<EClass> possibleContainers = getContainerReferences().stream().
				map(EReference::getEContainingClass).
				collect(Collectors.toList());
		
		if ( possibleContainers.size() == 0 ) {
			// TODO: How to recover from a not having a container???
			return AnalyserContext.getErrorModel().signalNoContainerForRefImmediateComposite(getType(), node);
		} else if ( possibleContainers.size() == 1 ) {
			return metamodel.getMetaclass(possibleContainers.get(0));
		} else {
			ArrayList<Type> containerTypes = new ArrayList<Type>();
			for(int i = 0; i < possibleContainers.size(); i++) {
				containerTypes.add(metamodel.getMetaclass(possibleContainers.get(i)));
			}

			return AnalyserContext.getTypingModel().getCommonType(containerTypes);
		}
		// throw new IllegalStateException(); // This should not happen (the compiler seems not see it...)
	}


	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getOperatorType(java.lang.String, anatlyzer.atl.types.Type, anatlyzer.atlext.ATL.LocatedElement)
	 */
	@Override
	public Type getOperatorType(String operatorSymbol, Type optionalArgument, LocatedElement node) {
		if ( operatorSymbol.equals("=") || operatorSymbol.equals("<>") ) {
			return AnalyserContext.getTypingModel().newBooleanType();
		}
		
		return AnalyserContext.getErrorModel().signalInvalidOperator(operatorSymbol, getType(), node);
		// throw new UnsupportedOperationException("No symbol " + operatorSymbol + " supported");
	}


	private ArrayList<MatchedRule> attachedRules = new ArrayList<MatchedRule>();
	private Set<MatchedRule> resolvingRules = new HashSet<MatchedRule>();

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#attachRule(java.lang.String, anatlyzer.atl.types.Type, anatlyzer.atlext.ATL.Rule)
	 */
	@Override
	public void attachRule(String ruleName, Type returnType, Rule rule) {
		if ( ! (rule instanceof MatchedRule) ) throw new IllegalArgumentException();
		
		if ( ATLUtils.isOneOneRule((MatchedRule) rule)) {
			attachedRules.add((MatchedRule) rule);	
			attachResolvingRule(ruleName, returnType, (MatchedRule) rule);
		}
	}
	
	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#attachResolvingRule(java.lang.String, anatlyzer.atl.types.Type, anatlyzer.atlext.ATL.MatchedRule)
	 */
	@Override
	public void attachResolvingRule(String ruleName, Type returnType, MatchedRule rule) {
		if ( rule instanceof LazyRule ) throw new IllegalArgumentException();
		if ( rule.isIsNoDefault()) return;	
		if ( rule.isIsAbstract() ) return;
		
		resolvingRules.add(rule);		

		// For any supertype, the rule *may* resolve the object 	
		for(EClass c : eClass.getEAllSuperTypes()) {
			if ( c.eIsProxy() ) { 
				// TODO: Handle this: System.out.println("WARNING: Ignoring proxy (extendType, ClassNamespace)"); 
				continue; 
			}
			
			IClassNamespace ns = (IClassNamespace) metamodel.getClassifier(c.getName());
			if ( ns instanceof ClassNamespace ) {
				((ClassNamespace) ns).resolvingRules.add(rule);
			}			
			
		}
		
		// For any subtype, the rule will certainly resolve the object (modulo the rule filter)
		ArrayList<IClassNamespace> nss = new ArrayList<IClassNamespace>(this.getAllSubclasses());		
		for(IClassNamespace sub : nss) {
			if ( sub instanceof ClassNamespace ) {
				((ClassNamespace) sub).resolvingRules.add(rule);
			}
		}

	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getResolvingRules()
	 */
	@Override
	public Set<MatchedRule> getResolvingRules() {
		return resolvingRules;
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getRules()
	 */
	@Override
	public List<MatchedRule> getRules() {
		return attachedRules;
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getAllSubclasses()
	 */
	@Override
	public Set<ClassNamespace> getAllSubclasses() {
		return metamodel.getAllSubclasses(eClass);
	}

	@Override
	public Set<ClassNamespace> getAllSubclasses(GlobalNamespace ns) {
		return metamodel.getAllSubclasses(eClass, ns);
	}


	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getDirectSubclasses()
	 */
	@Override
	public Collection<ClassNamespace> getDirectSubclasses() {
		return metamodel.getDirectSubclasses(eClass, AnalyserContext.getGlobalNamespace());		
	}

	public Collection<ClassNamespace> getDirectSubclasses(GlobalNamespace ns) {
		return metamodel.getDirectSubclasses(eClass, ns);	
	}

	/* (non-Javadoc)
	 * @see genericity.compiler.atl.analyser.namespaces.IClassNamespace#getAllSuperClasses()
	 */
	@Override
	public List<ClassNamespace> getAllSuperClasses() {
		ArrayList<ClassNamespace> result = new ArrayList<ClassNamespace>();
		ArrayList<EClass> visit = new ArrayList<EClass>();
		visit.addAll(eClass.getESuperTypes());
		while ( ! visit.isEmpty() ) {
			EClass c = visit.remove(0);
			if ( c.eIsProxy() ) continue;
			
			ClassNamespace ns = (ClassNamespace) metamodel.getClassifier(c.getName());
			result.add(ns);
			
			for(EClass sup : c.getESuperTypes()) {
				visit.add(sup);
			}
		}
		return result;
	}

	@Override
	public ITypeConstraint newTypeConstraint() {
		return new MetaclassTypeConstraint(getType());
	}

	
	
}
