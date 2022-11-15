package anatlyzer.atl.efinder;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Test;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.efinder.witness.EFinderWitnessFinder;

public class TestEFinder extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	

	@Test
	public void testEvaluation() throws Exception {
		String T = trafo("constraints");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		AnalysisResult analysis = getAnalysisResult();
		ATLModel model = analysis.getATLModel();
		Helper c1 = ATLUtils.getAllHelpers(model, (h) -> ATLUtils.getHelperName(h).equals("constraint1")).get(0);

		List<OclExpression> expressions = new ArrayList<>();
		expressions.add(ATLUtils.getBody(c1));
		
		EFinderWitnessFinder finder = new EFinderWitnessFinder();
		
		
		ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withExpr(expressions).
				// withRequiredHelpers(copiedHelpers).					
				withFinder(finder).
				withGlobal("thisModule");

		for(Map.Entry<String, Resource> mm : analysis.getNamespace().getLogicalNamesToMetamodels().entrySet()) {
			checker.configureMetamodel(mm.getKey(), mm.getValue());
		}

		checker.check();
		assertTrue(checker.getFinderResult() == ProblemStatus.ERROR_CONFIRMED);
		
		IWitnessModel witness = checker.getWitnessModel();
		Resource r = witness.getModel();
		assertTrue(r.getContents().size() > 0);
	}
		
}
