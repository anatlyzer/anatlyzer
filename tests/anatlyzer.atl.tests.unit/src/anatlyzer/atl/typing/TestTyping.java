package anatlyzer.atl.typing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	
}
