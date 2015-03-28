package anatlyzer.atl.quickfixast;

import anatlyzer.atl.types.Metaclass;
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

}
