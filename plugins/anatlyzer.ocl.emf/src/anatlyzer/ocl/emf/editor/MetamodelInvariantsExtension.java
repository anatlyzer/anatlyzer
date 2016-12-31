package anatlyzer.ocl.emf.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;

import anatlyzer.atl.analyser.AnalyserExtension;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.analysisext.AnalysisProvider;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.ocl.emf.OCLtoATL;

public class MetamodelInvariantsExtension implements AnalysisProvider, AnalyserExtension {

	public MetamodelInvariantsExtension() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPreparationTask() {
		return true;
	}
	
	@Override
	public List<AnalyserExtension> getExtensions(ATLModel m, GlobalNamespace ns) {
		return Collections.singletonList(this);
	}

	@Override
	public void perform(ATLModel model, GlobalNamespace mm, Unit root) {
		if ( ! (root instanceof Module) ) {
			return;
		}
		
		Module mod = (Module) root;
		if ( mod.getCommentsBefore().stream().noneMatch(s -> s.contains("@enable_invariants") ) )
			return;
		
		Set<String> srcNames = ATLUtils.getSourceMetamodelNames(model);
		Set<String> tgtNames = ATLUtils.getTargetMetamodelNames(model);

		for (MetamodelNamespace ns : mm.getMetamodels()) {
			for (EClass eClass : ns.getAllClasses()) {
				List<anatlyzer.atlext.ATL.ContextHelper> helpers = extractOCL(ns.getName(), eClass);
				for (anatlyzer.atlext.ATL.ContextHelper helper : helpers) {

					// helper.getCommentsBefore().add("@invariant mm=\"" + mmName+ "\"");

					
					StaticHelper staticHelper = AnalyserUtils.convertContextInvariant(helper);
					String mmName = ns.getName();
					if ( srcNames.contains(mmName)) {
						staticHelper.getCommentsBefore().add("@source_invariant mm=\"" + mmName+ "\""); 						
						// To make it work without any changes...
						staticHelper.getCommentsBefore().add("@precondition"); 						
					}
					if ( tgtNames.contains(mmName)) {
						staticHelper.getCommentsBefore().add("@target_invariant mm=\"" + mmName+ "\"");
					}
					
					mod.getElements().add(staticHelper);
					
					// System.out.println("MERGED:");
					// System.out.println(ATLSerializer.serialize(helper));
				}
			}
		}
	}

	private List<anatlyzer.atlext.ATL.ContextHelper> extractOCL(String mmName, EClass c) {
		ArrayList<anatlyzer.atlext.ATL.ContextHelper> helpers = new ArrayList<>();
		EAnnotation ann = c.getEAnnotation("http://www.eclipse.org/emf/2002/Ecore/OCL");
		if ( ann == null ) {
			ann = c.getEAnnotation("http://www.eclipse.org/ocl/examples/OCL");
		}
		
		if ( ann != null ) {
			OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);			
			Helper helper = ocl.createOCLHelper();

			for (Entry<String, String> entry : ann.getDetails().entrySet()) {
				String invName = entry.getKey();
				String inv = entry.getValue();
				try {				
					helper.setContext(c);
					Constraint constraint = helper.createInvariant(inv);
					constraint.setName(invName);
					
					helpers.add(new OCLtoATL().transform(mmName, constraint));
				} catch ( ParserException e ) {
					System.err.println("Problem in " + c.getName() + "." + invName + ": " + e.getMessage());
				}
			}
		}

		return helpers;
	}

}
