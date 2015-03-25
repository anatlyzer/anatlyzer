package anatlyzer.atl.unit;

import anatlyzer.atl.witness.IWitnessFinder.WitnessResult;
import anatlyzer.examples.api.BaseTest;

public class UnitTest extends BaseTest {
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
    
	protected int count(WitnessResult[] confirmed, WitnessResult expected) {
		int s = 0;
		for(int i = 0; i < confirmed.length; i++) 
			s += confirmed[i] == expected ? 1 : 0;
		return s;
	}
}
