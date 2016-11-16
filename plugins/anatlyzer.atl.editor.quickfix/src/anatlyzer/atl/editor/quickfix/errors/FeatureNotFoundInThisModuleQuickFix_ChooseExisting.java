package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.stringDistance.*;
import anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;

/**
 * This quick fix proposes a feature name close to the 
 * feature written by the user (which does not exist) using the Levenshtein distance.
 * 
 * @qfxName  Propose an existing feature name
 * @qfxError {@link anatlyzer.atl.errors.atl_error.AttributeNotFoundInThisModule}
 * 
 * @author jesusc
 *
 */
public class FeatureNotFoundInThisModuleQuickFix_ChooseExisting extends AbstractAtlQuickfix  {

	private List<String> attrs = new ArrayList<String>();
	private String closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein(), new LongestCommonSubstring());	// use Levenshtein distance by default
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, AttributeNotFoundInThisModule.class) && getClosest(marker) != null;
	}

	@Override public void resetCache() {
		attrs = new ArrayList<String>();
		closest = null;
		sd = new StringDistance(new Levenshtein(), new LongestCommonSubstring());
	}
	
	private NavigationOrAttributeCallExp getFeature(IMarker marker) {
		try {
			AttributeNotFoundInThisModule p = (AttributeNotFoundInThisModule) getProblem(marker);		
			// p.getOperationName() is null at this point 
			return (NavigationOrAttributeCallExp) p.getElement();
		} catch (CoreException e) {
			return null;			
		}
	}
	
	private String getClosest(IMarker marker) {
		if (this.closest != null) return this.closest;
		
		NavigationOrAttributeCallExp exp = this.getFeature(marker);
		
		List<Helper> allAttrHelpers = ATLUtils.getAllAttributes(getATLModel(marker));
		attrs.addAll(allAttrHelpers.stream().
					filter(h -> h instanceof StaticHelper).	
					map(h -> ATLUtils.getHelperName(h)).collect(Collectors.toList()));
						
		if ( this.attrs.size() == 0 ) {
			this.closest = null; 
		} else {
			this.closest = this.sd.closest(exp.getName(), this.attrs);
		}
		
		return this.closest;

	}
	

	@Override
	public QuickfixApplication getQuickfixApplication() {
		QuickfixApplication qfa = new QuickfixApplication(this);
		NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
		
		String newFeatureName = this.getClosest(this.marker);
		
		qfa.replace(nav, (original, trace) -> {
			trace.preserve(original.getSource());
			
			NavigationOrAttributeCallExp op = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
			op.setSource(original.getSource());
			op.setName(newFeatureName);
			return op;
		});
		
		return qfa;
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();
		new InDocumentSerializer(qfa, document).serialize();	
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Replace with "+this.getClosest(this.marker);
	}

	@Override
	public String getDisplayString() {
		return "Feature "+this.getFeature(this.marker).getName()+" not found, replace with "+this.getClosest(this.marker);
	}

	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
	
}
