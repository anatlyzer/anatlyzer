package anatlyzer.atl.editor.quickfix.errors;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoClassFoundInMetamodelQuickFix_FindSimilar extends AbstractAtlQuickfix  {

	private String  closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein(3), new LongestCommonSubstring());
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, NoClassFoundInMetamodel.class) && 
				getClosest((OclModelElement) getProblematicElement(marker)) != null;
	}

	@Override public void resetCache() { 
		closest = null;
		sd      = new StringDistance(new Levenshtein(), new LongestCommonSubstring());
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
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();		
	}

	@Override
	public String getDisplayString() {
		OclModelElement element = (OclModelElement) this.getProblematicElement();
		return "Class "+ element.getName()+ " not found, replace by "+this.getClosest(element);
	}


	@Override public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OclModelElement me = (OclModelElement) this.getProblematicElement();
		String closest = this.getClosest(me);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(me, (expr, trace) -> {
			OclModelElement newOme = OCLFactory.eINSTANCE.createOclModelElement();
			newOme.setName(closest);
			newOme.setModel( (OclModel) ATLCopier.copySingleElement(me.getModel()) );
			
			return newOme;
		});
		
		return qfa;
	}

}
