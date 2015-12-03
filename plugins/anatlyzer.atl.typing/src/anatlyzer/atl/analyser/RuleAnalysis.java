package anatlyzer.atl.analyser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.types.UnresolvedTypeError;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This traversal is in charge of analysing rule "calls".
 * 
 * @author jesus
 *
 */
public class RuleAnalysis extends AbstractAnalyserVisitor {
	
	
	public RuleAnalysis(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}
	
	public void perform(ComputedAttributes attr) {
		this.attr = attr.pushVisitor(this);
		startVisiting(root);
		attr.popVisitor(this);
	}

	@Override
	public void beforeModule(Module self) {
		// Compute the list of all written bindings
		List<? extends Binding> bindings = model.allObjectsOf(Binding.class);
		for (Binding binding : bindings) {
			EStructuralFeature f = (EStructuralFeature) binding.getWrittenFeature();
			if ( f != null ) {
				allWrittenFeatures.add(f);
			}
		}

		List<? extends BindingStat> stats = model.allObjectsOf(BindingStat.class);
		for (BindingStat bindingStat : stats) {
			// The expresion could be a simple var or an access to a feature (callexpr)
			OclExpression expr = bindingStat.getSource();
			if ( expr instanceof PropertyCallExp && ((PropertyCallExp) expr).getUsedFeature() != null ) {
				EStructuralFeature f = (EStructuralFeature) ((PropertyCallExp) expr).getUsedFeature();
				if ( f != null ) {
					allWrittenFeatures.add(f);
				}				
			}
		}
		
		// TODO: May be another list with imperatively assigned features
	}
	
	@Override
	public void inMatchedRule(MatchedRule self) {
		if ( self.isIsAbstract() )
			return;
		
		checkInheritedCompulsoryFeatures(self);
	}
	
	@Override
	public void inLazyRule(LazyRule self) {
		if ( self.isIsAbstract() )
			return;

		checkInheritedCompulsoryFeatures(self);
	}
	
	/**
	 * If some of the inherited output patterns is not redefined, then
	 * the check for compulsory features.
	 * @param self
	 */
	private void checkInheritedCompulsoryFeatures(RuleWithPattern self) {
		if ( self.getSuperRule() == null )
			return;
		
		Map<String, OutPatternElement> overriden = null;
		if ( self.getOutPattern() == null ) {
			overriden = new HashMap<String, OutPatternElement>();
		} else {
			overriden = self.getOutPattern().getElements().stream().collect(
					Collectors.toMap(e -> e.getVarName(), e -> e));
		}
		ArrayList<OutPatternElement> toCheck = new ArrayList<OutPatternElement>();
		
		for (RuleWithPattern sup : ATLUtils.allSuperRules(self)) {
			for (OutPatternElement inherited : sup.getOutPattern().getElements()) {
				if ( ! overriden.containsKey(inherited.getVarName()) ) {
					toCheck.add(inherited);
					overriden.put(inherited.getVarName(), inherited);
				}
			}
		}		
	
		toCheck.forEach(t -> {
			Metaclass mc = (Metaclass) t.getType().getInferredType();
			if ( mc instanceof UnresolvedTypeError ) 
				return;

			//setCurrentCompulsoryFeatures(mc);
			checkCompulsoryFeature(t, NoBindingForCompulsoryFeatureKind.MISSING_SUBRULE, self);
		});
		
	}
	
	

	@Override
	public VisitingActions preBinding(Binding self) {
		return actions();
	}
	
	@Override
	public void beforeMatchedRule(MatchedRule self) {
		// self.getInPattern().getElements().get(0);
	}
	
	// private HashSet<EStructuralFeature> compulsoryFeatures = null;
	private HashSet<EStructuralFeature> allWrittenFeatures = new HashSet<EStructuralFeature>();
	
	@Override
	public VisitingActions preSimpleOutPatternElement(SimpleOutPatternElement self) {
		// return actions("type" , "initExpression" , filter("getFlattenedBindings", self) , "reverseBindings");
		return actions("type" , "initExpression" , "bindings", "reverseBindings");
		// Using flattened bindings provoke error duplication in super/sub rules.
	}
	
	protected Pair<List<Binding>, List<BindingStat>> getFlattenedBindings(OutPatternElement self) {
		List<Binding> result = new ArrayList<Binding>(self.getBindings());
		List<BindingStat> stats = new ArrayList<BindingStat>();
		OutPattern outPattern = (OutPattern) self.eContainer();
		Rule r = (Rule) outPattern.eContainer();
		
		addBindings(self, r, result, stats);
		
		if ( r instanceof RuleWithPattern ) {
			for (RuleWithPattern sup : ATLUtils.allSuperRules((RuleWithPattern) r)) {
				addBindings(self, sup, result, stats);
			}
		}
		
		return new Pair<List<Binding>, List<BindingStat>>(result, stats);
	}
	
