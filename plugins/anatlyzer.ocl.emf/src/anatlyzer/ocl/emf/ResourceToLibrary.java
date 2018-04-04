package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.internal.helper.HelperUtil;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.ContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;

import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Library;

/**
 * This class implements a translation from CompleteOCL libraries
 * to an ATL library in which each helper is an invariant.
 * 
 * @author jesus
 */
public class ResourceToLibrary {

	public Library translate(String metamodelName, List<ConstraintCS> constraints) {
		return translate(metamodelName, constraints, Collections.emptyList(), Collections.emptyList());
	}
	
	public Library translate(String metamodelName, List<ConstraintCS> constraints, List<Constraint> constraintsEcore, List<DefCS> operations) {
		List<ContextHelper> helpers = new ArrayList<ContextHelper>();

		OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);			
		
		for (DefCS op : operations) {
			Helper helper = ocl.createOCLHelper();
			try {
				EStructuralFeature att = helper.defineAttribute(op.toString());
				System.out.println(att);
			} catch (ParserException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
		
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
		
		
		Library lib = ATLFactory.eINSTANCE.createLibrary();
		lib.setName("invariants");
		lib.getHelpers().addAll(helpers);
		
		return lib;
	}
	
	public Library translate(String metamodelName, CompleteOCLDocumentCS doc) {
		ArrayList<ConstraintCS> invs = new ArrayList<>();
		for (PackageDeclarationCS pkg : doc.getOwnedPackages()) {
//			String pkgName = pkg.getPackage().getName();
//			
//			System.out.println(pkg.getPivot());			
			for (ContextDeclCS ctx : pkg.getOwnedContexts()) {
				if ( ctx instanceof ClassifierContextDeclCS ) {
					ClassifierContextDeclCS cctx = (ClassifierContextDeclCS) ctx;
					invs.addAll(cctx.getOwnedInvariants());
				}
			}
		}

		return translate(metamodelName, invs);
	}
	
}
