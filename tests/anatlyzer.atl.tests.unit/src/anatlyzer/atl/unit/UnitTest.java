package anatlyzer.atl.unit;

import static org.junit.Assert.assertTrue;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.examples.api.BaseTest;

public class UnitTest extends BaseTest {
	
	protected String XML2CPL_XML = metamodel("xml2cpl/XML");
	protected String XML2CPL_CPL = metamodel("xml2cpl/CPL");
	
	protected String PNML2PETRINET_PNML = metamodel("pnml2petrinet/PNML_simplified");
	protected String PNML2PETRINET_PETRINET = metamodel("pnml2petrinet/PetriNet");
	
	protected void assertConfirmed(ProblemStatus status) {
		assertTrue( "Confirmed status expected, found " + status, status == ProblemStatus.ERROR_CONFIRMED || status == ProblemStatus.ERROR_CONFIRMED_SPECULATIVE);
	}
	
	protected void assertDiscarded(ProblemStatus status) {
		assertTrue(status == ProblemStatus.ERROR_DISCARDED);
	}
    protected String testr(String dir, String name) {
        return dir + "/" + getClass().getPackage().getName().replace(".", "/") + "/" + name;
    }

    protected String trafo(String name) {
    	if ( !name.endsWith(".atl") ) {
    		name = name + ".atl";
    	}
    	return testr("src", name);
    }

    protected String metamodel(String name) {
    	return "metamodels/" + name + ".ecore";
    }
    
	protected int count(ProblemStatus[] confirmed, ProblemStatus expected) {
		int s = 0;
		for(int i = 0; i < confirmed.length; i++) 
			s += confirmed[i] == expected ? 1 : 0;
		return s;
	}
}
