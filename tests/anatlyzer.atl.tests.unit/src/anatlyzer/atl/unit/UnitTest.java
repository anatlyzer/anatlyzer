package anatlyzer.atl.unit;

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
}
