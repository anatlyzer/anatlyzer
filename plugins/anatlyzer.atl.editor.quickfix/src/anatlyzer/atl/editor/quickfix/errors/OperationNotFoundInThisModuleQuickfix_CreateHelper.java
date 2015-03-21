package anatlyzer.atl.editor.quickfix.errors;

import java.util.*;
import java.util.function.*;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Levenshtein;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.types.*;
import anatlyzer.atl.types.impl.*;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.*;

public class OperationNotFoundInThisModuleQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	private static Map<Class<? extends Type>, Function<Type, String>> types = new HashMap<>();		// Function<? extends Type, String>
	
	static {
		types.put(anatlyzer.atl.types.IntegerType.class, t -> "Integer");
		types.put(anatlyzer.atl.types.StringType.class, t -> "String");
		types.put(anatlyzer.atl.types.FloatType.class, t -> "Real");
		//types.put(anatlyzer.atl.types.VariableExp.class, t -> "Real");
		types.put(MetaclassImpl.class, t -> {
			anatlyzer.atl.types.Metaclass typ = (anatlyzer.atl.types.Metaclass)t;
			return typ.getModel().getName()+"!"+typ.getName();	
		});
	}
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
	}
	
	private OperationCallExp getElement() {
		try {
			OperationNotFoundInThisModule p = (OperationNotFoundInThisModule) getProblem();
		
						
			// p.getOperationName() is null at this point 
			return (OperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	private Rule getContainerRule(OperationCallExp exp) {
		EObject res = exp; 
		
		do {
			res = res.eContainer();
		} while (res != null && !(res instanceof Rule));
		
		if (res!=null) return (Rule)res;
		return null;
	}
	
	private String getTypeName(Type inferredType) {		// This should be somewhere else...
		if (types.get((Class<? extends Type>)inferredType.getClass()) != null)
			return types.get((Class<? extends Type>)inferredType.getClass()).apply(inferredType);
		else return "OclAny";
	}
	
	private String buildNewHelper(OperationCallExp exp) {		
		String typeName = this.getTypeName(exp.getSource().getInferredType());
		
		String newHelper = "\nhelper def: "+exp.getOperationName()+"(";
		
		int idx = 0;
		for (OclExpression e : exp.getArguments()) {
			if ( idx > 0 ) newHelper+= ","; 
			newHelper += "arg"+idx+" : "+this.getTypeName(e.getInferredType());			
			idx++;
		}
		
		newHelper+= " ) : "+typeName+" =\n body;\n";
		
		return newHelper;
	}
	
	@Override
	public void apply(IDocument document) {
		OperationCallExp res = this.getElement();
		
		try {
			String newHelper = this.buildNewHelper(res);
			
			Rule r = this.getContainerRule(res);
			
			int[] sourceOffset = getElementOffset(r, document);
			int sourceOffsetEnd = sourceOffset[1];

			// Setting length to 0 means "insert", that is, replacing 0 characters
			document.replace(sourceOffsetEnd + 1, 0, newHelper);
			
		} catch ( BadLocationException e) {
			throw new RuntimeException(e);
		}
		
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