	private void addBindings(OutPatternElement out, Rule r, List<Binding> result, List<BindingStat> stats) {
		r.getOutPattern().getElements().stream().filter(e -> e.getVarName().equals(out.getVarName())).forEach(e ->  { 
			result.addAll(e.getBindings());
		});

		// This should be more fine-grained, filtering accoring to the type of out, but for the moment take all
		ActionBlock actionBlock = r.getActionBlock();
		if ( actionBlock != null ) {
			TreeIterator<EObject> it = actionBlock.eAllContents();
			while ( it.hasNext() ) {
				EObject obj = (EObject) it.next();
				if ( obj instanceof BindingStat ) {
					stats.add((BindingStat) obj);
				}
			}
		}
	}

	@Override
	public void beforeForEachOutPatternElement(ForEachOutPatternElement self) {
		// Metaclass mc = (Metaclass) attr.typeOf( self.getType() );
		Metaclass mc = (Metaclass) self.getType().getInferredType();
		if ( mc instanceof UnresolvedTypeError ) 
			return;

		checkCompulsoryFeature(self, NoBindingForCompulsoryFeatureKind.IN_NORMAL_RULE, null);
	}

	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		OutPattern outPattern = (OutPattern) self.eContainer();
		Rule r = (Rule) outPattern.eContainer();
		if ( outPattern.getElements().get(0) == self && r instanceof MatchedRule && ((MatchedRule) r).isIsAbstract() ) {
			 // Do not check the first output pattern of abstract classes
			return; 
		}
	
