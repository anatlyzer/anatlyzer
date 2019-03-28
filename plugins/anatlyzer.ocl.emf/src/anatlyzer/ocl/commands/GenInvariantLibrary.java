package anatlyzer.ocl.commands;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.ocl.emf.editor.MetamodelInvariantsExtension;

public class GenInvariantLibrary extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if ( selection instanceof StructuredSelection ) {
			Object o = ((StructuredSelection) selection).getFirstElement();
			if ( o instanceof IFile && ((IFile) o).getFileExtension().equals("ecore")) {
				IFile f = (IFile) o;
								
				ResourceSetImpl rs = new ResourceSetImpl();
				Resource mm = rs.getResource(URI.createFileURI(f.getLocation().toOSString()), true);
				
				
				InputDialog dialog = new InputDialog(HandlerUtil.getActiveShell(event), "Meta-model name", "Enter meta-model name", f.getFullPath().removeFileExtension().lastSegment(), new IInputValidator() {					
					@Override
					public String isValid(String newText) {
						return newText.trim().length() > 0 ? null : "Name cannot be empty";
					}
				});
				
				if ( dialog.open() == InputDialog.CANCEL ) 
					return null;
				
				String mmName = dialog.getValue();
				
				List<ContextHelper> helpersInvariants = new ArrayList<ContextHelper>();
				List<ContextHelper> helpersOperations = new ArrayList<ContextHelper>();
				TreeIterator<EObject> it = mm.getAllContents();
				while ( it.hasNext() ) {
					EObject obj = it.next();
					if ( obj instanceof EClass ) {
						EClass c = (EClass) obj;
						helpersInvariants.addAll(MetamodelInvariantsExtension.extractOclInvariants(c, true));
						helpersOperations.addAll(MetamodelInvariantsExtension.extractOclOperations(c, true));
					}
				}
				
				if ( helpersInvariants.size() == 0 ) {
					MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "No invariants", "The meta-model does not contain any OCL invariant. No file is generated.");					
					return null;
				}
				
				// helpers.forEach(h -> h.getCommentsBefore().add("@target_invariant"));								
				List<StaticHelper> staticHelpers = helpersInvariants.stream().map(ch -> AnalyserUtils.convertContextInvariant(ch)).collect(Collectors.toList());
				staticHelpers.forEach(h -> h.getCommentsBefore().add("@target_invariant"));
				helpersOperations.forEach(h -> h.getCommentsBefore().add("@eoperation"));
				
				Library lib = ATLFactory.eINSTANCE.createLibrary();
				lib.setName("inv" + mmName);
				// lib.getHelpers().addAll(helpers);
				lib.getHelpers().addAll(staticHelpers);
				
				lib.getHelpers().addAll(helpersOperations);
				
				
				String text = ATLSerializer.serialize(lib);
				IFile output = ResourcesPlugin.getWorkspace().getRoot().getFile(f.getFullPath().removeFileExtension().addFileExtension("atl"));
				try {
					if ( ! output.exists() )
						output.create(new ByteArrayInputStream(text.getBytes()), true, null);
					else 
						output.setContents(new ByteArrayInputStream(text.getBytes()), true, false, null);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
