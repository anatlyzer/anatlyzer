package anatlyzer.atl.commands;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.footprint.MetamodelPrunner;
import anatlyzer.atl.index.AnalysisIndex;

public class SliceTransformationMetmodelsHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		if ( editor instanceof AtlEditorExt ) {
			AtlEditorExt atlEditor = (AtlEditorExt) editor;
			IFile file = (IFile) atlEditor.getUnderlyingResource();
			
			AnalysisResult r = AnalysisIndex.getInstance().getAnalysis(file);

			for (MetamodelNamespace ns : r.getNamespace().getMetamodels()) {
				MetamodelPrunner prunner = new MetamodelPrunner(r.getATLModel(), ns);
				
				XMIResourceImpl res = new XMIResourceImpl();
				prunner.extractSource(res, ns.getName(), ns.getName() + "/prunned", ns.getName());
				try {
					res.save(new FileOutputStream(file.getLocation().removeLastSegments(1).append(ns.getName() + ".ecore").toOSString()), null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return null;
	}

}
