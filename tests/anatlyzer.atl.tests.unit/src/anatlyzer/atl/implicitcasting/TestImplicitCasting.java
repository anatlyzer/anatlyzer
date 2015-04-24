package anatlyzer.atl.implicitcasting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.analyser.batch.PossibleConflictingRulesNode;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.unit.UnitTest;

public class TestImplicitCasting extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testImplicitCasting() throws Exception {
		String T = trafo("implicit_ocl_kind_of");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());	
		assertTrue(problems().get(0) instanceof FeatureFoundInSubtype);
	}

	@Test
	public void testImplicitCasting_IfElse() throws Exception {
		String T = trafo("implicit_if_else");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
	}
	
	@Test
	public void testImplicitOclIsUndefined() throws Exception {
		String T = trafo("implicit_ocl_is_undefined");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof AccessToUndefinedValue);
	}
	
	
	@Test
	public void testImplicitInRule() throws Exception {
		String T = trafo("implicit_in_rule");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof FeatureNotFound);
	}

	/**
	 * Test the following situation:
	 * <pre>
	 *    Given v : Element, and Element is a supertype of Root,
	 *    
	 *    v.oclIsKindOf(XML!Root) ) then
	 *		  -- must be a root
	 *	  else
   	 *		  -- Since Root is the only subclass of Element, v must be an Element
   	 *    endif
	 * </pre> 
	 * @throws Exception
	 */
	@Test
	public void testFixOfError_XML2CPL() throws Exception {
		String T = trafo("xml2cpl_ocl_kind_of");
		typing(T, new Object[] { XML2CPL_XML, XML2CPL_CPL }, new String[] { "XML", "CPL" });
	
		assertEquals(2, problems().size());
		assertTrue(problems().get(0) instanceof BindingPossiblyUnresolved);
		assertTrue(problems().get(1) instanceof BindingWithResolvedByIncompatibleRule);
	}

}
