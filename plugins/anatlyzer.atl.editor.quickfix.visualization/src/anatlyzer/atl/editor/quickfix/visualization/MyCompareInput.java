package anatlyzer.atl.editor.quickfix.visualization;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.ZipFileStructureCreator;
import org.eclipse.compare.internal.BufferedResourceNode;
import org.eclipse.compare.internal.CompareUIPlugin;
import org.eclipse.compare.internal.Utilities;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.DiffTreeViewer;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffContainer;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceAction;
import org.eclipse.ui.ide.IDE;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.quickfix.search.ISearchState;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.ui.util.WorkbenchUtil;

public class MyCompareInput extends CompareEditorInput {

	private ISearchState stateLeft;
	private ISearchState stateRight;
	private IResource originalResource;
	private ISearchState stateAncestor;

	public MyCompareInput(CompareConfiguration configuration, IResource originalResource, ISearchState ancestor, ISearchState stateLeft, ISearchState stateRight) {
		super(configuration);
		this.originalResource = originalResource;
		this.stateAncestor   = ancestor;
		this.stateLeft = stateLeft;
		this.stateRight = stateRight;
	}

	public static CompareConfiguration createDefaultConfiguration() {
		CompareConfiguration conf = new CompareConfiguration();
		return conf;
	}

	
	// From the IDE class
	private static IFile getWorkspaceFile(IFileStore fileStore) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile[] files = root.findFilesForLocationURI(fileStore.toURI());
//		files = filterNonExistentFiles(files);
//		if (files == null || files.length == 0)
//			return null;

