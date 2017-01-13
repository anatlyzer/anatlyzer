package anatlyzer.atl.commands;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ATLPackage;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OCLFactory;

public class HOTGenerator {

	private static int ATL_PACKAGE = 0;
	private static int OCL_PACKAGE = 1;
	
	private ATLModel model;
	private StringBuffer buffer;

	public HOTGenerator(ATLModel model) {
		this.model = model;
		this.buffer = new StringBuffer();
	}

	public void generate() {
		List<Helper> helpers = this.model.allObjectsOf(Helper.class);
		helpers.forEach(h -> transformHelper(h));
	}

	private void transformHelper(Helper h) {
		if ( ATLUtils.isContextHelper(h) ) {
			String helperVar = newInstance(h, "h", ATL_PACKAGE);
		}		
	}

	protected String newInstance(EObject c, String varName, int packageType) {
		return newInstance(c.eClass(), varName, packageType);
	}

	protected String newInstance(EClass c, String varName, int packageType) {
		String factory = "ATLFactory.eINSTANCE.";
		if ( packageType == OCL_PACKAGE ) {
			factory = "OCLFactory.eINSTANCE.";
		}
		return factory + c.getName();
	}
	
}
