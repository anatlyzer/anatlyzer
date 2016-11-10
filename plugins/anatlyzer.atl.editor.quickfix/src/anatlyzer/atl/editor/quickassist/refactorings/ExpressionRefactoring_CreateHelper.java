package anatlyzer.atl.editor.quickassist.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.editor.quickassist.AbstractAtlQuickAssist;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class ExpressionRefactoring_CreateHelper extends AbstractAtlQuickAssist {

	@Override
	public boolean isApplicable() {
		return getElement() instanceof OclExpression && checkExpressionPosition() != null;
	}

	private OclExpression checkExpressionPosition() {
		Binding b = ATLUtils.getContainer(getElement(), Binding.class);
		if ( b != null && ATLUtils.findStartingVarExp(b.getValue()) != null) {
			return b.getValue();
		}
		return null;
	}

	@Override
	public String getDisplayString() {
		return "Factorize expression into helper";
	}

	@Override
	public void resetCache() { }

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		OclExpression exp = checkExpressionPosition();

		VariableExp selfVar = ATLUtils.findStartingVarExp(exp);
		VariableDeclaration selfVd = selfVar.getReferredVariable();
		
		List<VariableExp> refs = getExternalVariableReferences(exp);
		
		HashMap<VariableDeclaration, List<VariableExp>> uniqueVars = new HashMap<VariableDeclaration, List<VariableExp>>();
		List<VariableExp> arguments = new ArrayList<VariableExp>();
		for (VariableExp ve : refs) {
			if ( ! uniqueVars.containsKey(ve.getReferredVariable())) {
				uniqueVars.put(ve.getReferredVariable(), new ArrayList<VariableExp>());
				
				// Self is implicit, not passed
				if ( ve.getReferredVariable() != selfVar.getReferredVariable() )
					arguments.add(ve);
			}
				
			uniqueVars.get(ve.getReferredVariable()).add(ve);
		}
		
		String opName = inferName("newOperation", exp);
		
		ModuleElement anchor = ASTUtils.findHelperPosition(exp, opName);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(exp, (e_, trace) -> {
			VariableExp startVar = OCLFactory.eINSTANCE.createVariableExp();
			startVar.setReferredVariable(selfVd);
			
			OperationCallExp opCall = OCLFactory.eINSTANCE.createOperationCallExp();
			opCall.setOperationName(opName);
			opCall.setSource(startVar);
			
			for (VariableExp arg : arguments) {
				VariableExp argExp = OCLFactory.eINSTANCE.createVariableExp();
				argExp.setReferredVariable(arg.getReferredVariable());
				
				opCall.getArguments().add(argExp);
			}
			
			return opCall;
		});
		
		qfa.insertAfter(anchor, () -> { 
			ContextHelper helper = ASTUtils.buildNewContextOperation(opName, 
					selfVar.getInferredType(),
					exp.getInferredType(),				
					arguments);

			Operation op = (Operation) helper.getDefinition().getFeature();
			
			ATLCopier atlCopier = new ATLCopier(exp);
			
			for (int i = 0; i < op.getParameters().size(); i++) {
				Parameter parameter = op.getParameters().get(i);
				VariableExp actualParameter = arguments.get(i);
				
				atlCopier.bind(actualParameter.getReferredVariable(), parameter);
			}
			
			VariableDeclaration newSelfVarDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
			newSelfVarDcl.setVarName("self");
			atlCopier.bind(selfVar.getReferredVariable(), newSelfVarDcl);
			
			op.setBody((OclExpression) atlCopier.copy());
			
			return helper;
		});

		
		
//		
//		
//		
//		MatchedRule r = (MatchedRule) getElement();
//		qfa.replace(r, (original, trace) -> {
//			trace.preserve(original.getInPattern());
//			trace.preserve(original.getOutPattern());
//			trace.preserve(original.getActionBlock());
//			trace.preserve(original.getVariables());
//			
//			MatchedRule r2 = (MatchedRule) ATLCopier.copySingleElement(r);
//			r2.setName(computeRuleName());
//			
//			return r2;
//		});
		
		return qfa;
	}

	private String inferName(String defaultName, OclExpression exp) {
		boolean hasSelect = false;
		Metaclass oclIsKindOf = null;
		
		if ( exp instanceof IteratorExp && ((IteratorExp) exp).getName().equals("select"))
			hasSelect = true;
	
		TreeIterator<EObject> it = exp.eAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( obj instanceof IteratorExp && ((IteratorExp) obj).getName().equals("select"))
				hasSelect = true;
		
			if ( obj instanceof OperationCallExp && ((OperationCallExp) obj).getOperationName().equals("oclIsKindOf") ) {
				EList<OclExpression> args = ((OperationCallExp) obj).getArguments();
				if ( args.size() > 0 && args.get(0) instanceof OclModelElement ) {
					oclIsKindOf = (Metaclass) args.get(0).getInferredType();
				}
			}
		}
		
		if ( hasSelect && oclIsKindOf != null ) {
			return "filter" + oclIsKindOf.getName();
		}
		
		return defaultName;
	}

	private List<VariableExp> getExternalVariableReferences(OclExpression exp) {
		List<VariableExp> allVars = ATLUtils.findAllVarExp(exp);
		TreeIterator<EObject> it = exp.eAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();
			if ( allVars.contains(obj) ) {
				allVars.remove(obj);
			}
		}
		
		return allVars;
	}


}
