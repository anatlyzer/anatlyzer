package anatlyzer.atl.analyser.inc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import anatlyzer.atl.analyser.Analyser;
import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ATLModel.CopiedATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.BindingStat;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class IncrementalAnalyser extends Analyser {
	
	private AnalysisResult baseAnalysis;

	public IncrementalAnalyser(AnalysisResult baseAnalysis) {
		super(baseAnalysis.getNamespace(), createNewModel(baseAnalysis.getATLModel()));
		this.baseAnalysis = baseAnalysis;
	}
	
	public IncrementalAnalyser(AnalysisResult baseAnalysis, List<String> metamodelsToCopy) {
		super(null, createNewModel(baseAnalysis.getATLModel()));
		this.mm = adaptNamespace(baseAnalysis.getNamespace(), metamodelsToCopy);
		this.baseAnalysis = baseAnalysis;
	}

	private GlobalNamespace adaptNamespace(GlobalNamespace namespace, List<String> metamodelsToCopy) {
		HashSet<Resource> resources = new HashSet<Resource>(namespace.getLogicalNamesToMetamodels().values());
		HashMap<String, Resource> newMap = new HashMap<String, Resource>(namespace.getLogicalNamesToMetamodels());
		for (String mmName : metamodelsToCopy) {
			Resource r = newMap.get(mmName);
			resources.remove(r);
			
			Resource newResource = new XMIResourceImpl();
			
		    Copier copier = new Copier();
		    Collection<EObject> result = copier.copyAll(r.getContents());
		    copier.copyReferences();
			
			newResource.getContents().addAll(result);
			
			// Replace references in the transformation model
			replaceReferences(mmName, copier);
			
			newMap.put(mmName, newResource);
			resources.add(newResource);
		}
		
		return new GlobalNamespace(resources, newMap);
	}

	protected void replaceReferences(String mmName, Copier copier) {
		for (EObject obj : trafo.getResource().getContents()) {
			if ( obj instanceof Metaclass && 
					((Metaclass) obj).getModel() != null && 
					((Metaclass) obj).getModel().getName().equals(mmName)) {
				EClass original = ((Metaclass) obj).getKlass();
				EClass target   = (EClass) copier.get(original);
				if ( target == null ) {
					throw new IllegalStateException("No target EClass " + ((Metaclass) obj).getName() + ": " + original);
				}					
				((Metaclass) obj).setKlass(target);
			}
		}
		
		trafo.getRoot().eAllContents().forEachRemaining(obj -> {			
			if ( obj instanceof PropertyCallExp ) {
				EStructuralFeature f = (EStructuralFeature) ((PropertyCallExp) obj).getUsedFeature();		
				if ( f != null ) {					
					EStructuralFeature newF = (EStructuralFeature) copier.get(f);
					// Be aware that if newF is null then it is because it belongs to a model not
					// being updated!
					if ( newF != null ) {
						((PropertyCallExp) obj).setUsedFeature(newF);
					}
				} 
			} else if ( obj instanceof Binding ) {
				EStructuralFeature f = (EStructuralFeature) ((Binding) obj).getWrittenFeature();
				if ( f != null ) {					
					EStructuralFeature newF = (EStructuralFeature) copier.get(f);
					// Be aware that if newF is null then it is because it belongs to a model not
					// being updated!
					if ( newF != null ) {
						((Binding) obj).setWrittenFeature(newF);
					}
				} 
			}
		});
	}
	
	private static ATLModel createNewModel(ATLModel atlModel) {
		CopiedATLModel newModel = atlModel.copyAll();
		// newModel.clear();
		return newModel;
	}

	public CopiedATLModel getNewModel() {
		return (CopiedATLModel) trafo;
	}
	
	@Override
	public void perform() {
		trafo.clear();
		super.perform();
	}

}
