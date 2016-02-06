package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.stringDistance.Levenshtein;
import anatlyzer.atl.editor.quickfix.util.stringDistance.StringDistance;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.NoEnumLiteral;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoEnumLiteral_FindSimilar extends AbstractAtlQuickfix  {

	private String  closest = null;
	private StringDistance sd = new StringDistance(new Levenshtein());
	
	@Override
	public boolean isApplicable(IMarker marker) {
		setErrorMarker(marker);
		return checkProblemType(marker, NoEnumLiteral.class) && getPossibleNames().size() > 0;
	}
	
	@Override public void resetCache() { 
		closest = null;
		sd = new StringDistance(new Levenshtein());
	}
	
	private Set<String> getPossibleNames() {		
		HashSet<String> literals = new HashSet<String>();
		for (MetamodelNamespace mns : this.getAnalyserData(this.marker).getNamespace().getMetamodels()) {
			mns.getAllEnums().stream().flatMap(e -> e.getELiterals().stream()).
			map(l -> l.getName()).forEach(literals::add);;
		}
		return literals;
	}
	
	private EnumLiteralExp getElement() {
		return (EnumLiteralExp) getProblematicElement(marker);
	}
	
	private String getClosest(EnumLiteralExp literal) {
		if (this.closest != null) 
			return this.closest;
		this.closest = this.sd.closest(literal.getName(), new ArrayList<String>(this.getPossibleNames()));
		return this.closest;
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		EnumLiteralExp lit = this.getElement();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.replace(lit, (expr, trace) -> {
			String name = this.getClosest(lit);
			
			EnumLiteralExp newLit = OCLFactory.eINSTANCE.createEnumLiteralExp();
			newLit.setName(name);
			
			return newLit;
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
		return "Enum literal "+ this.getElement().getName()+" not found, replace by "+this.getClosest(this.getElement());
	}

	@Override
	public String getDisplayString() {
		return "Enum literal "+this.getElement().getName()+" not found, replace by "+this.getClosest(this.getElement());
	}

}
