package anatlyzer.atl.editor.quickfix.errors;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoClassFoundInMetamodelQuickFix_FindSimilar extends AbstractAtlQuickfix  {

	private IMarker marker;
	private String  closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein());
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, NoClassFoundInMetamodel.class);
	}

	private OclModelElement getElement() {
		try {
			NoClassFoundInMetamodel p = (NoClassFoundInMetamodel) getProblem();
		
			// p.getOperationName() is null at this point 
			return (OclModelElement)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	private List<String> getPossibleNames (OclModel model) {
		System.out.println("Meta-models: "+this.getAnalyserData(this.marker).getNamespace().getMetamodels());
		
		for (MetamodelNamespace mns : this.getAnalyserData(this.marker).getNamespace().getMetamodels()) {
			if (mns.getName().equals(model.getName())) {
				List<String> possible = mns.getAllClasses().stream().map(EClass::getName).collect(Collectors.toList());
				
				System.out.println("Possible names: "+possible);
				return possible;
			}
		}
		return Collections.emptyList();
	}
	
	private String getClosest(OclModelElement me) {
		if (this.closest != null) return this.closest;
		List<String> names = this.getPossibleNames(me.getModel());
		this.closest = this.sd.closest(me.getName(), names);
		return this.closest;
	}
	
	@Override
	public void apply(IDocument document) {
		OclModelElement me = this.getElement();
		
		System.out.println("Class not found: "+me.getName()+", replace by "+this.getClosest(me));
		
		int[] sourceOffset = getElementOffset(me, document);

		try {
			int offsetEnd = getProblemEndOffset();
		
			String rest = document.get(sourceOffset[0], offsetEnd - sourceOffset[0]);
			
			document.replace(sourceOffset[0], rest.length(), me.getModel().getName()+"!"+this.getClosest(me));
		} catch (CoreException | BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Class "+this.getElement().getName()+" not found, replace by "+this.getClosest(this.getElement());
	}

	@Override
	public String getDisplayString() {
		return "Class "+this.getElement().getName()+" not found, replace by "+this.getClosest(this.getElement());
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}

}
