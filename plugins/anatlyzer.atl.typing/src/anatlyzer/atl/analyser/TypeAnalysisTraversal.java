package anatlyzer.atl.analyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.xml.sax.ext.LexicalHandler;

import anatlyzer.atl.analyser.libtypes.AtlTypeDef;
import anatlyzer.atl.analyser.libtypes.AtlTypes;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.CollectionNamespace;
import anatlyzer.atl.analyser.namespaces.FeatureInfo;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analyser.recovery.IRecoveryAction;
import anatlyzer.atl.errors.atl_error.InvalidRuleInheritanceKind;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeatureKind;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.BooleanType;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EmptyCollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.FloatType;
import anatlyzer.atl.types.IntegerType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.ReflectiveClass;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.TupleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypeError;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.Unknown;
import anatlyzer.atl.types.UnresolvedTypeError;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BagExp;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.JavaBody;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.MapExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.OrderedSetExp;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.ResolveTempResolution;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.OCL2.SelectByKind;
import anatlyzer.atlext.processing.AbstractVisitor.VisitingActions;

public class TypeAnalysisTraversal extends AbstractAnalyserVisitor {

	public TypeAnalysisTraversal(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	
		attr = new ComputedAttributes(this);
		// mm.setDependencies(new EcoreTypeConverter(typ), errors);
	}
		
	
	public void perform() {
		
		// 1. Get meta-model elements for the explicitly named types
		explicitTypeTraversal();
		
		// 2. Process helpers and operations
		TopLevelTraversal helperOperations = new TopLevelTraversal(model, mm, root);
		helperOperations.perform(attr);
		
		// 3. Assign types
		startVisiting(root);

		// 4. Ocl Compliance
		OclCompliance compliance = new OclCompliance(model, mm, root);
		compliance.perform(attr);

		ComputeResolvers computeResolvers = new ComputeResolvers(model, mm, root);
		computeResolvers.perform(attr);

		ruleAnalysis();
	}
	
	protected void explicitTypeTraversal() {
		ExplicitTypeTraversal explicit = new ExplicitTypeTraversal(model, mm, root);
		explicit.perform(attr);	
	}

	protected void ruleAnalysis() {
		RuleAnalysis ruleAnalysis = new RuleAnalysis(model, mm, root);
		ruleAnalysis.perform(attr);
	}


	private ThisModuleType thisModuleType;

	private ThisModuleType getThisModuleType() {
		if ( thisModuleType == null ) {
			thisModuleType = typ().createThisModuleType();
			thisModuleType.setMetamodelRef(mm.getTransformationNamespace());
		}
		return thisModuleType;
	}

	
	public VisitingActions preModule(anatlyzer.atlext.ATL.Module self) { 
		return actions("libraries", "inModels", "outModels", 
				filter("getHelpers", self), filter("getRules", self));
	} 
	
	@Override
	public VisitingActions preMatchedRule(MatchedRule self) {
		return actions("inPattern", "variables", "outPattern" , "actionBlock"); 
	}
	
	@Override
	public VisitingActions preLazyRule(LazyRule self) {
		return actions("inPattern", "variables", "outPattern" , "actionBlock"); 
	}

	@Override
	public VisitingActions preCalledRule(CalledRule self) {
		return actions("parameters", "variables", "outPattern" , "actionBlock"); 
	}
		
	public List<Helper> getHelpers(anatlyzer.atlext.ATL.Module self) {
		LinkedList<Helper> helpers = new LinkedList<Helper>();
		for (ModuleElement me : self.getElements()) {
			if ( me instanceof Helper ) {
				Helper h = (Helper) me;
				OclFeature f = h.getDefinition().getFeature();
				if ( f instanceof Operation ) {
					if ( attr.typeOf( ((Operation) f).getReturnType() ) instanceof Unknown ) {
						helpers.addFirst(h);
						continue;
					} 
				} else if ( f instanceof Attribute ) {
					if ( attr.typeOf( ((Attribute) f).getType() ) instanceof Unknown ) {
						helpers.addFirst(h);
						continue;						
					}
				}
				
				helpers.addLast((Helper) me);				
			}
		}
		return helpers;
	}

	public List<Rule> getRules(anatlyzer.atlext.ATL.Module self) {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		for (ModuleElement me : self.getElements()) {
			if ( me instanceof Rule ) 
				rules.add((Rule) me);
		}
		return rules;
	}
	
	@Override
	public void inOperation(Operation self) {
		Type declared = attr.typeOf(self.getReturnType());
		Type inferred = attr.typeOf(self.getBody());
		
		Helper h = (Helper) self.eContainer().eContainer();
		h.setStaticReturnType(declared);
		h.setInferredReturnType(inferred);

		if ( declared instanceof Unknown ) {
			TopLevelTraversal.extendTypeForOperation(self, mm, attr, inferred);
		} else if ( !(inferred instanceof TypeError) && ! typ().assignableTypes(declared, inferred) ) {
			if ( ! AnalyserUtils.isExplicitReturnTypeForced(h) ) {
				errors().warningIncoherentHelperReturnType(self, inferred, declared);
				// The operation is redefined to use the inferred type if it is best
				TopLevelTraversal.extendTypeForOperation(self, mm, attr, determineBestInIncoherentType(declared, inferred));
			}
		}
	}
	
