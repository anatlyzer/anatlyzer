package witness.generator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.witness.UseReservedWords;

public class USENameModifyier {

	public String adapt(EClass c, boolean modify) {
		if ( UseReservedWords.isReserved(c.getName()) ) {
			String newWord = UseReservedWords.getReplacement(c.getName());
			if ( modify ) c.setName(newWord);
			return newWord;
		}
		return c.getName();
	}

	public String adapt(EStructuralFeature f, boolean modify) {
		if ( UseReservedWords.isReserved(f.getName()) ) {
			String newWord = UseReservedWords.getReplacement(f.getName());
			if ( modify ) f.setName(newWord);
			return newWord;
		}
		return f.getName();
	}

	public void adapt(EEnum c) {
		for (EEnumLiteral lit : c.getELiterals()) {
			if ( UseReservedWords.isReserved(lit.getName()) ) {
				String newName = UseReservedWords.getReplacement(lit.getName());
				String r = invalidLiteralOrNull(newName);
				lit.setName(r == null ? newName : r);
			} else {
				String r = invalidLiteralOrNull(lit.getName());
				if ( r != null ) {
					lit.setName(r);
				}
			}
		}
	}
	
	public static String invalidLiteralOrNull(String literalName) {
		// This looks like an USE internal error, but we bridge it like this
		// This happens in ./dataset/repos/MDEGroup/QMM-COMLAN-data/validation-subjects/metamodels/abapmapping.ecore
		if ( literalName.contains("_") ) {
			return literalName.replaceAll("_", "o");
		}
		return null;
	}
	
}
