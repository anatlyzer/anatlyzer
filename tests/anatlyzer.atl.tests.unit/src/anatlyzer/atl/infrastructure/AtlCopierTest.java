package anatlyzer.atl.infrastructure;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.ATLModel.CopiedATLModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class AtlCopierTest  extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void test() throws Exception {
		// Construct an expression
		String T = trafo("atl_copier_trafo");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		AnalysisResult result = getAnalysisResult();		
		ATLModel original        = result.getATLModel();
		
		List<MatchedRule> rules = original.allObjectsOf(MatchedRule.class);
		assertEquals(1, rules.size());
	
		MatchedRule rule = rules.get(0);
		assertEquals("src", rule.getInPattern().getElements().get(0).getVarName());
		InPatternElement inputElement = rule.getInPattern().getElements().get(0);
		
		
		// Try a substitution
		VariableDeclaration varDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
		varDcl.setVarName("x");
		Metaclass changed = TypesFactory.eINSTANCE.createMetaclass();
		changed.setName("X");
		varDcl.setInferredType(changed);
		
		HashMap<EObject, EObject> map = new HashMap<>();
		map.put(inputElement, varDcl);
		
		// Can't change things 
		assertEquals(changed, varDcl.getInferredType());
			OclExpression copy = (OclExpression) new ATLCopier(rule.getInPattern().getFilter()).bindAll(map).copy();
		assertEquals(changed, varDcl.getInferredType());		
	}

}
