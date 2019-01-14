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
import anatlyzer.atl.simplifier.IOclSimplifier;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
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
import anatlyzer.ui.util.ExtensionPointUtils;

public class ExpressionRefactoring_Simplify extends AbstractAtlQuickAssist {

	@Override
	public boolean isApplicable() {
		return getElement() instanceof OclExpression;
	}

	@Override
	public String getDisplayString() {
		return "Attempt to simplify expression";
	}

	@Override
	public void resetCache() { }

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		OclExpression exp = (OclExpression) getElement();

		IOclSimplifier simp = ExtensionPointUtils.getOclSimplifier();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(exp, (e_, trace) -> {
			// TODO: What about preservation
			EObject newExp = simp.simplify(getAnalysisResult().getAnalyser(), exp);
			System.out.println( ATLSerializer.serialize(newExp) );
			return newExp;
		});
		
		return qfa;
	}

}
