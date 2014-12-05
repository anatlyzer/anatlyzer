package anatlyzer.atl.analyser.recovery;

import anatlyzer.atl.analyser.AnalyserContext;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.ErrorModel;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OperationCallExp;


public class RecoverOperationNotFound implements IRecoveryAction {

	private LocatedElement	node;

	public RecoverOperationNotFound(IClassNamespace classNamespace, String operationName, LocatedElement node) {
		this.node = node;
	}

	@Override
	public Type recover(ErrorModel m, LocalProblem p) {
		if ( ! ( node instanceof OperationCallExp ))
			return null;
		
		// TODO: Pass the actual identified error to the error type
		return AnalyserContext.getTypingModel().newTypeErrorType(p);
		
		/*
		OperationCallExp opcall = (OperationCallExp) node;
		
		if ( opcall.container_() instanceof Binding ) {
			
		}
		
		// TODO Auto-generated method stub
		return null;
		*/
	}

}
