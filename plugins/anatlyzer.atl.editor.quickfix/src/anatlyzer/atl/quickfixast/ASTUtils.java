package anatlyzer.atl.quickfixast;

import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.RuleWithPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;

public class ASTUtils {

	public static OclModelElement createOclModelElement(Metaclass m) {
		OclModelElement ome = OCLFactory.eINSTANCE.createOclModelElement();
		ome.setName(m.getName());
		OclModel mm = OCLFactory.eINSTANCE.createOclModel();
		mm.setName(	m.getModel().getName() );
		ome.setModel(mm);
		return ome;
	}
	
	public static void completeRule(RuleWithPattern r, Metaclass sourceType, Metaclass targetType) {
		// source pattern
		InPattern p = ATLFactory.eINSTANCE.createInPattern();
		SimpleInPatternElement ipe = ATLFactory.eINSTANCE.createSimpleInPatternElement();
		p.getElements().add(ipe);
		r.setInPattern(p);
		
		OclModelElement ome = ASTUtils.createOclModelElement(sourceType);
		ipe.setType(ome);
		ipe.setVarName(sourceType.getName().substring(0, 1).toLowerCase());
		
		// target pattern
		OclModelElement outOme = ASTUtils.createOclModelElement(targetType);
		
		OutPattern outP = ATLFactory.eINSTANCE.createOutPattern();
		r.setOutPattern(outP);
		SimpleOutPatternElement ope = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
		ope.setVarName(targetType.getName().substring(0, 1).toLowerCase());
		ope.setType(outOme);
		outP.getElements().add(ope);		
	}

}
