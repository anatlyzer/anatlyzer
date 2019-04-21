package anatlyzer.atl.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.ExtendTransformation;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ExpressionStat;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.ForStat;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.IfStat;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.BagExp;
import anatlyzer.atlext.OCL.BagType;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.BooleanType;
import anatlyzer.atlext.OCL.CollectionExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IntegerType;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.MapElement;
import anatlyzer.atlext.OCL.MapExp;
import anatlyzer.atlext.OCL.MapType;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclAnyType;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.OrderedSetExp;
import anatlyzer.atlext.OCL.OrderedSetType;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.RealExp;
import anatlyzer.atlext.OCL.RealType;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.SequenceType;
import anatlyzer.atlext.OCL.SetExp;
import anatlyzer.atlext.OCL.SetType;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.StringType;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.TupleType;
import anatlyzer.atlext.OCL.TupleTypeAttribute;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.OCL2.SelectByKind;
import anatlyzer.atlext.processing.AbstractVisitor;

public class ATLSerializer extends AbstractVisitor {
	public static String serialize(ATLModel m) {		
		Module mod = m.allObjectsOf(Module.class).get(0);
		ATLSerializer s = new ATLSerializer();
		s.startVisiting(mod);
		return s.g(mod);
	}


	public static String serialize(EObject obj) {
		ATLSerializer s = new ATLSerializer();
		s.startVisiting(obj);
		return s.g(obj);
	}
	

	public static void serialize(EObject obj, String path) throws IOException {
		String s = serialize(obj);
		FileWriter writer = new FileWriter(path);
		writer.append(s);
		writer.close();
	}
	
	
	public static void serialize(ATLModel atlModel, String path) throws IOException {
		String s = serialize(atlModel);
		FileWriter writer = new FileWriter(path);
		writer.append(s);
		writer.close();
	}
	
	private HashMap<Object, String> str = new HashMap<Object, String>();
	
	public VisitingActions preModule(anatlyzer.atlext.ATL.Module self) { 
		return actions("libraries", "inModels", "outModels", 
				filter("getHelpers", self), filter("getRules", self) );
	} 

