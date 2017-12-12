package testing.metamodel;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import testing.metamodel.mutators.AbstractMutator;
import testing.metamodel.mutators.FileStorageStrategy;
import testing.metamodel.mutators.breaking.*;
import testing.metamodel.mutators.nonbreaking.*;
import testing.utils.MMResource;

public class EcoreMutantGenerator {
	/**
	 * Generation of mutants.
	 * @param ecoreFile
	 * @param outputFolder
	 */
	public void run (URI ecoreFile, String outputFolder) {
		// create resource set to represent persistent resource
		ResourceSet rs = new ResourceSetImpl();
		
		// register the default resource factory in the registry of the resource set (only needed for stand-alone)
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		
		// register the package (only needed for stand-alone)
		// EcorePackage ecorePackage = EcorePackage.eINSTANCE; 
		
		// get URI of the ecore file
		URI fileURI = ecoreFile;

		// demand load the resource for this file
		MMResource metamodel = new MMResource(rs.getResource(fileURI, true));
		
		// delete output folder
		this.deleteDirectory(outputFolder, true);

		// mutate meta-model
		AbstractMutator[] mutators = {
			new AddObligatoryMetaclass(),				
			new AddObligatoryMetaproperty(),
			new EliminateMetaclass(),
			new EliminateMetaproperty(),
			new ExtractAbstractSuperclass(),
			new ExtractNonabstractSuperclass(),
			new ExtractMetaclass(),
			new InlineMetaclass(),
			new MoveMetaproperty(), 
			new PullMetaproperty(),
			new PushMetaproperty(),
			new RenameMetaelement(),	
			new RestrictMetapropertyLB(),
			new RestrictMetapropertyType(),
			new RestrictMetapropertyUB(),
			new AddOptionalMetaclass(),
			new AddOptionalMetaproperty(),
			new GeneralizeMetapropertyLB(),
			new GeneralizeMetapropertyType(),
			new GeneralizeMetapropertyUB(),
			new FlattenHierarchy(),
		};
		
		FileStorageStrategy fileStorage = new FileStorageStrategy(outputFolder);
		for (AbstractMutator mutator : mutators) {
			mutator.setStorageStrategy(fileStorage);
			try {
				mutator.mutate(metamodel);
			} catch ( Exception e ) {
				System.out.println("Failed for " + mutator);
				e.printStackTrace();
			}
		}
		
		System.out.println("mutant generation finished!!!");
	}
	
	/**
	 * It deletes a directory.
	 * @param folder name of directory
	 * @param recursive it deletes the subdirectories recursively
	 */
	private void deleteDirectory (String directory, boolean recursive) {
		File folder = new File(directory);
		if (folder.exists())
			for (File file : folder.listFiles()) {				
				if (file.isDirectory()) deleteDirectory(file.getPath(), recursive);
				file.delete();
			}
		folder.delete();
	}
}
