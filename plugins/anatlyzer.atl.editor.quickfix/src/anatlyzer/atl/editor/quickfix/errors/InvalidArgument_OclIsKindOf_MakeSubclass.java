package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.InvalidArgument;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.OperationCallExp;

public class InvalidArgument_OclIsKindOf_MakeSubclass extends AbstractMetamodelChangeQuickfix {
	private Metaclass sourceType;
	private Metaclass result;

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, InvalidArgument.class)
				&& checkProblemIsKindOf((OperationCallExp) getProblematicElement(marker));
	}
	
	private boolean checkProblemIsKindOf(OperationCallExp op) {
		if ( ! op.getOperationName().equals("oclIsKindOf") ) {
			return false;
		}
		if ( op.getArguments().size() > 0 && op.getArguments().get(0).getInferredType() instanceof Metaclass ) {
			if ( op.getSource().getInferredType() instanceof Metaclass ) {
				result = (Metaclass) op.getArguments().get(0).getInferredType();
				sourceType = (Metaclass) op.getSource().getInferredType();
				return true;
			}
		}
		return false;
	}

	@Override
	public void resetCache() {
		this.result = null;
		this.sourceType = null;
	}
	

	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.mmModify(result.getKlass(), getChangedMetamodel(), (f) -> {
			Metaclass m = sourceType;
			result.getKlass().getESuperTypes().add(m.getKlass());
		});

		return qfa;
	}

	@Override
	public String getChangedMetamodel() {
		return this.result.getModel().getName();
	}
	
	@Override
	public String getDisplayString() {
		return "Make " + result.getName() + " a subclass of " + sourceType.getName();
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.make_subclass.createImage();
	}

}
