package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * In ATL types are not checked in let variable declarations and
 * return types. This quick fix aligns declared types with inferred
 * types when they do not match.
 * 
 * Example:
 * <pre>
 * 		let a : Integer = 'string'   =>  let a : String => 'string'
 * </pre>
 * 
 * @qfxName  Change declared type for inferred type
 * @qfxError {@link anatlyzer.atl.errors.atl_error.IncoherentVariableDeclaration}
 * 
 * @author jesusc
 *
 */
public class IncoherentDeclaredTypeQuickfix_AssignInferredType extends AbstractAtlQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, IncoherentVariableDeclaration.class);		
	}

	@Override public void resetCache() { }
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		LocatedElement elem = (LocatedElement) getProblematicElement();
		if ( elem instanceof VariableDeclaration ) {
			VariableDeclaration vd = (VariableDeclaration) elem;
			
			QuickfixApplication qfa = new QuickfixApplication(this);
			qfa.replace(vd.getType(), (original, trace) -> {
				Type t = vd.getInitExpression().getInferredType();
				return ATLUtils.getOclType(t);
			});			
			return qfa;
		} else {
			throw new RuntimeException("TODO: " + elem + " not handled.");
		}
		
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();	
	}

	@Override
	public String getDisplayString() {
		return "Replace declared with inferred type";
	}


}
