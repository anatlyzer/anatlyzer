package anatlyzer.atl.editor.quickfix.errors;

import java.util.LinkedList;



import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.text.IDocument;



import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.util.ATLUtils2;
//import anatlyzer.atl.editor.quickfix.util.ATLUtils2;
import anatlyzer.atl.editor.quickfix.util.stringDistance.*;
import anatlyzer.atl.errors.atl_error.NoModelFound;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ATLUtils.ModelInfo;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.StringExp;import anatlyzer.atlext.OCL.impl.OCLFactoryImpl;



/**
 * 
 * @author Usuario
 *
 */
public class NoModelFoundQuickfix_ChooseExistingOne extends AbstractAtlQuickfix {

	public NoModelFound getProblem() {
		try {
			return (NoModelFound) super.getProblem();
		} catch (CoreException ce) {
			
		}
		return null;
	}

	@Override
	public boolean isApplicable(IMarker marker) {		
		return checkProblemType(marker, NoModelFound.class);
	}


	@Override public void resetCache() { }
	
	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();
	}
	
	private String getModelName() {
		NoModelFound nmf = this.getProblem();
		OclModelElement ome = (OclModelElement)nmf.getElement();
		System.out.println(ome.getName());
		return ome.getModel().getName();
	}
	
	private String getClosest() {
		String old = this.getModelName();
		StringDistance sd = new StringDistance(new Levenshtein(), new LongestCommonSubstring());
		List<String> allContaining = this.getAllMetamodelNamesContaining(((OclModelElement)this.getProblem().getElement()).getName());
		
		if (allContaining.size()>0)
			return sd.closest(old, allContaining);		// we found several meta-models containing the element
		else 
			return sd.closest(old, new LinkedList<String>(ATLUtils2.getAllMetamodelNames(this.getATLModel())));	
	}

	private boolean hasClass (ModelInfo m, String className) {
		List<MetamodelNamespace> metamodels = this.getAnalyserData(this.marker).getNamespace().getMetamodels();
						
		for (MetamodelNamespace mm : metamodels) {
			if (! (mm.getName().equals(m.getMetamodelName()))) continue;
			for (EClass ecl : mm.getAllClasses()) {
				if (ecl.getName().equals(className)) return true;
			}
		}
		return false;
	}
	
	private List<String> getAllMetamodelNamesContaining(String name) {
		List<ModelInfo> lmi = ATLUtils.getModelInfo(this.getATLModel());
		return lmi.stream().
				filter(m -> this.hasClass(m, name)).
				map(a -> a.getMetamodelName()).
				collect(Collectors.toList());
	}

	@Override
	public String getDisplayString() {
		NoModelFound nmf = this.getProblem();
		System.out.println("nmf = "+nmf);
		return "Model "+this.getModelName()+" not found, replace by: "+this.getClosest();
	}

	private OclModel getOclModel(String c) {
		for (ModelInfo mi : ATLUtils.getModelInfo(this.getATLModel()) ) {
			if (mi.getMetamodelName().equals(c)) return mi.getModel();
		}
		return null;
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OclModelElement le = (OclModelElement)getProblematicElement();		
		QuickfixApplication qfa = new QuickfixApplication(this);
				
		qfa.replace(le, (expr, trace) -> {
			OclModel closest = getOclModel(this.getClosest());
			
			OclModel newModel = OCLFactory.eINSTANCE.createOclModel();			
			newModel.setName(closest.getMetamodel().getName());
			
			le.setModel(newModel);

			return le;
		});
		return qfa;
	}

	@Override
	public Image getImage() {
		return QuickfixImages.rename.createImage();
	}
}
