package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;

public class OclEMFUtils {
	
	public static List<ConstraintCS> getInvariants(CompleteOCLDocumentCS doc) {
		return doc.getOwnedPackages().stream().
			flatMap(p -> p.getOwnedContexts().stream()).
			filter(c -> c instanceof ClassifierContextDeclCS).
			map(c -> (ClassifierContextDeclCS) c).			
			flatMap(c -> c.getOwnedInvariants().stream() ).
			collect(Collectors.toList());
	}
	
	public static List<DefCS> getOperations(CompleteOCLDocumentCS doc) {
		return doc.getOwnedPackages().stream().
			flatMap(p -> p.getOwnedContexts().stream()).
			filter(c -> c instanceof ClassifierContextDeclCS).
			map(c -> (ClassifierContextDeclCS) c).			
			flatMap(c -> c.getOwnedDefinitions().stream() ).
			collect(Collectors.toList());
	}
	
	public static Resource toEcore(CompleteOCLCSResource r, URI uri) {		
		// From OCLInEcoreDocument
		@Nullable CS2AS cs2as = r.findCS2AS();
		@NonNull  ASResource asResource = cs2as.getASResource();
		Map<@NonNull String, @Nullable Object> options = new HashMap<>();
		//options.put(ClassUtil.nonNullState(OCLConstants.OCL_DELEGATE_URI), exportDelegateURI);
		Resource ecoreResource = AS2Ecore.createResource(cs2as.getEnvironmentFactory(), asResource, uri, options);
		return ecoreResource;
	}
	
	public static List<EClass> getClasses(Resource r) {
		List<EClass> classes = new ArrayList<EClass>();
		TreeIterator<EObject> it = r.getAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			if ( obj instanceof EClass ) {
				classes.add((EClass) obj);
			}
		}
		return classes;
	}

	public static Resource toMergedEcore(CompleteOCLCSResource r, List<? extends EPackage> packages) {
		ResourceSet rs = new ResourceSetImpl();
		Resource ecoreResource = rs.createResource(packages.get(0).eResource().getURI());
		//Resource n = toEcore(r, URI.createURI("to-be-merged.ecore"));
		Resource n = toEcore(r, packages.get(0).eResource().getURI()); // THIS IS TO FAKE CROSS-REFERENCES... SHOULD BE DONE BETTER
		
		List<EClass> classes = getClasses(n);
		
		Collection<? extends EPackage> copied = EcoreUtil.copyAll(packages);
		for (EPackage ePackage : copied) {
			for (EClassifier classifier : ePackage.getEClassifiers()) {
				if ( classifier instanceof EClass ) {
					classes.stream().filter(c -> {
						return c.getName().equals(classifier.getName()) && c.getEPackage().getNsURI().equals(ePackage.getNsURI());
					}).findFirst().ifPresent(c -> {
						merge((EClass) classifier, c);
					});				
				}
			}
			
			if ( ePackage.getESuperPackage() == null ) {
				ecoreResource.getContents().add(ePackage);
			}
		}
		
		
		
		return ecoreResource;
	}

	private static void merge(EClass klass, EClass withOCL) {
		klass.getEAnnotations().addAll(withOCL.getEAnnotations()); // TODO: Merge better
		
		for (EStructuralFeature fwithOCL : new ArrayList<>(withOCL.getEStructuralFeatures())) {
			Optional<EStructuralFeature> feature = klass.getEStructuralFeatures().stream().filter(f -> fwithOCL.getName().equals(f.getName())).findAny();
			if ( feature.isPresent() ) {
				feature.get().getEAnnotations().addAll(fwithOCL.getEAnnotations());
			} else {
				klass.getEStructuralFeatures().add(fwithOCL);
			}
		}
		
	}	
}
