package testing.metamodel.mutators;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;

import testing.utils.MMResource;

public class FileStorageStrategy implements IStorageStrategy  {
	private String outputFolder;

	public FileStorageStrategy(String folder) {
		this.outputFolder = folder;
	}

	/**
	 * It saves the received resource (i.e., meta-model) in the specified folder.
	 * @param atlModel
	 * @param outputFolder
	 */
	@Override
	public void save (MMResource metamodel) {
		String file = null;
		try {
			file              = this.getValidNameOfFile(outputFolder);
			URI          uri  = URI.createFileURI(new File(file).getAbsolutePath());
			OutputStream os   = metamodel.getResourceSet().getURIConverter().createOutputStream(uri);
			metamodel.save(os, null);
			os.close();
		}
		catch (IOException e) { 
			System.err.println("ERROR when saving file " + file + "\n"+e.getMessage()); 
		}
	}

	/**
	 * It returns the next valid name for a mutant: <type-of-mutant>_mutant_<available-i-in-directory>.ecore
	 * @param outputFolder folder where the mutants will be generated
	 */
	private /*static*/ long index = 1;
	private String getValidNameOfFile (String outputFolder) {
		String outputfile = null;
		String aux        = null;
		for (long i=index; outputfile==null; i++) {
			aux = File.separatorChar + this.getClass().getSimpleName() + "_mutant_" + i + ".ecore";
			if (!new File(outputFolder, aux).exists()) { 
				outputfile = outputFolder + aux;
				index = i;
			}
			else index = i;
		}
		return outputfile;
	}

}
