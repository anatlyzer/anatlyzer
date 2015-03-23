package witness.generator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class USENameModifyier {

	public String adapt(EClass c, boolean modify) {
		if ( c.getName().equals("Set") ) {
			if ( modify ) c.setName("Set_");
			return "Set_";
		}
		return c.getName();
	}

	public String adapt(EStructuralFeature f, boolean modify) {
		if ( f.getName().equals("max") ) {
			if ( modify ) f.setName("max_");
			return "max_";
		}
		else if ( f.getName().equals("enum") ) {
			
			if ( modify ) f.setName("enum_");
			return "enum_";
		
		}
		return f.getName();
	}
	
}
