package anatlyzer.atlext.processing;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

@SuppressWarnings("unchecked")
public class AbstractVisitor  {
	protected boolean _debug = false;
	protected EObject current;
	
	public VisitingActions preOclFeature(anatlyzer.atlext.OCL.OclFeature self) { return new VisitingActions("annotations"); } 
	public void inOclFeature(anatlyzer.atlext.OCL.OclFeature self) { if ( _debug ) System.out.println("Visiting OclFeature"); }
	public void beforeOclFeature(anatlyzer.atlext.OCL.OclFeature self) { }
	public void afterOclFeature(anatlyzer.atlext.OCL.OclFeature self) { }
	public VisitingActions preOclContextDefinition(anatlyzer.atlext.OCL.OclContextDefinition self) { return new VisitingActions("annotations" , "context_"); } 
	public void inOclContextDefinition(anatlyzer.atlext.OCL.OclContextDefinition self) { if ( _debug ) System.out.println("Visiting OclContextDefinition"); }
	public void beforeOclContextDefinition(anatlyzer.atlext.OCL.OclContextDefinition self) { }
	public void afterOclContextDefinition(anatlyzer.atlext.OCL.OclContextDefinition self) { }
	public VisitingActions preModuleElement(anatlyzer.atlext.ATL.ModuleElement self) { return new VisitingActions("annotations"); } 
	public void inModuleElement(anatlyzer.atlext.ATL.ModuleElement self) { if ( _debug ) System.out.println("Visiting ModuleElement"); }
	public void beforeModuleElement(anatlyzer.atlext.ATL.ModuleElement self) { }
	public void afterModuleElement(anatlyzer.atlext.ATL.ModuleElement self) { }
	public VisitingActions preStaticHelper(anatlyzer.atlext.ATL.StaticHelper self) { return new VisitingActions("annotations" , "callableParameters" , "definition"); } 
	public void inStaticHelper(anatlyzer.atlext.ATL.StaticHelper self) { if ( _debug ) System.out.println("Visiting StaticHelper"); }
	public void beforeStaticHelper(anatlyzer.atlext.ATL.StaticHelper self) { }
	public void afterStaticHelper(anatlyzer.atlext.ATL.StaticHelper self) { }
	public VisitingActions preMapType(anatlyzer.atlext.OCL.MapType self) { return new VisitingActions("annotations" , "type" , "valueType" , "keyType"); } 
	public void inMapType(anatlyzer.atlext.OCL.MapType self) { if ( _debug ) System.out.println("Visiting MapType"); }
	public void beforeMapType(anatlyzer.atlext.OCL.MapType self) { }
	public void afterMapType(anatlyzer.atlext.OCL.MapType self) { }
	public VisitingActions preLazyRule(anatlyzer.atlext.ATL.LazyRule self) { return new VisitingActions("annotations" , "outPattern" , "actionBlock" , "variables" , "inPattern" , "callableParameters"); } 
	public void inLazyRule(anatlyzer.atlext.ATL.LazyRule self) { if ( _debug ) System.out.println("Visiting LazyRule"); }
	public void beforeLazyRule(anatlyzer.atlext.ATL.LazyRule self) { }
	public void afterLazyRule(anatlyzer.atlext.ATL.LazyRule self) { }
	public VisitingActions preJavaBody(anatlyzer.atlext.OCL.JavaBody self) { return new VisitingActions("annotations" , "type"); } 
	public void inJavaBody(anatlyzer.atlext.OCL.JavaBody self) { if ( _debug ) System.out.println("Visiting JavaBody"); }
	public void beforeJavaBody(anatlyzer.atlext.OCL.JavaBody self) { }
	public void afterJavaBody(anatlyzer.atlext.OCL.JavaBody self) { }
	public VisitingActions preBinding(anatlyzer.atlext.ATL.Binding self) { return new VisitingActions("annotations" , "value" , "resolvedBy"); } 
	public void inBinding(anatlyzer.atlext.ATL.Binding self) { if ( _debug ) System.out.println("Visiting Binding"); }
	public void beforeBinding(anatlyzer.atlext.ATL.Binding self) { }
	public void afterBinding(anatlyzer.atlext.ATL.Binding self) { }
	public VisitingActions preModuleCallable(anatlyzer.atlext.ATL.ModuleCallable self) { return new VisitingActions("callableParameters"); } 
	public void inModuleCallable(anatlyzer.atlext.ATL.ModuleCallable self) { if ( _debug ) System.out.println("Visiting ModuleCallable"); }
	public void beforeModuleCallable(anatlyzer.atlext.ATL.ModuleCallable self) { }
	public void afterModuleCallable(anatlyzer.atlext.ATL.ModuleCallable self) { }
	public VisitingActions preOrderedSetType(anatlyzer.atlext.OCL.OrderedSetType self) { return new VisitingActions("annotations" , "type" , "elementType"); } 
	public void inOrderedSetType(anatlyzer.atlext.OCL.OrderedSetType self) { if ( _debug ) System.out.println("Visiting OrderedSetType"); }
	public void beforeOrderedSetType(anatlyzer.atlext.OCL.OrderedSetType self) { }
	public void afterOrderedSetType(anatlyzer.atlext.OCL.OrderedSetType self) { }
	public VisitingActions preSuperExp(anatlyzer.atlext.OCL.SuperExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inSuperExp(anatlyzer.atlext.OCL.SuperExp self) { if ( _debug ) System.out.println("Visiting SuperExp"); }
	public void beforeSuperExp(anatlyzer.atlext.OCL.SuperExp self) { }
	public void afterSuperExp(anatlyzer.atlext.OCL.SuperExp self) { }
	public VisitingActions preIntegerExp(anatlyzer.atlext.OCL.IntegerExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inIntegerExp(anatlyzer.atlext.OCL.IntegerExp self) { if ( _debug ) System.out.println("Visiting IntegerExp"); }
	public void beforeIntegerExp(anatlyzer.atlext.OCL.IntegerExp self) { }
	public void afterIntegerExp(anatlyzer.atlext.OCL.IntegerExp self) { }
	public VisitingActions preMapElement(anatlyzer.atlext.OCL.MapElement self) { return new VisitingActions("annotations" , "key" , "value"); } 
	public void inMapElement(anatlyzer.atlext.OCL.MapElement self) { if ( _debug ) System.out.println("Visiting MapElement"); }
	public void beforeMapElement(anatlyzer.atlext.OCL.MapElement self) { }
	public void afterMapElement(anatlyzer.atlext.OCL.MapElement self) { }
	public VisitingActions preGetAppliedStereotypesBody(anatlyzer.atlext.OCL.GetAppliedStereotypesBody self) { return new VisitingActions("annotations" , "type"); } 
	public void inGetAppliedStereotypesBody(anatlyzer.atlext.OCL.GetAppliedStereotypesBody self) { if ( _debug ) System.out.println("Visiting GetAppliedStereotypesBody"); }
	public void beforeGetAppliedStereotypesBody(anatlyzer.atlext.OCL.GetAppliedStereotypesBody self) { }
	public void afterGetAppliedStereotypesBody(anatlyzer.atlext.OCL.GetAppliedStereotypesBody self) { }
	public VisitingActions preBindingStat(anatlyzer.atlext.ATL.BindingStat self) { return new VisitingActions("annotations" , "source" , "value"); } 
	public void inBindingStat(anatlyzer.atlext.ATL.BindingStat self) { if ( _debug ) System.out.println("Visiting BindingStat"); }
	public void beforeBindingStat(anatlyzer.atlext.ATL.BindingStat self) { }
	public void afterBindingStat(anatlyzer.atlext.ATL.BindingStat self) { }
	public VisitingActions preModule(anatlyzer.atlext.ATL.Module self) { return new VisitingActions("annotations" , "libraries" , "inModels" , "outModels" , "elements"); } 
	public void inModule(anatlyzer.atlext.ATL.Module self) { if ( _debug ) System.out.println("Visiting Module"); }
	public void beforeModule(anatlyzer.atlext.ATL.Module self) { }
	public void afterModule(anatlyzer.atlext.ATL.Module self) { }
	public VisitingActions preTupleExp(anatlyzer.atlext.OCL.TupleExp self) { return new VisitingActions("annotations" , "type" , "tuplePart"); } 
	public void inTupleExp(anatlyzer.atlext.OCL.TupleExp self) { if ( _debug ) System.out.println("Visiting TupleExp"); }
	public void beforeTupleExp(anatlyzer.atlext.OCL.TupleExp self) { }
	public void afterTupleExp(anatlyzer.atlext.OCL.TupleExp self) { }
	public VisitingActions preCallable(anatlyzer.atlext.ATL.Callable self) { return new VisitingActions("callableParameters"); } 
	public void inCallable(anatlyzer.atlext.ATL.Callable self) { if ( _debug ) System.out.println("Visiting Callable"); }
	public void beforeCallable(anatlyzer.atlext.ATL.Callable self) { }
	public void afterCallable(anatlyzer.atlext.ATL.Callable self) { }
	public VisitingActions preVariableExp(anatlyzer.atlext.OCL.VariableExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inVariableExp(anatlyzer.atlext.OCL.VariableExp self) { if ( _debug ) System.out.println("Visiting VariableExp"); }
	public void beforeVariableExp(anatlyzer.atlext.OCL.VariableExp self) { }
	public void afterVariableExp(anatlyzer.atlext.OCL.VariableExp self) { }
	public VisitingActions preOclModel(anatlyzer.atlext.OCL.OclModel self) { return new VisitingActions("annotations"); } 
	public void inOclModel(anatlyzer.atlext.OCL.OclModel self) { if ( _debug ) System.out.println("Visiting OclModel"); }
	public void beforeOclModel(anatlyzer.atlext.OCL.OclModel self) { }
	public void afterOclModel(anatlyzer.atlext.OCL.OclModel self) { }
	public VisitingActions preLetExp(anatlyzer.atlext.OCL.LetExp self) { return new VisitingActions("annotations" , "type" , "variable" , "in_"); } 
	public void inLetExp(anatlyzer.atlext.OCL.LetExp self) { if ( _debug ) System.out.println("Visiting LetExp"); }
	public void beforeLetExp(anatlyzer.atlext.OCL.LetExp self) { }
	public void afterLetExp(anatlyzer.atlext.OCL.LetExp self) { }
	public VisitingActions preIfStat(anatlyzer.atlext.ATL.IfStat self) { return new VisitingActions("annotations" , "condition" , "thenStatements" , "elseStatements"); } 
	public void inIfStat(anatlyzer.atlext.ATL.IfStat self) { if ( _debug ) System.out.println("Visiting IfStat"); }
	public void beforeIfStat(anatlyzer.atlext.ATL.IfStat self) { }
	public void afterIfStat(anatlyzer.atlext.ATL.IfStat self) { }
	public VisitingActions preContextHelper(anatlyzer.atlext.ATL.ContextHelper self) { return new VisitingActions("annotations" , "callableParameters" , "definition"); } 
	public void inContextHelper(anatlyzer.atlext.ATL.ContextHelper self) { if ( _debug ) System.out.println("Visiting ContextHelper"); }
	public void beforeContextHelper(anatlyzer.atlext.ATL.ContextHelper self) { }
	public void afterContextHelper(anatlyzer.atlext.ATL.ContextHelper self) { }
	public VisitingActions preSetType(anatlyzer.atlext.OCL.SetType self) { return new VisitingActions("annotations" , "type" , "elementType"); } 
	public void inSetType(anatlyzer.atlext.OCL.SetType self) { if ( _debug ) System.out.println("Visiting SetType"); }
	public void beforeSetType(anatlyzer.atlext.OCL.SetType self) { }
	public void afterSetType(anatlyzer.atlext.OCL.SetType self) { }
	public VisitingActions preCollectionExp(anatlyzer.atlext.OCL.CollectionExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inCollectionExp(anatlyzer.atlext.OCL.CollectionExp self) { if ( _debug ) System.out.println("Visiting CollectionExp"); }
	public void beforeCollectionExp(anatlyzer.atlext.OCL.CollectionExp self) { }
	public void afterCollectionExp(anatlyzer.atlext.OCL.CollectionExp self) { }
	public VisitingActions preIfExp(anatlyzer.atlext.OCL.IfExp self) { return new VisitingActions("annotations" , "type" , "thenExpression" , "condition" , "elseExpression"); } 
	public void inIfExp(anatlyzer.atlext.OCL.IfExp self) { if ( _debug ) System.out.println("Visiting IfExp"); }
	public void beforeIfExp(anatlyzer.atlext.OCL.IfExp self) { }
	public void afterIfExp(anatlyzer.atlext.OCL.IfExp self) { }
	public VisitingActions preOrderedSetExp(anatlyzer.atlext.OCL.OrderedSetExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inOrderedSetExp(anatlyzer.atlext.OCL.OrderedSetExp self) { if ( _debug ) System.out.println("Visiting OrderedSetExp"); }
	public void beforeOrderedSetExp(anatlyzer.atlext.OCL.OrderedSetExp self) { }
	public void afterOrderedSetExp(anatlyzer.atlext.OCL.OrderedSetExp self) { }
	public VisitingActions preStringExp(anatlyzer.atlext.OCL.StringExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inStringExp(anatlyzer.atlext.OCL.StringExp self) { if ( _debug ) System.out.println("Visiting StringExp"); }
	public void beforeStringExp(anatlyzer.atlext.OCL.StringExp self) { }
	public void afterStringExp(anatlyzer.atlext.OCL.StringExp self) { }
	public VisitingActions preOperatorCallExp(anatlyzer.atlext.OCL.OperatorCallExp self) { return new VisitingActions("annotations" , "type" , "source" , "arguments" , "resolveTempResolvedBy"); } 
	public void inOperatorCallExp(anatlyzer.atlext.OCL.OperatorCallExp self) { if ( _debug ) System.out.println("Visiting OperatorCallExp"); }
	public void beforeOperatorCallExp(anatlyzer.atlext.OCL.OperatorCallExp self) { }
	public void afterOperatorCallExp(anatlyzer.atlext.OCL.OperatorCallExp self) { }
	public VisitingActions preOperationCallExp(anatlyzer.atlext.OCL.OperationCallExp self) { return new VisitingActions("annotations" , "type" , "source" , "arguments" , "resolveTempResolvedBy"); } 
	public void inOperationCallExp(anatlyzer.atlext.OCL.OperationCallExp self) { if ( _debug ) System.out.println("Visiting OperationCallExp"); }
	public void beforeOperationCallExp(anatlyzer.atlext.OCL.OperationCallExp self) { }
	public void afterOperationCallExp(anatlyzer.atlext.OCL.OperationCallExp self) { }
	public VisitingActions preLoopExp(anatlyzer.atlext.OCL.LoopExp self) { return new VisitingActions("annotations" , "type" , "source" , "body" , "iterators"); } 
	public void inLoopExp(anatlyzer.atlext.OCL.LoopExp self) { if ( _debug ) System.out.println("Visiting LoopExp"); }
	public void beforeLoopExp(anatlyzer.atlext.OCL.LoopExp self) { }
	public void afterLoopExp(anatlyzer.atlext.OCL.LoopExp self) { }
	public VisitingActions preInPattern(anatlyzer.atlext.ATL.InPattern self) { return new VisitingActions("annotations" , "elements" , "filter"); } 
	public void inInPattern(anatlyzer.atlext.ATL.InPattern self) { if ( _debug ) System.out.println("Visiting InPattern"); }
	public void beforeInPattern(anatlyzer.atlext.ATL.InPattern self) { }
	public void afterInPattern(anatlyzer.atlext.ATL.InPattern self) { }
	public VisitingActions preOclExpression(anatlyzer.atlext.OCL.OclExpression self) { return new VisitingActions("annotations" , "type"); } 
	public void inOclExpression(anatlyzer.atlext.OCL.OclExpression self) { if ( _debug ) System.out.println("Visiting OclExpression"); }
	public void beforeOclExpression(anatlyzer.atlext.OCL.OclExpression self) { }
	public void afterOclExpression(anatlyzer.atlext.OCL.OclExpression self) { }
	public VisitingActions preSequenceExp(anatlyzer.atlext.OCL.SequenceExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inSequenceExp(anatlyzer.atlext.OCL.SequenceExp self) { if ( _debug ) System.out.println("Visiting SequenceExp"); }
	public void beforeSequenceExp(anatlyzer.atlext.OCL.SequenceExp self) { }
	public void afterSequenceExp(anatlyzer.atlext.OCL.SequenceExp self) { }
	public VisitingActions preRealType(anatlyzer.atlext.OCL.RealType self) { return new VisitingActions("annotations" , "type"); } 
	public void inRealType(anatlyzer.atlext.OCL.RealType self) { if ( _debug ) System.out.println("Visiting RealType"); }
	public void beforeRealType(anatlyzer.atlext.OCL.RealType self) { }
	public void afterRealType(anatlyzer.atlext.OCL.RealType self) { }
	public VisitingActions preQuery(anatlyzer.atlext.ATL.Query self) { return new VisitingActions("annotations" , "libraries" , "body" , "helpers"); } 
	public void inQuery(anatlyzer.atlext.ATL.Query self) { if ( _debug ) System.out.println("Visiting Query"); }
	public void beforeQuery(anatlyzer.atlext.ATL.Query self) { }
	public void afterQuery(anatlyzer.atlext.ATL.Query self) { }
	public VisitingActions preIterator(anatlyzer.atlext.OCL.Iterator self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inIterator(anatlyzer.atlext.OCL.Iterator self) { if ( _debug ) System.out.println("Visiting Iterator"); }
	public void beforeIterator(anatlyzer.atlext.OCL.Iterator self) { }
	public void afterIterator(anatlyzer.atlext.OCL.Iterator self) { }
	public VisitingActions preMatchedRule(anatlyzer.atlext.ATL.MatchedRule self) { return new VisitingActions("annotations" , "outPattern" , "actionBlock" , "variables" , "inPattern"); } 
	public void inMatchedRule(anatlyzer.atlext.ATL.MatchedRule self) { if ( _debug ) System.out.println("Visiting MatchedRule"); }
	public void beforeMatchedRule(anatlyzer.atlext.ATL.MatchedRule self) { }
	public void afterMatchedRule(anatlyzer.atlext.ATL.MatchedRule self) { }
	public VisitingActions preIntegerType(anatlyzer.atlext.OCL.IntegerType self) { return new VisitingActions("annotations" , "type"); } 
	public void inIntegerType(anatlyzer.atlext.OCL.IntegerType self) { if ( _debug ) System.out.println("Visiting IntegerType"); }
	public void beforeIntegerType(anatlyzer.atlext.OCL.IntegerType self) { }
	public void afterIntegerType(anatlyzer.atlext.OCL.IntegerType self) { }
	public VisitingActions preTupleType(anatlyzer.atlext.OCL.TupleType self) { return new VisitingActions("annotations" , "type" , "attributes"); } 
	public void inTupleType(anatlyzer.atlext.OCL.TupleType self) { if ( _debug ) System.out.println("Visiting TupleType"); }
	public void beforeTupleType(anatlyzer.atlext.OCL.TupleType self) { }
	public void afterTupleType(anatlyzer.atlext.OCL.TupleType self) { }
	public VisitingActions preForStat(anatlyzer.atlext.ATL.ForStat self) { return new VisitingActions("annotations" , "iterator" , "collection" , "statements"); } 
	public void inForStat(anatlyzer.atlext.ATL.ForStat self) { if ( _debug ) System.out.println("Visiting ForStat"); }
	public void beforeForStat(anatlyzer.atlext.ATL.ForStat self) { }
	public void afterForStat(anatlyzer.atlext.ATL.ForStat self) { }
	public VisitingActions preTupleTypeAttribute(anatlyzer.atlext.OCL.TupleTypeAttribute self) { return new VisitingActions("annotations" , "type"); } 
	public void inTupleTypeAttribute(anatlyzer.atlext.OCL.TupleTypeAttribute self) { if ( _debug ) System.out.println("Visiting TupleTypeAttribute"); }
	public void beforeTupleTypeAttribute(anatlyzer.atlext.OCL.TupleTypeAttribute self) { }
	public void afterTupleTypeAttribute(anatlyzer.atlext.OCL.TupleTypeAttribute self) { }
	public VisitingActions prePropertyCallExp(anatlyzer.atlext.OCL.PropertyCallExp self) { return new VisitingActions("annotations" , "type" , "source"); } 
	public void inPropertyCallExp(anatlyzer.atlext.OCL.PropertyCallExp self) { if ( _debug ) System.out.println("Visiting PropertyCallExp"); }
	public void beforePropertyCallExp(anatlyzer.atlext.OCL.PropertyCallExp self) { }
	public void afterPropertyCallExp(anatlyzer.atlext.OCL.PropertyCallExp self) { }
	public VisitingActions preRuleWithPattern(anatlyzer.atlext.ATL.RuleWithPattern self) { return new VisitingActions("annotations" , "outPattern" , "actionBlock" , "variables" , "inPattern"); } 
	public void inRuleWithPattern(anatlyzer.atlext.ATL.RuleWithPattern self) { if ( _debug ) System.out.println("Visiting RuleWithPattern"); }
	public void beforeRuleWithPattern(anatlyzer.atlext.ATL.RuleWithPattern self) { }
	public void afterRuleWithPattern(anatlyzer.atlext.ATL.RuleWithPattern self) { }
	public VisitingActions preTuplePart(anatlyzer.atlext.OCL.TuplePart self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inTuplePart(anatlyzer.atlext.OCL.TuplePart self) { if ( _debug ) System.out.println("Visiting TuplePart"); }
	public void beforeTuplePart(anatlyzer.atlext.OCL.TuplePart self) { }
	public void afterTuplePart(anatlyzer.atlext.OCL.TuplePart self) { }
	public VisitingActions preCallableParameter(anatlyzer.atlext.ATL.CallableParameter self) { return new VisitingActions(); } 
	public void inCallableParameter(anatlyzer.atlext.ATL.CallableParameter self) { if ( _debug ) System.out.println("Visiting CallableParameter"); }
	public void beforeCallableParameter(anatlyzer.atlext.ATL.CallableParameter self) { }
	public void afterCallableParameter(anatlyzer.atlext.ATL.CallableParameter self) { }
	public VisitingActions preBagExp(anatlyzer.atlext.OCL.BagExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inBagExp(anatlyzer.atlext.OCL.BagExp self) { if ( _debug ) System.out.println("Visiting BagExp"); }
	public void beforeBagExp(anatlyzer.atlext.OCL.BagExp self) { }
	public void afterBagExp(anatlyzer.atlext.OCL.BagExp self) { }
	public VisitingActions preNavigationOrAttributeCallExp(anatlyzer.atlext.OCL.NavigationOrAttributeCallExp self) { return new VisitingActions("annotations" , "type" , "source"); } 
	public void inNavigationOrAttributeCallExp(anatlyzer.atlext.OCL.NavigationOrAttributeCallExp self) { if ( _debug ) System.out.println("Visiting NavigationOrAttributeCallExp"); }
	public void beforeNavigationOrAttributeCallExp(anatlyzer.atlext.OCL.NavigationOrAttributeCallExp self) { }
	public void afterNavigationOrAttributeCallExp(anatlyzer.atlext.OCL.NavigationOrAttributeCallExp self) { }
	public VisitingActions preHelper(anatlyzer.atlext.ATL.Helper self) { return new VisitingActions("annotations" , "callableParameters" , "definition"); } 
	public void inHelper(anatlyzer.atlext.ATL.Helper self) { if ( _debug ) System.out.println("Visiting Helper"); }
	public void beforeHelper(anatlyzer.atlext.ATL.Helper self) { }
	public void afterHelper(anatlyzer.atlext.ATL.Helper self) { }
	public VisitingActions preOutPattern(anatlyzer.atlext.ATL.OutPattern self) { return new VisitingActions("annotations" , "dropPattern" , "elements"); } 
	public void inOutPattern(anatlyzer.atlext.ATL.OutPattern self) { if ( _debug ) System.out.println("Visiting OutPattern"); }
	public void beforeOutPattern(anatlyzer.atlext.ATL.OutPattern self) { }
	public void afterOutPattern(anatlyzer.atlext.ATL.OutPattern self) { }
	public VisitingActions preAttribute(anatlyzer.atlext.OCL.Attribute self) { return new VisitingActions("annotations" , "initExpression" , "type"); } 
	public void inAttribute(anatlyzer.atlext.OCL.Attribute self) { if ( _debug ) System.out.println("Visiting Attribute"); }
	public void beforeAttribute(anatlyzer.atlext.OCL.Attribute self) { }
	public void afterAttribute(anatlyzer.atlext.OCL.Attribute self) { }
	public VisitingActions preIteratorExp(anatlyzer.atlext.OCL.IteratorExp self) { return new VisitingActions("annotations" , "type" , "source" , "body" , "iterators"); } 
	public void inIteratorExp(anatlyzer.atlext.OCL.IteratorExp self) { if ( _debug ) System.out.println("Visiting IteratorExp"); }
	public void beforeIteratorExp(anatlyzer.atlext.OCL.IteratorExp self) { }
	public void afterIteratorExp(anatlyzer.atlext.OCL.IteratorExp self) { }
	public VisitingActions preInPatternElement(anatlyzer.atlext.ATL.InPatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inInPatternElement(anatlyzer.atlext.ATL.InPatternElement self) { if ( _debug ) System.out.println("Visiting InPatternElement"); }
	public void beforeInPatternElement(anatlyzer.atlext.ATL.InPatternElement self) { }
	public void afterInPatternElement(anatlyzer.atlext.ATL.InPatternElement self) { }
	public VisitingActions preForEachOutPatternElement(anatlyzer.atlext.ATL.ForEachOutPatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression" , "bindings" , "collection" , "iterator"); } 
	public void inForEachOutPatternElement(anatlyzer.atlext.ATL.ForEachOutPatternElement self) { if ( _debug ) System.out.println("Visiting ForEachOutPatternElement"); }
	public void beforeForEachOutPatternElement(anatlyzer.atlext.ATL.ForEachOutPatternElement self) { }
	public void afterForEachOutPatternElement(anatlyzer.atlext.ATL.ForEachOutPatternElement self) { }
	public VisitingActions preVariableDeclaration(anatlyzer.atlext.OCL.VariableDeclaration self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inVariableDeclaration(anatlyzer.atlext.OCL.VariableDeclaration self) { if ( _debug ) System.out.println("Visiting VariableDeclaration"); }
	public void beforeVariableDeclaration(anatlyzer.atlext.OCL.VariableDeclaration self) { }
	public void afterVariableDeclaration(anatlyzer.atlext.OCL.VariableDeclaration self) { }
	public VisitingActions preResolveTempResolution(anatlyzer.atlext.OCL.ResolveTempResolution self) { return new VisitingActions(); } 
	public void inResolveTempResolution(anatlyzer.atlext.OCL.ResolveTempResolution self) { if ( _debug ) System.out.println("Visiting ResolveTempResolution"); }
	public void beforeResolveTempResolution(anatlyzer.atlext.OCL.ResolveTempResolution self) { }
	public void afterResolveTempResolution(anatlyzer.atlext.OCL.ResolveTempResolution self) { }
	public VisitingActions preNumericExp(anatlyzer.atlext.OCL.NumericExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inNumericExp(anatlyzer.atlext.OCL.NumericExp self) { if ( _debug ) System.out.println("Visiting NumericExp"); }
	public void beforeNumericExp(anatlyzer.atlext.OCL.NumericExp self) { }
	public void afterNumericExp(anatlyzer.atlext.OCL.NumericExp self) { }
	public VisitingActions preOclModelElement(anatlyzer.atlext.OCL.OclModelElement self) { return new VisitingActions("annotations" , "type"); } 
	public void inOclModelElement(anatlyzer.atlext.OCL.OclModelElement self) { if ( _debug ) System.out.println("Visiting OclModelElement"); }
	public void beforeOclModelElement(anatlyzer.atlext.OCL.OclModelElement self) { }
	public void afterOclModelElement(anatlyzer.atlext.OCL.OclModelElement self) { }
	public VisitingActions preBagType(anatlyzer.atlext.OCL.BagType self) { return new VisitingActions("annotations" , "type" , "elementType"); } 
	public void inBagType(anatlyzer.atlext.OCL.BagType self) { if ( _debug ) System.out.println("Visiting BagType"); }
	public void beforeBagType(anatlyzer.atlext.OCL.BagType self) { }
	public void afterBagType(anatlyzer.atlext.OCL.BagType self) { }
	public VisitingActions preRuleResolutionInfo(anatlyzer.atlext.ATL.RuleResolutionInfo self) { return new VisitingActions(); } 
	public void inRuleResolutionInfo(anatlyzer.atlext.ATL.RuleResolutionInfo self) { if ( _debug ) System.out.println("Visiting RuleResolutionInfo"); }
	public void beforeRuleResolutionInfo(anatlyzer.atlext.ATL.RuleResolutionInfo self) { }
	public void afterRuleResolutionInfo(anatlyzer.atlext.ATL.RuleResolutionInfo self) { }
	public VisitingActions preStringType(anatlyzer.atlext.OCL.StringType self) { return new VisitingActions("annotations" , "type"); } 
	public void inStringType(anatlyzer.atlext.OCL.StringType self) { if ( _debug ) System.out.println("Visiting StringType"); }
	public void beforeStringType(anatlyzer.atlext.OCL.StringType self) { }
	public void afterStringType(anatlyzer.atlext.OCL.StringType self) { }
	public VisitingActions preTypedElement(anatlyzer.atlext.OCL.TypedElement self) { return new VisitingActions(); } 
	public void inTypedElement(anatlyzer.atlext.OCL.TypedElement self) { if ( _debug ) System.out.println("Visiting TypedElement"); }
	public void beforeTypedElement(anatlyzer.atlext.OCL.TypedElement self) { }
	public void afterTypedElement(anatlyzer.atlext.OCL.TypedElement self) { }
	public VisitingActions preLibrary(anatlyzer.atlext.ATL.Library self) { return new VisitingActions("annotations" , "libraries" , "helpers"); } 
	public void inLibrary(anatlyzer.atlext.ATL.Library self) { if ( _debug ) System.out.println("Visiting Library"); }
	public void beforeLibrary(anatlyzer.atlext.ATL.Library self) { }
	public void afterLibrary(anatlyzer.atlext.ATL.Library self) { }
	public VisitingActions preCollectionType(anatlyzer.atlext.OCL.CollectionType self) { return new VisitingActions("annotations" , "type" , "elementType"); } 
	public void inCollectionType(anatlyzer.atlext.OCL.CollectionType self) { if ( _debug ) System.out.println("Visiting CollectionType"); }
	public void beforeCollectionType(anatlyzer.atlext.OCL.CollectionType self) { }
	public void afterCollectionType(anatlyzer.atlext.OCL.CollectionType self) { }
	public VisitingActions preOclAnyType(anatlyzer.atlext.OCL.OclAnyType self) { return new VisitingActions("annotations" , "type"); } 
	public void inOclAnyType(anatlyzer.atlext.OCL.OclAnyType self) { if ( _debug ) System.out.println("Visiting OclAnyType"); }
	public void beforeOclAnyType(anatlyzer.atlext.OCL.OclAnyType self) { }
	public void afterOclAnyType(anatlyzer.atlext.OCL.OclAnyType self) { }
	public VisitingActions preIterateExp(anatlyzer.atlext.OCL.IterateExp self) { return new VisitingActions("annotations" , "type" , "source" , "body" , "iterators" , "result"); } 
	public void inIterateExp(anatlyzer.atlext.OCL.IterateExp self) { if ( _debug ) System.out.println("Visiting IterateExp"); }
	public void beforeIterateExp(anatlyzer.atlext.OCL.IterateExp self) { }
	public void afterIterateExp(anatlyzer.atlext.OCL.IterateExp self) { }
	public VisitingActions prePatternElement(anatlyzer.atlext.ATL.PatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inPatternElement(anatlyzer.atlext.ATL.PatternElement self) { if ( _debug ) System.out.println("Visiting PatternElement"); }
	public void beforePatternElement(anatlyzer.atlext.ATL.PatternElement self) { }
	public void afterPatternElement(anatlyzer.atlext.ATL.PatternElement self) { }
	public VisitingActions preSetExp(anatlyzer.atlext.OCL.SetExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inSetExp(anatlyzer.atlext.OCL.SetExp self) { if ( _debug ) System.out.println("Visiting SetExp"); }
	public void beforeSetExp(anatlyzer.atlext.OCL.SetExp self) { }
	public void afterSetExp(anatlyzer.atlext.OCL.SetExp self) { }
	public VisitingActions preEnumLiteralExp(anatlyzer.atlext.OCL.EnumLiteralExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inEnumLiteralExp(anatlyzer.atlext.OCL.EnumLiteralExp self) { if ( _debug ) System.out.println("Visiting EnumLiteralExp"); }
	public void beforeEnumLiteralExp(anatlyzer.atlext.OCL.EnumLiteralExp self) { }
	public void afterEnumLiteralExp(anatlyzer.atlext.OCL.EnumLiteralExp self) { }
	public VisitingActions preCollectionOperationCallExp(anatlyzer.atlext.OCL.CollectionOperationCallExp self) { return new VisitingActions("annotations" , "type" , "source" , "arguments" , "resolveTempResolvedBy"); } 
	public void inCollectionOperationCallExp(anatlyzer.atlext.OCL.CollectionOperationCallExp self) { if ( _debug ) System.out.println("Visiting CollectionOperationCallExp"); }
	public void beforeCollectionOperationCallExp(anatlyzer.atlext.OCL.CollectionOperationCallExp self) { }
	public void afterCollectionOperationCallExp(anatlyzer.atlext.OCL.CollectionOperationCallExp self) { }
	public VisitingActions preLibraryRef(anatlyzer.atlext.ATL.LibraryRef self) { return new VisitingActions("annotations"); } 
	public void inLibraryRef(anatlyzer.atlext.ATL.LibraryRef self) { if ( _debug ) System.out.println("Visiting LibraryRef"); }
	public void beforeLibraryRef(anatlyzer.atlext.ATL.LibraryRef self) { }
	public void afterLibraryRef(anatlyzer.atlext.ATL.LibraryRef self) { }
	public VisitingActions preSimpleOutPatternElement(anatlyzer.atlext.ATL.SimpleOutPatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression" , "bindings" , "reverseBindings"); } 
	public void inSimpleOutPatternElement(anatlyzer.atlext.ATL.SimpleOutPatternElement self) { if ( _debug ) System.out.println("Visiting SimpleOutPatternElement"); }
	public void beforeSimpleOutPatternElement(anatlyzer.atlext.ATL.SimpleOutPatternElement self) { }
	public void afterSimpleOutPatternElement(anatlyzer.atlext.ATL.SimpleOutPatternElement self) { }
	public VisitingActions preBooleanType(anatlyzer.atlext.OCL.BooleanType self) { return new VisitingActions("annotations" , "type"); } 
	public void inBooleanType(anatlyzer.atlext.OCL.BooleanType self) { if ( _debug ) System.out.println("Visiting BooleanType"); }
	public void beforeBooleanType(anatlyzer.atlext.OCL.BooleanType self) { }
	public void afterBooleanType(anatlyzer.atlext.OCL.BooleanType self) { }
	public VisitingActions preOutPatternElement(anatlyzer.atlext.ATL.OutPatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression" , "bindings"); } 
	public void inOutPatternElement(anatlyzer.atlext.ATL.OutPatternElement self) { if ( _debug ) System.out.println("Visiting OutPatternElement"); }
	public void beforeOutPatternElement(anatlyzer.atlext.ATL.OutPatternElement self) { }
	public void afterOutPatternElement(anatlyzer.atlext.ATL.OutPatternElement self) { }
	public VisitingActions preLocatedElement(anatlyzer.atlext.ATL.LocatedElement self) { return new VisitingActions("annotations"); } 
	public void inLocatedElement(anatlyzer.atlext.ATL.LocatedElement self) { if ( _debug ) System.out.println("Visiting LocatedElement"); }
	public void beforeLocatedElement(anatlyzer.atlext.ATL.LocatedElement self) { }
	public void afterLocatedElement(anatlyzer.atlext.ATL.LocatedElement self) { }
	public VisitingActions preExpressionStat(anatlyzer.atlext.ATL.ExpressionStat self) { return new VisitingActions("annotations" , "expression"); } 
	public void inExpressionStat(anatlyzer.atlext.ATL.ExpressionStat self) { if ( _debug ) System.out.println("Visiting ExpressionStat"); }
	public void beforeExpressionStat(anatlyzer.atlext.ATL.ExpressionStat self) { }
	public void afterExpressionStat(anatlyzer.atlext.ATL.ExpressionStat self) { }
	public VisitingActions prePrimitiveExp(anatlyzer.atlext.OCL.PrimitiveExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inPrimitiveExp(anatlyzer.atlext.OCL.PrimitiveExp self) { if ( _debug ) System.out.println("Visiting PrimitiveExp"); }
	public void beforePrimitiveExp(anatlyzer.atlext.OCL.PrimitiveExp self) { }
	public void afterPrimitiveExp(anatlyzer.atlext.OCL.PrimitiveExp self) { }
	public VisitingActions preStatement(anatlyzer.atlext.ATL.Statement self) { return new VisitingActions("annotations"); } 
	public void inStatement(anatlyzer.atlext.ATL.Statement self) { if ( _debug ) System.out.println("Visiting Statement"); }
	public void beforeStatement(anatlyzer.atlext.ATL.Statement self) { }
	public void afterStatement(anatlyzer.atlext.ATL.Statement self) { }
	public VisitingActions preStringToStringMap(java.util.Map.Entry self) { return new VisitingActions(); } 
	public void inStringToStringMap(java.util.Map.Entry self) { if ( _debug ) System.out.println("Visiting StringToStringMap"); }
	public void beforeStringToStringMap(java.util.Map.Entry self) { }
	public void afterStringToStringMap(java.util.Map.Entry self) { }
	public VisitingActions preOclFeatureDefinition(anatlyzer.atlext.OCL.OclFeatureDefinition self) { return new VisitingActions("annotations" , "feature" , "context_"); } 
	public void inOclFeatureDefinition(anatlyzer.atlext.OCL.OclFeatureDefinition self) { if ( _debug ) System.out.println("Visiting OclFeatureDefinition"); }
	public void beforeOclFeatureDefinition(anatlyzer.atlext.OCL.OclFeatureDefinition self) { }
	public void afterOclFeatureDefinition(anatlyzer.atlext.OCL.OclFeatureDefinition self) { }
	public VisitingActions preDropPattern(anatlyzer.atlext.ATL.DropPattern self) { return new VisitingActions("annotations"); } 
	public void inDropPattern(anatlyzer.atlext.ATL.DropPattern self) { if ( _debug ) System.out.println("Visiting DropPattern"); }
	public void beforeDropPattern(anatlyzer.atlext.ATL.DropPattern self) { }
	public void afterDropPattern(anatlyzer.atlext.ATL.DropPattern self) { }
	public VisitingActions preRuleVariableDeclaration(anatlyzer.atlext.ATL.RuleVariableDeclaration self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inRuleVariableDeclaration(anatlyzer.atlext.ATL.RuleVariableDeclaration self) { if ( _debug ) System.out.println("Visiting RuleVariableDeclaration"); }
	public void beforeRuleVariableDeclaration(anatlyzer.atlext.ATL.RuleVariableDeclaration self) { }
	public void afterRuleVariableDeclaration(anatlyzer.atlext.ATL.RuleVariableDeclaration self) { }
	public VisitingActions preRealExp(anatlyzer.atlext.OCL.RealExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inRealExp(anatlyzer.atlext.OCL.RealExp self) { if ( _debug ) System.out.println("Visiting RealExp"); }
	public void beforeRealExp(anatlyzer.atlext.OCL.RealExp self) { }
	public void afterRealExp(anatlyzer.atlext.OCL.RealExp self) { }
	public VisitingActions preStaticRule(anatlyzer.atlext.ATL.StaticRule self) { return new VisitingActions("callableParameters" , "annotations" , "outPattern" , "actionBlock" , "variables"); } 
	public void inStaticRule(anatlyzer.atlext.ATL.StaticRule self) { if ( _debug ) System.out.println("Visiting StaticRule"); }
	public void beforeStaticRule(anatlyzer.atlext.ATL.StaticRule self) { }
	public void afterStaticRule(anatlyzer.atlext.ATL.StaticRule self) { }
	public VisitingActions preOclUndefinedExp(anatlyzer.atlext.OCL.OclUndefinedExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inOclUndefinedExp(anatlyzer.atlext.OCL.OclUndefinedExp self) { if ( _debug ) System.out.println("Visiting OclUndefinedExp"); }
	public void beforeOclUndefinedExp(anatlyzer.atlext.OCL.OclUndefinedExp self) { }
	public void afterOclUndefinedExp(anatlyzer.atlext.OCL.OclUndefinedExp self) { }
	public VisitingActions prePrimitive(anatlyzer.atlext.OCL.Primitive self) { return new VisitingActions("annotations" , "type"); } 
	public void inPrimitive(anatlyzer.atlext.OCL.Primitive self) { if ( _debug ) System.out.println("Visiting Primitive"); }
	public void beforePrimitive(anatlyzer.atlext.OCL.Primitive self) { }
	public void afterPrimitive(anatlyzer.atlext.OCL.Primitive self) { }
	public VisitingActions preOperation(anatlyzer.atlext.OCL.Operation self) { return new VisitingActions("annotations" , "parameters" , "returnType" , "body"); } 
	public void inOperation(anatlyzer.atlext.OCL.Operation self) { if ( _debug ) System.out.println("Visiting Operation"); }
	public void beforeOperation(anatlyzer.atlext.OCL.Operation self) { }
	public void afterOperation(anatlyzer.atlext.OCL.Operation self) { }
	public VisitingActions preMapExp(anatlyzer.atlext.OCL.MapExp self) { return new VisitingActions("annotations" , "type" , "elements"); } 
	public void inMapExp(anatlyzer.atlext.OCL.MapExp self) { if ( _debug ) System.out.println("Visiting MapExp"); }
	public void beforeMapExp(anatlyzer.atlext.OCL.MapExp self) { }
	public void afterMapExp(anatlyzer.atlext.OCL.MapExp self) { }
	public VisitingActions preCalledRule(anatlyzer.atlext.ATL.CalledRule self) { return new VisitingActions("callableParameters" , "annotations" , "outPattern" , "actionBlock" , "variables" , "parameters"); } 
	public void inCalledRule(anatlyzer.atlext.ATL.CalledRule self) { if ( _debug ) System.out.println("Visiting CalledRule"); }
	public void beforeCalledRule(anatlyzer.atlext.ATL.CalledRule self) { }
	public void afterCalledRule(anatlyzer.atlext.ATL.CalledRule self) { }
	public VisitingActions preUnit(anatlyzer.atlext.ATL.Unit self) { return new VisitingActions("annotations" , "libraries"); } 
	public void inUnit(anatlyzer.atlext.ATL.Unit self) { if ( _debug ) System.out.println("Visiting Unit"); }
	public void beforeUnit(anatlyzer.atlext.ATL.Unit self) { }
	public void afterUnit(anatlyzer.atlext.ATL.Unit self) { }
	public VisitingActions preNumericType(anatlyzer.atlext.OCL.NumericType self) { return new VisitingActions("annotations" , "type"); } 
	public void inNumericType(anatlyzer.atlext.OCL.NumericType self) { if ( _debug ) System.out.println("Visiting NumericType"); }
	public void beforeNumericType(anatlyzer.atlext.OCL.NumericType self) { }
	public void afterNumericType(anatlyzer.atlext.OCL.NumericType self) { }
	public VisitingActions preSimpleInPatternElement(anatlyzer.atlext.ATL.SimpleInPatternElement self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inSimpleInPatternElement(anatlyzer.atlext.ATL.SimpleInPatternElement self) { if ( _debug ) System.out.println("Visiting SimpleInPatternElement"); }
	public void beforeSimpleInPatternElement(anatlyzer.atlext.ATL.SimpleInPatternElement self) { }
	public void afterSimpleInPatternElement(anatlyzer.atlext.ATL.SimpleInPatternElement self) { }
	public VisitingActions preRule(anatlyzer.atlext.ATL.Rule self) { return new VisitingActions("annotations" , "outPattern" , "actionBlock" , "variables"); } 
	public void inRule(anatlyzer.atlext.ATL.Rule self) { if ( _debug ) System.out.println("Visiting Rule"); }
	public void beforeRule(anatlyzer.atlext.ATL.Rule self) { }
	public void afterRule(anatlyzer.atlext.ATL.Rule self) { }
	public VisitingActions preBooleanExp(anatlyzer.atlext.OCL.BooleanExp self) { return new VisitingActions("annotations" , "type"); } 
	public void inBooleanExp(anatlyzer.atlext.OCL.BooleanExp self) { if ( _debug ) System.out.println("Visiting BooleanExp"); }
	public void beforeBooleanExp(anatlyzer.atlext.OCL.BooleanExp self) { }
	public void afterBooleanExp(anatlyzer.atlext.OCL.BooleanExp self) { }
	public VisitingActions preSelectByKind(anatlyzer.atlext.OCL2.SelectByKind self) { return new VisitingActions("annotations" , "type" , "source" , "arguments" , "resolveTempResolvedBy"); } 
	public void inSelectByKind(anatlyzer.atlext.OCL2.SelectByKind self) { if ( _debug ) System.out.println("Visiting SelectByKind"); }
	public void beforeSelectByKind(anatlyzer.atlext.OCL2.SelectByKind self) { }
	public void afterSelectByKind(anatlyzer.atlext.OCL2.SelectByKind self) { }
	public VisitingActions preParameter(anatlyzer.atlext.OCL.Parameter self) { return new VisitingActions("annotations" , "type" , "initExpression"); } 
	public void inParameter(anatlyzer.atlext.OCL.Parameter self) { if ( _debug ) System.out.println("Visiting Parameter"); }
	public void beforeParameter(anatlyzer.atlext.OCL.Parameter self) { }
	public void afterParameter(anatlyzer.atlext.OCL.Parameter self) { }
	public VisitingActions preSequenceType(anatlyzer.atlext.OCL.SequenceType self) { return new VisitingActions("annotations" , "type" , "elementType"); } 
	public void inSequenceType(anatlyzer.atlext.OCL.SequenceType self) { if ( _debug ) System.out.println("Visiting SequenceType"); }
	public void beforeSequenceType(anatlyzer.atlext.OCL.SequenceType self) { }
	public void afterSequenceType(anatlyzer.atlext.OCL.SequenceType self) { }
	public VisitingActions preActionBlock(anatlyzer.atlext.ATL.ActionBlock self) { return new VisitingActions("annotations" , "statements"); } 
	public void inActionBlock(anatlyzer.atlext.ATL.ActionBlock self) { if ( _debug ) System.out.println("Visiting ActionBlock"); }
	public void beforeActionBlock(anatlyzer.atlext.ATL.ActionBlock self) { }
	public void afterActionBlock(anatlyzer.atlext.ATL.ActionBlock self) { }
	public VisitingActions preOclType(anatlyzer.atlext.OCL.OclType self) { return new VisitingActions("annotations" , "type"); } 
	public void inOclType(anatlyzer.atlext.OCL.OclType self) { if ( _debug ) System.out.println("Visiting OclType"); }
	public void beforeOclType(anatlyzer.atlext.OCL.OclType self) { }
	public void afterOclType(anatlyzer.atlext.OCL.OclType self) { }
		
	protected VisitingActions actions(Object... things) {
		return new VisitingActions(things);
	}

	public void startVisiting(EObject init) {
		visit(init);
	}	
	
	public EObject getCurrent() { return current; }
	public void setCurrent(EObject o) { current = o; }
	
	protected void visitOclContextDefinition(anatlyzer.atlext.OCL.OclContextDefinition this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclContextDefinition(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclContextDefinition(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclContextDefinition(this_);
		visitor.afterOclContextDefinition(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitStaticHelper(anatlyzer.atlext.ATL.StaticHelper this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preStaticHelper(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeStaticHelper(this_);
		
		visitActions(v, this_);		
					
		visitor.inStaticHelper(this_);
		visitor.afterStaticHelper(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitMapType(anatlyzer.atlext.OCL.MapType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preMapType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeMapType(this_);
		
		visitActions(v, this_);		
					
		visitor.inMapType(this_);
		visitor.afterMapType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitLazyRule(anatlyzer.atlext.ATL.LazyRule this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preLazyRule(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeLazyRule(this_);
		
		visitActions(v, this_);		
					
		visitor.inLazyRule(this_);
		visitor.afterLazyRule(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitJavaBody(anatlyzer.atlext.OCL.JavaBody this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preJavaBody(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeJavaBody(this_);
		
		visitActions(v, this_);		
					
		visitor.inJavaBody(this_);
		visitor.afterJavaBody(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBinding(anatlyzer.atlext.ATL.Binding this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBinding(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBinding(this_);
		
		visitActions(v, this_);		
					
		visitor.inBinding(this_);
		visitor.afterBinding(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOrderedSetType(anatlyzer.atlext.OCL.OrderedSetType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOrderedSetType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOrderedSetType(this_);
		
		visitActions(v, this_);		
					
		visitor.inOrderedSetType(this_);
		visitor.afterOrderedSetType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSuperExp(anatlyzer.atlext.OCL.SuperExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSuperExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSuperExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inSuperExp(this_);
		visitor.afterSuperExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIntegerExp(anatlyzer.atlext.OCL.IntegerExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIntegerExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIntegerExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inIntegerExp(this_);
		visitor.afterIntegerExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitMapElement(anatlyzer.atlext.OCL.MapElement this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preMapElement(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeMapElement(this_);
		
		visitActions(v, this_);		
					
		visitor.inMapElement(this_);
		visitor.afterMapElement(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitGetAppliedStereotypesBody(anatlyzer.atlext.OCL.GetAppliedStereotypesBody this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preGetAppliedStereotypesBody(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeGetAppliedStereotypesBody(this_);
		
		visitActions(v, this_);		
					
		visitor.inGetAppliedStereotypesBody(this_);
		visitor.afterGetAppliedStereotypesBody(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBindingStat(anatlyzer.atlext.ATL.BindingStat this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBindingStat(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBindingStat(this_);
		
		visitActions(v, this_);		
					
		visitor.inBindingStat(this_);
		visitor.afterBindingStat(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitModule(anatlyzer.atlext.ATL.Module this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preModule(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeModule(this_);
		
		visitActions(v, this_);		
					
		visitor.inModule(this_);
		visitor.afterModule(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitTupleExp(anatlyzer.atlext.OCL.TupleExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preTupleExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeTupleExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inTupleExp(this_);
		visitor.afterTupleExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitVariableExp(anatlyzer.atlext.OCL.VariableExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preVariableExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeVariableExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inVariableExp(this_);
		visitor.afterVariableExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclModel(anatlyzer.atlext.OCL.OclModel this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclModel(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclModel(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclModel(this_);
		visitor.afterOclModel(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitLetExp(anatlyzer.atlext.OCL.LetExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preLetExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeLetExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inLetExp(this_);
		visitor.afterLetExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIfStat(anatlyzer.atlext.ATL.IfStat this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIfStat(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIfStat(this_);
		
		visitActions(v, this_);		
					
		visitor.inIfStat(this_);
		visitor.afterIfStat(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitContextHelper(anatlyzer.atlext.ATL.ContextHelper this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preContextHelper(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeContextHelper(this_);
		
		visitActions(v, this_);		
					
		visitor.inContextHelper(this_);
		visitor.afterContextHelper(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSetType(anatlyzer.atlext.OCL.SetType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSetType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSetType(this_);
		
		visitActions(v, this_);		
					
		visitor.inSetType(this_);
		visitor.afterSetType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIfExp(anatlyzer.atlext.OCL.IfExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIfExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIfExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inIfExp(this_);
		visitor.afterIfExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOrderedSetExp(anatlyzer.atlext.OCL.OrderedSetExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOrderedSetExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOrderedSetExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inOrderedSetExp(this_);
		visitor.afterOrderedSetExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitStringExp(anatlyzer.atlext.OCL.StringExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preStringExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeStringExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inStringExp(this_);
		visitor.afterStringExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOperatorCallExp(anatlyzer.atlext.OCL.OperatorCallExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOperatorCallExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOperatorCallExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inOperatorCallExp(this_);
		visitor.afterOperatorCallExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOperationCallExp(anatlyzer.atlext.OCL.OperationCallExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOperationCallExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOperationCallExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inOperationCallExp(this_);
		visitor.afterOperationCallExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitInPattern(anatlyzer.atlext.ATL.InPattern this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preInPattern(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeInPattern(this_);
		
		visitActions(v, this_);		
					
		visitor.inInPattern(this_);
		visitor.afterInPattern(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSequenceExp(anatlyzer.atlext.OCL.SequenceExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSequenceExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSequenceExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inSequenceExp(this_);
		visitor.afterSequenceExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitRealType(anatlyzer.atlext.OCL.RealType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preRealType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeRealType(this_);
		
		visitActions(v, this_);		
					
		visitor.inRealType(this_);
		visitor.afterRealType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitQuery(anatlyzer.atlext.ATL.Query this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preQuery(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeQuery(this_);
		
		visitActions(v, this_);		
					
		visitor.inQuery(this_);
		visitor.afterQuery(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIterator(anatlyzer.atlext.OCL.Iterator this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIterator(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIterator(this_);
		
		visitActions(v, this_);		
					
		visitor.inIterator(this_);
		visitor.afterIterator(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitMatchedRule(anatlyzer.atlext.ATL.MatchedRule this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preMatchedRule(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeMatchedRule(this_);
		
		visitActions(v, this_);		
					
		visitor.inMatchedRule(this_);
		visitor.afterMatchedRule(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIntegerType(anatlyzer.atlext.OCL.IntegerType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIntegerType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIntegerType(this_);
		
		visitActions(v, this_);		
					
		visitor.inIntegerType(this_);
		visitor.afterIntegerType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitTupleType(anatlyzer.atlext.OCL.TupleType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preTupleType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeTupleType(this_);
		
		visitActions(v, this_);		
					
		visitor.inTupleType(this_);
		visitor.afterTupleType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitForStat(anatlyzer.atlext.ATL.ForStat this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preForStat(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeForStat(this_);
		
		visitActions(v, this_);		
					
		visitor.inForStat(this_);
		visitor.afterForStat(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitTupleTypeAttribute(anatlyzer.atlext.OCL.TupleTypeAttribute this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preTupleTypeAttribute(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeTupleTypeAttribute(this_);
		
		visitActions(v, this_);		
					
		visitor.inTupleTypeAttribute(this_);
		visitor.afterTupleTypeAttribute(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitTuplePart(anatlyzer.atlext.OCL.TuplePart this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preTuplePart(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeTuplePart(this_);
		
		visitActions(v, this_);		
					
		visitor.inTuplePart(this_);
		visitor.afterTuplePart(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitCallableParameter(anatlyzer.atlext.ATL.CallableParameter this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preCallableParameter(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeCallableParameter(this_);
		
		visitActions(v, this_);		
					
		visitor.inCallableParameter(this_);
		visitor.afterCallableParameter(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBagExp(anatlyzer.atlext.OCL.BagExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBagExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBagExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inBagExp(this_);
		visitor.afterBagExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitNavigationOrAttributeCallExp(anatlyzer.atlext.OCL.NavigationOrAttributeCallExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preNavigationOrAttributeCallExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeNavigationOrAttributeCallExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inNavigationOrAttributeCallExp(this_);
		visitor.afterNavigationOrAttributeCallExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOutPattern(anatlyzer.atlext.ATL.OutPattern this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOutPattern(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOutPattern(this_);
		
		visitActions(v, this_);		
					
		visitor.inOutPattern(this_);
		visitor.afterOutPattern(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitAttribute(anatlyzer.atlext.OCL.Attribute this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preAttribute(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeAttribute(this_);
		
		visitActions(v, this_);		
					
		visitor.inAttribute(this_);
		visitor.afterAttribute(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIteratorExp(anatlyzer.atlext.OCL.IteratorExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIteratorExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIteratorExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inIteratorExp(this_);
		visitor.afterIteratorExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitForEachOutPatternElement(anatlyzer.atlext.ATL.ForEachOutPatternElement this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preForEachOutPatternElement(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeForEachOutPatternElement(this_);
		
		visitActions(v, this_);		
					
		visitor.inForEachOutPatternElement(this_);
		visitor.afterForEachOutPatternElement(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitVariableDeclaration(anatlyzer.atlext.OCL.VariableDeclaration this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preVariableDeclaration(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeVariableDeclaration(this_);
		
		visitActions(v, this_);		
					
		visitor.inVariableDeclaration(this_);
		visitor.afterVariableDeclaration(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitResolveTempResolution(anatlyzer.atlext.OCL.ResolveTempResolution this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preResolveTempResolution(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeResolveTempResolution(this_);
		
		visitActions(v, this_);		
					
		visitor.inResolveTempResolution(this_);
		visitor.afterResolveTempResolution(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclModelElement(anatlyzer.atlext.OCL.OclModelElement this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclModelElement(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclModelElement(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclModelElement(this_);
		visitor.afterOclModelElement(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBagType(anatlyzer.atlext.OCL.BagType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBagType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBagType(this_);
		
		visitActions(v, this_);		
					
		visitor.inBagType(this_);
		visitor.afterBagType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitRuleResolutionInfo(anatlyzer.atlext.ATL.RuleResolutionInfo this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preRuleResolutionInfo(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeRuleResolutionInfo(this_);
		
		visitActions(v, this_);		
					
		visitor.inRuleResolutionInfo(this_);
		visitor.afterRuleResolutionInfo(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitStringType(anatlyzer.atlext.OCL.StringType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preStringType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeStringType(this_);
		
		visitActions(v, this_);		
					
		visitor.inStringType(this_);
		visitor.afterStringType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitLibrary(anatlyzer.atlext.ATL.Library this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preLibrary(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeLibrary(this_);
		
		visitActions(v, this_);		
					
		visitor.inLibrary(this_);
		visitor.afterLibrary(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitCollectionType(anatlyzer.atlext.OCL.CollectionType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preCollectionType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeCollectionType(this_);
		
		visitActions(v, this_);		
					
		visitor.inCollectionType(this_);
		visitor.afterCollectionType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclAnyType(anatlyzer.atlext.OCL.OclAnyType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclAnyType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclAnyType(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclAnyType(this_);
		visitor.afterOclAnyType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitIterateExp(anatlyzer.atlext.OCL.IterateExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preIterateExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeIterateExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inIterateExp(this_);
		visitor.afterIterateExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSetExp(anatlyzer.atlext.OCL.SetExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSetExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSetExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inSetExp(this_);
		visitor.afterSetExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitEnumLiteralExp(anatlyzer.atlext.OCL.EnumLiteralExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preEnumLiteralExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeEnumLiteralExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inEnumLiteralExp(this_);
		visitor.afterEnumLiteralExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitCollectionOperationCallExp(anatlyzer.atlext.OCL.CollectionOperationCallExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preCollectionOperationCallExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeCollectionOperationCallExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inCollectionOperationCallExp(this_);
		visitor.afterCollectionOperationCallExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitLibraryRef(anatlyzer.atlext.ATL.LibraryRef this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preLibraryRef(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeLibraryRef(this_);
		
		visitActions(v, this_);		
					
		visitor.inLibraryRef(this_);
		visitor.afterLibraryRef(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSimpleOutPatternElement(anatlyzer.atlext.ATL.SimpleOutPatternElement this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSimpleOutPatternElement(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSimpleOutPatternElement(this_);
		
		visitActions(v, this_);		
					
		visitor.inSimpleOutPatternElement(this_);
		visitor.afterSimpleOutPatternElement(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBooleanType(anatlyzer.atlext.OCL.BooleanType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBooleanType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBooleanType(this_);
		
		visitActions(v, this_);		
					
		visitor.inBooleanType(this_);
		visitor.afterBooleanType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitExpressionStat(anatlyzer.atlext.ATL.ExpressionStat this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preExpressionStat(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeExpressionStat(this_);
		
		visitActions(v, this_);		
					
		visitor.inExpressionStat(this_);
		visitor.afterExpressionStat(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclFeatureDefinition(anatlyzer.atlext.OCL.OclFeatureDefinition this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclFeatureDefinition(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclFeatureDefinition(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclFeatureDefinition(this_);
		visitor.afterOclFeatureDefinition(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitDropPattern(anatlyzer.atlext.ATL.DropPattern this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preDropPattern(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeDropPattern(this_);
		
		visitActions(v, this_);		
					
		visitor.inDropPattern(this_);
		visitor.afterDropPattern(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitRuleVariableDeclaration(anatlyzer.atlext.ATL.RuleVariableDeclaration this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preRuleVariableDeclaration(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeRuleVariableDeclaration(this_);
		
		visitActions(v, this_);		
					
		visitor.inRuleVariableDeclaration(this_);
		visitor.afterRuleVariableDeclaration(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitRealExp(anatlyzer.atlext.OCL.RealExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preRealExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeRealExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inRealExp(this_);
		visitor.afterRealExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclUndefinedExp(anatlyzer.atlext.OCL.OclUndefinedExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclUndefinedExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclUndefinedExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclUndefinedExp(this_);
		visitor.afterOclUndefinedExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOperation(anatlyzer.atlext.OCL.Operation this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOperation(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOperation(this_);
		
		visitActions(v, this_);		
					
		visitor.inOperation(this_);
		visitor.afterOperation(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitMapExp(anatlyzer.atlext.OCL.MapExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preMapExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeMapExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inMapExp(this_);
		visitor.afterMapExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitCalledRule(anatlyzer.atlext.ATL.CalledRule this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preCalledRule(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeCalledRule(this_);
		
		visitActions(v, this_);		
					
		visitor.inCalledRule(this_);
		visitor.afterCalledRule(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitUnit(anatlyzer.atlext.ATL.Unit this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preUnit(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeUnit(this_);
		
		visitActions(v, this_);		
					
		visitor.inUnit(this_);
		visitor.afterUnit(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSimpleInPatternElement(anatlyzer.atlext.ATL.SimpleInPatternElement this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSimpleInPatternElement(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSimpleInPatternElement(this_);
		
		visitActions(v, this_);		
					
		visitor.inSimpleInPatternElement(this_);
		visitor.afterSimpleInPatternElement(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitBooleanExp(anatlyzer.atlext.OCL.BooleanExp this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preBooleanExp(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeBooleanExp(this_);
		
		visitActions(v, this_);		
					
		visitor.inBooleanExp(this_);
		visitor.afterBooleanExp(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSelectByKind(anatlyzer.atlext.OCL2.SelectByKind this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSelectByKind(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSelectByKind(this_);
		
		visitActions(v, this_);		
					
		visitor.inSelectByKind(this_);
		visitor.afterSelectByKind(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitParameter(anatlyzer.atlext.OCL.Parameter this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preParameter(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeParameter(this_);
		
		visitActions(v, this_);		
					
		visitor.inParameter(this_);
		visitor.afterParameter(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitSequenceType(anatlyzer.atlext.OCL.SequenceType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preSequenceType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeSequenceType(this_);
		
		visitActions(v, this_);		
					
		visitor.inSequenceType(this_);
		visitor.afterSequenceType(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitActionBlock(anatlyzer.atlext.ATL.ActionBlock this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preActionBlock(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeActionBlock(this_);
		
		visitActions(v, this_);		
					
		visitor.inActionBlock(this_);
		visitor.afterActionBlock(this_);
		visitor.setCurrent(pop);	
	} 
	protected void visitOclType(anatlyzer.atlext.OCL.OclType this_) {
		AbstractVisitor visitor = this;
		VisitingActions v = visitor.preOclType(this_);
		if ( v == null ) {
			return;
		}
	
		EObject pop = visitor.getCurrent();
		visitor.setCurrent(this_);
		visitor.beforeOclType(this_);
		
		visitActions(v, this_);		
					
		visitor.inOclType(this_);
		visitor.afterOclType(this_);
		visitor.setCurrent(pop);	
	} 
		
	private void visitActions(VisitingActions v, EObject this_) {
		AbstractVisitor visitor = this;
		
		for(VisitingAction va : v.getActions(this_)) {
			if ( va.isMethodCall() ) {
				va.performMethodCall();		
			} else if ( va.isFilter() ) {
				Object res = va.performMethodCall();
				if ( res instanceof java.util.Collection ) {
					for(Object o : (java.util.Collection<?>) res) {
						visit((EObject) o);
					}
				} else {
					visit((EObject) res);
				}
			} else if ( va.isReference() ) {
				EReference r = va.getEReference();
				
				Object refObj   = this_.eGet(r);
				if ( refObj == null ) continue;
				if ( refObj instanceof java.util.Collection ) {
					for(Object o : (java.util.Collection<?>) refObj) {
						visit((EObject) o);
					}
				} else {
					visit((EObject) refObj);
				}
			}
		}
	
	}
	
	protected void visit(EObject obj) {	
	if ( obj.eClass().getName().equals("OclContextDefinition") ) { visitOclContextDefinition((anatlyzer.atlext.OCL.OclContextDefinition) obj); return; }
	if ( obj.eClass().getName().equals("StaticHelper") ) { visitStaticHelper((anatlyzer.atlext.ATL.StaticHelper) obj); return; }
	if ( obj.eClass().getName().equals("MapType") ) { visitMapType((anatlyzer.atlext.OCL.MapType) obj); return; }
	if ( obj.eClass().getName().equals("LazyRule") ) { visitLazyRule((anatlyzer.atlext.ATL.LazyRule) obj); return; }
	if ( obj.eClass().getName().equals("JavaBody") ) { visitJavaBody((anatlyzer.atlext.OCL.JavaBody) obj); return; }
	if ( obj.eClass().getName().equals("Binding") ) { visitBinding((anatlyzer.atlext.ATL.Binding) obj); return; }
	if ( obj.eClass().getName().equals("OrderedSetType") ) { visitOrderedSetType((anatlyzer.atlext.OCL.OrderedSetType) obj); return; }
	if ( obj.eClass().getName().equals("SuperExp") ) { visitSuperExp((anatlyzer.atlext.OCL.SuperExp) obj); return; }
	if ( obj.eClass().getName().equals("IntegerExp") ) { visitIntegerExp((anatlyzer.atlext.OCL.IntegerExp) obj); return; }
	if ( obj.eClass().getName().equals("MapElement") ) { visitMapElement((anatlyzer.atlext.OCL.MapElement) obj); return; }
	if ( obj.eClass().getName().equals("GetAppliedStereotypesBody") ) { visitGetAppliedStereotypesBody((anatlyzer.atlext.OCL.GetAppliedStereotypesBody) obj); return; }
	if ( obj.eClass().getName().equals("BindingStat") ) { visitBindingStat((anatlyzer.atlext.ATL.BindingStat) obj); return; }
	if ( obj.eClass().getName().equals("Module") ) { visitModule((anatlyzer.atlext.ATL.Module) obj); return; }
	if ( obj.eClass().getName().equals("TupleExp") ) { visitTupleExp((anatlyzer.atlext.OCL.TupleExp) obj); return; }
	if ( obj.eClass().getName().equals("VariableExp") ) { visitVariableExp((anatlyzer.atlext.OCL.VariableExp) obj); return; }
	if ( obj.eClass().getName().equals("OclModel") ) { visitOclModel((anatlyzer.atlext.OCL.OclModel) obj); return; }
	if ( obj.eClass().getName().equals("LetExp") ) { visitLetExp((anatlyzer.atlext.OCL.LetExp) obj); return; }
	if ( obj.eClass().getName().equals("IfStat") ) { visitIfStat((anatlyzer.atlext.ATL.IfStat) obj); return; }
	if ( obj.eClass().getName().equals("ContextHelper") ) { visitContextHelper((anatlyzer.atlext.ATL.ContextHelper) obj); return; }
	if ( obj.eClass().getName().equals("SetType") ) { visitSetType((anatlyzer.atlext.OCL.SetType) obj); return; }
	if ( obj.eClass().getName().equals("IfExp") ) { visitIfExp((anatlyzer.atlext.OCL.IfExp) obj); return; }
	if ( obj.eClass().getName().equals("OrderedSetExp") ) { visitOrderedSetExp((anatlyzer.atlext.OCL.OrderedSetExp) obj); return; }
	if ( obj.eClass().getName().equals("StringExp") ) { visitStringExp((anatlyzer.atlext.OCL.StringExp) obj); return; }
	if ( obj.eClass().getName().equals("OperatorCallExp") ) { visitOperatorCallExp((anatlyzer.atlext.OCL.OperatorCallExp) obj); return; }
	if ( obj.eClass().getName().equals("OperationCallExp") ) { visitOperationCallExp((anatlyzer.atlext.OCL.OperationCallExp) obj); return; }
	if ( obj.eClass().getName().equals("InPattern") ) { visitInPattern((anatlyzer.atlext.ATL.InPattern) obj); return; }
	if ( obj.eClass().getName().equals("SequenceExp") ) { visitSequenceExp((anatlyzer.atlext.OCL.SequenceExp) obj); return; }
	if ( obj.eClass().getName().equals("RealType") ) { visitRealType((anatlyzer.atlext.OCL.RealType) obj); return; }
	if ( obj.eClass().getName().equals("Query") ) { visitQuery((anatlyzer.atlext.ATL.Query) obj); return; }
	if ( obj.eClass().getName().equals("Iterator") ) { visitIterator((anatlyzer.atlext.OCL.Iterator) obj); return; }
	if ( obj.eClass().getName().equals("MatchedRule") ) { visitMatchedRule((anatlyzer.atlext.ATL.MatchedRule) obj); return; }
	if ( obj.eClass().getName().equals("IntegerType") ) { visitIntegerType((anatlyzer.atlext.OCL.IntegerType) obj); return; }
	if ( obj.eClass().getName().equals("TupleType") ) { visitTupleType((anatlyzer.atlext.OCL.TupleType) obj); return; }
	if ( obj.eClass().getName().equals("ForStat") ) { visitForStat((anatlyzer.atlext.ATL.ForStat) obj); return; }
	if ( obj.eClass().getName().equals("TupleTypeAttribute") ) { visitTupleTypeAttribute((anatlyzer.atlext.OCL.TupleTypeAttribute) obj); return; }
	if ( obj.eClass().getName().equals("TuplePart") ) { visitTuplePart((anatlyzer.atlext.OCL.TuplePart) obj); return; }
	if ( obj.eClass().getName().equals("CallableParameter") ) { visitCallableParameter((anatlyzer.atlext.ATL.CallableParameter) obj); return; }
	if ( obj.eClass().getName().equals("BagExp") ) { visitBagExp((anatlyzer.atlext.OCL.BagExp) obj); return; }
	if ( obj.eClass().getName().equals("NavigationOrAttributeCallExp") ) { visitNavigationOrAttributeCallExp((anatlyzer.atlext.OCL.NavigationOrAttributeCallExp) obj); return; }
	if ( obj.eClass().getName().equals("OutPattern") ) { visitOutPattern((anatlyzer.atlext.ATL.OutPattern) obj); return; }
	if ( obj.eClass().getName().equals("Attribute") ) { visitAttribute((anatlyzer.atlext.OCL.Attribute) obj); return; }
	if ( obj.eClass().getName().equals("IteratorExp") ) { visitIteratorExp((anatlyzer.atlext.OCL.IteratorExp) obj); return; }
	if ( obj.eClass().getName().equals("ForEachOutPatternElement") ) { visitForEachOutPatternElement((anatlyzer.atlext.ATL.ForEachOutPatternElement) obj); return; }
	if ( obj.eClass().getName().equals("VariableDeclaration") ) { visitVariableDeclaration((anatlyzer.atlext.OCL.VariableDeclaration) obj); return; }
	if ( obj.eClass().getName().equals("ResolveTempResolution") ) { visitResolveTempResolution((anatlyzer.atlext.OCL.ResolveTempResolution) obj); return; }
	if ( obj.eClass().getName().equals("OclModelElement") ) { visitOclModelElement((anatlyzer.atlext.OCL.OclModelElement) obj); return; }
	if ( obj.eClass().getName().equals("BagType") ) { visitBagType((anatlyzer.atlext.OCL.BagType) obj); return; }
	if ( obj.eClass().getName().equals("RuleResolutionInfo") ) { visitRuleResolutionInfo((anatlyzer.atlext.ATL.RuleResolutionInfo) obj); return; }
	if ( obj.eClass().getName().equals("StringType") ) { visitStringType((anatlyzer.atlext.OCL.StringType) obj); return; }
	if ( obj.eClass().getName().equals("Library") ) { visitLibrary((anatlyzer.atlext.ATL.Library) obj); return; }
	if ( obj.eClass().getName().equals("CollectionType") ) { visitCollectionType((anatlyzer.atlext.OCL.CollectionType) obj); return; }
	if ( obj.eClass().getName().equals("OclAnyType") ) { visitOclAnyType((anatlyzer.atlext.OCL.OclAnyType) obj); return; }
	if ( obj.eClass().getName().equals("IterateExp") ) { visitIterateExp((anatlyzer.atlext.OCL.IterateExp) obj); return; }
	if ( obj.eClass().getName().equals("SetExp") ) { visitSetExp((anatlyzer.atlext.OCL.SetExp) obj); return; }
	if ( obj.eClass().getName().equals("EnumLiteralExp") ) { visitEnumLiteralExp((anatlyzer.atlext.OCL.EnumLiteralExp) obj); return; }
	if ( obj.eClass().getName().equals("CollectionOperationCallExp") ) { visitCollectionOperationCallExp((anatlyzer.atlext.OCL.CollectionOperationCallExp) obj); return; }
	if ( obj.eClass().getName().equals("LibraryRef") ) { visitLibraryRef((anatlyzer.atlext.ATL.LibraryRef) obj); return; }
	if ( obj.eClass().getName().equals("SimpleOutPatternElement") ) { visitSimpleOutPatternElement((anatlyzer.atlext.ATL.SimpleOutPatternElement) obj); return; }
	if ( obj.eClass().getName().equals("BooleanType") ) { visitBooleanType((anatlyzer.atlext.OCL.BooleanType) obj); return; }
	if ( obj.eClass().getName().equals("ExpressionStat") ) { visitExpressionStat((anatlyzer.atlext.ATL.ExpressionStat) obj); return; }
	if ( obj.eClass().getName().equals("OclFeatureDefinition") ) { visitOclFeatureDefinition((anatlyzer.atlext.OCL.OclFeatureDefinition) obj); return; }
	if ( obj.eClass().getName().equals("DropPattern") ) { visitDropPattern((anatlyzer.atlext.ATL.DropPattern) obj); return; }
	if ( obj.eClass().getName().equals("RuleVariableDeclaration") ) { visitRuleVariableDeclaration((anatlyzer.atlext.ATL.RuleVariableDeclaration) obj); return; }
	if ( obj.eClass().getName().equals("RealExp") ) { visitRealExp((anatlyzer.atlext.OCL.RealExp) obj); return; }
	if ( obj.eClass().getName().equals("OclUndefinedExp") ) { visitOclUndefinedExp((anatlyzer.atlext.OCL.OclUndefinedExp) obj); return; }
	if ( obj.eClass().getName().equals("Operation") ) { visitOperation((anatlyzer.atlext.OCL.Operation) obj); return; }
	if ( obj.eClass().getName().equals("MapExp") ) { visitMapExp((anatlyzer.atlext.OCL.MapExp) obj); return; }
	if ( obj.eClass().getName().equals("CalledRule") ) { visitCalledRule((anatlyzer.atlext.ATL.CalledRule) obj); return; }
	if ( obj.eClass().getName().equals("Unit") ) { visitUnit((anatlyzer.atlext.ATL.Unit) obj); return; }
	if ( obj.eClass().getName().equals("SimpleInPatternElement") ) { visitSimpleInPatternElement((anatlyzer.atlext.ATL.SimpleInPatternElement) obj); return; }
	if ( obj.eClass().getName().equals("BooleanExp") ) { visitBooleanExp((anatlyzer.atlext.OCL.BooleanExp) obj); return; }
	if ( obj.eClass().getName().equals("SelectByKind") ) { visitSelectByKind((anatlyzer.atlext.OCL2.SelectByKind) obj); return; }
	if ( obj.eClass().getName().equals("Parameter") ) { visitParameter((anatlyzer.atlext.OCL.Parameter) obj); return; }
	if ( obj.eClass().getName().equals("SequenceType") ) { visitSequenceType((anatlyzer.atlext.OCL.SequenceType) obj); return; }
	if ( obj.eClass().getName().equals("ActionBlock") ) { visitActionBlock((anatlyzer.atlext.ATL.ActionBlock) obj); return; }
	if ( obj.eClass().getName().equals("OclType") ) { visitOclType((anatlyzer.atlext.OCL.OclType) obj); return; }
		if ( obj.eClass().getName().equals("StringToStringMap") ) { return; }
	
		unknownClassOf(obj);

	}
	
	protected void unknownClassOf(EObject obj) {
		throw new IllegalStateException("Cannot handle class: " + obj.eClass().getName());	
	}
	
	//
	// Framework code inlined as a template
	//


	public static class VisitingActions {
		private Object[] actions;
		public VisitingActions(Object... actions) {
			this.actions = actions;
		}
		
		public VisitingAction[] getActions(EObject obj) {
			VisitingAction[] result = new VisitingAction[actions.length];
			EClass  eclass      = obj.eClass();
			
			for(int i = 0; i < actions.length; i++) {
				if ( actions[i] instanceof String ) {
					String refName = (String) actions[i];
					result[i] = new VisitingAction((EReference) eclass.getEStructuralFeature(refName));
				} else if ( actions[i] instanceof VisitingAction ) {
					result[i] = (VisitingAction) actions[i];
				}
			}
			return result;
		}
	}

	public static class VisitingAction {
		public static final int REFERENCE = 0;
		public static final int METHOD_CALL = 1;
		public static final int FILTER_CALL = 2;
		
		private int actionType = -1;

		// For references
		private EReference	reference;
		
		// For method calls
		private Object[]	arguments;
		private java.lang.reflect.Method	method;
		private AbstractVisitor receptor;
		
		public VisitingAction(AbstractVisitor receptor, java.lang.reflect.Method method, Object[] arguments) {
			actionType = METHOD_CALL;
			this.receptor = receptor;
			this.method = method;
			this.arguments = arguments;
		}

		public static VisitingAction createFilter(AbstractVisitor  receptor, java.lang.reflect.Method method, Object[] arguments) {
			VisitingAction va = new VisitingAction(receptor, method, arguments);
			va.actionType = FILTER_CALL;
			return va;
		}
		
		public VisitingAction(EReference r) {
			actionType = REFERENCE;
			this.reference = r;
		}

		public int getActionType() {
			return actionType;
		}
		
		public boolean isReference() {
			return getActionType() == REFERENCE;
		}
		
		public EReference getEReference() {
			return this.reference;
		}
		
		public boolean isMethodCall() {
			return getActionType() == METHOD_CALL;
		}
		
		public boolean isFilter() {
			return getActionType() == FILTER_CALL;
		}
		
		public Object performMethodCall() {
			try {
				return method.invoke(receptor, arguments);
			} catch ( Exception e ) {
				throw new RuntimeException(e);
			}	
		}
	}
	

	private java.util.HashMap<String, java.lang.reflect.Method> allMethods = null;
	public java.util.HashMap<String, java.lang.reflect.Method> getAllMethods() {
		if ( allMethods == null ) {
			allMethods = new java.util.HashMap<String, java.lang.reflect.Method>();
			for(java.lang.reflect.Method m : this.getClass().getMethods()) {
				allMethods.put(getMethodKey(m.getName(), m.getParameterCount()), m);
			}
		}
		return allMethods;
	}
	
	private final String getMethodKey(String name, int paramCount) {
		return name + "/" + paramCount;
	}
	
	public VisitingAction method(String methodName, Object... arguments) {
		String key = getMethodKey(methodName, arguments.length);
		if ( getAllMethods().containsKey(key) ) {
			return new VisitingAction(this, getAllMethods().get(key), arguments);			
		}
		
		throw new RuntimeException("No method " + methodName + " found in " + this.getClass().getName());
	}

	public VisitingAction filter(String methodName, Object... arguments) {
		String key = getMethodKey(methodName, arguments.length);
		if ( getAllMethods().containsKey(key) ) {
			return VisitingAction.createFilter(this, getAllMethods().get(key), arguments);
		}
		
		throw new RuntimeException("No method " + methodName + " found in " + this.getClass().getName());
	}

		
}
