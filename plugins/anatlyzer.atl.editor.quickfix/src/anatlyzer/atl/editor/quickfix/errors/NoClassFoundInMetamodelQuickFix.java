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

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoClassFoundInMetamodelQuickFix extends AbstractAtlQuickfix  {

	private IMarker marker;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		this.marker = marker;
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
	
	@Override
	public void apply(IDocument document) {
		OclModelElement me = this.getElement();
		OclModel model = me.getModel();

		System.out.println("Meta-models: "+this.getAnalyserData(this.marker).getNamespace().getMetamodels());
				
		for (MetamodelNamespace mns : this.getAnalyserData(this.marker).getNamespace().getMetamodels()) {
			if (mns.getName().equals(model.getName())) {
				List<String> possible = mns.getAllClasses().stream().map(EClass::getName).collect(Collectors.toList());
				
				System.out.println("Possible names: "+possible);
			}
		}
		
		System.out.println("Class not found: "+me.getName());		
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Class "+this.getElement().getName()+" not found";
	}

	@Override
	public String getDisplayString() {
		return "Class "+this.getElement().getName()+" not found";
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
