package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;

import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;

/**
 * This quickfix proposes enclosing the problem in an "if" expression checking the
 * expression "obj.oclIsKindOf(PossibleSubtype)". It uses the most inner code block.
 * 
 * The quickfix tries to generate a reasonable default for the false branch.
 * 
 * @qfxName  Surround with 'if' expression (Add surrounding if to expression)
 * @qfxError {@link anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype}
 * 
 * @author jesusc
 */
public class FeatureFoundInSubtypeQuickfix_AddIfToExpression extends OperationFoundInSubtypeQuickfix_AddIfToExpression {

	@Override public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureFoundInSubtype.class);
	}
	
}