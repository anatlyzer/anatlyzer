package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;

public class FeatureNotFoundQuickFix extends AbstractAtlQuickfix  {

	private List<String> attrs = new ArrayList<String>();
	private String closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein());	// use Levenshtein distance by default
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, FeatureNotFound.class);
	}

	private NavigationOrAttributeCallExp getFeature() {
		try {
			FeatureNotFound p = (FeatureNotFound) getProblem();
		
			// p.getOperationName() is null at this point 
			return (NavigationOrAttributeCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	private String getClosest() {
		if (this.closest != null) return this.closest;
		
		NavigationOrAttributeCallExp exp = this.getFeature();
		OclExpression e = exp.getSource();
		System.out.println("Source: "+e);
		
		if (e.getInferredType() instanceof Metaclass) {
			Metaclass type = (Metaclass) e.getInferredType();
			EClass eclass = type.getKlass();
			this.attrs = eclass.getEAllStructuralFeatures().stream().
							map( att -> att.getName() ).							
							collect(Collectors.toList());
			
			// Consider also attributes
			List<Helper> allAttrHelpers = ATLUtils.getAllAttributes(getATLModel());
			attrs.addAll(allAttrHelpers.stream().map(h -> ATLUtils.getHelperName(h)).collect(Collectors.toList()));
			
			// Could be checked that the selected names are also type
			// compatible with the rest of the expression
			
			System.out.println("possible attrs: "+this.attrs);
						
			this.closest = this.sd.closest(exp.getName(), this.attrs);
			
			return this.closest;
		}
		this.closest = exp.getName();
		return this.closest;	// TODO: Do something here		
	}
	
	@Override
	public void apply(IDocument document) {
		NavigationOrAttributeCallExp exp = this.getFeature();
		System.out.println("Feature not found: "+exp.getName());		
		System.out.println("Closest: "+this.getClosest());
		
		int[] sourceOffset = getElementOffset(exp.getSource(), document);

		int sourceOffsetEnd = sourceOffset[1];
		
		int offsetEnd;
		try {
			offsetEnd = getProblemEndOffset();
		
			String rest = document.get(sourceOffsetEnd, offsetEnd - sourceOffsetEnd);
			
			document.replace(sourceOffsetEnd, rest.length(), "."+this.getClosest());
		} catch (CoreException | BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Feature "+this.getFeature().getName()+" not found, replace with "+this.getClosest();
	}

	@Override
	public String getDisplayString() {
		return "Feature "+this.getFeature().getName()+" not found, replace with "+this.getClosest();
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

	@Override
	public QuickfixApplication getQuickfixApplication() {
		throw new UnsupportedOperationException("To be implemented");
	}

}