		checkCompulsoryFeature(self, NoBindingForCompulsoryFeatureKind.IN_NORMAL_RULE, null);
	}
	
	@Override
	public void inForEachOutPatternElement(ForEachOutPatternElement self) {
		checkCompulsoryFeature(self, NoBindingForCompulsoryFeatureKind.IN_NORMAL_RULE, null);
	}

	private HashSet<EStructuralFeature> getCurrentCompulsoryFeatures(Metaclass mc) {
		HashSet<EStructuralFeature> compulsoryFeatures = new HashSet<EStructuralFeature>();
		
		for(EStructuralFeature f : mc.getKlass().getEAllStructuralFeatures() ) {			
			if ( f.getLowerBound() != 0 && f.getDefaultValue() == null ) {
				compulsoryFeatures.add(f);
			}
		}
		
		return compulsoryFeatures;
	}

	protected void checkCompulsoryFeature(OutPatternElement self, NoBindingForCompulsoryFeatureKind kind, RuleWithPattern rule) {		
		// Type targetVar = attr.typeOf( self.getOutPatternElement() );
		Type targetVar = self.getInferredType();
		if ( targetVar instanceof UnresolvedTypeError ) 
			return;
		IClassNamespace ns = (IClassNamespace) targetVar.getMetamodelRef();
		
		HashSet<EStructuralFeature> compulsoryFeatures = getCurrentCompulsoryFeatures((Metaclass) targetVar);
		
		Pair<List<Binding>, List<BindingStat>> pair = getFlattenedBindings(self);
		for (Binding binding : pair._1) {
			EStructuralFeature f = ns.getStructuralFeatureInfo(binding.getPropertyName());
			// An error indicating that the target feature of the binding is not valid
			// should have been collected
			if ( f == null )
				continue; 
			compulsoryFeatures.remove(f);
		}
		
		for (BindingStat bStat : pair._2) {
			checkBindingStat(bStat, compulsoryFeatures);
		}
		
		for (EStructuralFeature f : compulsoryFeatures) {			
			if ( f instanceof EReference && allWrittenFeatures.contains( ((EReference) f).getEOpposite()) ) 
				continue; // Assumes that if the opposite is written in other rule, it is the one that corresponds to this object
				
			if ( TypeUtils.isFeatureMustBeInitialized(f) )
				errors().signalNoBindingForCompulsoryFeature(f, self, kind, rule);				
		}
	}

	@Override
	public void inBinding(Binding self) {
		// Type rightType = attr.typeOf(self.getValue());
		Type rightType = self.getValue().getInferredType();

		// Type targetVar = attr.typeOf( self.getOutPatternElement() );
		Type targetVar =self.getOutPatternElement().getInferredType();
		IClassNamespace ns = (IClassNamespace) targetVar.getMetamodelRef();
		EStructuralFeature f = ns.getStructuralFeatureInfo(self.getPropertyName());
		
		// An error indicating that the target feature of the binding is not valid
		// should have been collected
		if ( f == null )
			return; 
						
		if ( rightType instanceof UnionType ) {
			UnionType ut = (UnionType) rightType;
			for(Type t : ut.getPossibleTypes() ) {
				analyseBinding(self, t, targetVar, f);
			}
		} else {
			analyseBinding(self, rightType, targetVar, f);
		}
	}
	
	private void checkBindingStat(BindingStat self, HashSet<EStructuralFeature> compulsoryFeatures) {
		if ( ! (self.getSource() instanceof NavigationOrAttributeCallExp) )
			return;
		
		NavigationOrAttributeCallExp assigned = (NavigationOrAttributeCallExp) self.getSource();
		if ( assigned.getSource() instanceof VariableExp ) {
			// Type targetVar = attr.typeOf( assigned.getSource() );
			Type targetVar = assigned.getSource().getInferredType();
			
			// Rule out "thisModule" assignments
			if ( targetVar instanceof Metaclass) { 
				// The same as the declarative case
				IClassNamespace ns = (IClassNamespace) targetVar.getMetamodelRef();
				EStructuralFeature f = ns.getStructuralFeatureInfo(assigned.getName());
		
				// The feature is removed from the current compulsoryFeatures set, which is
				// checked later to be empty
				compulsoryFeatures.remove(f);
			}
		} 
	}

	private void analyseBinding(Binding self, Type rightType, Type targetVar, EStructuralFeature f) {
		if ( rightType instanceof TypeError )
			return;
		
		// Assignments of 0/1:1 features with collections
		if ( (! f.isMany()) && rightType instanceof CollectionType  ) {
			errors().signalBindingExpectedOneAssignedMany(self, (Metaclass) targetVar, self.getPropertyName());
		}
		
		// Assignment of attributes with objects
		if ( f instanceof EAttribute && !(rightType instanceof PrimitiveType )) {
			if (rightType instanceof CollectionType && ((CollectionType) rightType).getContainedType() instanceof PrimitiveType ) {
				// That's fine
			} else if (rightType instanceof CollectionType && ((CollectionType) rightType).getContainedType() instanceof EmptyCollectionType ) {
				// That's fine				
			} else if ( rightType instanceof EnumType && f.getEType() instanceof EEnum ) {
				// That's fine				
			} else {
				errors().signalBindingPrimitiveExpectedButObjectAssigned(self, (Metaclass) targetVar, self.getPropertyName());
			}
		} else if ( f instanceof EAttribute && rightType instanceof PrimitiveType ) {
			// Invalid primitive assignments
			if ( ! typ().assignableTypes(self.getLeftType(), rightType) ) {
				errors().signalPrimitiveBindingInvalidAssignement(self, (Metaclass) targetVar, self.getPropertyName());
			}
		}
		
		// Assignment of references with primitive values
		if ( f instanceof EReference && 
				(rightType instanceof PrimitiveType || rightType instanceof EnumType ||
				(rightType instanceof CollectionType && ((CollectionType) rightType).getContainedType() instanceof PrimitiveType) )) {
			
			errors().signalBindingObjectExpectedButPrimitiveAssigned(self, (Metaclass) targetVar, self.getPropertyName());
		}		
		
		if ( f instanceof EReference ) {
			analyseRuleResolution(self, rightType, (EReference) f);
		}
		
		/* TODO: Other analysis */
		/*
		 * - Compulsory features in the meta-model not assigned
		 * - Assignments object-object due to: lazy/called rules, or implicit assignment
		 */
	}

	/**
	 * Analyse if a binding is properly resolved by some rule.
	 * 
	 * @param self
	 * @param rightType
	 * @param f
	 */
	protected void analyseRuleResolution(Binding self, Type rightType, EReference f) {
		if ( rightType instanceof Metaclass ) {			
			Metaclass rightMetaclass = (Metaclass) rightType;
			IClassNamespace ns = (IClassNamespace) rightType.getMetamodelRef();			
			
			Set<MatchedRule> rules = ns.getResolvingRules();
			boolean isAssignable    = TypeUtils.isClassAssignableTo(rightMetaclass.getKlass(), f.getEReferenceType());
			if ( rules.size() == 0 && ! isAssignable ) {
				String mmName = ((OclModelElement) self.getOutPatternElement().getType()).getModel().getName();
				
				errors().signalBindingWithoutRule(self, 
						errors().newElement(rightMetaclass), 
						errors().newElement(mmName, f.getEReferenceType()) );

				// System.err.println("!!!!! WARNING!!! No rule for binding.  " + f.getEContainingClass().getName() + "." + f.getName() + " <- " + TypeUtils.typeToString(rightType) + ". " + self.getLocation());
			} else if ( ! isAssignable ){
				findPossibleUnresolvedClasses(self, rightMetaclass, f.getEReferenceType()); //, rules);				
				findRulesWithWrongTargetType(self, rightMetaclass, f.getEReferenceType(), rules);
			}
		} else if ( rightType instanceof CollectionType ) {
			CollectionType ct = (CollectionType) rightType;
			analyseRuleResolution(self, ct.getContainedType(), f);
		} else if ( rightType instanceof UnionType ) {
			UnionType ut = (UnionType) rightType;
			for(Type t : ut.getPossibleTypes() ) {
				analyseRuleResolution(self, t, f);
			}
		}
	}

	private void findRulesWithWrongTargetType(Binding b, Metaclass rightType, EClass targetType, Set<MatchedRule> resolvingRules) {
		ArrayList<MatchedRule> problematicRules = new ArrayList<MatchedRule>();
		ArrayList<EClass> targetClasses = new ArrayList<EClass>();
		ArrayList<EClass> sourceClasses = new ArrayList<EClass>();
		
		for (MatchedRule matchedRule : resolvingRules) {
			if ( matchedRule.isIsAbstract() || ! ATLUtils.isOneOneRule(matchedRule) )
				continue;
			
//			Metaclass srcMetaclass = (Metaclass) attr.typeOf( matchedRule.getInPattern().getElements().get(0) );
//			Metaclass tgtMetaclass = (Metaclass) attr.typeOf( matchedRule.getOutPattern().getElements().get(0) );
			Metaclass srcMetaclass = (Metaclass) matchedRule.getInPattern().getElements().get(0).getInferredType();
			Metaclass tgtMetaclass = (Metaclass) matchedRule.getOutPattern().getElements().get(0).getInferredType();

			if ( ! TypeUtils.isClassAssignableTo(tgtMetaclass.getKlass(), targetType)  ) {
				problematicRules.add(matchedRule);
				sourceClasses.add(srcMetaclass.getKlass());
				targetClasses.add(tgtMetaclass.getKlass());
				// System.err.println("Problem in rule " + matchedRule.getName() + " " + m.getName() + " - " + targetType.getName() + "/" + TypeUtils.typeToString(rightType) + "." + b.getLocation() + " " + matchedRule.getLocation());
			}
		}
		
		if ( problematicRules.size() > 0 ) {
			errors().signalBindingWithResolvedByIncompatibleRule(b, rightType.getKlass(), targetType, problematicRules, sourceClasses, targetClasses);
		}
	}

	private void findPossibleUnresolvedClasses(Binding b, Metaclass rightMetaclass, EClass targetType) { //, List<MatchedRule> rules) {
		RuleAnalysis.findPossibleUnresolvedClasses(rightMetaclass, 
				(problematicClasses, problematicClassesImplicit) -> {					
					errors().signalBindingPossiblyUnresolved(b, rightMetaclass.getKlass(), targetType, problematicClasses, problematicClassesImplicit);				
					return true;
				});
	}
	
	public static void findPossibleUnresolvedClasses(Metaclass rightMetaclass, 
			BiFunction<List<EClass>, List<EClass>, Boolean> info) { 
		LinkedList<ClassNamespace> pending = new LinkedList<ClassNamespace>();
		pending.add( (ClassNamespace) rightMetaclass.getMetamodelRef() );
	
		LinkedList<ClassNamespace> notResolved = new LinkedList<ClassNamespace>();
		LinkedList<ClassNamespace> notResolvedImplicit = new LinkedList<ClassNamespace>();
		
		while ( ! pending.isEmpty() ) {
			ClassNamespace ns = pending.pop();
			
			if ( ns.getRules().isEmpty() ) {
				Collection<ClassNamespace> direct = ns.getDirectSubclasses();
				if ( direct.size() > 0 ) {
					pending.addAll(direct);
				} else {
					notResolved.add(ns);
					notResolvedImplicit.add(ns);
				}
			} else {
				// There are rules but we don't know if the filters are complete
				for(MatchedRule r : ns.getRules()) {
					if ( r.getInPattern().getFilter() != null ) {
						notResolved.add(ns);
						break;
					}
				}
				
			}
		}
		
		if ( notResolved.size() > 0 ) {
			ArrayList<EClass> problematicClasses = new ArrayList<EClass>(notResolved.size());
			for (IClassNamespace classNamespace : notResolved) {
				problematicClasses.add(classNamespace.getKlass());
			}

			ArrayList<EClass> problematicClassesImplicit = new ArrayList<EClass>(notResolvedImplicit.size());
			for (IClassNamespace classNamespace : notResolvedImplicit) {
				problematicClassesImplicit.add(classNamespace.getKlass());
			}
			
			info.apply(problematicClasses, problematicClassesImplicit);
		}

	}
}

