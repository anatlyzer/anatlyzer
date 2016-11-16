package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.LongestCommonSubstring;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * Given a feature that cannot be found in the meta-model this quick fix
 * searches for a similar feature using distance metrics like Levenshtein and LongestCommonSubstring.
 * 
 * This problem may arise both in the source part (OCL expressions) or
 * in the target part (in the left part of a binding).
 * 
 * TODO: This does not consider BindingStat yet
 * 
 * @qfxName  Choose existing feature
 * @qfxError {@link anatlyzer.atl.errors.atl_error.FeatureNotFound}
 * 
 * @author jdelara, jesusc
 */
public class FeatureNotFoundQuickFix_ChooseExisting extends AbstractAtlQuickfix  {

	private List<String> attrs = new ArrayList<String>();
	private String closest = null;
	//private StringDistance sd = new StringDistance(new Levenshtein());	// use Levenshtein distance by default
	private StringDistance sd = new StringDistance(new Levenshtein(3), new LongestCommonSubstring());	// use Levenshtein distance by default
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureNotFound.class) && getClosest(marker) != null;
	}

	@Override public void resetCache() {
		attrs = new ArrayList<String>();
		closest = null;
		//sd = new StringDistance(new Levenshtein());
		sd = new StringDistance(new LongestCommonSubstring());
	}
	
	private boolean isSourceFeature(IMarker marker) {
		return getProblematicElement(marker) instanceof NavigationOrAttributeCallExp;
	}
	
	private String getFeatureName(IMarker marker) {
		if ( getProblematicElement(marker) instanceof NavigationOrAttributeCallExp ) {
			return ((NavigationOrAttributeCallExp) getProblematicElement(marker)).getName();
		} else if ( getProblematicElement(marker) instanceof Binding ) {
			return ((Binding) getProblematicElement(marker)).getPropertyName();
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	private Type getFeatureType(IMarker marker){
		if ( getProblematicElement(marker) instanceof NavigationOrAttributeCallExp ) {
			return ((NavigationOrAttributeCallExp) getProblematicElement(marker)).getSource().getInferredType();
		} else if ( getProblematicElement(marker) instanceof Binding ) {
			return ((Binding) getProblematicElement(marker)).getOutPatternElement().getInferredType();
		} else {
			throw new UnsupportedOperationException();
		}		
	}
	
	private String getClosest(IMarker marker) {
		if (this.closest != null) return this.closest;
		
		Type t = getFeatureType(marker);
		String noFeature;
		try {
			noFeature = ((FeatureNotFound) getProblem(marker)).getFeatureName();
		} catch (CoreException e) {
			return null;
		}
		
		if (t instanceof Metaclass) {
			Metaclass type = (Metaclass) t;
			EClass eclass = type.getKlass();
			this.attrs = eclass.getEAllStructuralFeatures().stream().
							map( att -> att.getName() ).							
							collect(Collectors.toList());
			
			// Consider also attributes if it is in the source
			if ( isSourceFeature(marker) ) {
				List<Helper> allAttrHelpers = ATLUtils.getAllAttributes(getATLModel(marker));
				attrs.addAll(allAttrHelpers.stream().map(h -> ATLUtils.getHelperName(h)).collect(Collectors.toList()));
			}
			
			// Could be checked that the selected names are also type
			// compatible with the rest of the expression
			
			System.out.println("possible attrs: "+this.attrs);
						
			if ( this.attrs.size() == 0 ) {
				this.closest = null; 
			} else {
				this.closest = this.sd.closest(noFeature, this.attrs);
			}
			
			return this.closest;
		}
		this.closest = null; // 
		return this.closest;	// TODO: Do something here		
	}
	

	@Override
	public QuickfixApplication getQuickfixApplication() {
		QuickfixApplication qfa = new QuickfixApplication(this);

		String newFeatureName = this.getClosest(this.marker);

		if ( getProblematicElement() instanceof NavigationOrAttributeCallExp) {		
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) getProblematicElement();
			qfa.replace(nav, (original, trace) -> {
				trace.preserve(original.getSource());
				
				NavigationOrAttributeCallExp op = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
				op.setSource(original.getSource());
				op.setName(newFeatureName);
				return op;
			});
		} else if ( getProblematicElement() instanceof Binding ) {
			Binding b = (Binding) getProblematicElement();
			qfa.replace(b, (original, trace) -> {
				trace.preserve(original.getValue());
				
				Binding newBinding = ATLFactory.eINSTANCE.createBinding();
				newBinding.setPropertyName(newFeatureName);
				newBinding.setValue(original.getValue());
				return newBinding;
			});
			
		} else {
			throw new UnsupportedOperationException();
		}
		
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
		return "Feature "+this.getFeatureName(this.marker)+" not found, replace with "+this.getClosest(this.marker);
	}

	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
}
