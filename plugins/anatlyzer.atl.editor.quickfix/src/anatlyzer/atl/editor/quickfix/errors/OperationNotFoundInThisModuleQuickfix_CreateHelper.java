package anatlyzer.atl.editor.quickfix.errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;

public class OperationNotFoundInThisModuleQuickfix_CreateHelper extends AbstractAtlQuickfix {
		
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
	}
	
	@Override
	public void apply(IDocument document) {
		System.out.println("Operation not found in thisModule: create helper or lazy rule");
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Operation not found in thisModule: create helper or lay rule";
	}

	@Override
	public String getDisplayString() {
		return "Operation not found in thisModule: create helper or lazy rule";
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