	public List<Helper> getHelpers(anatlyzer.atlext.ATL.Module self) {
		LinkedList<Helper> helpers = new LinkedList<Helper>();
		for (ModuleElement me : self.getElements()) {
			if ( me instanceof Helper && ! ExtendTransformation.isAddedEOperation(me) ) {
				helpers.add((Helper) me);
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
	public void inLibrary(Library self) {
		String s = "";
		s += commentsBefore(self);
		
		s += "library " + self.getName() + ";" + cr();
		
		for(ModuleElement r : self.getHelpers()) {
			s += g(r) + cr(2);
		}
		
		s(s);
	}
	
	private String commentsBefore(LocatedElement self) {
		String s = "";
		for(String c : self.getCommentsBefore()) {
			if ( ! c.trim().startsWith("--") ) {
				c = "-- " + c;
			}
			s += c + cr();
		}
		return s;
	}

	@Override
	public void inQuery(Query self) {
		String s = commentsBefore(self);
		s += "query " + self.getName() + " = " + g(self.getBody()) + ";" + cr(2);
		
		for(ModuleElement r : self.getHelpers()) {
			if ( ExtendTransformation.isAddedEOperation(r) )
				continue;
			
			s += g(r) + cr(2);
		}

		s(s);		
	}
	

	@Override
	public void inModule(Module self) {
		String s = commentsBefore(self);
			
		s += "module " + self.getName() + ";" + cr() +
		      "create ";
		
		List<String> l1 = sl();
		for (OclModel model: self.getOutModels()) {
			l1.add( model.getName() + " : " + model.getMetamodel().getName() );
		}

		List<String> l2 = sl();
		for (OclModel model: self.getInModels()) {
			l2.add( model.getName() + " : " + model.getMetamodel().getName() );
		}
		
		String trafoType = " from ";
		if ( self.isIsRefining() ) {
			trafoType = " refining ";
		}
		
		s += join(l1) + trafoType + join(l2) + ";" + cr(2);

		for(ModuleElement r : self.getElements()) {
			if ( ExtendTransformation.isAddedEOperation(r) )
				continue;
			
			s += serializeModuleElement(r) + cr(2);
		}

		s(s);
	}
	
	protected String serializeModuleElement(ModuleElement r) {
		return g(r);
	}

	@Override
	public void inMatchedRule(MatchedRule self) {
		ruleGenerator(self, "rule");
	}
	
	// Matched and Lazy rules
	public void ruleGenerator(RuleWithPattern self, String header) {
		String extends_ = self.getSuperRule() != null ? " extends " + self.getSuperRule().getName() : "";
		String s = ifs(self.isIsAbstract(), "abstract ") + header + " " + self.getName() + extends_ + " {" + cr();
				
		s += tab(1) + g(self.getInPattern()) + cr();
		if ( self.getVariables().size() > 0 ) {
			s += "using {" +  cr();
			for(RuleVariableDeclaration rv : self.getVariables()) {
				s += tab(1) + rv.getVarName() + " : " + g(rv.getType()) + " = " + g(rv.getInitExpression()) + ";";
			}
			s += "}" + cr();
		}
		
		if ( self.getOutPattern() != null ) {
			s += tab(1) + "  to " + g(self.getOutPattern()) + cr();
		}
		
		if ( self.getActionBlock() != null ) {
			s += g(self.getActionBlock());
		}
		
		s(s + cr() + "}");
	}
	
	@Override
	public void inLazyRule(LazyRule self) {
		ruleGenerator(self, "lazy rule");
	}
	
	@Override
	public void inCalledRule(CalledRule self) {
		List<String> params = sl();
		for (Parameter p : self.getParameters()) {
			params.add( p.getVarName() + " : " + g(p.getType()) );
		}

		String prefix = "";
		if ( self.isIsEndpoint() ) {
			prefix = "endpoint ";
		} else if ( self.isIsEntrypoint() ) {
			prefix = "entrypoint ";
		}
		
		String s = prefix + "rule " + self.getName() + "( " +  join(params) + ")" + " {" + cr();
		if ( self.getVariables().size() > 0 ) {
			s += "using {" +  cr();
			for(RuleVariableDeclaration rv : self.getVariables()) {
				s += tab(1) + rv.getVarName() + " : " + g(rv.getType()) + " = " + g(rv.getInitExpression()) + ";";
			}
			s += "}" + cr();
		}
		
		if ( self.getOutPattern() != null )
			s += tab(1) + "  to " + g(self.getOutPattern()) + cr();
		
		if ( self.getActionBlock() != null ) {
			s += g(self.getActionBlock());
		}
		
		s(s + cr() + "}");
	}

	@Override
	public void inForEachOutPatternElement(ForEachOutPatternElement self) {
		String s = self.getVarName() + " : " + "distinct " + g(self.getType()) + 
				" foreach(" + self.getIterator().getVarName() + " in " + g(self.getCollection()) + ") (" + cr();
		
		List<String> l = sl();
		for(Binding b : self.getBindings()) {
			l.add(g(b));
		}

		s += join(l, "," + cr()) + cr() + ")";
		
		s(s);
	}
	
	@Override
	public void inActionBlock(ActionBlock self) {
		String s = "do {" + cr();
		for(Statement stmt : self.getStatements()) {
			s += tab(1) + g(stmt) + cr();
		}
		s += "}";
		s(s);
	}
	
	@Override
	public void inBindingStat(BindingStat self) {
		String operator = " <- ";
		if ( self.isIsAssignment() )
			operator = " <:= ";
		s(g(self.getSource()) + operator + g(self.getValue()) + ";");
	}
		
	@Override
	public void inInPattern(InPattern self) {
		List<String> l = sl();
		for(InPatternElement e : self.getElements()) {
			l.add(g(e));
		}
		
		String s = "from " + join(l);
		
		if ( self.getFilter() != null ) {
			s += "( " + g(self.getFilter()) + " )";
		}
		
		s(s);
	}
	
	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		String modelConstraint = "";
		if ( self.getModels().size() > 0 ) {
			modelConstraint = " in ";
			List<String> l = sl();
			for(OclModel e : self.getModels()) {
				l.add(g(e));
			}
			modelConstraint += join(l, ", ");
		}
		s(self.getVarName() + " : " + g(self.getType()) + modelConstraint);
	}
	
	@Override
	public void inOutPattern(OutPattern self) {
		List<String> l = sl();
		for(OutPatternElement e : self.getElements()) {
			l.add(g(e));
		}
				
		String s = join(l);
		
	
		if ( self.getDropPattern() != null ) {
			s += "\n\t" + "drop";
		}
		
		s(s);
	}
	
	@Override
	public void beforeSimpleOutPatternElement(SimpleOutPatternElement self) {
		inctab();
	}
	
	@Override
	public void afterSimpleInPatternElement(SimpleInPatternElement self) {
		dectab();
	}
	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		String s = self.getVarName() + " : " + g(self.getType());
		
		List<String> l = sl();
		for(Binding b : self.getBindings()) {
			l.add(g(b));
		}
		
		if ( l.size() > 0 ) {
			// s(s + "(" + cr() + join(l, "," + cr()) + cr() + ")");
			s(s + " (" + cr() + join(l, "") + cr() + genTab() + ")");
		} else {
			s(s);
		}
	}

	/*
	@Override
	public VisitingActions preBinding(Binding self) {
		pushIndentation()
		return super.preBinding(self);
	}
	*/
	
	// protected int cindent = 0;
	
	@Override
	public VisitingActions preBinding(Binding self) {
		inctab();
		return super.preBinding(self);
	}
	
	@Override
	public void afterBinding(Binding self) {
		dectab();
	}
	
	@Override
	public void inBinding(Binding self) {
		if ( self.getOutPatternElement() == null ) { // this may happen in speculative quick fixes when "Remove binding" is applied
			s(genTab() + self.getPropertyName() + " <- " + g(self.getValue()));
			return;
		}
		
		String separator = "";
		String init = cr();
		if ( self.getOutPatternElement().getBindings().indexOf(self) != self.getOutPatternElement().getBindings().size() - 1) {
			separator = ",";
		}
		if ( self.getOutPatternElement().getBindings().indexOf(self) == 0 ) {
			init = "";
		}			
		
		s(init + genTab() + self.getPropertyName() + " <- " + g(self.getValue()) + separator);
	}
	
	//
	// Helpers
	//
	@Override
	public void inContextHelper(ContextHelper self) {
		String s = commentsBefore(self) + "helper context " + g(ATLUtils.getHelperType(self)) + 
				" def: " + g(self.getDefinition().getFeature()) + ";";
		s(s);
	}
	
	@Override
	public void inOperation(Operation self) {
		String paramsStr = "";
		List<String> params = sl();
		for (Parameter p : ((Operation) self.getDefinition().getFeature()).getParameters() ) {
			params.add( p.getVarName() + " : " + g(p.getType()) );
		}
		paramsStr = "(" + join(params) + ")";

		String s = self.getName() + paramsStr + " : " + g(self.getReturnType()) + " = " +
				g(self.getBody());
		s(s);
	}

	@Override
	public void inAttribute(Attribute self) {
		String s = self.getName() + " : " + g(self.getType()) + " = " +
				g(self.getInitExpression());
		s(s);
	}
	
	@Override
	public void inStaticHelper(StaticHelper self) {
		String s = commentsBefore(self) + "helper " + 
				" def: " + g(self.getDefinition().getFeature()) + ";";
		s(s);
	}
	
	//
	// Expressions
	// This could be reusable
	//
	
	private static HashMap<String, Integer> precedences = new HashMap<String, Integer>();
	static {
		precedences.put("not", 110);
		
		precedences.put("*", 100);
		precedences.put("/", 100);

		precedences.put("-", 90);
		precedences.put("+", 90);
		
		precedences.put("<", 80);
		precedences.put("<=", 80);
		precedences.put(">=", 80);
		precedences.put(">", 80);
		
		precedences.put("=", 70);
		precedences.put("<>", 70);
		
		// They have the same precedence, it has been checked
		precedences.put("and", 60);
		precedences.put("or", 60);
		precedences.put("xor", 60);
		
		precedences.put("implies", 30);
		
		
		/* Operator precedence */	
		/*
	    The NavigationOperators
	    not +, -, unary
	    *, / multiply and divide
	    +, - add and subtract
	    <, <=, >=, > relational comparisons
	    =, <> equality and inequality
	    and logical and
	    or inclusive or
	    xor exclusive or
	    implies logical implication
		*/

	}
	
	@Override
	public void inOperatorCallExp(OperatorCallExp self) {
		int precedence1 = precedences.getOrDefault(self.getOperationName(), -1);
		
		if ( self.getArguments().size() == 0 ) {
			String src = g(self.getSource());
			if ( self.getSource() instanceof OperatorCallExp ) {
				int precedence2 = precedences.getOrDefault(((OperationCallExp) self.getSource()).getOperationName(), -1);
				if ( precedence1 > precedence2 ) {
					src = "(" + src + ")";
				}
			} 
			s(self.getOperationName() + " " + src);
		} else {
			String src = g(self.getSource());
			String arg = g(self.getArguments().get(0));
						
			if ( self.getSource() instanceof OperatorCallExp ) {
				int precedence2 = precedences.getOrDefault(((OperationCallExp) self.getSource()).getOperationName(), -1);
				if ( precedence1 > precedence2 ) {
					src = "(" + src + ")";
				}
				// Same precedence but distinct operator
				else if ( precedence1 == precedence2 && !self.getOperationName().equals(((OperationCallExp) self.getSource()).getOperationName())) {
					src = "(" + src + ")";
				}
			} else if ( self.getSource() instanceof LetExp ) {
				src = "(" + src + ")";
			}
			
			if ( self.getArguments().get(0) instanceof OperatorCallExp ) {
				int precedence2 = precedences.getOrDefault(((OperationCallExp) self.getArguments().get(0)).getOperationName(), -1);
				if ( precedence1 > precedence2 ) {
					arg = "(" + arg + ")";
				}
				// Same precedence but distinct operator
				else if ( precedence1 == precedence2 && !self.getOperationName().equals(((OperationCallExp) self.getArguments().get(0)).getOperationName())) {
					arg = "(" + arg + ")";
				}
			} else if ( self.getArguments().get(0) instanceof LetExp ) {
				arg = "(" + arg + ")";
			}
			
			s(src + " " + self.getOperationName() + " " + arg);			
		}
	}
	
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		s(g(self.getSource()) + "." + self.getName());
	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		String s = g(self.getSource()) + "." + self.getOperationName();
		
		List<String> l = sl();
		for(OclExpression e : self.getArguments()) {
			l.add(g(e));
		}
		
		s += "(" + join(l) + ")";

		s(s);
	}

	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		String s = g(self.getSource()) + "->" + self.getOperationName();
		
