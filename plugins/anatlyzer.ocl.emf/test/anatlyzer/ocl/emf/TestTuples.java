package anatlyzer.ocl.emf;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.tests.api.StandaloneUSEWitnessFinder;
import anatlyzer.ocl.emf.OclValidator.ValidationResult;

public class TestTuples {

	@Test
	public void test() throws FileNotFoundException, IOException, ParserException {
		setupLibraryMetamodel();
		
		String testcase = "model/tests/tuples.ocl";
		// FileInputStream input = new FileInputStream(testcase);		

		String prefix = Activator.getContext().getBundle().getLocation().replaceFirst("reference:", "");
		
		ResourceSet rs = new ResourceSetImpl();
		CSResource r = (CSResource) rs.getResource(URI.createURI(prefix + "/" + testcase), true);
		CompleteOCLDocumentCS doc = (CompleteOCLDocumentCS) r.getContents().get(0);		

		OclValidator validator = new OclValidator();		
		
		validator.addOclDefinition((CompleteOCLCSResource) doc.eResource());
		List<EPackage> packages = OclValidator.getPackagesOfDocument(doc);
		packages.forEach(validator::addMetamodel);
		
		validator.withWitnessFinder(new StandaloneUSEWitnessFinder());
		validator.invoke();
		
		
		ValidationResult result = validator.getResult();
		assertEquals(ProblemStatus.ERROR_CONFIRMED, result.getStatus());
	}



	private void setupLibraryMetamodel() throws IOException, FileNotFoundException {
		XMIResourceImpl r = new XMIResourceImpl();
		r.load(new FileInputStream("./model/extlibrary.ecore"), null);		
		EPackage p = (EPackage) r.getContents().get(0);		
		EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
	}
	
	
}
