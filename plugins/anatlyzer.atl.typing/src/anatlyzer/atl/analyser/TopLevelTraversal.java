package anatlyzer.atl.analyser;

import java.util.LinkedList;
import java.util.List;

import anatlyzer.atl.analyser.generators.USESerializer;
import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.ITypeNamespace;
import anatlyzer.atl.analyser.namespaces.TransformationNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ExpressionStat;
import anatlyzer.atlext.ATL.ForEachOutPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.Statement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * 
 * @author jesus
 *
 */
public class TopLevelTraversal extends AbstractAnalyserVisitor {
	
	/** Called rules whose type cannot be determined without analysing the last sentence of the do {} */
	private LinkedList<CalledRule> pendingCalledRules = new LinkedList<CalledRule>();
	
	public TopLevelTraversal(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}
	
	public void perform(ComputedAttributes attr) {
		this.attr = attr.pushVisitor(this);
		startVisiting(root);
		attr.popVisitor(this);
		
		// Lastly deal with pending called rules
		for (CalledRule pendingCalledRule : pendingCalledRules) {			
			treatPendingCalledRule(pendingCalledRule);
		}
	}
	
	//@Override
	//public VisitingActions preRule(Rule self) {
		// return actions(); // Do not visit anything else
	//}


	@Override
	public void inBinding(Binding self) {
		Metaclass targetVar = (Metaclass) attr.typeOf( self.getOutPatternElement().getType() );
		IClassNamespace ns = (IClassNamespace) targetVar.getMetamodelRef();
		
		Type t = null;
		if ( ! ns.hasFeature(self.getPropertyName()) ) {
			t = this.errors().signalNoFeature(targetVar.getKlass(), self.getPropertyName(), self); // No need to halt, recovery is just "ignore"
		} else {
			t = ns.getFeatureType(self.getPropertyName(), self);
		}
		
		attr.linkStructType(t);
	}
	
	
	@Override
	public void inAttribute(Attribute self) {
		extendTypeForAttribute(self, mm, attr,  attr.typeOf(self.getType()));
	}

	public static void extendTypeForAttribute(Attribute self, GlobalNamespace mm, ComputedAttributes attr, Type t) {
		OclContextDefinition ctx = ((OclFeatureDefinition) self.eContainer()).getContext_();
		if ( ctx == null ) {
			mm.getTransformationNamespace().extendType(self.getName(), t, self);
			return;
		}
		
		Type ctxType = attr.typeOf(ctx.getContext_());
		ITypeNamespace nspace = (ITypeNamespace) ctxType.getMetamodelRef();
		
		nspace.extendType(self.getName(), t, self);
	}
	
	@Override
	public void inOperation(Operation self) {
		extendTypeForOperation(self, mm, attr,  attr.typeOf(self.getReturnType()));
	}
	
	@Override
	public void inContextHelper(ContextHelper self) {
		self.setContextType(attr.typeOf(self.getDefinition().getContext_().getContext_()));
	}
	
	public static void extendTypeForOperation(Operation self, GlobalNamespace mm, ComputedAttributes attr, Type t) {	
		OclContextDefinition ctx = ((OclFeatureDefinition) self.eContainer()).getContext_();
		if ( ctx == null ) {
			mm.getTransformationNamespace().extendType(self.getName(), t, self);
			return;
		} else {
			Type ctxType = attr.typeOf(ctx.getContext_());
			ITypeNamespace nspace = (ITypeNamespace) ctxType.getMetamodelRef();
			
			nspace.extendType(self.getName(), t, self);			
		}
	}

	
	/**
	 * Called and lazy rules are treated as global helpers with a return type belonging
	 * to a target meta-model.
	 */
	@Override
	public void inCalledRule(CalledRule self) {
		if ( self.isIsEndpoint() || self.isIsEntrypoint() )
			return;

		// Some called rules just feature a do { } block
		if ( self.getOutPattern() != null ) {
			ExpressionStat s = getCalledRuleReturnStatement(self);
			if ( s == null ) {
				Type t = attr.typeOf(self.getOutPattern().getElements().get(0).getType()); 
				mm.getTransformationNamespace().attachRule(self.getName(), t, self);
			} else {
				if ( s.getExpression() instanceof VariableExp ) {
					VariableDeclaration vd = ((VariableExp) s.getExpression()).getReferredVariable();
					if ( self.getOutPattern().getElements().contains(vd) ) {

						// Same as above
						Type t = attr.typeOf(vd); 
						mm.getTransformationNamespace().attachRule(self.getName(), t, self);
						
					} else {
						throw new UnsupportedOperationException(self.getLocation());
					}
				} else {
					pendingCalledRules.add(self);					
				}
			}
		} else {
			pendingCalledRules.add(self);
		}
	}


	private ExpressionStat getCalledRuleReturnStatement(CalledRule self) {
		if ( self.getActionBlock() == null ) 
			return null;
		List<Statement> statements = self.getActionBlock().getStatements();
		Statement last = statements.get(statements.size() - 1);
				
		if ( last instanceof ExpressionStat ) {
			return (ExpressionStat) last;
		}
		return null;
	}
	
