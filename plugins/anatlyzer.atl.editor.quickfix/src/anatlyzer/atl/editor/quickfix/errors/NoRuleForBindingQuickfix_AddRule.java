package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.ModelElement;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixScope;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.OperationCallExp;

public class NoRuleForBindingQuickfix_AddRule extends AbstractAtlQuickfix {

	public NoRuleForBindingQuickfix_AddRule() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingWithoutRule.class);
	}

	@Override
	public void apply(IDocument document) {

		try {
			BindingWithoutRule p = (BindingWithoutRule) getProblem();
			Binding b = (Binding) p.getElement();
			Rule r = b.getOutPatternElement().getOutPattern().getRule();
			
			Unit u = ATLUtils.getContainer(r, Unit.class);
			
			Metaclass tgt = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
			List<Metaclass> sources = ATLUtils.getUnderlyingBindingRightMetaclasses(b);
			if ( sources.size() == 1 ) {
				Metaclass src = sources.get(0);
				
				// Not sure if in this case it makes sense to use b as an anchor
				QuickfixScope<Unit> qs = new QuickfixScope<Unit>(u);
				qs.insertAfter(r, () -> {
					MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
					String ruleName = src.getKlass().getName() + "2" + tgt.getKlass().getName();
					mr.setName(ruleName);
					
					ASTUtils.completeRule(mr, src, tgt);

					return mr;
				});
				
				new InDocumentSerializer(qs, document).serialize();
			} else {
				// For union types, add several rules
				document.replace(getEnd(getElementOffset(r, document)) + 1, 0, "\n-- TODO: Add several rules\n");							
			}
			
			
			//p.getRightType()
			//p.get
			
			
			/*
			ModelElement tgt = p.getLeft();
			ModelElement src = p.getRight();
			
			String newRule = "\nrule " + src.getKlass().getName() + "2" + tgt.getKlass().getName() + "{\n" +
					"\tfrom src : " + src.getMetamodelName() + "!" + src.getKlass().getName() + "\n" +
					"\t  to tgt : " + tgt.getMetamodelName() + "!" + tgt.getKlass().getName() + "(\n" +
					"\t  )\n}\n";					
			
			Binding b = (Binding) p.getElement();
			Rule r = b.getOutPatternElement().getOutPattern().getRule();
			
			
			int[] sourceOffset = getElementOffset(r, document);
			int sourceOffsetStart = sourceOffset[0];
			int sourceOffsetEnd = sourceOffset[1];

			// Setting length to 0 means "insert", that is, replacing 0 characters
			document.replace(sourceOffsetEnd + 1, 0, newRule);			
			*/
		} catch (CoreException e) {
			throw new RuntimeException(e);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Add a new rule to resolve the binding";
	}

	@Override
	public String getDisplayString() {
		return "Add new rule";
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}


}
