package witness.generator;

import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import transML.exceptions.transException;

public class ErrorMMGenerator extends WitnessGeneratorMemory {

	private String outputFileName;

	public ErrorMMGenerator(EPackage errorSliceMetamodel,
			EPackage effectiveMetamodel, EPackage languageMetamodel,
			String oclConstraint, String outputFileName) {
		super(errorSliceMetamodel, effectiveMetamodel, languageMetamodel, oclConstraint);
		this.outputFileName = outputFileName;
	}

	@Override
	public boolean generate() throws transException {		
		adaptMetamodels();
		
		Resource r = getErrorMM().eResource();
		if ( r == null ) {
			r = new XMIResourceImpl();
			r.getContents().add(getErrorMM());
		}
		
		try {
			r.save(new FileOutputStream(outputFileName), null);
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public EPackage getErrorMM() {
		return this.errorMM;
	}
	
}