	private void treatPendingCalledRule(CalledRule self) {
		ExpressionStat last = getCalledRuleReturnStatement(self);
		if ( last == null ) {
			// throw new UnsupportedOperationException(self.getLocation());
			mm.getTransformationNamespace().attachRule(self.getName(), typ().newUnknownType(), self);	 
			// TODO: Indicate void in a better way!
			return;
		}

		if ( last.getExpression() instanceof OperationCallExp && ((OperationCallExp) last.getExpression()).getSource() instanceof VariableExp ) {
			OperationCallExp op = (OperationCallExp) last.getExpression();
			VariableExp ve = (VariableExp) op.getSource();
			if ( ve.getReferredVariable().getVarName().equals("thisModule") ) {
				Type t = findImperativeRuleByName(op.getOperationName(), op);
				mm.getTransformationNamespace().attachRule(self.getName(), t, self);				
				return;
			}
		} else if ( last.getExpression() instanceof VariableExp && ((VariableExp) last.getExpression()).getReferredVariable() instanceof RuleVariableDeclaration) {
			Type t = attr.typeOf( ((VariableExp) last.getExpression()).getReferredVariable() );
			mm.getTransformationNamespace().attachRule(self.getName(), t, self);				
			return;
		}
		throw new UnsupportedOperationException(USESerializer.gen(last.getExpression()) + " - " + self.getLocation());		
	}

	
	private Type findImperativeRuleByName(String operationName, OperationCallExp node) {
		Module m = model.getModule();
		for(ModuleElement e : m.getElements()) {
			if ( e instanceof Rule && ((Rule) e).getName().equals(operationName) ) {
				TransformationNamespace tspace = mm.getTransformationNamespace();
				// So far, no types because we do not argument types because there is no overriding...
				Type t = tspace.getOperationType(operationName, new Type[0], node);
				return t;
			}
		}
		
		return errors().signalNoThisModuleOperation(operationName, node);
	}

	@Override
	public void inLazyRule(LazyRule self) {
		// Abstract lazy rules cannot be called, so they are not added to the transformation namespace
		if ( self.isIsAbstract() ) 
			return;
		
		Type t = attr.typeOf(self.getOutPattern().getElements().get(0).getType()); 
		mm.getTransformationNamespace().attachRule(self.getName(), t, self);
	}
	
	@Override
	public void inMatchedRule(MatchedRule self) {
		Metaclass m = (Metaclass) attr.typeOf(self.getInPattern().getElements().get(0));
		if ( self.getOutPattern() == null || self.getOutPattern().getElements().isEmpty() ) {
			errors().signalMatchedRuleWithoutOutputPattern(self);
			return;
		}
		
		Type t = attr.typeOf(self.getOutPattern().getElements().get(0).getType()); 
		
		IClassNamespace ns = (IClassNamespace) m.getMetamodelRef();
		// System.out.println("TopLevelTraversal.inMatchedRule(): " + self.getName());
		ns.attachRule(self.getName(), t, self);
	}

	//  
	// Variables 
	//

	/** 
	 * The evaluation order to the variable declarations of a rule is important!
	 */
	public VisitingActions preMatchedRule(anatlyzer.atlext.ATL.MatchedRule self) { 
		return new VisitingActions("variables" , "inPattern", "outPattern" , "actionBlock"); 
	} 

	public VisitingActions preLazyRule(anatlyzer.atlext.ATL.LazyRule self) { 
		return new VisitingActions("variables" , "inPattern", "outPattern" , "actionBlock" ); 
	} 

	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		assert(!( self instanceof SimpleInPatternElement || self instanceof Iterator));
		
		// This is left for another pass
		if ( self.getVarName().equals("self") ||
			 self.getVarName().equals("thisModule")	) return;

		// TODO: Ver que debe estar partida en dos reglas de eclectic
		if ( self.getType() != null ) {
			attr.linkExprType( attr.typeOf(self.getType()) );
			// type[self] <- type[self.type]
			// TODO: Get the type of the variable declaration
		} else {
			// This is left for another pass
			
			// TODO: Get the type of the initExpression
			// type[self] <- type[self.initExpression]
		}		
	}
	
	@Override
	public void inRuleVariableDeclaration(RuleVariableDeclaration self) {
		if ( self.getType() != null ) {
			attr.linkExprType( attr.typeOf(self.getType()) );			
		}
	}
	
	@Override
	public void inParameter(Parameter self) {
		attr.linkExprType( attr.typeOf(self.getType()) );
	}
	
	@Override
	public void inSimpleInPatternElement(SimpleInPatternElement self) {
		attr.linkExprType( attr.typeOf(self.getType()) );
	}
	
	@Override
	public void inSimpleOutPatternElement(SimpleOutPatternElement self) {
		attr.linkExprType( attr.typeOf(self.getType()) );
	}
	
	@Override
	public void inForEachOutPatternElement(ForEachOutPatternElement self) {
		attr.linkExprType( attr.typeOf(self.getType()) );
	
		// Deal only with the case of iterators defined for ForEachOutPatternElement,
		// which type is given by the enclosing foreach
		// attr.linkExprType(self.getIterator(), attr.typeOf(self.getType()));
		// This was wrong, right? Now in TypeAnalysisTraversal
	}
	
	
	
}
