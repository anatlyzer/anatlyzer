package anatlyzer.atl.witness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
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
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;

public class SourceMetamodelsData implements IMetamodelRewrite {

	private EPackage root;
	private HashMap<EObject, EObject> trace;
	private IAnalyserResult result;

	protected SourceMetamodelsData(IAnalyserResult result, EPackage newRoot, HashMap<EObject, EObject> trace) {
		this.root = newRoot;
		this.trace = trace;
		this.result = result;
	}
	
	@Override
	public IAnalyserResult getAnalysis() {
		return result;
	}

	public static SourceMetamodelsData get(IAnalyserResult analyser) {
		List<ModelInfo> srcModels = ATLUtils.getModelInfo(analyser.getATLModel()).stream().filter(i -> i.isInput()).collect(Collectors.toList());
		
		Copier copier = new EcoreUtil.Copier();
		Resource r = new ResourceImpl();
		
		
		
		for (ModelInfo mInfo : srcModels) {			
			MetamodelNamespace ns = analyser.getNamespaces().getNamespace(mInfo.getMetamodelName());		
			addToCopier(copier, r, mInfo.getMetamodelName(), ns);
		}
		
		// The copy procedure for the meta-model needs to be separated to avoid the copier to redirect
		// references to the Ecore meta-model (in particular, data types) to the the copied ecore
		MetamodelNamespace mmm = analyser.getNamespaces().getMetaMetamodel();
		if ( mmm != null ) {
			Copier mmCopier = new EcoreUtil.Copier();
			addToCopier(mmCopier, r, mmm.getName(), mmm);

			copier.putAll(mmCopier);
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
		
		return new SourceMetamodelsData(analyser, newRoot, copier);
	}


	private static void addToCopier(Copier copier, Resource r, String metamodelName,
			MetamodelNamespace ns) {
		copier.copyAll(ns.getResource().getContents());
		copier.copyReferences();

		EPackage newRoot = getRoot(metamodelName, ns.getResource(), copier);
		r.getContents().add(newRoot);
	}
	
	private static void checkRenaming(EStructuralFeature src, EStructuralFeature tgt) {
		if ( UseReservedWords.isReserved(tgt.getName()) ) {
			tgt.setName(UseReservedWords.getReplacement(tgt.getName()));
		}
	}

	public EPackage getSinglePackage() {
		return root;
	}

	@Override
	public EClass getTarget(EClass c) {
		return (EClass) trace.get(c);
	}

	@Override
	public EObject getTarget(EObject orig, BiFunction<EObject, EObject, Boolean> comparator) {
		for (Entry<EObject, EObject> entry : trace.entrySet()) {
			if ( comparator.apply(entry.getKey(), orig) ) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public EObject getOriginal(EObject orig, BiFunction<EObject, EObject, Boolean> comparator) {
		for (Entry<EObject, EObject> entry : trace.entrySet()) {
			if ( comparator.apply(entry.getKey(), orig) ) {
				return entry.getKey();
			}
		}
		return null;
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


	@Override
	public EClass getOriginal(EClass eClass) {
//		trace.forEach((k, v) -> {
//			System.out.println(k + " = " + v);
//		});
//		return (EClass) trace.entrySet().stream().filter(entry -> entry.getValue() == eClass).findAny().map(e -> e.getKey()).orElse(null);
		// Using pointers it is not working, so relying on names
		// This is not going to work if there are classes with the same name in different packages!
		return trace.entrySet().stream().
				filter(entry -> entry.getValue() instanceof EClass).
				filter(entry -> ((EClass) entry.getValue()).getName().equals(eClass.getName()) ).
				findAny().map(e -> (EClass) e.getKey()).orElse(null);
	}

	@Override
	public EStructuralFeature getOriginal(EStructuralFeature f) {
		// return (EStructuralFeature) trace.entrySet().stream().filter(entry -> entry.getValue() == f).findAny().map(e -> e.getKey()).orElse(null);
		return trace.entrySet().stream().
				filter(entry -> entry.getValue() instanceof EStructuralFeature).
				filter(entry -> {
					EStructuralFeature fTgt = (EStructuralFeature) entry.getValue();

					return 							
						fTgt.getEContainingClass() != null && // Not sure why this constraint is required to avoid a NPE in some cases
						fTgt.getName().equals(f.getName()) && 
						fTgt.getEContainingClass().getName().equals(f.getEContainingClass().getName());
				}).findAny().map(e -> (EStructuralFeature) e.getKey()).orElse(null);
	}


	
}