	@Override
	public void inAttribute(Attribute self) {
		Type declared = attr.typeOf(self.getType());
		Type inferred = attr.typeOf(self.getInitExpression());

		Helper h = (Helper) self.eContainer().eContainer();
		h.setStaticReturnType(declared);
		h.setInferredReturnType(inferred);

		if ( declared instanceof Unknown ) {
			TopLevelTraversal.extendTypeForAttribute(self, mm, attr, inferred);
		} else if ( !(inferred instanceof TypeError) && ! typ().assignableTypes(declared, inferred) ) {
			if ( ! AnalyserUtils.isExplicitReturnTypeForced(h) ) {
				errors().warningIncoherentHelperReturnType(self, inferred, declared);
				TopLevelTraversal.extendTypeForAttribute(self, mm, attr, determineBestInIncoherentType(declared, inferred));
			}
		}
	}
	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
// 		This has to be checked per rule
//		if ( self.getInferredType() instanceof Metaclass  ) {
//			Rule r = self.getOutPattern().getRule();
//			if ( r instanceof RuleWithPattern && ((RuleWithPattern) r).isIsAbstract() )
//				return;
//			
//			Metaclass m = (Metaclass) self.getInferredType();
//			if ( m.getKlass().isAbstract() ) {
//				errors().signalCannonInstantiateAbstractClass(m, self);
//			}
//		}
	}

	public VisitingActions preLetExp(anatlyzer.atlext.OCL.LetExp self) { return actions("type" , "variable" , "in_"); } 

	@Override
	public void inMatchedRule(MatchedRule self) {
		// This is a sanity check. The parser does not allow matched rules
		// without from part.
		if ( self.getInPattern() == null )
			return;
		
		if ( self.getInPattern().getFilter() != null ) {
			Type t = attr.typeOf(self.getInPattern().getFilter());
			if ( ! (t instanceof BooleanType ) && !(t instanceof TypeError)) {
				errors().signalMatchedRuleWithNonBooleanFilter(t, self);
			}
		}
		
		// Check inheritance structure
		// We follow: "Surveying Rule Inheritance in Model-to-Model Transformation Languages"
		
		if ( self.getSuperRule() != null ) {
			RuleWithPattern sup = self.getSuperRule();
			// 1. Check that the number of input patterns is the same as the number of
			//    elements in the super rule
			if ( sup.getInPattern().getElements().size() != self.getInPattern().getElements().size() ) {
				errors().signalInvalidRuleInheritance(self.getInPattern(), 
						InvalidRuleInheritanceKind.DIFFERENT_NUMBER_OF_IPE,
						"Different number of input elements in the super rule");
			} else {
				// Check that all IPE's has the same name
				for (InPatternElement supIPE : sup.getInPattern().getElements()) {
					boolean notFound = true;
					for (InPatternElement ipe : self.getInPattern().getElements()) {
						if ( supIPE.getVarName().equals(ipe.getVarName()) ) {
							notFound = false;
							break;
						}
					}
					
					if ( notFound ) {
						errors().signalInvalidRuleInheritance(self.getInPattern(), 
								InvalidRuleInheritanceKind.DIFFERENT_IPE_NAMES,
								"No input variable " + supIPE.getVarName() + " found in sub-rule: " + self.getName());						
					}
				}
				
			}
			
			// 2. Invariant to Check Co-Variance of Input Elements
			//
			// context TransformationRule inv CoVarianceOfInputElements:
			// −− select InputElements of context rule
			// self.inpattern.elems −> forall(ie : InputElement |
			//   −− query and iterate all effectively inherited input elements
			//   self.allEffInhIE() −> collect(varName)
			//   −− if an effectively inherited input element is overridden
			//   −> includes(ie.varName) implies
			//   −− then check co−variance condition
			//   ie.class.allSuperClasses() −> union(ie.class) −> includesAll(
			//      self.allEffInhIE() −> select(iie : InputElement | iie.varName = ie.varName)
			//      −> collect(class) −> flatten()
			//   )
			// )
			
			
		}

		// Not sure if in the paper
		// Check that every OPE can be instantiated
		checkInstantiation(self);
		
	}
	
	@Override
	public void inLazyRule(LazyRule self) {
		checkInstantiation(self);
	}
	
	private void checkInstantiation(RuleWithPattern self) {
		ATLUtils.getAllOutputPatterns(self, (ope -> {
			if ( ! (ope.getType().getInferredType() instanceof Metaclass) )
				return;
			
			Metaclass mc = (Metaclass) ope.getType().getInferredType();
			if ( mc instanceof UnresolvedTypeError ) 
				return;

			if ( mc.getKlass().isAbstract() && ! self.isIsAbstract() ) {
				errors().signalCannonInstantiateAbstractClass(mc, ope);
			}
		}));	
	}
	
	
	@Override
	public void inBinding(Binding self) {
		Type t = attr.typeOf(self.getValue());
		if ( attr.wasCasted(self.getValue()) ){
			self.getValue().setInferredType(t); 
			typ().markImplicitlyCasted(self.getValue(), t, attr.noCastedTypeOf(self.getValue()));
		}
	}
	
	@Override
	public void inBindingStat(BindingStat self) {
		Type left = attr.typeOf(self.getSource());
		Type right = attr.typeOf(self.getValue());
		
		if ( !(left instanceof TypeError || right instanceof TypeError) ) {
			// The semantics of binding statements is addition, so I need to 
			// unwrap the nested element
			if ( left instanceof CollectionType ) {
				left = ((CollectionType) left).getContainedType();
				if (right instanceof CollectionType ) {
					right = ((CollectionType) right).getContainedType();					
				}
			}
			
			if ( ! typ().assignableTypes(left, right) ) {
				errors().signalInvalidAssignmentInBindingStatement(attr.typeOf(self.getSource()), attr.typeOf(self.getValue()), self);
			}
		}
	}
	
	@Override
	public void inLetExp(LetExp self) {
		attr.linkExprType( attr.typeOf( self.getIn_() ) );
	}
	
	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		treatVariableDeclaration(self);
	}


	private void treatVariableDeclaration(VariableDeclaration self) {	
		Type exprType = attr.typeOf( self.getInitExpression() );
		if ( self.getType() == null ) {
			attr.linkExprType(exprType);
		} else {
			Type declared = attr.typeOf( self.getType() );

			if ( ! typ().assignableTypes(declared, exprType) ) {
				errors().warningVarDclIncoherentTypes(exprType, declared, self);
				attr.linkExprType(determineBestInIncoherentType(declared, exprType));
				
			} else {
				Type t = typ().determineVariableType(declared, exprType,  AnalyserContext.isVarDclInferencePreferred());				
				attr.linkExprType(t);
				// attr.linkExprType(exprType);
			}			
		}	
	}
	
	private Type determineBestInIncoherentType(Type declared, Type exprType) {
		if ( exprType instanceof TypeError ) {
			return declared;
		}
		if ( AnalyserContext.isVarDclInferencePreferred() ) {
			Type moreConcrete = typ().moreConcrete(declared, exprType);
			if ( moreConcrete == exprType || moreConcrete == null) {
				return exprType;					
			} else {
				return declared;
			}
		} else {
			return declared;
		}
	}


	@Override
	public void inRuleVariableDeclaration(RuleVariableDeclaration self) {
		treatVariableDeclaration(self);
		/*
		Type t = attr.typeOf( self.getInitExpression() );
		if ( self.getType() != null ) {
			// Decide about the most concrete type
			// TODO: WARNING MAY ARISE IF THEY DO NO COINCIDE
		} else {
			attr.linkExprType(t);
		}
		*/
	}


	@Override
	public VisitingActions preIfExp(IfExp self) {
		return actions("type", 
				method("openIfScope", self),
				"condition", 
				"thenExpression" , 
				method("openElseScope", self), 
				"elseExpression",
				method("closeIfElseScope", self));
	}	

	@Override
	public VisitingActions preOperatorCallExp(OperatorCallExp self) {
		if ( "implies".equals(self.getOperationName()) ) {
			return actions("type", 
				method("openImpliesScope", self),
				"source", 
				"arguments" , 
				method("closeImpliesScope", self));
		} else {
			return super.preOperatorCallExp(self);
		}
	}
	
	@Override
	public void inIfExp(IfExp self) {
		final Type thenPart = attr.typeOf(self.getThenExpression());
		final Type elsePart = attr.typeOf(self.getElseExpression());
				
		if ( ! typ().equalTypes(thenPart, elsePart) ) {
			// TODO: Compatibility hard-coded here... Is there a general method?
			if ( thenPart instanceof CollectionType && elsePart instanceof CollectionType ) {
				CollectionType ctThen = (CollectionType) thenPart;
				CollectionType ctElse = (CollectionType) elsePart;
				
				if ( ctThen.getContainedType() instanceof EmptyCollectionType && ! (ctElse.getContainedType() instanceof EmptyCollectionType) ) {
					attr.linkExprType( elsePart );
					return;
				} else if ( ctElse.getContainedType() instanceof EmptyCollectionType && ! (ctThen.getContainedType() instanceof EmptyCollectionType) ) {
					attr.linkExprType( thenPart );					
					return;
				}
			} else if ( thenPart instanceof MapType && elsePart instanceof MapType ) {
				MapType ctThen = (MapType) thenPart;
				MapType ctElse = (MapType) elsePart;
				
				Type keyType = null;
				Type valueType = null;
				
				if ( ctThen.getKeyType() instanceof EmptyCollectionType && ! (ctElse.getKeyType() instanceof EmptyCollectionType) ) {
					keyType = ctElse.getKeyType();
				} else if ( ctElse.getKeyType() instanceof EmptyCollectionType && ! (ctThen.getKeyType() instanceof EmptyCollectionType) ) {
					keyType = ctThen.getKeyType();					
				}

				if ( ctThen.getValueType() instanceof EmptyCollectionType && ! (ctElse.getValueType() instanceof EmptyCollectionType) ) {
					valueType = ctElse.getValueType();
				} else if ( ctElse.getValueType() instanceof EmptyCollectionType && ! (ctThen.getValueType() instanceof EmptyCollectionType) ) {
					valueType = ctThen.getValueType();
				}

				if ( keyType != null && valueType != null ) {
					attr.linkExprType( typ().newMapType(keyType, valueType) );
					return;
				}
				
			} else if ( thenPart instanceof TypeError ) {
				attr.linkExprType( elsePart );					
				return;
			} else if ( elsePart instanceof TypeError ) {
				attr.linkExprType( thenPart );					
				return;
			} 

			Type recovered = typ().getCommonType(thenPart, elsePart);
			// TODO: Do this better because this generates a lot of false errors()...
			/*
			Type recovered = errors().signalDifferentBranchTypes(thenPart, elsePart, self, new IRecoveryAction() {
				@Override
				public Type recover(ErrorModel m, LocalProblem p) {
					// TODO: Launch a greedy algorithm to decide the best option
					//       between thenPart and elsePart										
					return thenPart;
				}
			});
			*/
			
			attr.linkExprType( recovered );
		} else {			
			attr.linkExprType( thenPart );
		}
	}
	
	// 
	// Navigation
	//
	
	@Override
	public VisitingActions preIteratorExp(IteratorExp self) {
		return actions("source", "iterators", "body");
	}
	
	
	
	@Override
	public void inIterator(Iterator self) {
		if ( self.eContainer() instanceof LoopExp ) { // IteratorExp & IterateExp
			Type collType = attr.typeOf(((LoopExp) self.eContainer()).getSource());
			Type t  = null;
			if ( !(collType instanceof CollectionType) ) {
				// t = errors().signalIteratorOverNoCollectionType(collType, (LoopExp) self.eContainer());
				// Ignore the error and pass the mono-valued type to the iterator
				// The problem is reported in IteratorExp
				t = collType;
			} else {
				t = ((CollectionNamespace) collType.getMetamodelRef()).unwrap();
			}
	
			attr.linkExprType( t );
		// } else if ( self.container_() instanceof IterateExp ) {
			
		} else if ( self.eContainer() instanceof ForEachOutPatternElement ){
			ForEachOutPatternElement e = (ForEachOutPatternElement) self.eContainer();
			Type t = attr.typeOf(e.getCollection());
			if ( ! (t instanceof CollectionType) ) {
				t = AnalyserContext.getErrorModel().signalExpectedCollectionInForEachOutputPattern(e);
			} else {
				t = ((CollectionType) t).getContainedType();
			}
			attr.linkExprType(self, t);
		} else {
			ForStat fs = (ForStat) self.eContainer();
			Type t = attr.typeOf(fs.getCollection());
			if ( ! (t instanceof CollectionType) ) {
				t = AnalyserContext.getErrorModel().signalExpectedCollectionInForStat(fs);
			} else {
				t = ((CollectionType) t).getContainedType();
			}
			attr.linkExprType(self, t);
			
		}
	}
	
	@Override
	public VisitingActions preIterateExp(IterateExp self) {
		return actions("source", "iterators" , "result", "type" , "body" );
	}
	
	@Override
	public VisitingActions preForEachOutPatternElement(ForEachOutPatternElement self) {
		return actions("type" , "initExpression" , "collection", "iterator", "bindings" ); 
	}
	
	@Override
	public VisitingActions preForStat(ForStat self) {
		return actions("collection" , "iterator" , "statements" ); 
	}
	
	
	@Override
	public void inIterateExp(IterateExp self) {
		Type srcType =  attr.typeOf( self.getSource() );
		if ( !(srcType instanceof CollectionType) ) {
			errors().signalIteratorOverNoCollectionType(srcType, self);			
		} 
		attr.linkExprType( attr.typeOf( self.getBody() ) );
				
	}
	
	@Override
	public void inIteratorExp(IteratorExp self) {
		Type srcType =  attr.typeOf( self.getSource() );
		if ( !(srcType instanceof CollectionType) ) {
			Type t = errors().signalIteratorOverNoCollectionType(srcType, self);
			attr.linkExprType(t);
		} else {
			Type bodyType = attr.typeOf( self.getBody() ); 
			
			CollectionNamespace cspace = (CollectionNamespace) srcType.getMetamodelRef();
			Type iteratorType = cspace.getIteratorType(self.getName(), bodyType, self);
			attr.linkExprType( iteratorType );

			if ( iteratorType.getNoCastedType() != null ) {				
				typ().markImplicitlyCasted(self, iteratorType, iteratorType.getNoCastedType());
			}
		}
		
	}
		
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		checkAccessToUndefined(self);
		checkAccessToEmptyCollection(self);

		Type t = attr.typeOf( self.getSource() );
		ITypeNamespace tspace = (ITypeNamespace) t.getMetamodelRef();
		Type t2 = tspace.getFeatureType(self.getName(), self, (casted) -> {
			// Beware this may collide with the other was casted...
			typ().markImplicitlyCasted(self.getSource(), casted, t);
			attr.linkExprType(self.getSource(), casted);
		});
		
		computeUndefinedAttribute(self, tspace);
		
		attr.linkExprType(t2);
		
		if ( attr.wasCasted(getActualCastedElement(self.getSource())) ){
			self.getSource().setInferredType(t); 
			typ().markImplicitlyCasted(getActualCastedElement(self.getSource()), t, attr.noCastedTypeOf(getActualCastedElement(self.getSource())));
		}
	}


	private OclExpression getActualCastedElement(OclExpression expr) {
		if ( expr instanceof VariableExp && ((VariableExp) expr).getReferredVariable().eContainer() instanceof LetExp ) {
			OclExpression initExpression = ((VariableDeclaration) ((VariableExp) expr).getReferredVariable()).getInitExpression();
			if ( attr.wasCasted(initExpression) ) {			
				return initExpression;
			}
		}
		return expr;
	}


	private void checkAccessToUndefined(PropertyCallExp self) {
		if ( mayBeUndefined.contains(self.getSource()) ) {
			AnalyserContext.getErrorModel().signalAccessToUndefinedValue(self);
		}
	}
	
	private void checkAccessToEmptyCollection(PropertyCallExp self) {
		if ( self.getSource() instanceof PropertyCallExp && 
				mayBeEmptyCollection.contains(((PropertyCallExp) self.getSource()).getSource()) ) {
			AnalyserContext.getErrorModel().signalAccessToUndefinedValue_ThroughEmptyCollection(self);
		}
	}
	
	protected Set<OclExpression> mayBeUndefined = new HashSet<OclExpression>();

	private void computeUndefinedAttribute(NavigationOrAttributeCallExp self,
			ITypeNamespace tspace) {
		// Check cardinalities
		// This could go in another pass
		if ( tspace instanceof ClassNamespace ) {
			ClassNamespace cn = (ClassNamespace) tspace;
			FeatureInfo info = cn.getFeatureInfo(self.getName());
			if ( info != null && info.mayBeUndefined() ) {
				switch ( attr.getVarScope().getUndefinedStatus(self) ) {
				case MAY_BE_UNDEFINED: 
					mayBeUndefined.add(self);
					// System.out.println("May be undefined! " + self.getLocation());
					break;
				case NOT_CHECKED:
					mayBeUndefined.add(self);
					// System.out.println("Undefined not checked! " + self.getLocation());
					break;
				case CANNOT_BE_UNDEFINED:
					errors().discardedAccessToUndefinedValue(self);
					break;
				}
			}
		}
	}

	protected Set<OclExpression> mayBeEmptyCollection = new HashSet<OclExpression>();

	private void computeEmptyCollection(CollectionOperationCallExp self) {
		if ( self.getOperationName().equals("first") || self.getOperationName().equals("last") || self.getOperationName().equals("at")) {
			boolean hasEmptyCollectionHint = false;
			if ( self.getSource() instanceof LoopExp ) {
				// Need to be checked by the solver...
				hasEmptyCollectionHint = true;
			} else if ( self.getSource() instanceof NavigationOrAttributeCallExp ) {
				NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) self.getSource();
				Type t = attr.typeOf( nav.getSource() );
				ITypeNamespace tspace = (ITypeNamespace) t.getMetamodelRef();
				if ( tspace instanceof ClassNamespace ) {
					FeatureInfo info = ((ClassNamespace) tspace).getFeatureInfo(nav.getName());
					if ( info != null ) 
						hasEmptyCollectionHint = info.mayBeEmptyCollection();
				}
			} else if ( self.getSource() instanceof OperationCallExp ) {
				hasEmptyCollectionHint = ((OperationCallExp) self.getSource()).getOperationName().equals("allInstances");
			}
			
			if ( ! hasEmptyCollectionHint )
				return;
			
			switch ( attr.getVarScope().getEmptyCollectionStatus(self.getSource()) ) {
			case MAY_BE_UNDEFINED: 
				mayBeEmptyCollection.add(self.getSource());
				// System.out.println("May be undefined! " + self.getLocation());
				break;
			case NOT_CHECKED:
				mayBeEmptyCollection.add(self.getSource());
				// System.out.println("Undefined not checked! " + self.getLocation());
				break;
			case CANNOT_BE_UNDEFINED:
				// TODO: Do I need to do this?
				// errors().discardedAccessToUndefinedValue(self);
				break;
			}			
		}
	}

	@Override
	public void inOperationCallExp(OperationCallExp self) {
		// This is a way to include unchecked exceptions
		if ( self.getOperationName().equals("fail_") ) {
			attr.linkExprType(TypesFactory.eINSTANCE.createTypeError());
			return;
		}
		
		if ( ! AtlTypes.oclAny().hasOperation(self.getOperationName()) ) {
			checkAccessToUndefined(self);
		}
		
		if ( self.getOperationName().equals("resolveTemp") ) {
			resolveResolveTemp(self);
			return;
		} else if ( self.getOperationName().equals("oclAsType") ) {
			// Special addition, not supported by ATL
			Type type;
			if (self.getArguments().size() != 1) {
				type = errors().signalInvalidArgument("oclAsType", "Required one parameter", self);
			} else {
				type = attr.typeOf( self.getArguments().get(0) );
			}
			attr.linkExprType(type);
			return;
		}
		
		Type t = attr.typeOf( self.getSource() );
		Type[] arguments  = new Type[self.getArguments().size()];
		for(int i = 0; i < self.getArguments().size(); i++) {
			arguments[i] = attr.typeOf(self.getArguments().get(i));
		}
			
		ITypeNamespace tspace = (ITypeNamespace) t.getMetamodelRef();	
		attr.linkExprType( tspace.getOperationType(self.getOperationName(), arguments, self) );
		
		
		// TODO Shouldnt' I check that this is in an ifCondition?? Perhaps add this to openScope after condition has been
		// evaluated and add a traversal to fill the scope if needed??
					
		addToKindOfScope(self, arguments);
		addToOclUndefinedScope(self);
		
		// marking already casted elements...
		if ( attr.wasCasted(self.getSource()) ){
			self.getSource().setInferredType(t);
			typ().markImplicitlyCasted(self.getSource(), t, computeNoCastedType(self));
		}
	}

	private void addToOclUndefinedScope(OperationCallExp self) {
		int oclUndefinedStyle = -1;
		
		if ( self.getOperationName().equals("oclIsUndefined") ) { 
			oclUndefinedStyle = 0;
		} else if ( self.getOperationName().equals("=") && self.getArguments().size() == 1 && self.getArguments().get(0) instanceof OclUndefinedExp ){
			oclUndefinedStyle = 0;
		} else if ( self.getOperationName().equals("<>") && self.getArguments().size() == 1 && self.getArguments().get(0) instanceof OclUndefinedExp ){
			oclUndefinedStyle = 1;
		}
		
		if ( oclUndefinedStyle != -1 ) {
			// Discard those with a negation
			int numberOfNegations = computeConditionPosition(self);
					
			if ( numberOfNegations != -1 ) {
				numberOfNegations += oclUndefinedStyle;
				VariableExp ve = VariableScope.findStartingVarExp(self);
				// Undefined checking only works if the expression starts with a variable.
				// Thinks like Type.allInstances()->first() <> Undefined do not work
				if ( ve != null )  {
					boolean hasNegation = numberOfNegations % 2 == 1;
					if ( ! hasNegation ) {
						attr.getVarScope().putIsUndefined(ve.getReferredVariable(), self.getSource());
					} else {
						System.out.println(self);
						attr.getVarScope().putIsNotUndefined(ve.getReferredVariable(), self.getSource());
					}
				}
			}
		}
	}
	
	private void addToKindOfScope(OperationCallExp self, Type[] arguments) {
		if ( self.getOperationName().equals("oclIsKindOf") || 
			 self.getOperationName().equals("oclIsTypeOf") ) {
				
			if ( self.getInferredType() instanceof Unknown ) {
				// Avoid reasoning for OclAny, which is not properly implemented
				return;
			}
			
			if ( arguments.length != 1 ) {
				return;
			}  else {
				// This is to avoid false positives in expressions such as: e.input.oclIsKindOf(WF!Fork) or e.input.oclIsKindOf(WF!Join)
				Type noCasted = computeNoCastedType(self);
				if ( ! TypingModel.isCompatibleOclKindOfParam(noCasted, arguments[0]) ) {
					//but we don't report an error if you pass a supertype, which yields to true
					if ( ! TypingModel.isCompatibleOclKindOfParam(arguments[0], noCasted) ) {
						errors().signalInvalidArgument("oclIsKindOf", 
							"Type " + TypeUtils.typeToString(arguments[0]) + " not compatible with " + TypeUtils.typeToString(noCasted), self);
					}
					return;
				}
			}
				
			int numberOfNegations = computeConditionPosition(self);						
			if ( numberOfNegations != -1 ) {
				VariableExp ve = VariableScope.findStartingVarExp(self);
				boolean hasNegation = numberOfNegations % 2 == 1;
				if ( ! hasNegation ) {
					Type kindOfType = attr.typeOf(self.getArguments().get(0));
					attr.getVarScope().putKindOf(ve.getReferredVariable(), self.getSource(), kindOfType);
					// If something is confirmed at runtime to be "of type", then in cannot be undefined
					attr.getVarScope().putIsNotUndefined(ve.getReferredVariable(), self.getSource());
				} else {
					Type kindOfType = attr.typeOf(self.getArguments().get(0));
					attr.getVarScope().putNotKindOf(ve.getReferredVariable(), self.getSource(), kindOfType);
				}
			}
		}
	}
	

	private Type computeNoCastedType(OperationCallExp self) {
		return attr.noCastedTypeOf(self.getSource());
	}


	/**
	 * Checks if a given expression is an condition position and the estimated number
	 * of negations. An odd number of negations means "one" negation, while 0 or even number
	 * basically means no negation.
	 * 
	 * @param self
	 * @return -1 if it is not in condition composition
	 */
	protected int computeConditionPosition(OclExpression self) {
		// boolean hasNegation = false;
		int numberOfNegations = 0;
		boolean inConditionPosition = false;
		OclExpression child = self;
		EObject parent = self.eContainer();
		
		// We have to stop at the loop expression because the it sets an scope
		while ( parent instanceof OclExpression && !(parent instanceof LoopExp) ) {
//				if ( hasNegation == false && parent instanceof OperatorCallExp ) {
//					hasNegation = ((OperatorCallExp) parent).getOperationName().equals("not");
//				}

			if ( parent instanceof OperatorCallExp && ((OperatorCallExp) parent).getOperationName().equals("not") ) {
				numberOfNegations++;
			}
			else if ( parent instanceof IfExp && ((IfExp) parent).getCondition() == child ) {
				inConditionPosition = true;
				break;
			}  else if ( parent instanceof IfStat && ((IfStat) parent).getCondition() == child ) {
				inConditionPosition = true;
				break;
			}  else if ( parent instanceof OperatorCallExp && ((OperationCallExp) parent).getOperationName().equals("implies")) {
				inConditionPosition = true;
				break;				
			}
			
			child  = (OclExpression) parent;
			parent = parent.eContainer();				
		}
		
		if ( parent instanceof InPattern && ((InPattern) parent).getFilter() == child ) {
			inConditionPosition = true;
		}

		if ( ! inConditionPosition ) {
			numberOfNegations = -1;
		}
		return numberOfNegations;
	}
	
	private void resolveResolveTemp(OperationCallExp self) {
		if ( ! (root instanceof Module ) ) {
			// errors().signalNoRecoverableError("resolveTemp only available in transformation modules", self);
			Type t = errors().signalInvalidArgument("resolveTemp", "resolveTemp only available in transformation modules", self);	
			attr.linkExprType(t);
			return;
		}
		
		if ( self.getArguments().size() != 2 ) {
			Type t = errors().signalInvalidArgument("resolveTemp", "resolveTemp expects two arguments", self);	
			attr.linkExprType(t);
			return;
		}

		if ( ! (self.getArguments().get(1) instanceof StringExp ) ) {
			System.out.println("Cannot deal with resolveTemp with second argument not being string: " + self.getLocation());
			// attr.linkExprType(typ().newUnknownType());
			// attr.linkExprType(typ().newTypeErrorType(null));
			attr.linkExprType(typ().newUnknownType());
			return;
		}
				
		OclExpression resolvedObj = self.getArguments().get(0);
		String expectedVarName = ((StringExp) self.getArguments().get(1)).getStringSymbol();
		ArrayList<MatchedRule> compatibleRules = new ArrayList<MatchedRule>();
		String withSameVarRules = ""; // TODO: Convert into a collection

		Type type_ = attr.typeOf(resolvedObj);
		
		Type errorType = null;
		
		List<Type> selectedTypes = new ArrayList<Type>();		
		
		for(Type t : typ().allPossibleTypes(type_)) {
			if ( t instanceof TypeError ) {
				// In case the error is propagatated, I don't want to check anything else
				errorType = t;
				break;
			}
			
			if ( t instanceof TupleType ) {
				// TODO: Try to resolve rules, but for the moment just return OclAny
				selectedTypes.add(typ().newUnknownType());
				break;
			}
			
			if ( ! (t instanceof Metaclass) ) {
				errorType = errors().signalInvalidArgument("ResolveTemp expects an object", "Expression type is " + TypeUtils.typeToString(t), self);				
				break;
			}
				
			// This is similar to RuleAnalysis#analyseRuleResolution
			Metaclass m = (Metaclass) t;
			IClassNamespace ns = (IClassNamespace) m.getMetamodelRef();						
			Set<MatchedRule> rules = ns.getResolvingRules();
			
			if ( rules.size() == 0 ) {
				Type r = errors().signalResolveTempWithoutRule(self, type_); 
				errorType = r;
			} else {
				Type selectedType = null;
				for (MatchedRule mr : rules) {
					// This is the rule!
					for(SimpleOutPatternElement sope : ATLUtils.getAllSimpleOutputPatternElement(mr) ) {
						if ( sope.getVarName().equals(expectedVarName) ) {
							Type tsope = attr.typeOf(sope.getType());
							
							withSameVarRules += mr.getName() + ", ";
							if ( selectedType != null && ! typ().equalTypes(tsope, selectedType)) {
								errors().signalResolveTempGetsDifferentTargetTypes("Several rules may resolve the same resolveTemp with different target types: " + withSameVarRules, self);
							}
							
							// Create a resolution info object, even when what it is resolved may be
							// conflicting. Perhaps it could be marked what conflicts with what!
							ResolveTempResolution resolution = OCLFactory.eINSTANCE.createResolveTempResolution();
							resolution.setRule(mr);
							resolution.setElement(sope);
							resolution.getAllInvolvedRules().add(mr);
							resolution.getAllInvolvedRules().addAll(ATLUtils.allSuperRules(mr));
							self.getResolveTempResolvedBy().add(resolution);
							
							selectedType = tsope;								
						}
					}									
				}
				

				if ( selectedType != null ) {
					// The source element may be resolved by at least one rule.
					selectedTypes.add(selectedType);
					// Now we check if resolution is complete
					RuleAnalysis.findPossibleUnresolvedClasses(m, 
							(problematicClasses, problematicClassesImplicit) -> {					
								errors().signalResolveTempPossiblyUnresolved(self, resolvedObj, m.getKlass(), problematicClasses, problematicClassesImplicit);
								return true;
							});					
				} else {
					compatibleRules = new ArrayList<MatchedRule>(rules);
					errorType = errors().signalResolveTempOutputPatternElementNotFound(self, type_, expectedVarName, compatibleRules);					
				}
			}
		}
		
		Type finalType = null;
		if ( selectedTypes.isEmpty() ) {
			finalType = errorType;
		} else {
			finalType = typ().getCommonType(selectedTypes);			
		}
		
		if ( finalType == null ) {
			// TODO: Check TextualPathExp2PathExp.atl raising the exception.
			//       It is the only case in which it happens
			throw new IllegalStateException("resolveTemp: " + self.getLocation());
			// finalType = typ().newUnknownType();
		}

		attr.linkExprType(finalType);

		
		/*
		boolean sourceCompatibleRuleFound = false;
		Module m = (Module) root;
		for(ModuleElement e : m.getElements()) {
			if ( e instanceof MatchedRule && ! ((MatchedRule) e).isIsAbstract()) {
				MatchedRule mr = (MatchedRule) e;
				if ( mr.getInPattern().getElements().size() == 1 ) {
					SimpleInPatternElement pe = (SimpleInPatternElement) mr.getInPattern().getElements().get(0);
					Type supertype = attr.typeOf(pe.getType());
					if ( typ().isCompatible(type_, supertype) ) {
						sourceCompatibleRuleFound = true;
						
						compatibleRules.add(mr);
						
						// This is the rule!
						for(SimpleOutPatternElement sope : ATLUtils.getAllSimpleOutputPatternElement(mr) ) {
							if ( sope.getVarName().equals(expectedVarName) ) {
								Type t = attr.typeOf(sope.getType());
								
								withSameVarRules += mr.getName() + ", ";
								if ( selectedType != null && ! typ().equalTypes(t, selectedType)) {
									errors().signalResolveTempGetsDifferentTargetTypes("Several rules may resolve the same resolveTemp with different target types: " + withSameVarRules, self);
								}
								
								// Create a resolution info object, even when what it is resolved may be
								// conflicting. Perhaps it could be marked what conflicts with what!
								ResolveTempResolution resolution = OCLFactory.eINSTANCE.createResolveTempResolution();
								resolution.setRule(mr);
								resolution.setElement(sope);
								self.getResolveTempResolvedBy().add(resolution);
								
								selectedType = t;								
							}
						}				
						
					}
				}
			}
		}
		
		if ( selectedType != null ) {
			attr.linkExprType(selectedType);
			return;
		}
		
		if ( ! sourceCompatibleRuleFound ) {
			Type r = errors().signalResolveTempWithoutRule(self, type_); 
			attr.linkExprType(r);
		} else {
			Type r = errors().signalResolveTempOutputPatternElementNotFound(self, type_, expectedVarName, compatibleRules);
			attr.linkExprType(r);
		}
		*/
	}


	@Override
	public void inVariableExp(VariableExp self) {
		if ( self.getReferredVariable().getVarName().equals("self") ) {
			// Find the container that defines the self's type
			EObject container = self.eContainer();
			while ( container != null ) {
				if ( container instanceof OclFeatureDefinition ) {
					OclContextDefinition ctx = ((OclFeatureDefinition) container).getContext_();	
					if ( ctx == null ) {
						attr.linkExprType( getThisModuleType() ); // self may refer to thisModule in a global helper...						
					} else {
						Type selfType = attr.typeOf(ctx.getContext_() );
						attr.linkExprType(self.getReferredVariable(), selfType);
						attr.linkExprType(selfType);
					}
					break;
				}
				container = container.eContainer();
			}
			
			if ( container == null ) {
				throw new IllegalStateException("Could not find context for self " + self.getLocation());
			}
			
		} else if ( self.getReferredVariable().getVarName().equals("thisModule") ) {
			attr.linkExprType( getThisModuleType() );
		} else {
			Type t = attr.typeOf(self.getReferredVariable());
			attr.linkExprType( t );
			
			
			if ( self.getReferredVariable().getInitExpression() != null && attr.wasCasted(self.getReferredVariable().getInitExpression()) ){
				// self.getSource().setInferredType(t); 
				typ().markImplicitlyCasted(self, t, attr.noCastedTypeOf(self.getReferredVariable().getInitExpression()));
			}

			
		}
	}
	
	/* OCL2 - Extensions, not available in ATL */
	@Override
	public void inSelectByKind(SelectByKind self) {
		final Type receptorType = attr.typeOf(self.getSource());
		final Type[] arguments  = new Type[self.getArguments().size()];
		for(int i = 0; i < self.getArguments().size(); i++) {
			arguments[i] = attr.typeOf(self.getArguments().get(i));
		}
		if ( self.getArguments().size() != 1 || !(self.getArguments().get(0) instanceof OclModelElement)  ) {
			ReflectiveClass refType = TypesFactory.eINSTANCE.createReflectiveClass();
			errors().signalOperationCallInvalidParameter("selectByType", new Type[] { refType }, arguments, 
					Collections.singletonList("type"), self);
			
			attr.linkExprType( receptorType );
			return;
		}
		
		if ( receptorType.getMetamodelRef() instanceof CollectionNamespace ) {
			CollectionNamespace cspace = (CollectionNamespace) receptorType.getMetamodelRef();
			Type iteratorType = cspace.newCollectionType(arguments[0]);
			attr.linkExprType( iteratorType );
		} else {
			// TODO: Create a proper error  
			Type errorType = errors().signalCollectionOperationNotFound("selectByKind", self);
			attr.linkExprType( errorType );
		}
	}
	
	@Override
	public void inCollectionOperationCallExp(final CollectionOperationCallExp self) {		
		final Type receptorType = attr.typeOf(self.getSource());
		final Type[] arguments  = new Type[self.getArguments().size()];
		for(int i = 0; i < self.getArguments().size(); i++) {
			arguments[i] = attr.typeOf(self.getArguments().get(i));
		}
		
		if ( ! ( receptorType instanceof CollectionType ) ) {
			final ITypeNamespace tspace = (ITypeNamespace) receptorType.getMetamodelRef();	
			if ( AnalyserContext.isOclStrict() ) {
				Type recType = errors().signalCollectionOperationOverNoCollectionType(receptorType, self, new IRecoveryAction() {				
					@Override
					public Type recover(ErrorModel m, LocalProblem p) {
						return tspace.getOperationType(self.getOperationName(), arguments, self);
					}
				});

				attr.linkExprType( recType );
			} else {
				Type t = tspace.getOperationType(self.getOperationName(), arguments, self);
				attr.linkExprType( t );
			}
			
		} else {
			CollectionNamespace namespace = (CollectionNamespace) receptorType.getMetamodelRef();
			String          operationName = self.getOperationName();
			
			Type t = namespace.getOperationType(operationName, arguments, self);
			attr.linkExprType(t);
		}
		
		computeEmptyCollection(self);
		
		// Check the condition that discard access to empty collections
		if ( isNotEmptyCollection(self) || isEmptyCollection(self) ) {
			int numberOfNegations = computeConditionPosition(self);

			if ( numberOfNegations != -1 ) {
				VariableExp ve = VariableScope.findStartingVarExp(self);
				// The A.allInstances()->notEmpty() is indicated with vd = null
				VariableDeclaration vd = ve == null ? null : ve.getReferredVariable();
				boolean hasNegation = numberOfNegations % 2 == 1;
				if ( ! hasNegation ) {
					if ( isEmptyCollection(self) ) {
						attr.getVarScope().putIsEmptyCollection(vd, self.getSource());
					} else if ( isNotEmptyCollection(self) ) {
						attr.getVarScope().putIsNotEmptyCollection(vd, self.getSource());
					}
				} else {
					if ( isNotEmptyCollection(self) ) {
						attr.getVarScope().putIsEmptyCollection(vd, self.getSource());
					} else if ( isEmptyCollection(self) ) {
						attr.getVarScope().putIsNotEmptyCollection(vd, self.getSource());
					}
				}
			}

		}
	}


	protected boolean isEmptyCollection(final CollectionOperationCallExp self) {
		return self.getOperationName().equals("isEmpty") || 
				isCollectionSize(self, (op, arg) -> "=".equals(op) && isIntLiteral(arg, (i) -> i == 0)); 
	}


	private boolean isIntLiteral(OclExpression arg, Function<Integer, Boolean> f) {
		return (arg instanceof IntegerExp) && f.apply( ((IntegerExp) arg).getIntegerSymbol() );
	}


	private boolean isCollectionSize(CollectionOperationCallExp self, BiFunction<String, OclExpression, Boolean> checker) {
		EObject container = self.eContainer();
		if ( container instanceof OperatorCallExp ) {
			OperatorCallExp op = (OperatorCallExp) container;
			return op.getArguments().size() == 1 ? checker.apply(op.getOperationName(), op.getArguments().get(0)) : false;
		}
		return false;
	}


	protected boolean isNotEmptyCollection(final CollectionOperationCallExp self) {
		return self.getOperationName().equals("notEmpty") || 
				isCollectionSize(self, (op, arg) -> {
					return ( ">".equals(op) && isIntLiteral(arg, (i) -> i >= 0) ) || 
						   ( ">=".equals(op) && isIntLiteral(arg, (i) -> i > 0) ) ||
						   ( "=".equals(op) && isIntLiteral(arg, (i) -> i != 0) );
				});
	}

	@Override
	public void inOperatorCallExp(OperatorCallExp self) {
		Type t = attr.typeOf(self.getSource());
		Type optional = null;
		if ( self.getArguments().size() > 0 )
			optional = attr.typeOf(self.getArguments().get(0));

		if ( optional instanceof TypeError ) {
			// propagate error
			attr.linkExprType(optional);
			return;
		}
		
		
		
		// Optional attributes of type integer, float and boolean will never provide a null
		if ( !(t instanceof IntegerType) && !(t instanceof FloatType) && !(t instanceof BooleanType) &&
			  ! (self.getOperationName().equals("=") || self.getOperationName().equals("<>")) ) {
			checkAccessToUndefined(self);
		}
		
		addToOclUndefinedScope(self);
		
		SmellDetection.checkOperatorSmell(errors(), attr, self);
		
		ITypeNamespace tspace = (ITypeNamespace) t.getMetamodelRef();
		attr.linkExprType(tspace.getOperatorType(self.getOperationName(), optional, self));
	}
	// 
	// Literal values
	// 
	
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		for(MetamodelNamespace ns : mm.getMetamodels()) {
			EnumType enum_ = ns.findEnumLiteral(self.getName());
			if ( enum_ != null ) {
				attr.linkExprType( enum_ );
				return;
			}
		}
		
		attr.linkExprType( errors().signalNoEnumLiteral(self.getName(), self) );
	}
	
	@Override
	public void inStringExp(StringExp obj) {
		attr.linkExprType(typ().newStringType());
	}

	@Override
	public void inIntegerExp(IntegerExp obj) {
		attr.linkExprType(typ().newIntegerType());
	}

	@Override
	public void inRealExp(RealExp obj) {
		attr.linkExprType(typ().newFloatType());
	}

	@Override
	public void inBooleanExp(BooleanExp self) {
		attr.linkExprType(typ().newBooleanType());
	}
	
	@Override
	public void inOclUndefinedExp(OclUndefinedExp obj) {
		attr.linkExprType(typ().newOclUndefinedType());		
	};

	@Override
	public void inSequenceExp(SequenceExp self) {
		// Three cases:
		//   - Non empty inicialization -> types is the union of all the expressions (elements reference)
		//   - Empty inicialization within VarDcl -> type of the VarDcl (may create circular dep if done naively!)
		//   - Unknown

		if ( self.getElements().isEmpty() ) {
			attr.linkExprType( typ().newSequenceType( typ().newEmptyCollectionType() ) );
		} else {
			Type commonType = computeCommonType(self.getElements());			
			attr.linkExprType( typ().newSequenceType( commonType ) );
		}		
	}
		
	private Type computeCommonType(List<OclExpression> elements) {
		List<Type> values = new ArrayList<Type>();
		for(int i = 0; i < elements.size(); i++) {
			values.add(attr.typeOf(elements.get(i)));
		}
		
		return typ().getCommonType(values);
	}

	
	/* Same as SequenceExp */
	@Override
	public void inBagExp(BagExp self) {
		if ( self.getElements().isEmpty() ) {
			attr.linkExprType( typ().newBagType( typ().newEmptyCollectionType() ) );
		} else {
			Type commonType = computeCommonType(self.getElements());
			attr.linkExprType( typ().newBagType( commonType ) );
		}		
	}

	/* Same as SequenceExp */
	@Override
	public void inSetExp(SetExp self) {
		if ( self.getElements().isEmpty() ) {
			attr.linkExprType( typ().newSetType( typ().newEmptyCollectionType() ) );
		} else {
			Type commonType = computeCommonType(self.getElements());
			attr.linkExprType( typ().newSetType( commonType ) );
		}		
	}
	

	/* Same as Copied from ordered set, using SET as OrderedSet! */
	@Override
	public void inOrderedSetExp(OrderedSetExp self) {
		if ( self.getElements().isEmpty() ) {
			attr.linkExprType( typ().newOrderedSetType( typ().newEmptyCollectionType() ) );
		} else {
			Type commonType = computeCommonType(self.getElements());
			attr.linkExprType( typ().newOrderedSetType( commonType ) );
		}		
	}

	@Override
	public void inMapExp(MapExp self) {
		if ( self.getElements().size() != 0 ) {
			// throw new UnsupportedOperationException("TODO: Implement map initialization with elements" + self.getLocation());
			List<Type> keys   = new ArrayList<Type>();
			List<Type> values = new ArrayList<Type>();
			for(int i = 0; i < self.getElements().size(); i++) {
				keys.add(attr.typeOf(self.getElements().get(i).getKey()));
				values.add(attr.typeOf(self.getElements().get(i).getValue()));
			}
			
			Type commonType = typ().getCommonType(keys);
			attr.linkExprType( typ().newMapType( commonType, typ().getCommonType(values)) );
		} else {
			attr.linkExprType( typ().newMapType( typ().newEmptyCollectionType(), typ().newEmptyCollectionType()) );
		}
	}
	
	@Override
	public void inTupleExp(TupleExp self) {
		Type[] attTypes   = new Type[self.getTuplePart().size()];
		String[] attNames = new String[self.getTuplePart().size()];
		
		int i = 0;
		for(TuplePart tp : self.getTuplePart()) {
			// TODO: As with var. declarations, check if type and initializer are compatible...
			if ( tp.getType() != null ) {
				attTypes[i] = attr.typeOf(tp.getType());
			} else {
				attTypes[i] = attr.typeOf(tp.getInitExpression());				
			}
			tp.setInferredType(attTypes[i]);
			attNames[i] = tp.getVarName();
			i++;
		}
		attr.linkExprType( typ().newTupleTuple(attNames, attTypes) );
	}
	
	// Begin-of Scopes
	@Override
	public void beforeMatchedRule(MatchedRule self) {
		attr.getVarScope().openScope();
	}
	
	@Override
	public void beforeLazyRule(LazyRule self) {
		// This is needed because in practice filters can be written, with "oclIsKindOf" expressions
		// inside, although the runtime will ignore it the analyzer can't
		attr.getVarScope().openScope();
	}
	
	@Override
	public void afterMatchedRule(MatchedRule self) {
		attr.getVarScope().closeScope();
	}

	@Override
	public void afterLazyRule(LazyRule self) {
		attr.getVarScope().closeScope();
	}
	
	@Override
	public void beforeContextHelper(ContextHelper self) {
		attr.getVarScope().openScope();
		attr.getVarScope().putVariable("self", ATLUtils.getHelperType(self).getInferredType());
	}
	
	public void afterContexHelper(ContextHelper self) {
		attr.getVarScope().closeScope();
	}
	
	public void openIfScope(IfExp self) {
		attr.getVarScope().openScope();
	}

	public void openImpliesScope(OperatorCallExp self) {
		attr.getVarScope().openScope();
	}

	public void openElseScope(IfExp self) {
		attr.getVarScope().negateCurrentScope();
	}


	public void closeIfElseScope(IfExp self) {
		attr.getVarScope().closeScope();
	}

	public void closeImpliesScope(OperatorCallExp self) {
		attr.getVarScope().closeScope();
	}

	@Override
	public void beforeIteratorExp(IteratorExp self) {
		attr.getVarScope().openScope();
	}
	
	@Override
	public void afterIteratorExp(IteratorExp self) {
		attr.getVarScope().closeScope();
	}
	

	// End-of Scopes
	
	
	// Generated code and profile support
	
	@Override
	public void inJavaBody(JavaBody self) {
		// There is no inference for Java code, we just use the type
		// declared in the operation as the inferred type
		Operation op = (Operation) self.eContainer();
		attr.linkExprType( attr.typeOf(op.getReturnType()) );
	}
	
	// End-of generated code
	
}
