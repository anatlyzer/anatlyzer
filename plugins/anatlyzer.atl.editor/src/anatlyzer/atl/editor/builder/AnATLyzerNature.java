package anatlyzer.atl.editor.builder;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.EditorDescriptor;
import org.eclipse.ui.internal.registry.EditorRegistry;
import org.eclipse.ui.internal.registry.FileEditorMapping;

public class AnATLyzerNature implements IProjectNature {

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = "anatlyzer.atl.editor.anatlyzerATLNature";

	private IProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException {
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();

		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(AnATLyzerBuilder.BUILDER_ID)) {
				return;
			}
		}

		ICommand[] newCommands = new ICommand[commands.length + 1];
		System.arraycopy(commands, 0, newCommands, 0, commands.length);
		ICommand command = desc.newCommand();
		command.setBuilderName(AnATLyzerBuilder.BUILDER_ID);
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec(newCommands);
		project.setDescription(desc, null);
		
		try {
			setAnATLyzerDefaultEditor();
		} catch ( Exception e ) {
			System.out.println("This do not work in Luna");
			e.printStackTrace();
		}
		
	}

	private void setAnATLyzerDefaultEditor() {
		String extension = "atl";
	    String editorId = "org.eclipse.m2m.atl.adt.editor.AtlEditor";

	    EditorRegistry editorReg = (EditorRegistry)PlatformUI.getWorkbench().getEditorRegistry();
	    //EditorDescriptor editor = (EditorDescriptor) editorReg.findEditor(editorId);
	    //editor.
	    IFileEditorMapping[] mappings = editorReg.getFileEditorMappings();
	    for (IFileEditorMapping mapping : mappings) {
	    	if ( "atl".equals(mapping.getExtension()) ) {
	    	   	IEditorDescriptor[] descriptors = mapping.getEditors();
	    	   	IEditorDescriptor anatlyzerEditor = null;
	    	   	for (IEditorDescriptor editorDesc : descriptors) {
					if ( "anatlyzer.atl.editor.AtlEditorExt".equals( ((EditorDescriptor) editorDesc).getClassName()) ) {
						anatlyzerEditor = editorDesc;
						break;
					}
				}
	    	   	
	    	   	if ( anatlyzerEditor != null ) {
	    	   		((FileEditorMapping) mapping).setDefaultEditor((EditorDescriptor) anatlyzerEditor);
	    	   		break;
	    	   	}
	    	}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(AnATLyzerBuilder.BUILDER_ID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i,
						commands.length - i - 1);
				description.setBuildSpec(newCommands);
				project.setDescription(description, null);			
				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	public IProject getProject() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

}
