package anatlyzer.atl.connectionanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.UnconnectedElementsAnalysis.Result;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.unit.UnitTest;

public class TestConnectedElementsAnalysis extends UnitTest {
	String XML = metamodel("ant2maven/XML");
	String MAVEN1 = metamodel("ant2maven/MavenMaven");
	String MAVEN2 = metamodel("ant2maven/MavenProject");
	
	@Test
	public void testBindingResolutionWithUnion() throws Exception {
		String T = trafo("maven2xml_simple");
		typing(T, new Object[] { XML, XML, MAVEN1, MAVEN2 }, new String[] { "XMLMaven", "XMLProject", "MavenMaven", "MavenProject" });
	
		Result r = unconnectedAnalysis();
		
	}
	
}
