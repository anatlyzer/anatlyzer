package witness.generator;

import org.eclipse.emf.ecore.EClass;

public class USENameModifyier {

	public String adapt(EClass c, boolean modify) {
		if ( c.getName().equals("Set") ) {
			if ( modify ) c.setName("Set_");
			return "Set_";
		}
		return c.getName();
	}
	
}
