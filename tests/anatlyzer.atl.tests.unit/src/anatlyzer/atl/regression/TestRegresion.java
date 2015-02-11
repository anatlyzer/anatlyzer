package anatlyzer.atl.regression;

import org.junit.Test;

import anatlyzer.atl.unit.UnitTest;

public class TestRegresion extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testBindingTargetFeatureNotFound() throws Exception {
		String T = trafo("binding_target_feature_not_found");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" }, true);
	}

	
}
