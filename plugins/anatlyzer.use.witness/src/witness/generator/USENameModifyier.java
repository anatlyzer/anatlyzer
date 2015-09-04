package witness.generator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.witness.UseReservedWords;

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

	public void adapt(EEnum c) {
		for (EEnumLiteral lit : c.getELiterals()) {
			if ( UseReservedWords.isReserved(lit.getName()) ) {
				lit.setName(UseReservedWords.getReplacement(lit.getName()));
			}
		}
	}
	
}
