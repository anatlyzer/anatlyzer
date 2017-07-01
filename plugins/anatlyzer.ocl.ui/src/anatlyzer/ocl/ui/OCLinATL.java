package anatlyzer.ocl.ui;

import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.emf.EMFModel;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atlext.ATL.Query;
import anatlyzer.ui.util.AtlEngineUtils;

public class OCLinATL implements IConstraintCheckerBridge {

	public TranslationResult exec(String oclExpr) {
		String aQuery = "query dummy = \n" + oclExpr + ";";

		EMFModel libModel = AtlEngineUtils.loadATLText(aQuery);
		if ( libModel.getResource().getErrors().size() > 0 ) {
			System.out.println(libModel.getResource().getErrors());
			return TranslationResult.error(libModel.getResource().getErrors().stream().map(o -> o.toString()).collect(Collectors.toList()));
		}
		ATLModel model = new ATLModel(libModel.getResource());
		Query q = (Query) model.getRoot();
		return new TranslationResult(q.getBody());
	}

	@Override
	public String getName() {
		return "ATL/OCL";
	}

	@Override
	public TranslationResult translate(String text, Resource mmResource) {
		return exec(text);
	}
	
	
	
}
