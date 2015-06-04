package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;

public class SourceMetamodelsData {

	private EPackage root;
	private HashMap<EObject, EObject> trace;

	public SourceMetamodelsData(EPackage newRoot, HashMap<EObject, EObject> trace) {
		this.root = newRoot;
		this.trace = trace;
	}

	public static SourceMetamodelsData get(Analyser analyser) {
		List<ModelInfo> srcModels = ATLUtils.getModelInfo(analyser.getATLModel()).stream().filter(i -> i.isInput()).collect(Collectors.toList());
		
		Copier copier = new EcoreUtil.Copier();
		Resource r = new ResourceImpl();
		
		for (ModelInfo mInfo : srcModels) {			
			MetamodelNamespace ns = analyser.getNamespaces().getNamespace(mInfo.getMetamodelName());
			
			copier.copyAll(ns.getResource().getContents());
			copier.copyReferences();
		
			EPackage newRoot = getRoot(mInfo.getMetamodelName(), ns.getResource(), copier);
			r.getContents().add(newRoot);
		}
		
		EPackage newRoot = null;
		if ( r.getContents().size() == 1 ) {
			newRoot = (EPackage) r.getContents().get(0);
		} else {
			newRoot = EcoreFactory.eINSTANCE.createEPackage();
			newRoot.setName("srcRoot");
			newRoot.setNsPrefix("srcRoot");
			newRoot.setNsURI("http://" + "srcRoot" + "/" + "gen");
			
			for (EObject pkg : r.getContents()) {
				newRoot.getESubpackages().add((EPackage) pkg);
			}
			
			r.getContents().add(newRoot);
			
		}
		
		adapt(newRoot, "");
		
		copier.forEach((src, tgt) -> { 
			if ( src instanceof EStructuralFeature ) {
				checkRenaming((EStructuralFeature) src, (EStructuralFeature) tgt);
			}
		});
		
		return new SourceMetamodelsData(newRoot, copier);
	}
	
	private static void checkRenaming(EStructuralFeature src, EStructuralFeature tgt) {
		if ( UseReservedWords.isReserved(tgt.getName()) ) {
			tgt.setName(UseReservedWords.getReplacement(tgt.getName()));
		}
	}

	public EPackage getSinglePackage() {
		return root;
	}

	public EClass getTarget(EClass c) {
		return (EClass) trace.get(c);
	}

	public EEnum getTarget(EEnum c) {
		return (EEnum) trace.get(c);
	}

	public EStructuralFeature getTarget(EStructuralFeature f) {
		return (EStructuralFeature) trace.get(f);
	}
	
	private static void adapt(EPackage parent, String prefix) {
		for (EClassifier eClassifier : parent.getEClassifiers()) {
			eClassifier.setName(prefix + eClassifier.getName());
			if ( eClassifier instanceof EEnum ) {
				// Renamings of eenums is done without "_", because it seems that USE has
				// problems with underscores
				eClassifier.setName( eClassifier.getName().replaceAll("_", "s"));
			}
		}
		
		for (EPackage ePackage : parent.getESubpackages()) {
			adapt(ePackage, prefix + ePackage.getName() + "_");
		}		
		
		for (EPackage ePackage : new ArrayList<EPackage>(parent.getESubpackages())) {
			parent.getEClassifiers().addAll(ePackage.getEClassifiers());
			EcoreUtil.delete(ePackage);
		}		
	}

	private static EPackage getRoot(String mmName, Resource resource, HashMap<EObject, EObject> trace) {
		if ( resource.getContents().size() == 1 ) {
			return (EPackage) getTgt(trace, resource.getContents().get(0));
		}
		
		EPackage newRoot = EcoreFactory.eINSTANCE.createEPackage();
		newRoot.setName(mmName);
		newRoot.setNsPrefix(mmName);
		newRoot.setNsURI("http://" + mmName + "/" + "gen");
		for (EObject pkg : resource.getContents()) {
			newRoot.getESubpackages().add( (EPackage) getTgt(trace, pkg) );
		}
		
		return newRoot;
	}

	private static EObject getTgt(HashMap<EObject, EObject> trace, EObject src) {
		if ( ! trace.containsKey(src) ) 
			throw new IllegalStateException();
		return trace.get(src);
	}

	
}
