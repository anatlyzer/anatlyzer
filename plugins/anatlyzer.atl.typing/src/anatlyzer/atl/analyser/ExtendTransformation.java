package anatlyzer.atl.analyser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.types.Type;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclContextDefinition;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OclType;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.SequenceType;

/**
 * This is "an action class" to extend a transformation with
 * the EOperations included in the source meta-model.
 * 
 * Special support for UML Profiles is also provided.
 * 
 * @author jesus
 *
 */
public class ExtendTransformation {

	public static final String EOPERATION_TAG = "@eoperation";
	private Unit unit;
	private GlobalNamespace mm;
	private ATLModel trafo;
	private HashMap<MetamodelNamespace, OclModel> models= new HashMap<MetamodelNamespace, OclModel>();
	private IEOperationHandler eOperationHandler = null;

	public ExtendTransformation(ATLModel trafo, GlobalNamespace mm, Unit unit) {
		this.trafo = trafo;
		this.mm = mm;
		this.unit = unit;
	}
	
	public ExtendTransformation withEOperationHandler(IEOperationHandler eOperationHandler) {
		this.eOperationHandler = eOperationHandler;
		return this;
	}
	
	public void perform() {
		HashSet<MetamodelNamespace> inputMetamodels = new HashSet<MetamodelNamespace>();
		
		// Precompute relationship between ATL meta-model declarations and loaded meta-models
		// and gather meta-models acting as input
		if ( unit instanceof Module ) { 
			for(MetamodelNamespace m : mm.getMetamodels()) {
				Module mod = (Module) unit;
				for(OclModel in : mod.getInModels()) {
					if ( in.getMetamodel().getName().equals(m.getName())) {
						models.put(m, in.getMetamodel());						
						inputMetamodels.add(m);
					}
				}
				
				// I need to use also the 
			}
			
			for(MetamodelNamespace m : inputMetamodels) {
				extendWith(m);
			}
		}		
	}

	private void extendWith(MetamodelNamespace m) {
		for(EClass c : m.getAllClasses()) {
			for(EOperation op : c.getEOperations()) {
				try {
					extendWith(c, op);
				} catch ( CannotFindClassForOperation e) {
					// Ignore the operation and notify
					System.out.println("Operation: " + op.getName() + " ignored -> " + e.getMessage());
				}
			}
		}
	}

	private void extendWith(EClass c, EOperation op) {
		if ( eOperationHandler != null && eOperationHandler.canHandle(c, op) ) {
			if ( eOperationHandler.handle(unit, c, op) ) {
				return ;
			}			
		}
		
		if ( op.getEType() == null ) {
			// Operations without return type cannot be invoked in ATL
			return;
		}
		

		ContextHelper helper = ATLFactory.eINSTANCE.createContextHelper();
		helper.getCommentsBefore().add(EOPERATION_TAG);
		OclFeatureDefinition fd  = OCLFactory.eINSTANCE.createOclFeatureDefinition();
		OclContextDefinition ctx = OCLFactory.eINSTANCE.createOclContextDefinition();
		Operation oclOp          = OCLFactory.eINSTANCE.createOperation();
		
		ctx.setContext_(createType(c, false));
		fd.setContext_(ctx);
		fd.setFeature(oclOp);
		
		oclOp.setName(op.getName());
		oclOp.setReturnType(createType(op.getEType(), op.isMany()));
		oclOp.setBody( OCLFactory.eINSTANCE.createJavaBody() );
		
		for(EParameter p : op.getEParameters()) {
			Parameter pnew = OCLFactory.eINSTANCE.createParameter();
			pnew.setVarName(p.getName());
			pnew.setType(createType(p.getEType(), p.isMany()));
			oclOp.getParameters().add(pnew);
		}
		
		helper.setDefinition(fd);
		
		if ( unit instanceof Module ) {
			((Module) unit).getElements().add(helper);
		} else if ( unit instanceof Library ) {
			((Library) unit).getHelpers().add(helper);
		} else {
			// Ignore
		}
		
		
	}

	private class CannotFindClassForOperation extends RuntimeException {
		public CannotFindClassForOperation(String string) {
			super(string);
		}

		private static final long serialVersionUID = 1L;		
	}

	/**
	 * Looks up the classifier in the loaded meta-models, and for that meta-model it finds
	 * the corresponding OclModel element in ATL (representing the meta-model).
	 * @param c
	 * @return
	 */
	private OclModel findOclModel(EClassifier c) {
		for(MetamodelNamespace m : mm.getMetamodels()) {
			// The second check "models.get(m) != null" is needed in case
			// the following interaction: IN : UML, OUT : EMF,
			// but in processing UML#Class.applyStereotype : EObject, 
			// if EMF is processed before the code without the check returns
			// a null because models only contains input models.
			if ( m.belongsTo(c) && models.get(m) != null ) {
				return models.get(m);
			}
		}

		// If it is part of the meta-model, which is always implicitly available
		if ( mm.getMetaMetamodel().belongsTo(c) ) {
			// Any source model is fine
			return models.values().iterator().next();
		}
		
		throw new CannotFindClassForOperation("Could not find type: " + c.getName());		
	}

	private OclType createType(EClassifier eType, boolean isMany) {
		OclType r = null;
		if ( eType instanceof EDataType && ! (eType instanceof EEnum) ) {
			EDataType c = (EDataType) eType;
			String instance = c.getInstanceClassName() == null ? "" : c.getInstanceClassName();
			
			if ( c.getName().endsWith("String") || instance.equals("java.lang.String")) {
				r = OCLFactory.eINSTANCE.createStringType();
			} else if ( c.getName().endsWith("Boolean") ) {
				r = OCLFactory.eINSTANCE.createBooleanType();
			} else if ( c.getName().equals("EInt") || c.getName().endsWith("Integer") || 
					    c.getName().equals("UnlimitedNatural") ||
					    c.getName().endsWith("Long") ) {
				r = OCLFactory.eINSTANCE.createIntegerType();
			} else if ( c.getName().contains("Double") || c.getName().contains("Float")) {
				r = OCLFactory.eINSTANCE.createRealType();
			} else {
				// System.err.println("EOperation with type: " + getClass().getSimpleName() + ":" + "Type [" + c.getName() + "] not supported");
				r = OCLFactory.eINSTANCE.createOclAnyType();
			}
		} else {
			// Covers EClass and EEnum
			OclModelElement t = OCLFactory.eINSTANCE.createOclModelElement();
			t.setName(eType.getName());
			t.setModel(findOclModel(eType));
			if ( t.getModel() == null ) {
				findOclModel(eType);
			}
			r = t;
		}
		
		if ( isMany ) {
			SequenceType seq = OCLFactory.eINSTANCE.createSequenceType();
			seq.setElementType(r);
			r = seq;
		}
		return r;
	}

	public static boolean isAddedEOperation(ModuleElement r) {
		return r.getCommentsBefore().contains(ExtendTransformation.EOPERATION_TAG);
	}
	
	public static interface IEOperationHandler {
		public boolean canHandle(EClass c, EOperation op);
		public boolean handle(Unit unit, EClass c, EOperation op);
	}
	
}
