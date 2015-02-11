package anatlyzer.experiments.configuration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import anatlyzer.experiments.extensions.IExperiment;

public class ExperimentConfiguration {
	public String name;
	public String extensionID;
	public List<Project> projects = new ArrayList<Project>();

	public void execute(final String extension, IExperiment experiment, IProgressMonitor monitor) {
		IProject[] allProjects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (final Project project : projects) {
			IProject p = findProject(allProjects, project.name);
			if ( p == null ) {
				System.err.println("Project " + project.name + " not found");
				continue;
			} 
			
			try {
				monitor.beginTask("Collecting files", IProgressMonitor.UNKNOWN);
				
				final ArrayList<IResource> selected = new ArrayList<IResource>();
				p.accept(new IResourceVisitor() {
					@Override
					public boolean visit(IResource resource) throws CoreException {
						if ( resource.isDerived() ) return false;
						if ( resource.getType() == IResource.FILE && 
								extension.equals(resource.getFileExtension()) &&
								! ignoreFile(project, (IFile) resource)) {

							selected.add(resource);
						}
							
						return true; // try to visit any children whatsoever...
					}

				});
				monitor.done();
				
				monitor.beginTask("Executing experiment", selected.size());
				for (IResource iResource : selected) {
					monitor.subTask(iResource.getName());
					
					experiment.perform(iResource);
				}
				monitor.done();
				
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			System.out.println(project.name);
		}
	}

	private boolean ignoreFile(Project project, IFile resource) {
		return project.ignoredFiles.contains(resource.getFullPath().toFile().getName());
	}
	
	private IProject findProject(IProject[] allProjects, String projectName) {
		for (IProject iProject : allProjects) {
			if ( iProject.getName().equals(projectName) ) {
				return iProject;
			}			
		}
		return null;
	}
}
