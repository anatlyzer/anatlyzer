package anatlyzer.atl.problemgraph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.errors.atl_error.NoBindingForCompulsoryFeature;
import anatlyzer.atl.graph.ProblemGraph;
import anatlyzer.atl.graph.ProblemGraph.IProblemTree;
import anatlyzer.atl.graph.ProblemGraph.IProblemTreeNode;
import anatlyzer.atl.unit.UnitTest;

public class TestProblemGraph extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testProblemGraphOrdering() throws Exception {
		String T = trafo("problem_graph_ordering");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });

		ProblemGraph graph = getProblemGraph();
		assertEquals(3, problems().size());
		assertEquals(3, graph.getProblemPaths().size());

		IProblemTree tree = graph.getProblemTree();
		assertEquals(2, tree.getNodes().size());
		
		IProblemTreeNode featureNotFound   = null;
		IProblemTreeNode compulsoryFeature = null;
		
		for(IProblemTreeNode n : tree.getNodes()) {
			if ( n.getProblem() instanceof FeatureNotFound ) featureNotFound = n;
			if ( n.getProblem() instanceof NoBindingForCompulsoryFeature ) compulsoryFeature = n;
		}
		
		assertNotNull(featureNotFound);
		assertNotNull(compulsoryFeature);
		
		assertEquals(1, featureNotFound.getNodes().size());
		assertTrue(featureNotFound.getNodes().get(0).getProblem() instanceof BindingPossiblyUnresolved);
		assertTrue(featureNotFound.getNodes().get(0).getNodes().isEmpty());		
	}

	
}
