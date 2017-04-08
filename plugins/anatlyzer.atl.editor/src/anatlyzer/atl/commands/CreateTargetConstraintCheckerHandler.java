
package anatlyzer.atl.commands;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.AtlEditorExt;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.StringExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class CreateTargetConstraintCheckerHandler extends AbstractHandler implements IHandler {

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
				
				AnalysisResult r = AnalysisIndex.getInstance().getAnalysis(file);
				if ( r != null ) {
					Query unit = generate(r);
					try {
						serialize(unit, file);
					} catch (CoreException e) {
						throw new ExecutionException("Cannot serialize!", e);
					}
				}
			}
		}
		
		return null;
	}

	private void serialize(Query unit, IFile file) throws CoreException {
		IPath newPath = file.getFullPath().removeFileExtension().addFileExtension("checker.atl");
		String r = ATLSerializer.serialize(unit);
		
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(newPath);
		if ( ! f.exists() ) {
			f.create(new ByteArrayInputStream(r.getBytes()), true, null);
		} else {
			f.setContents(new ByteArrayInputStream(r.getBytes()), true, false, null);
		}		
	}

	private Query generate(AnalysisResult r) {
		List<Helper> targets = ATLUtils.getAllHelpers(r.getATLModel()).stream().filter(AnalyserUtils::isTargetInvariant).collect(Collectors.toList());
			
		List<ModelInfo> models = ATLUtils.getModelInfo(r.getATLModel()).stream().filter(m -> m.isOutput()).collect(Collectors.toList());
		
		Query q = ATLFactory.eINSTANCE.createQuery();
		q.setName("checker");
		q.getHelpers().addAll(targets.stream().map(h -> (Helper) ATLCopier.copySingleElement(h)).collect(Collectors.toList()));
		models.forEach(m -> {
			String tag = m.isURI() ? "@nsURI " : "@path ";
			tag = tag + m.getMetamodelName() + "=" + m.getURIorPath();			
			q.getCommentsBefore().add(tag);
		});
			
		
		if ( targets.size() == 0 ) {
			q.getCommentsBefore().add("No helpers");
			q.setBody(OCLFactory.eINSTANCE.createBooleanExp());
			return q;
		}

		
		VariableDeclaration thisModule = OCLFactory.eINSTANCE.createVariableDeclaration();
		thisModule.setVarName("thisModule");

		
		List<OperationCallExp> checkers = targets.stream().map(h -> {
			OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
			VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
			varRef.setReferredVariable(thisModule);
			op.setSource(varRef);
			op.setOperationName(ATLUtils.getHelperName(h));
			
			OperationCallExp debug = OCLFactory.eINSTANCE.createOperationCallExp();
			StringExp str = OCLFactory.eINSTANCE.createStringExp();
			str.setStringSymbol(ATLUtils.getHelperName(h));
			debug.getArguments().add(str);
			debug.setOperationName("debug");
			debug.setSource(op);
			
			return debug;
		}).collect(Collectors.toList());
		
		
		OperationCallExp checker = checkers.get(0);
		for(int i = 1; i < checkers.size(); i++) {
			OperationCallExp c = checkers.get(i);
			OperatorCallExp andOp = OCLFactory.eINSTANCE.createOperatorCallExp();
			andOp.setOperationName("and");
			andOp.setSource(checker);
			andOp.getArguments().add(c);
			
			checker = andOp;
		}
		
		q.setBody(checker);
		return q;
	}
}