		List<String> l = sl();
		for(OclExpression e : self.getArguments()) {
			l.add(g(e));
		}
		
		s += "(" + join(l) + ")";

		s(s);
	}
	
	@Override
	public void inIfExp(IfExp self) {
		String s = "if " + g(self.getCondition()) + " then" + inctab() + g(self.getThenExpression()) +  
				dectab() + "else" + inctab() + g(self.getElseExpression()) + dectab() + "endif";
		s(s);
	}
	
	@Override
	public void inLetExp(LetExp self) {
		String type = "";
		if ( self.getVariable().getType() != null ) {
			type = " : " + g(self.getVariable().getType());
		}
		String s = "let " + self.getVariable().getVarName() + type + " = " + g(self.getVariable().getInitExpression()) + 
				cr() + tab(0) + " in " + g(self.getIn_());
		s(s);
	}
	
	@Override
	public void inIteratorExp(IteratorExp self) {
		List<String> l = sl();
		for(Iterator it : self.getIterators()) {
			l.add(it.getVarName());
		}
						
		String s = g(self.getSource()) + "->" + self.getName() + "(" + 
					join(l) + " | " + g(self.getBody()) + ")";
		s(s);
	}
	
	@Override
	public void inIterateExp(IterateExp self) {
		String itType = "";
		if ( self.getIterators().get(0).getType() != null ) {
			itType = " : " + g(self.getIterators().get(0).getType());
		}
		String s = g(self.getSource()) + "->" + "iterate" + "(" + 
				self.getIterators().get(0).getVarName() + itType + ";" + 
				self.getResult().getVarName() + ":" + g(self.getResult().getType()) + " = " + g(self.getResult().getInitExpression()) + 
				" | " + g(self.getBody()) + ")";
		s(s);
	}
	
	@Override
	public void inVariableExp(VariableExp self) {
		s(self.getReferredVariable().getVarName());
	}
	
	@Override
	public void inStringExp(StringExp self) {
		s("'" + self.getStringSymbol().replaceAll("(\\\\n|\\\\t)", "\\\\$1") + "'");
	}  // \\\\\\\\n
	
	@Override
	public void inIntegerExp(IntegerExp self) {
		s(self.getIntegerSymbol()+"");
	}
	
	@Override
	public void inRealExp(RealExp self) {
		s(self.getRealSymbol()+"");
	}
	
	@Override
	public void inBooleanExp(BooleanExp self) {
		s(self.isBooleanSymbol() ? "true" : "false");
	}
	
	@Override
	public void inOclUndefinedExp(OclUndefinedExp self) {
		s("OclUndefined");
	}
	
	@Override
	public void inSequenceExp(SequenceExp self) {
		doCollectionExp("Sequence", self);
	}
	
	@Override
	public void inSetExp(SetExp self) {
		doCollectionExp("Set", self);
	}
	
	@Override
	public void inOrderedSetExp(OrderedSetExp self) {
		doCollectionExp("OrderedSet", self);
	}
	
	@Override
	public void inBagExp(BagExp self) {
		doCollectionExp("Bag", self);
	}
	
	private void doCollectionExp(String atlText, CollectionExp self) {
		String s = atlText + " {";
		
		List<String> l = sl();
		for(OclExpression e : self.getElements()) {
			l.add(g(e));
		}
		
		s += join(l) + " }";

		s(s);		
	}
	
	@Override
	public void inMapExp(MapExp self) {
		String s = "Map" + " {";
		
		List<String> l = sl();
		for(MapElement e : self.getElements()) {
			l.add("(" + g(e.getKey()) + ", " + g(e.getValue()) + ")");
		}
		
		s += join(l) + " }";
		s(s);		
	}
	
	@Override
	public void inTupleExp(TupleExp self) {
		String s = "Tuple" + " {";
		List<String> l = sl();
		for(TuplePart e : self.getTuplePart()) {
			l.add(e.getVarName() + " = " + g(e.getInitExpression()));
		}
		s += join(l) + " }";
		s(s);			
	}

	@Override
	public void inOclModel(OclModel self) {
		s(self.getName());
	}
	
	@Override
	public void inOclModelElement(OclModelElement self) {
		s(self.getModel().getName() + "!" + norm(self.getName()));
	}
	
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		s("#" + norm(self.getName()));
	}

	@Override
	public void inOclAnyType(OclAnyType self) {
		s("OclAny");
	}
	
	@Override
	public void inIntegerType(IntegerType self) {
		s("Integer");
	}
	
	@Override
	public void inStringType(StringType self) {
		s("String");
	}
	
	@Override
	public void inRealType(RealType self) {
		s("Real");
	}
	
	@Override
	public void inBooleanType(BooleanType self) {
		s("Boolean");
	}
	
	@Override
	public void inSequenceType(SequenceType self) {
		s("Sequence(" + g(self.getElementType()) + ")");
	}

	@Override
	public void inSetType(SetType self) {
		s("Set(" + g(self.getElementType()) + ")");
	}
	
	@Override
	public void inOrderedSetType(OrderedSetType self) {
		s("OrderedSet(" + g(self.getElementType()) + ")");
	}
	
	@Override
	public void inBagType(BagType self) {
		s("Bag(" + g(self.getElementType()) + ")");
	}	
	
	@Override
	public void inMapType(MapType self) {
		String s = "Map(" + g(self.getKeyType()) + ", " + g(self.getValueType()) + ")";
		s(s);
	}
	
	@Override
	public void inTupleType(TupleType self) {
		String s = "TupleType" + "(";
		List<String> l = sl();
		for(TupleTypeAttribute e : self.getAttributes()) {
			l.add(e.getName() + " : " + g(e.getType()));
		}
		s += join(l) + ")";
		s(s);			
	}
	
	//
	// END-OF Expressions
	//
	
	/*
	@Override
	public void inOclModel(OclModel self) {
		s(self.getMetamodel().getName() + "!" + self.getName());
	}
	*/
	
	//
	// Imperative
	//
	@Override
	public void inExpressionStat(ExpressionStat self) {
		s(g(self.getExpression()) + ";");
	}
	
	@Override
	public void inIfStat(IfStat self) {
		String s = "if ( " + g(self.getCondition()) + " ) {" + cr() + tab(1);
		List<String> l = sl();
		for(Statement e : self.getThenStatements()) {
			l.add(g(e));
		}
		
		s += join(l, cr(1) + "\n") + " }";

		if ( self.getElseStatements().size() > 0 ) {
			s += "else {" + cr() + tab(1);
			l = sl();
			for(Statement e : self.getElseStatements()) {
				l.add(g(e));
			}
			
			s += join(l, cr(1) + "\n") + " }";
			
		}
		s(s);
	}
	
	@Override
	public void inForStat(ForStat self) {
		String s = "for (" + self.getIterator().getVarName() + " in " + g(self.getCollection()) + ") {" + cr();
		List<String> l = sl();
		for(Statement e : self.getStatements()) {
			l.add(g(e));
		}
		s += join(l, cr(1) + "\n") + " }";		
		s = s + "}";
		s(s);
	}
	
	//
	// Utilities
	//
	
	protected ArrayList<String> sl() {
		return new ArrayList<String>();
	}
	
	protected String cr() {
		return cr(1);
	}
	
	private String cr(int n) {
		String s = "";
		for(int i = 0; i < n; i++) {
			s += "\n";
		}
		return s;
	}
	
	protected String ifs(boolean v, String t) {
		return ifs(v, t, "");
	}
	
	protected String ifs(boolean v, String t, String f) {
		if ( v ) return t;
		return f;
	}
	
	protected void s(String s) {
		str.put(this.getCurrent(), s);
	}
	

	protected String join(List<String> l) {
		return join(l, ", ");
	}
	
	protected String join(List<String> l, String separator) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < l.size(); i++) {
			sb.append(l.get(i));
			// To avoid extremely large lines
			if ( i > 0 && i % 80 == 0 ) {
				sb.append("\n");
			}
			if ( i + 1 < l.size() ) {
				sb.append(separator);
			}
		}
		
		return sb.toString();
	}
	
	
	protected String g(EObject obj) {
		if ( ! str.containsKey(obj) ) throw new IllegalArgumentException("Not found " + obj);
		return str.get(obj);
	}
	
	protected String inctab() {
		indentationStack.add("\t");
		return cr() + genTab();
	}
	
	protected String dectab() {
		if ( indentationStack.size() == 0 )
			return cr(); // this is a fallback... Try to remove and test 
		
		indentationStack.remove(indentationStack.size() - 1);
		return cr() + genTab();
	}
	
	protected String tab(int n) {
		String s = "";
		for(int i = 0; i < n; i++) {
			s += "   ";
		}
		return s;		
	}
	
	protected String genTab() {
		String s = "";
		for (String string : indentationStack) {
			s += string;
		}
		return s;
	}
	

	protected ArrayList<String> indentationStack = new ArrayList<String>();
	
	/*
	protected String otab() {
		if ( indentationStack.isEmpty() ) {
			indentationStack.add(0);
		}
		indentationStack.add(indentationStack.get(indentationStack.size() - 1) + 1);		
		return tab(indentationStack.get(indentationStack.size() - 1));
	}
	
	protected String ctab() {
		indentationStack.remove(indentationStack.size() - 1);
		return "";
	}
	*/


	private static HashSet<String> reservedWords = new HashSet<String>();
	static {
		reservedWords.add("let");
		reservedWords.add("in");
		reservedWords.add("Sequence");
		reservedWords.add("Set");
		reservedWords.add("OclType");
	}
	
	private String norm(String name) {
		if ( reservedWords.contains(name) ) 
			return '"' + name + '"';
		return name;
	}
	
}
