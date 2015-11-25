package anatlyzer.atl.typing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;

public class TestTyping extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	
	@Test
	public void testOclAnyDeclared() throws Exception {
		String T = trafo("typing_ocl_any_declared");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());
	
		Module module = getModel().getModule();
		Helper helper1 = (Helper) module.getElements().get(0);
		Helper helper2 = (Helper) module.getElements().get(1);
		
		assertTrue(helper1.getInferredReturnType() instanceof Metaclass);
		assertTrue(helper2.getInferredReturnType() instanceof SequenceType &&
				   ((CollectionType) helper2.getInferredReturnType()).getContainedType() instanceof Metaclass);		
	}

	
	@Test
	public void testFeatureFoundInAllSubtypes() throws Exception {
		String T = trafo("typing_feature_found_in_all_subtypes");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		// TODO: Create another test in which the problem is in a non-abstract class
		assertEquals(0, problems().size());
	}
	
	@Test
	public void testOperationFoundInAllSubtypes() throws Exception {
		String T = trafo("typing_operation_found_in_all_subtypes");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		// TODO: Create another test in which the problem is in a non-abstract class
		assertEquals(0, problems().size());
	}
	
	@Test
	public void testOperationFoundInAllSubtypesExceptOne() throws Exception {
		String T = trafo("typing_operation_found_in_all_subtypes_except_one");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());
		assertTrue(problems().get(0) instanceof OperationFoundInSubtype);
		
	}

}
