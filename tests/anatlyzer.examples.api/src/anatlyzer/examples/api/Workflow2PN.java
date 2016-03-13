package anatlyzer.examples.api;

import anatlyzer.atl.unit.BaseTest;

public class Workflow2PN extends BaseTest {
	public static final String TRANSFORMATION = "examples/workflow2pn/trafo/workflow2pn.atl";

	public static final String WF_METAMODEL  = "examples/workflow2pn/metamodels/workflow.ecore";
	public static final String PN_METAMODEL   = "examples/workflow2pn/metamodels/petri_nets.ecore";
	
	public static void main(String[] args) throws Exception {
		new Workflow2PN().run();
	}

	private void run() throws Exception {
		typing(TRANSFORMATION, new Object[] { WF_METAMODEL, PN_METAMODEL }, 
				   new String[] { "WF", "PN" });

		// 99:4-99:21
		
		printStatistics();
		printErrorsByType();
	}
	
}
