package anatlyzer.examples.api;

import anatlyzer.atl.unit.BaseTest;

public class Modisco extends BaseTest {
	public static final String Java2KDM_TRANSFORMATION = "third-party/largeprojects/modisco/java2kdm/javaToKdm.atl";
	public static final String KDM2UML_TRANSFORMATION =  "third-party/largeprojects/modisco/kdm2uml/KDMtoUML.atl";

	public static final String JAVA_METAMODEL  = "third-party/largeprojects/modisco/metamodels/java.ecore";
	public static final String KDM_METAMODEL   = "third-party/largeprojects/modisco/metamodels/kdm.ecore";
	public static final String UML_METAMODEL   = "third-party/largeprojects/modisco/metamodels/UML2_1_0.ecore";	
	
	public static void main(String[] args) throws Exception {
		new Modisco().run();
	}

	private void run() throws Exception {
		String selectedError = "268:4-268:33";
		typing(Java2KDM_TRANSFORMATION, new Object[] { JAVA_METAMODEL, KDM_METAMODEL }, 
				   new String[] { "java", "kdm" });
		
		printStatistics();
		printErrorsByType();
		
        
        generateGraphviz(selectedError);
        generateErrorSlice("java", "tmp_/Java2KDM.slice.ecore", selectedError);
        generateEffectiveMetamodel("java", "tmp_/Java2KDM.effective.ecore");

        // if ( selectedError != null ) sliceTrafo(selectedError);
        
        generateCSP(selectedError);
	}
	
}
