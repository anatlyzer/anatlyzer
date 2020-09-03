package anatlyzer.ide.interactive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialog;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.ide.model.InteractiveTransformationModel;

/**
 * Utilities for dealing with test files 
 * 
 * @author jesus
 *
 */
public class TestFiles {

	public static List<IFile> findTests(@NonNull AnalysisResult result, InteractiveTransformationModel spec, IProject project) throws CoreException {
		List<IFile> suites = new ArrayList<>();
		List<IFolder> folders = getSuiteFolders(project, spec, result);
		for (IFolder folder : folders) {
			folder.accept((IResource r) -> {
				if (r instanceof IFile &&
					(r.getFileExtension().equalsIgnoreCase("xmi") || 
					 Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey(r.getFileExtension()))) {
					suites.add((IFile) r);
					return false;
				}
				return true;
			});
		}
		return suites;
	}

	public static IFolder getOutputSuiteFolder(IProject project, AnalysisResult result, @Nullable InteractiveTransformationModel spec) {
		return getSuiteFolders(project, spec, result).get(0);
	}

	
	public static List<IFolder> getSuiteFolders(IProject project, @Nullable InteractiveTransformationModel spec, AnalysisResult result) {
		List<String> interactive = ATLUtils.findTags(result.getATLModel().getRoot(), "interactive");
		if (spec != null) {
			interactive.addAll(spec.getSuiteFolders());
		}
		List<IFolder> folders = new ArrayList<>();
		for (String string : interactive) {
			String[] parts = string.split("=");

			if (parts.length > 1 && parts[0].toLowerCase().equals("suite")) {
				String f = parts[1];
				if (f.startsWith("/")) {
					// Full path
					throw new UnsupportedOperationException();
				} else {
					IFolder folder = project.getFolder(f);
					if (! folder.exists()) {
						MessageDialog.openError(null, "Error", "Folder doens't exists: " + f);
						return Collections.emptyList();
					}
					folders.add(folder);
				}
			}
		}
		return folders;
	}
	
}
