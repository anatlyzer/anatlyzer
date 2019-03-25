package witness.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.IWitnessModel;

public class UseFoundWitnessModel implements IWitnessModel {

	private Resource errorMetamodel;
	private Resource foundModel;

	private IAnalyserResult original;
	private Resource originalModel;
	private IMetamodelRewrite data;

	public UseFoundWitnessModel(Resource errorMetamodel, Resource model) {
		this.errorMetamodel = errorMetamodel;
		this.foundModel = model;
	}
	
	@Override
	public Resource getMetamodel() {
		return errorMetamodel;
	}

	@Override
	public Resource getModel() {
		return foundModel;
	}
	
	@Override
	public Set<EPackage> getOriginalMetamodel() {
		IAnalyserResult r = data.getAnalysis();
		List<ModelInfo> srcModels = ATLUtils.getModelInfo(r.getATLModel()).stream().filter(i -> i.isInput()).collect(Collectors.toList());
		
		HashSet<EPackage> s = new HashSet<EPackage>();
		for (ModelInfo mInfo : srcModels) {			
			MetamodelNamespace ns = r.getNamespaces().getNamespace(mInfo.getMetamodelName());
			s.addAll(ns.getLoadedPackages());
		}
		
		return s;
	}
	
	@Override
	public Resource getModelAsOriginal() {
		if ( originalModel == null )
			restablishConformance();
		return originalModel;
	}
	
	@Override
	public void setMetamodelRewritingData(IMetamodelRewrite data) {
		this.data = data;
	}
	
	protected void restablishConformance() {
		HashMap<EObject, EObject> targetToSource = new HashMap<EObject, EObject>();
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.createResource(URI.createURI("witness.xmi"));
		
		List<EObject> allObjects = new ArrayList<EObject>();
		TreeIterator<EObject> it = foundModel.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();			
			EObject asOriginal = copyObject(obj, data);
			if ( asOriginal == null )
				continue;
			allObjects.add(asOriginal);
			targetToSource.put(obj, asOriginal);
		}
		
		it = foundModel.getAllContents();
		while ( it.hasNext() ) {
			EObject obj = it.next();

			if ( targetToSource.containsKey(obj) ) {
				EList<EStructuralFeature> features = obj.eClass().getEAllStructuralFeatures();
				for (EStructuralFeature f : features) {
					if ( f.isDerived() )
						continue;
					setFeature(obj, targetToSource, f, data);
				}
			}
		}

		for (EObject eObject : allObjects) {
			if ( eObject.eContainer() == null ) {
				r.getContents().add(eObject);
			}
		}
		
		this.originalModel = r;
	}
	
	private EEnumLiteral convertEnumLiteral(EEnum original, Object obj) {
		EEnumLiteral lit = (EEnumLiteral) obj;
		return original.getEEnumLiteral(lit.getValue());
	}

	@SuppressWarnings("unchecked")
	private void setFeature(EObject obj, HashMap<EObject, EObject> targetToSource, EStructuralFeature f, IMetamodelRewrite data) {
		EObject orig = targetToSource.get(obj);
		EStructuralFeature origFeature = data.getOriginal(f);
		if ( origFeature == null ) {
			return;
		}
		
		if ( f instanceof EAttribute ) {
			if ( f.getEType() instanceof EEnum ) {
				if ( f.isMany() ) {
					Collection<? extends Object> objects = (Collection<? extends Object>) obj.eGet(f);
					List<EEnumLiteral> literals = objects.stream().map(o -> convertEnumLiteral((EEnum) origFeature.getEType(), o)).collect(Collectors.toList());
					((EList<Object>) orig.eGet(origFeature)).addAll(literals);
				} else {
					orig.eSet(origFeature, convertEnumLiteral((EEnum) origFeature.getEType(), obj.eGet(f)));
				}	
			} else {
				if ( f.isMany() ) {
					((EList<Object>) orig.eGet(origFeature)).addAll((Collection<? extends Object>) obj.eGet(f));
				} else {
					Object value = obj.eGet(f);
					orig.eSet(origFeature, value);
				}			
			}
		} else {
			if ( f.isMany() ) {
				for(EObject pointedObj : (Collection<? extends EObject>) obj.eGet(f)) { 
					((EList<EObject>) orig.eGet(origFeature)).add(getCorresponding(pointedObj, targetToSource));
				}
			} else {
				EObject pointedObj = (EObject) obj.eGet(f);
				if ( pointedObj != null ) {
					EObject origPointedObj = getCorresponding(pointedObj, targetToSource);
					orig.eSet(origFeature, origPointedObj);
				}
			}	
		}
		
		
	}

	private EObject getCorresponding(EObject obj, HashMap<EObject, EObject> targetToSource) {
		if ( ! targetToSource.containsKey(obj) )
			throw new IllegalStateException("Not found: " + obj);
		return targetToSource.get(obj);
	}

	private EObject copyObject(EObject obj, IMetamodelRewrite data) {
		EClass origClass = data.getOriginal(obj.eClass());
		if ( origClass == null )
			return null;
		return EcoreUtil.create(origClass);
	}

	
}
