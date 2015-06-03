package anatlyzer.evaluation.instrumentation;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.SequenceExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class PossiblyUnresolvedBindingInstrumenter {

	private HashMap<Binding, String> bindingToChecker = new HashMap<Binding, String>();
	
	private StaticHelper createBindingChecker(Binding b) {
		String opName = "checkBinding" + "_" + ATLUtils.getRule(b).getName() + "_" + b.getOutPatternElement().getVarName() + "_" + b.getOutPatternElement().getBindings().indexOf(b);
		bindingToChecker.put(b, opName);
		
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(opName);
		operation.setReturnType(OCLFactory.eINSTANCE.createBooleanType());

		
		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setFeature (operation);

		Parameter parameter = OCLFactory.eINSTANCE.createParameter();
		parameter.setType(OCLFactory.eINSTANCE.createOclAnyType());
		parameter.setVarName("_src_");
		operation.getParameters().add(parameter);
					
		List<OclExpression> conditions = b.getResolvedBy().stream().map(r -> {
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			op.setOperationName(getCheckRuleName(r.getRule()));			
			VariableExp varexp = OCLFactory.eINSTANCE.createVariableExp();
			VariableDeclaration tm = OCLFactory.eINSTANCE.createVariableDeclaration();
			tm.setVarName("thisModule");
			varexp.setReferredVariable(tm);
			op.setSource(varexp);
			
			VariableExp varParam = OCLFactory.eINSTANCE.createVariableExp();
			varParam.setReferredVariable(parameter);
			op.getArguments().add(varParam);
			return op;
		}).collect(Collectors.toList());
		
		
		StringExp str = OCLFactory.eINSTANCE.createStringExp();
		str.setStringSymbol("Fail");
		OperationCallExp fail = OCLFactory.eINSTANCE.createOperationCallExp();
		fail.setSource(str);
		fail.setOperationName("fail_");
		
		OclExpression body = null;
		if ( conditions.isEmpty() ) {		
			body = fail;
		} else {
			IfExp firstIf = OCLFactory.eINSTANCE.createIfExp();
			firstIf.setCondition(conditions.get(0));
			BooleanExp isTrue1 = OCLFactory.eINSTANCE.createBooleanExp();
			isTrue1.setBooleanSymbol(true);
			firstIf.setThenExpression(isTrue1);
			firstIf.setElseExpression(fail);

			body = firstIf;
			for (int i = 1; i < conditions.size(); i++) {
				OclExpression c = conditions.get(i);
				IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
				ifexp.setCondition(c);
				BooleanExp isTrue = OCLFactory.eINSTANCE.createBooleanExp();
				isTrue.setBooleanSymbol(true);
				ifexp.setThenExpression(isTrue);
				ifexp.setElseExpression(body);
				body = ifexp;
			}
		}
		
		operation.setBody(body);
		
		StaticHelper helper = ATLFactory.eINSTANCE.createStaticHelper();
		helper.setDefinition(def);
		return helper;
	}
	
	private StaticHelper createRuleChecker(MatchedRule r) {
		Operation operation = OCLFactory.eINSTANCE.createOperation();
		operation.setName(getCheckRuleName(r));
		operation.setReturnType(OCLFactory.eINSTANCE.createBooleanType());

		OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		def.setFeature (operation);

		Parameter parameter = OCLFactory.eINSTANCE.createParameter();
		parameter.setType(OCLFactory.eINSTANCE.createOclAnyType());
		parameter.setVarName("_src_");
		operation.getParameters().add(parameter);
					
		operation.setBody( generateCheckBody(r, parameter) );

		StaticHelper helper = ATLFactory.eINSTANCE.createStaticHelper();
		helper.setDefinition(def);
		return helper;
	}

	protected String getCheckRuleName(MatchedRule r) {
		return "checkRule_" + r.getName();
	}
	
	private OclExpression generateCheckBody(MatchedRule r, VariableDeclaration src) {
		InPatternElement element = r.getInPattern().getElements().get(0);
		Metaclass m = (Metaclass) element.getInferredType();
			
		// Generate if p.oclIsKindOf(T) then <filter-or-true> else <next>
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName("oclIsKindOf");
		OclModelElement me = OCLFactory.eINSTANCE.createOclModelElement();
		me.setName(m.getName());
		OclModel mo = OCLFactory.eINSTANCE.createOclModel();
		mo.setName(m.getModel().getName());
		me.setModel(mo);
		op.getArguments().add(me);
		
		VariableExp varexp = OCLFactory.eINSTANCE.createVariableExp();
		varexp.setReferredVariable(src);
		op.setSource(varexp);
		
		if ( r.getInPattern().getFilter() != null ) {
			IfExp ifExp = OCLFactory.eINSTANCE.createIfExp();
			ifExp.setCondition(op);
			OclExpression filterCond = (OclExpression) new ATLCopier(r.getInPattern().getFilter()).
				bind(element, src).
				copy();
			ifExp.setThenExpression(filterCond);
			ifExp.setElseExpression(OCLFactory.eINSTANCE.createBooleanExp());
		
			return ifExp;
		} else {
			return op;
		}
	}

	
	public void instrumentModel(ATLModel model) {
		List<Binding> allBindings = model.allObjectsOf(Binding.class).stream().
				filter(b -> isApplicable(b) ).collect(Collectors.toList());
		for (Binding binding : allBindings) {
			StaticHelper checker1 = createBindingChecker(binding);
			model.getModule().getElements().add(checker1);
		}

		Stream<MatchedRule> rules = allBindings.stream().flatMap(b -> b.getResolvedBy().stream().map(r -> r.getRule()));
		rules.distinct().forEach(r -> {
			StaticHelper checker2 = createRuleChecker(r);
			model.getModule().getElements().add(checker2);			
		});
	
		for (Binding binding : allBindings) {
			modifyBinding(binding);
		}		
	}

	public void instrumentBinding(ATLModel model, Binding binding) {
		if ( isApplicable(binding) ) {		
			StaticHelper checker1 = createBindingChecker(binding);
			model.getModule().getElements().add(checker1);
			binding.getResolvedBy().stream().map(r -> r.getRule()).forEach(r -> {
				StaticHelper checker2 = createRuleChecker(r);
				model.getModule().getElements().add(checker2);	
			});
			modifyBinding(binding);
		}
	}
	
	private boolean isApplicable(Binding b) {
		return ATLUtils.isReferenceBinding(b);
	}

	private void modifyBinding(Binding binding) {
		SequenceExp seqexp = OCLFactory.eINSTANCE.createSequenceExp();
		seqexp.getElements().add(binding.getValue());
		CollectionOperationCallExp flatten = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		flatten.setOperationName("flatten");
		flatten.setSource(seqexp);
		
		IteratorExp select = OCLFactory.eINSTANCE.createIteratorExp();
		select.setName("select");
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName("src_");
		select.getIterators().add(it);
		select.setSource(flatten);
		
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName(bindingToChecker.get(binding));			
		VariableExp varexp = OCLFactory.eINSTANCE.createVariableExp();
		VariableDeclaration tm = OCLFactory.eINSTANCE.createVariableDeclaration();
		tm.setVarName("thisModule");
		varexp.setReferredVariable(tm);
		op.setSource(varexp);
		
		VariableExp varParam = OCLFactory.eINSTANCE.createVariableExp();
		varParam.setReferredVariable(it);
		op.getArguments().add(varParam);

		select.setBody(op);
		
		binding.setValue(select);
	}
	
}
