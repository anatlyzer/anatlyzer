package anatlyzer.ocl.emf;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.utilities.UMLReflection;
import org.junit.Test;

import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.Helper;

public class TestTranslation {

	@Test
	public void test() throws FileNotFoundException, IOException, ParserException {
		XMIResourceImpl r = new XMIResourceImpl();
		r.load(new FileInputStream("./model/extlibrary.ecore"), null);		
		EPackage p = (EPackage) r.getContents().get(0);
		
		EPackage.Registry.INSTANCE.put(p.getNsURI(), p);
		
		FileInputStream input = new FileInputStream("./model/library.ocl");
		
		OCLInput oclInput = new OCLInput(input);
		
		OCL ocl = OCL.newInstance();
		
		for (Constraint constraint : ocl.parse(oclInput)) {
			System.out.println(constraint);
			System.out.println(constraint.getSpecification().getBodyExpression().eClass());
			
			if ( isInvariant(constraint)) {
				Helper exp = new OCLtoATL().transform(constraint);
				
				System.out.println( ATLSerializer.serialize(exp) );
				
			}
		}
	}
	
	

	private boolean isInvariant(Constraint constraint) {
		return UMLReflection.INVARIANT.equals(constraint.getStereotype());
	}
	
}
