package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;

import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.ocl.emf.editor.MetamodelInvariantsExtension;

/**
 * This class implements a translation from CompleteOCL libraries
 * to an ATL library in which each helper is an invariant.
 * 
 * @author jesus
 */
public class ResourceToLibrary {

	private Library lib;

	public ResourceToLibrary() {
		this.lib = ATLFactory.eINSTANCE.createLibrary();
		this.lib.setName("invariants");
	}
	
	public Library getLibrary() {
		return this.lib;
	}
	
	
	
	public void translate(String metamodelName, List<ConstraintCS> constraints) {
		translate(metamodelName, constraints, Collections.emptyList());
	}
	
	public void translate(String metamodelName, List<ConstraintCS> constraints, List<Constraint> constraintsEcore) {
		List<ContextHelper> helpers = new ArrayList<ContextHelper>();

		OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		
		for (ConstraintCS inv : constraints) {						
			// Parse again but with Ecore
			Helper helper = ocl.createOCLHelper();
			helper.setContext((EClassifier) ((ClassifierContextDeclCS) inv.eContainer()).getReferredClass().getESObject());

			Constraint constraint;
			try {
				constraint = helper.createInvariant(inv.getOwnedSpecification().toString());
				constraint.setName(inv.getName());
			} catch (ParserException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// Translate
			ContextHelper h = new OCLtoATL().transform(metamodelName, constraint);	
			// Save the invariant and the translation
			helpers.add(h);
		}
		
		for(Constraint c : constraintsEcore) {
			ContextHelper h = new OCLtoATL().transform(metamodelName, c);	
			helpers.add(h);		
		}
		
		
		lib.getHelpers().addAll(helpers);
	}
	

	public void translateEcoreOcl(String mmName, Resource ecoreResource) {
		for(EClass c : OclEMFUtils.getClasses(ecoreResource)) {
			lib.getHelpers().addAll( MetamodelInvariantsExtension.extractOclOperations(mmName, c, true) );
			lib.getHelpers().addAll( MetamodelInvariantsExtension.extractOclInvariants(mmName, c, true) );
		}
	}

	public void translate(String metamodelName, @NonNull ASResource asResource) {
		Model m = (Model) asResource.getContents().get(0);
		new FIXOCL().tryToFix(m);
		List<ContextHelper> helpers = new PivotOCLtoATL().transform(metamodelName, m);
		
		lib.getHelpers().addAll(helpers);
		
	}

//	private void translate(String metamodelName, EObject o) {	
//		if ( o instanceof Model ) {
//			Model m = (Model) o;
//			for (Package pkg : m.getOwnedPackages()) {
//				for (Class class1 : pkg.getOwnedClasses()) {
//					for (org.eclipse.ocl.pivot.Constraint constraint : class1.getOwnedInvariants()) {
//						ContextHelper h = new OCLtoATL().transform(metamodelName, constraint);	
//					}
//				}
//			}
//		} 
//		throw new UnsupportedOperationException();
//	}
	
}
