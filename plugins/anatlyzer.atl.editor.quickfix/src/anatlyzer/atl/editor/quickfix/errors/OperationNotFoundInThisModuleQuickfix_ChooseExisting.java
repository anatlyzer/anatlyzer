package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.analyser.namespaces.TransformationNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;

public class OperationNotFoundInThisModuleQuickfix_ChooseExisting extends AbstractAtlQuickfix {
		
	private IMarker marker;
	
	@Override
	public boolean isApplicable(IMarker marker) {
		this.marker = marker;
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
	}
	
	private OperationCallExp getElement() {
		try {
			OperationNotFoundInThisModule p = (OperationNotFoundInThisModule) getProblem();
		
			ATLModel model = getAnalyserData(marker).getAnalyser().getATLModel();
			if ( model.getRoot() instanceof Module ) {
				Module m = (Module) model.getRoot();
				m.getElements().stream().filter(e -> e instanceof Helper).
					map(e -> (Helper) e).
					forEach(h -> System.out.println(ATLUtils.getHelperName(h)));
			} else if ( model.getRoot() instanceof Library ) {
				// Same for library... 
			}
			
			
			// p.getOperationName() is null at this point 
			return (OperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	@Override
	public void apply(IDocument document) {
		OperationCallExp res = this.getElement();
		
		TransformationNamespace tns = this.getAnalyserData(this.marker).getNamespace().getTransformationNamespace();
		
		
				
		System.out.println("Operation "+res.getOperationName()+" not found in thisModule: choose an existing one.");
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		OperationCallExp res = this.getElement();
		return "Operation "+res.getOperationName()+" not found in thisModule: choose an existing one.";
	}

	@Override
	public String getDisplayString() {
		OperationCallExp res = this.getElement();
		return "Operation "+res.getOperationName()+" not found in thisModule: choose an existing one.";
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
