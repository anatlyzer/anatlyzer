package anatlyzer.atl.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.ui.util.AtlEngineUtils;

public class CreateAbstractSyntaxHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		
		if ( selection instanceof TextSelection ) {
			TextSelection s = (TextSelection) selection;
			IEditorInput input = HandlerUtil.getActiveEditorInput(event);
			IEditorPart editor = HandlerUtil.getActiveEditor(event);
			if ( editor instanceof AtlEditorExt ) {
				AtlEditorExt atlEditor = (AtlEditorExt) editor;
				IFile file = (IFile) atlEditor.getUnderlyingResource();
				
				IFile output = file.getProject().getFile("ast.xmi");
				IFile outputMM = file.getProject().getFile("ATL.ecore");
				
				try {
					EMFModelFactory modelFactory = new EMFModelFactory();
					EMFReferenceModel atl = (EMFReferenceModel) modelFactory.getBuiltInResource("ATL.ecore");
					
					
					HashMap<Object, Object> options = new HashMap<Object, Object>();
					options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
					EMFModel atlEMFModel = AtlEngineUtils.loadATLFile(file, file.getContents(), false);
				
					atlEMFModel.getResource().save(new FileOutputStream(output.getLocation().toOSString()), options);
					atl.getResource().save(new FileOutputStream(outputMM.getLocation().toOSString()), options);
					
					output.refreshLocal(1, null);
					outputMM.refreshLocal(1, null);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ATLCoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		}
		System.out.println(selection);
		return null;
	}

}
