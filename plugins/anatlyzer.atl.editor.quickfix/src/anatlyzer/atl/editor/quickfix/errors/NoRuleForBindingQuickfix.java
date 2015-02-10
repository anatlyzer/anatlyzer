package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.BindingWithoutRule;
import anatlyzer.atl.errors.atl_error.FlattenOverNonNestedCollection;
import anatlyzer.atl.errors.atl_error.ModelElement;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class NoRuleForBindingQuickfix extends AbstractAtlQuickfix {

	public NoRuleForBindingQuickfix() {
	}

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingWithoutRule.class);
	}

	@Override
	public void apply(IDocument document) {

		try {
			BindingWithoutRule p = (BindingWithoutRule) getProblem();
			//p.getRightType()
			//p.get
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

			/*
			int last = document.getLineOffset(document.getNumberOfLines() - 1);
			
			
			document.replace(last, 0, newRule);			
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
