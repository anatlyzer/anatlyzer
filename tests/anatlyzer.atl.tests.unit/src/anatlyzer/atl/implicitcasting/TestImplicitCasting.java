package anatlyzer.atl.implicitcasting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.errors.atl_error.AccessToUndefinedValue;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.errors.atl_error.BindingWithResolvedByIncompatibleRule;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FeatureNotFound;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.unit.UnitTest;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclExpression;

public class TestImplicitCasting extends UnitTest {
	String ABCD = metamodel("ABCD");
	String WXYZ = metamodel("WXYZ");
	
	@Test
	public void testImplicitCasting() throws Exception {
		String T = trafo("implicit_ocl_kind_of");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());	
		assertTrue(problems().get(0) instanceof FeatureFoundInSubtype);
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(0).getStatus());
	}

	@Test
	public void testImplicitCasting_IfElse() throws Exception {
		String T = trafo("implicit_if_else");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof FeatureFoundInSubtype);
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(0).getStatus());
	}
	

	@Test
	public void testImplicitCasting_IfElseNotKind() throws Exception {
		String T = trafo("implicit_if_else_not_kind");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());		
	}
	
	
	@Test
	public void testImplicitOclIsUndefined() throws Exception {
		String T = trafo("implicit_ocl_is_undefined");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof AccessToUndefinedValue);
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(0).getStatus());
	}
	
	
	@Test
	public void testImplicitInRule() throws Exception {
		String T = trafo("implicit_in_rule");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof FeatureNotFound);
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(0).getStatus());
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

	@Test
	public void testInvalidateFoundInSubtype() throws Exception {
		String T = trafo("invalidate_found_in_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(3, problems().size());		
		
		assertTrue(problems().get(0) instanceof FeatureFoundInSubtype);
		assertEquals(ProblemStatus.WITNESS_REQUIRED, problems().get(0).getStatus());

		assertTrue(problems().get(1) instanceof FeatureFoundInSubtype);
		assertEquals(ProblemStatus.WITNESS_REQUIRED, problems().get(1).getStatus());

		assertTrue(problems().get(2) instanceof FeatureFoundInSubtype);
		assertEquals(ProblemStatus.STATICALLY_CONFIRMED, problems().get(2).getStatus());

	}	

	@Test
	public void testInvalidateOclIsUndefined() throws Exception {
		String T = trafo("invalidate_ocl_is_undefined");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(1, problems().size());		
		assertTrue(problems().get(0) instanceof AccessToUndefinedValue);
		assertEquals(ProblemStatus.WITNESS_REQUIRED, problems().get(0).getStatus());
	}
	
	@Test
	public void testFeatureFoundInSubtype_yields_implicit_casting() throws Exception {
		String T = trafo("feature_found_in_subtype");
		typing(T, new Object[] { ABCD, WXYZ }, new String[] { "ABCD", "WXYZ" });
		
		assertEquals(0, problems().size());
		
		// src.optionalD.propOfD  => The type of src.optionalC was originally D, but has been casted to the union of all subtypes to 
		//                           correctly reflect the fact that it can resolve all calls
		MatchedRule r = (MatchedRule) getModel().getModule().getElements().get(0);
		Binding b     = r.getOutPattern().getElements().get(0).getBindings().get(0);
		
		OclExpression src_optionalD = ((NavigationOrAttributeCallExp) b.getValue()).getSource();
		
		assertTrue(src_optionalD.getInferredType() instanceof UnionType);
		
		UnionType ut = (UnionType) src_optionalD.getInferredType();		
		assertTrue(ut.getPossibleTypes().stream().anyMatch(p -> ((Metaclass) p).getName().equals("D1")));
		assertTrue(ut.getPossibleTypes().stream().anyMatch(p -> ((Metaclass) p).getName().equals("D2")));
		assertTrue(ut.getPossibleTypes().stream().anyMatch(p -> ((Metaclass) p).getName().equals("D3")));		
	}

}