		// for now only return the first file
		return files[0];
	}

	
	private boolean fThreeWay = true;

	@Override
	protected Object prepareInput(IProgressMonitor pm)
			throws InvocationTargetException, InterruptedException {

//		IFileStore fs1 = EFS.getLocalFileSystem().getStore(new Path("/tmp/r1.txt"));
//		IFileStore fs2 = EFS.getLocalFileSystem().getStore(new Path("/tmp/r2.txt"));
//		
//		IFile fLeftResource  = getWorkspaceFile(fs1); // ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path("/tmp/r1.txt"));
//		IFile fRightResource = getWorkspaceFile(fs2); // ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path("/tmp/r2.txt"));


		try {
			IFolder folder = WorkbenchUtil.getOrCreateFolder(originalResource.getProject().getFullPath().append("generated"));
			IPath leftPath = folder.getFullPath().append("left.atl");
			IPath rightPath = folder.getFullPath().append("right.atl");
			IPath ancestorPath = folder.getFullPath().append("original.atl");
			
			IFile fLeftResource  = ResourcesPlugin.getWorkspace().getRoot().getFile(leftPath);
			IFile fRightResource = ResourcesPlugin.getWorkspace().getRoot().getFile(rightPath);
			IFile fAncestorResource = null;
			
			ATLSerializer.serialize(stateLeft.getAnalysisResult().getATLModel(), fLeftResource.getLocation().toOSString());
			ATLSerializer.serialize(stateRight.getAnalysisResult().getATLModel(), fRightResource.getLocation().toOSString());
			
			if ( stateAncestor != null ) {
				fThreeWay = true;				
				fAncestorResource = ResourcesPlugin.getWorkspace().getRoot().getFile(ancestorPath);
				ATLSerializer.serialize(stateAncestor.getAnalysisResult().getATLModel(), fAncestorResource.getLocation().toOSString());
			} else {
				fThreeWay = false;								
			}
			
			
//			IFile fAncestorResource = null;
////			IFile fLeftResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/ddd/src/ddd/BB.java"));
////			IFile fRightResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/ddd/src/ddd/AA.java"));
//			IFile fLeftResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/ddd/src/ddd/aa.atl"));
//			IFile fRightResource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/ddd/src/ddd/bb.atl"));
			
			
//			fLeftResource.refreshLocal(1, pm);
//			fRightResource.refreshLocal(1, pm);
						
			// fix for PR 1GFMLFB: ITPUI:WIN2000 - files that are out of sync
			// with the file system appear as empty
			fLeftResource.refreshLocal(IResource.DEPTH_INFINITE, pm);
			fRightResource.refreshLocal(IResource.DEPTH_INFINITE, pm);

			if (fThreeWay && fAncestorResource != null)
				fAncestorResource.refreshLocal(IResource.DEPTH_INFINITE, pm);
			// end fix

			pm.beginTask(
					Utilities.getString("ResourceCompare.taskName"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$

			String leftLabel = fLeftResource.getName();
			String rightLabel = fRightResource.getName();

			String title;
			if (fThreeWay) {
				String format = Utilities
						.getString("ResourceCompare.threeWay.title"); //$NON-NLS-1$
				String ancestorLabel = fAncestorResource.getName();
				title = MessageFormat.format(format, new String[] {
						ancestorLabel, leftLabel, rightLabel });
			} else {
				String format = Utilities
						.getString("ResourceCompare.twoWay.title"); //$NON-NLS-1$
				title = MessageFormat.format(format, new String[] { leftLabel,
						rightLabel });
			}
			setTitle(title);

			Differencer d = new Differencer() {
				protected Object visit(Object parent, int description,
						Object ancestor, Object left, Object right) {
					return new MyDiffNode((IDiffContainer) parent, description,
							(ITypedElement) ancestor, (ITypedElement) left,
							(ITypedElement) right);
				}
			};

			// Created by jesusc
			Object fAncestor = getStructure(fAncestorResource);
			IStructureComparator fLeft = getStructure(fLeftResource);
			IStructureComparator fRight = getStructure(fRightResource);
				
			Object fRoot = d.findDifferences(fThreeWay, pm, null, fAncestor, fLeft,
					fRight);
			return fRoot;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new InvocationTargetException(ex);
		} finally {
			pm.done();
		}
	}

	private IStructureComparator getStructure(IResource input) {
		
		if (input instanceof IContainer)
			return new FilteredBufferedResourceNode(input);
			
		if (input instanceof IFile) {
			IStructureComparator rn= new FilteredBufferedResourceNode(input);
			IFile file= (IFile) input;
			String type= normalizeCase(file.getFileExtension());
			if ("JAR".equals(type) || "ZIP".equals(type)) //$NON-NLS-2$ //$NON-NLS-1$
				return new ZipFileStructureCreator().getStructure(rn);
			return rn;
		}
		return null;
	}

	static class FilteredBufferedResourceNode extends BufferedResourceNode {
		FilteredBufferedResourceNode(IResource resource) {
			super(resource);
		}
		protected IStructureComparator createChild(IResource child) {
			String name= child.getName();
			if (CompareUIPlugin.getDefault().filter(name, child instanceof IContainer, false))
				return null;
			return new FilteredBufferedResourceNode(child);
		}
	}

	private static final boolean NORMALIZE_CASE= true;
	
	private static String normalizeCase(String s) {
		if (NORMALIZE_CASE && s != null)
			return s.toUpperCase();
		return s;
	}
	

	// For the moment is null
	private DiffTreeViewer fDiffViewer;

	
	class MyDiffNode extends DiffNode {
		
		private boolean fDirty= false;
		private ITypedElement fLastId;
		private String fLastName;
		
		
		public MyDiffNode(IDiffContainer parent, int description, ITypedElement ancestor, ITypedElement left, ITypedElement right) {
			super(parent, description, ancestor, left, right);
		}
		public void fireChange() {
			super.fireChange();
			setDirty(true);
			fDirty= true;
			if (fDiffViewer != null)
				fDiffViewer.refresh(this);
		}
		void clearDirty() {
			fDirty= false;
		}
		public String getName() {
			if (fLastName == null)
				fLastName= super.getName();
			if (fDirty)
				return '<' + fLastName + '>';
			return fLastName;
		}
		
		public ITypedElement getId() {
			ITypedElement id= super.getId();
			if (id == null)
				return fLastId;
			fLastId= id;
			return id;
		}
	}

}
