package anatlyzer.atl.analyser.batch.invariants;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.OclSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.Pair;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.TupleExp;
import anatlyzer.atlext.OCL.TuplePart;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class AllInstancesNode extends AbstractInvariantReplacerNode {

	private MatchedRule rule;

	public AllInstancesNode(MatchedRule rule) {
		super(new ArrayList<>(), rule);
		this.rule = rule;
	}

	@Override
	public void genErrorSlice(ErrorSlice slice) {
		for(Metaclass m : ATLUtils.getAllPatternTypes(rule) ) {
			slice.addExplicitMetaclass(m);
		}

		// TODO: Slice only the required ones!
		if ( rule.getVariables().size() > 0 ) {
			for (RuleVariableDeclaration v : rule.getVariables()) {
				OclSlice.slice(slice, v.getInitExpression());
			}
		}

		if ( rule.getInPattern().getFilter() != null )
			OclSlice.slice(slice, rule.getInPattern().getFilter());
	}
	
	@Override
	public OclExpression genExpr(CSPModel builder) {
		if ( rule.getInPattern().getElements().size() == 1 ) {
			InPatternElement firstIPE = rule.getInPattern().getElements().get(0);
			
			OperationCallExp op = createAllInstances(firstIPE);
	
			if ( rule.getInPattern().getFilter() != null ) {
				IteratorExp select = builder.createIterator(op, "select", firstIPE.getVarName());
				OclExpression body = (OclExpression) new ATLCopier(rule.getInPattern().getFilter()).
					bind(firstIPE, select.getIterators().get(0)).
					copy();
				select.setSource(op);
				select.setBody(body);
				return select;
			}
			return op;
		} else {
			// General case.
			// T1.allInstances()->map(t1 | T2.allInstances()->map(t2 | Tuple { t1 = t1, t2 = t2 }))
			builder.openEmptyScope();
			
			OperationCallExp op = createAllInstances(rule.getInPattern().getElements().get(0));
			IteratorExp innerMap = builder.createIterator(op, "map", rule.getInPattern().getElements().get(0).getVarName());
			IteratorExp externalMap = innerMap;
			
			builder.addToScope(rule.getInPattern().getElements().get(0), innerMap.getIterators().get(0));
			
			// I do this in a separate piece of code because the other one is known to work...
			for (int i = 1; i < rule.getInPattern().getElements().size(); i++) {
				InPatternElement e = rule.getInPattern().getElements().get(i);
				
				op = createAllInstances(e);
				IteratorExp newMap = builder.createIterator(op, "map", e.getVarName());
			
				builder.addToScope(e, newMap.getIterators().get(0));
				
				innerMap.setBody(newMap);
				innerMap = newMap;				
			}
			
			TupleExp t = OCLFactory.eINSTANCE.createTupleExp();
			for (int i = 0; i < rule.getInPattern().getElements().size(); i++) {
				InPatternElement e = rule.getInPattern().getElements().get(i);
				TuplePart part = OCLFactory.eINSTANCE.createTuplePart();
				part.setVarName(e.getVarName());
				part.setInitExpression(builder.createVarRef(builder.getVar(e)));
				
				t.getTuplePart().add(part);
			}
			
			innerMap.setBody(t);
			
			
			IteratorExp result = null;
			if ( rule.getInPattern().getFilter() != null ) {
				IteratorExp select = builder.createIterator(externalMap, "select", "ipe_tuple");

				builder.openEmptyScope();

				Pair<LetExp, LetExp> lets = genMultipleIPEBindings(rule, select.getIterators().get(0), builder);
				LetExp externalLet = lets._1;
				LetExp innerLet = lets._2;

				OclExpression body = builder.gen(rule.getInPattern().getFilter());
				innerLet.setIn_(body);

				builder.closeScope();
				
				// OclExpression body = (OclExpression) new ATLCopier(rule.getInPattern().getFilter()).
				//	bind(firstIPE, select.getIterators().get(0)).
				//	copy();
				select.setBody(externalLet);

				result = select;
			} else {
				result = externalMap;
			}
			
			builder.closeScope();
			return result;
		}
		
	}

	private Pair<LetExp, LetExp> genMultipleIPEBindings(MatchedRule rule, VariableDeclaration tupleVar, CSPModel builder) {
		LetExp innerLet = createLetForIPE(rule.getInPattern().getElements().get(0), tupleVar);
		LetExp externalLet = innerLet;
		builder.addToScope(rule.getInPattern().getElements().get(0), innerLet.getVariable());
		
		for (int i = 1; i < rule.getInPattern().getElements().size(); i++) {
			InPatternElement e = rule.getInPattern().getElements().get(i);
			
			LetExp newLet = createLetForIPE(e, tupleVar);
			builder.addToScope(e, newLet.getVariable());

			innerLet.setIn_(newLet);
			innerLet = newLet;
		}
		
		return new Pair<LetExp, LetExp>(externalLet, innerLet);
	}
	
	public LetExp createLetForIPE(InPatternElement e, VariableDeclaration tupleVar) {
		LetExp innerLet = OCLFactory.eINSTANCE.createLetExp();
		VariableDeclaration vd = OCLFactory.eINSTANCE.createVariableDeclaration();
		vd.setVarName(e.getVarName());
		vd.setType((OclType) ATLCopier.copySingleElement(e.getType()));
		
		NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
		nav.setName(e.getVarName());
		VariableExp varExp = OCLFactory.eINSTANCE.createVariableExp();
		varExp.setReferredVariable(tupleVar);
		nav.setSource(varExp);
		
		vd.setInitExpression(nav);
		innerLet.setVariable(vd);
		return innerLet;
	}

	protected OperationCallExp createAllInstances(InPatternElement e) {
		OclModelElement sourceType = (OclModelElement) ATLCopier.copySingleElement(e.getType());
		
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("allInstances");
		op.setSource(sourceType);
		
		return op;
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }
	
	@Override
	public Pair<LetExp, LetExp> genIteratorBindings(CSPModel builder, Iterator it) {
		if ( rule.getInPattern().getElements().size() == 1 ) {
			builder.addToScope(rule.getInPattern().getElements().get(0), it);
		} else {
			return genMultipleIPEBindings(rule, it, builder);			
		}
		return null;
	}
	
}
