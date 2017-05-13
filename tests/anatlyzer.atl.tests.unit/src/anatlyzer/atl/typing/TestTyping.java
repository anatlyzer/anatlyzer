package anatlyzer.atl.typing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.atl_error.OperationFoundInSubtype;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.MapType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.SequenceType;
import anatlyzer.atl.types.StringType;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.OclExpression;

public class TestTyping extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	String XML = metamodel("xml2dsl/XML");
	String DSL = metamodel("xml2dsl/DSL");
	
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

	@Test
	public void testMapCommonType() throws Exception {
		String T = trafo("test_map_common_type");
		typing(T, new Object[] { XML, DSL }, new String[] { "XML", "DSL" });
		
		StaticHelper helper = getModel().allObjectsOf(StaticHelper.class).get(0);
		OclExpression body = ATLUtils.getBody(helper);
		System.out.println(TypeUtils.typeToString(body.getInferredType()));
		
		// We want to make sure that the analysis of the iterate expression
		// yields a proper return type in the form of a map type, instead
		// of a union of maps
		assertTrue(body.getInferredType() instanceof MapType);
		MapType mapType = (MapType) body.getInferredType();
		assertTrue(mapType.getKeyType() instanceof StringType);
		assertTrue(mapType.getValueType() instanceof Metaclass);
	}
}
