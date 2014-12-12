package anatlyzer.atl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.ActionBlock;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclUndefinedExp;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class ATLSerializer extends AbstractVisitor {
	public static String serialize(ATLModel m) {		
		Module mod = m.allObjectsOf(Module.class).get(0);
		ATLSerializer s = new ATLSerializer();
		s.startVisiting(mod);
		return s.g(mod);
	}

	private HashMap<Object, String> str = new HashMap<Object, String>();
	
	@Override
	public void inModule(Module self) {
		String s = "";
		
		for(String c : self.getCommentsBefore()) {
			s += c + cr();
		}
		
		
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
		
		s += join(l1) + " from " + join(l2) + ";" + cr(2);

		for(ModuleElement r : self.getElements()) {
			s += g(r) + cr(2);
		}

		s(s);
	}

	@Override
	public void inMatchedRule(MatchedRule self) {
		ruleGenerator(self, "rule");
	}
	
	// Matched and Lazy rules
	public void ruleGenerator(RuleWithPattern self, String header) {
		String extends_ = self.getSuperRule() != null ? " extends " + self.getSuperRule().getName() : "";
		String s = ifs(self.isIsAbstract(), "abstract ") + header + " " + self.getName() + extends_ + " {" + cr();
				
		s += tab(1) + "from " + g(self.getInPattern()) + cr();
		if ( self.getVariables().size() > 0 ) {
			s += "using {" +  cr();
			for(RuleVariableDeclaration rv : self.getVariables()) {
				s += tab(1) + rv.getVarName() + " : " + g(rv.getType()) + " = " + g(rv.getInitExpression()) + ";";
			}
			s += "}" + cr();
		}
		s += tab(1) + "  to " + g(self.getOutPattern()) + cr();
		
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
	public void inActionBlock(ActionBlock self) {
		String s = "do {" + cr();
		for(Statement stmt : self.getStatements()) {
			s += tab(1) + g(stmt) + ";" + cr();
		}
		s += "}";
		s(s);
	}
	
	@Override
	public void inBindingStat(BindingStat self) {
		s(g(self.getSource()) + " <- " + g(self.getValue()));
	}
		
	@Override
	public void inInPattern(InPattern self) {
		List<String> l = sl();
		for(InPatternElement e : self.getElements()) {
			l.add(g(e));
		}
		
		String s = join(l);
		
		if ( self.getFilter() != null ) {
			s += "( " + g(self.getFilter()) + " )";
		}
		
		s(s);
	}
	
	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		s(self.getVarName() + " : " + g(self.getType()));
	}
	
	@Override
	public void inOutPattern(OutPattern self) {
		List<String> l = sl();
		for(OutPatternElement e : self.getElements()) {
			l.add(g(e));
		}
		
		String s = join(l);
		
		// TODO: Add bindings
		
		s(s);
	}
	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		String s = self.getVarName() + " : " + g(self.getType());
		
		List<String> l = sl();
		for(Binding b : self.getBindings()) {
			l.add(g(b));
		}
		
		if ( l.size() > 0 ) {
			s(s + "(" + cr() + join(l, "," + cr()) + cr() + ")");
		} else {
			s(s);
		}
	}

	@Override
	public void inBinding(Binding self) {
		s(self.getPropertyName() + " <- " + g(self.getValue()));
	}
	
	//
	// Expressions
	// This could be reusable
	//
	
	@Override
	public void inOperatorCallExp(OperatorCallExp self) {
		if ( self.getArguments().size() == 0 ) {
			s(self.getOperationName() + " " + g(self.getSource()));
		} else {
			s(g(self.getSource()) + " " + self.getOperationName() + " " + g(self.getArguments().get(0)));			
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
		String s = "if ( " + g(self.getCondition()) + " ) then" + cr() + tab(1) + g(self.getThenExpression()) + 
				 cr() + tab(0) + "else" + cr() + tab(1) + g(self.getElseExpression()) + cr() + tab(0) + "endif";
		s(s);
	}
	
	@Override
	public void inIteratorExp(IteratorExp self) {
		String s = g(self.getSource()) + "->" + self.getName() + "(" + 
					self.getIterators().get(0).getVarName() + " | " + g(self.getBody()) + ")";
		s(s);
	}
	
	@Override
	public void inVariableExp(VariableExp self) {
		s(self.getReferredVariable().getVarName());
	}
	
	@Override
	public void inStringExp(StringExp self) {
		s("'" + self.getStringSymbol() + "'");
	}
	
	@Override
	public void inIntegerExp(IntegerExp self) {
		s(self.getIntegerSymbol()+"");
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
		String s = "Sequence {";
		
		List<String> l = sl();
		for(OclExpression e : self.getElements()) {
			l.add(g(e));
		}
		
		s += join(l) + " }";

		s(s);		
	}
	
	@Override
	public void inOclModelElement(OclModelElement self) {
		s(self.getModel().getName() + "!" + self.getName());
	}
	
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		s("#" + self.getName());
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
	// Utilities
	//
	
	private ArrayList<String> sl() {
		return new ArrayList<String>();
	}
	
	private String cr() {
		return cr(1);
	}
	
	private String cr(int n) {
		String s = "";
		for(int i = 0; i < n; i++) {
			s += "\n";
		}
		return s;
	}
	
	private String ifs(boolean v, String t) {
		return ifs(v, t, "");
	}
	
	private String ifs(boolean v, String t, String f) {
		if ( v ) return t;
		return f;
	}
	
	private void s(String s) {
		str.put(this.getCurrent(), s);
	}
	

	private String join(List<String> l) {
		return join(l, ", ");
	}
	
	private String join(List<String> l, String separator) {
		String r = "";
		for(int i = 0; i < l.size(); i++) {
			r += l.get(i);
			if ( i + 1 < l.size() ) {
				r += separator;
			}
		}
		return r;
	}
	
	
	private String g(EObject obj) {
		if ( ! str.containsKey(obj) ) throw new IllegalArgumentException("Not found " + obj);
		return str.get(obj);
	}
	
	private String tab(int n) {
		String s = "";
		for(int i = 0; i < n; i++) {
			s += "   ";
		}
		return s;		
	}
	
}
