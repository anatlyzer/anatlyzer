package anatlyzer.examples.api;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.tests.api.AnalysisLoader;
import anatlyzer.atl.tests.api.AtlLoader;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

/**
 * This example shows how to the extract the footprint of a transformation
 * 
 * @author jesus
 */
public class UML2Java_ExtractFootprint {
	public static final String TRANSFORMATION = "examples/uml2java/trafo/uml2java.atl";

	public static final String UML_METAMODEL  = "examples/uml2java/metamodels/UML2_3_0_0.ecore";
	public static final String JAVA_METAMODEL   = "examples/uml2java/metamodels/java.ecore";
	
	public static void main(String[] args) throws Exception {
		new UML2Java_ExtractFootprint().run();
	}

	private void run() throws Exception {
		Resource atlTrafo = AtlLoader.load(TRANSFORMATION);
		AnalysisLoader loader = AnalysisLoader.fromResource(atlTrafo, new Object[] { UML_METAMODEL, JAVA_METAMODEL },  new String[] { "UML", "Java" });

		AnalysisResult result = loader.analyse();
				
		// Extract the footprint		
        XMIResourceImpl r =  new XMIResourceImpl(URI.createURI("examples/uml2java/metamodels/UML_footprint.ecore"));
        TrafoMetamodelData data = new TrafoMetamodelData(result.getATLModel(), result.getNamespace().getNamespace("UML"));
        new EffectiveMetamodelBuilder(data).extractSource(r, "uml_footprint", "http://uml_footprint", "uml_footprint", "UML Footprint");
        r.save(null);
	}

	
	
}
