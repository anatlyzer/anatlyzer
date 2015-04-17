package anatlyzer.atl.editor.quickfix.errors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.errors.atl_error.IncoherentHelperReturnType;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;

public class IncoherentHelperReturnTypeQuickfix_AssignInferredType extends AbstractAtlQuickfix {
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, IncoherentHelperReturnType.class);
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();			
		new InDocumentSerializer(qfa, document).serialize();
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Change return type";
	}

	@Override
	public String getDisplayString() {
		return "Change declared type with inferred type";
	}

	@Override
	public QuickfixApplication getQuickfixApplication() {
		Operation operation  = (Operation)getProblematicElement();
		OclType   returnType = operation.getReturnType();
		Helper    helper     = ATLUtils.getContainer(operation, Helper.class);
		
		QuickfixApplication qfa = new QuickfixApplication();
		qfa.replace(returnType, (type, trace) -> {
			return ATLUtils.getOclType(helper.getInferredReturnType());
		});
		return qfa;
	}

//	private OclType getOclType (Type t) {
//		if (t == null) throw new IllegalArgumentException();
//		
//		if (t instanceof StringType)       return OCLFactory.eINSTANCE.createStringType();
//		if (t instanceof BooleanType)      return OCLFactory.eINSTANCE.createBooleanType();
//		if (t instanceof IntegerType)      return OCLFactory.eINSTANCE.createIntegerType();
//		if (t instanceof FloatType)        return OCLFactory.eINSTANCE.createRealType();
//		if (t instanceof Unknown)          return OCLFactory.eINSTANCE.createOclAnyType();
//		if (t instanceof OclUndefinedType) return OCLFactory.eINSTANCE.createOclAnyType();
//		if (t instanceof MapType)          return OCLFactory.eINSTANCE.createMapType();      // types?
//		if (t instanceof TupleType)        return OCLFactory.eINSTANCE.createTupleType();    // types?
//        if (t instanceof CollectionType) {
//        	anatlyzer.atlext.OCL.CollectionType oclType = null;        	
//        	if      (t instanceof SequenceType) oclType = OCLFactory.eINSTANCE.createSequenceType();
//        	else if (t instanceof SetType)      oclType = OCLFactory.eINSTANCE.createSequenceType();
//        	else    return OCLFactory.eINSTANCE.createOclAnyType();        	
//        	oclType.setElementType( getOclType (((CollectionType)t).getContainedType()) );
//			return oclType;
//		}
//        if (t instanceof Metaclass) {
//        	OclModelElement oclType  = OCLFactory.eINSTANCE.createOclModelElement();
//			OclModel        oclModel = OCLFactory.eINSTANCE.createOclModel();			
//			oclModel.setName(((Metaclass) t).getModel().getName());
//        	oclType.setName(((Metaclass)t).getName());
//			oclType.setModel(oclModel);
//        	return oclType;
//        }
//		//if (t instanceof UnionType) return ...
//        //if (t instanceof EnumType)  return ...
//        
//		throw new UnsupportedOperationException(t.getClass().getName());
//	}
}
