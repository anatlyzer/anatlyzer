package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;

public class FeatureNotFoundQuickFix_ChooseExisting extends AbstractAtlQuickfix  {

	private List<String> attrs = new ArrayList<String>();
	private String closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein());	// use Levenshtein distance by default
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureNotFound.class) && getClosest(marker) != null;
	}

	@Override public void resetCache() {
		attrs = new ArrayList<String>();
		closest = null;
		sd = new StringDistance(new Levenshtein());
	}
	
	private NavigationOrAttributeCallExp getFeature(IMarker marker) {
		try {
			FeatureNotFound p = (FeatureNotFound) getProblem(marker);		
			// p.getOperationName() is null at this point 
			return (NavigationOrAttributeCallExp)p.getElement();
		} catch (CoreException e) {
			return null;			
		}
	}
	
	private String getClosest(IMarker marker) {
		if (this.closest != null) return this.closest;
		
		NavigationOrAttributeCallExp exp = this.getFeature(marker);
		OclExpression e = exp.getSource();
		System.out.println("Source: "+e);
		
		if (e.getInferredType() instanceof Metaclass) {
			Metaclass type = (Metaclass) e.getInferredType();
			EClass eclass = type.getKlass();
			this.attrs = eclass.getEAllStructuralFeatures().stream().
							map( att -> att.getName() ).							
							collect(Collectors.toList());
			
			// Consider also attributes
			List<Helper> allAttrHelpers = ATLUtils.getAllAttributes(getATLModel(marker));
			attrs.addAll(allAttrHelpers.stream().map(h -> ATLUtils.getHelperName(h)).collect(Collectors.toList()));
			
			// Could be checked that the selected names are also type
			// compatible with the rest of the expression
			
			System.out.println("possible attrs: "+this.attrs);
						
			if ( this.attrs.size() == 0 ) {
				this.closest = null; 
			} else {
				this.closest = this.sd.closest(exp.getName(), this.attrs);
			}
			
			return this.closest;
		}
		this.closest = null; // 
		return this.closest;	// TODO: Do something here		
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
		return "Feature "+this.getFeature(this.marker).getName()+" not found, replace with "+this.getClosest(this.marker);
	}

	@Override
	public String getDisplayString() {
		return "Feature "+this.getFeature(this.marker).getName()+" not found, replace with "+this.getClosest(this.marker);
	}

	}
