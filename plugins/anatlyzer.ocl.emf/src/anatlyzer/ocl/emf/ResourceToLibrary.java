package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.ExpressionInOCL;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.parser.OCLFactoryWithHistory;
import org.eclipse.ocl.examples.pivot.internal.impl.ConstraintImpl;
import org.eclipse.ocl.examples.xtext.base.basecs.ConstraintCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.PackageDeclarationCS;
import org.eclipse.ocl.helper.OCLHelper;

import anatlyzer.atl.util.ATLSerializer;
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
		HashMap<ConstraintCS, ContextHelper> helpers = new HashMap<ConstraintCS, ContextHelper>();
		
		for (ConstraintCS inv : constraints) {						
			// Parse again but with Ecore
			OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);			
			Helper helper = ocl.createOCLHelper();
			helper.setContext((EClassifier) ((ClassifierContextDeclCS) inv.eContainer()).getClassifier().getETarget());

			Constraint constraint;
			try {
				constraint = helper.createInvariant(inv.getSpecification().toString());
				constraint.setName(inv.getName());
			} catch (ParserException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			// Translate
			ContextHelper h = new OCLtoATL().transform(metamodelName, constraint);	
			// Save the invariant and the translation
			helpers.put(inv, h);
		}
		
		
		Library lib = ATLFactory.eINSTANCE.createLibrary();
		lib.setName("invariants");
		lib.getHelpers().addAll(helpers.values());
		
		return lib;
	}
	
	public Library translate(String metamodelName, CompleteOCLDocumentCS doc) {
		ArrayList<ConstraintCS> invs = new ArrayList<>();
		for (PackageDeclarationCS pkg : doc.getPackages()) {
//			String pkgName = pkg.getPackage().getName();
//			
//			System.out.println(pkg.getPivot());			
			for (ContextDeclCS ctx : pkg.getContexts()) {
				if ( ctx instanceof ClassifierContextDeclCS ) {
					ClassifierContextDeclCS cctx = (ClassifierContextDeclCS) ctx;
					invs.addAll(cctx.getInvariants());
				}
			}
		}

		return translate(metamodelName, invs);
	}
	
}
