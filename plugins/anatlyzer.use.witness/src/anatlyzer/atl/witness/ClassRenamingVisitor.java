package anatlyzer.atl.witness;

import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.processing.AbstractVisitor;

/**
 * Rename classes and also variables.
 * @author jesus
 *
 */
public class ClassRenamingVisitor extends AbstractVisitor implements IObjectVisitor {

	private SourceMetamodelsData srcMetamodels;
	private HashSet<EObject> renamed = new HashSet<EObject>();
	private ErrorSlice slice;
	
	public ClassRenamingVisitor(SourceMetamodelsData srcMetamodels, ErrorSlice slice) {
		this.srcMetamodels = srcMetamodels;
		this.slice = slice;
	}
	
	@Override
	public void perform(EObject root) {
		startVisiting(root);
	}

	@Override
	public void inStaticHelper(StaticHelper self) {
		renameHelper(self);
	}
	
	@Override
	public void inContextHelper(ContextHelper self) {
		renameHelper(self);
	}
	
	public void renameHelper(Helper self) {
		if ( UseReservedWords.isReserved( ATLUtils.getHelperName(self)) ) {
			if ( self.getDefinition().getFeature() instanceof Attribute ) {
				((Attribute) self.getDefinition().getFeature()).setName( UseReservedWords.getReplacement(ATLUtils.getHelperName(self))  );
			} else {
				((Operation) self.getDefinition().getFeature()).setName( UseReservedWords.getReplacement(ATLUtils.getHelperName(self))  );				
			}
		}
		
		
		Type t = self.getInferredReturnType();		
		if ( t instanceof CollectionType ) {
			while ( t instanceof CollectionType ) {
				CollectionType ct = (CollectionType) t;
				t = ct.getContainedType();	
			}
			
			if ( t instanceof Metaclass ) {
				EClass newClass = srcMetamodels.getTarget(((Metaclass) t).getKlass());
				((Metaclass) t).setName(newClass.getName());
			}						
		} else if ( t instanceof Metaclass ) {
			Metaclass m = (Metaclass) t;
			EClass newClass = srcMetamodels.getTarget(m.getKlass());
			if ( newClass != null ) {
				m.setName(newClass.getName());
			} else {
				// This may happen if the helper has a return type a target element 
				// (e.g., as a result of invoking a lazy rule or a resolveTemp)
				System.err.println("Cannot find class " + m.getKlass().getName());
			}
		} else if ( t instanceof EnumType ) {
			EnumType m = (EnumType) t;
			EEnum newEEnum = srcMetamodels.getTarget((EEnum) m.getEenum());		
			// Could be null, see Oclslice
			if ( newEEnum != null ) {
				m.setName(newEEnum.getName());
			}
		}

	}
	
	@Override
	public void inOclModelElement(OclModelElement self) {
		if ( renamed.contains(self) ) {
			return;
		}
		
		/*
		if ( self.getInferredType() instanceof CollectionType ) {
			Type t = self.getInferredType();
			while ( ! (t instanceof CollectionType) ) {
				CollectionType ct = (CollectionType) self.getInferredType();
				t = ct.getContainedType();	
			}
			
			if ( t instanceof Metaclass ) {
				EClass newClass = srcMetamodels.getTarget(((Metaclass) t).getKlass());
				((Metaclass) t).setName(newClass.getName());
				
				copiedMetaclass = renameMetaclass((Metaclass) t);
				
			}			
		} else 
		*/	
		if ( self.getInferredType() instanceof Metaclass ) {
			Metaclass m = (Metaclass) self.getInferredType();
			EClass newClass = srcMetamodels.getTarget(m.getKlass());
			if ( newClass == null ) {
				// It happens in e.g., DSLBridge/XML2DSL.atl, helper findType(s : String) which returns
				// the result of a resolveTemp, thus polluting the path with target elements that
				// do not need to be renamed because they are discarded...
				
				// The other cause is that the classes is part of the target (a dummy value)
				if ( slice != null && slice.getTargetMetaclassesNeededInError().contains(m.getKlass()) ) {
					self.setName("TGT_" + m.getName());
					// Do not change the name because it is not a copy
					// m.setName(self.getName());
				} else {
					System.err.println("No class " + m.getName());					
				}
			} else {
				self.setName(newClass.getName());
				m.setName(newClass.getName());
			}
		} else if ( self.getInferredType() instanceof EnumType ) {
			EnumType m = (EnumType) self.getInferredType();
			EEnum newEEnum = srcMetamodels.getTarget((EEnum) m.getEenum());
			// Could be null, see OclSlice
			if ( newEEnum != null ) {
				self.setName(newEEnum.getName());			
				m.setName(newEEnum.getName());
			}
		}
	}
		
	
//	@Override
//	public void inOclModelElement(OclModelElement self) {
	public void inOclModelElement_old(OclModelElement self) {

		Type copy = null;
		
		/*
		if ( self.getInferredType() instanceof CollectionType ) {
			Type t = self.getInferredType();
			while ( ! (t instanceof CollectionType) ) {
				CollectionType ct = (CollectionType) self.getInferredType();
				t = ct.getContainedType();	
			}
			
			if ( t instanceof Metaclass ) {
				copiedMetaclass = renameMetaclass((Metaclass) t);
				
			}
			
			EcoreUtil.copy(ct);
			
		} else 
		*/	
			
		if ( self.getInferredType() instanceof Metaclass ) {
			Metaclass m = (Metaclass) self.getInferredType();
			Metaclass metaclass = renameMetaclass(m);
		
			self.setName(metaclass.getName());
			
			copy = metaclass;
			
			self.setInferredType(metaclass);
		} else if ( self.getInferredType() instanceof EnumType ) {
			EnumType m = (EnumType) self.getInferredType();
			EEnum newEEnum = srcMetamodels.getTarget((EEnum) m.getEenum());
			self.setName(newEEnum.getName());
			
			// Make a copy
			EnumType enumt = TypesFactory.eINSTANCE.createEnumType();
			enumt.setEenum(m.getEenum());
			enumt.setName(newEEnum.getName());
			enumt.setMetamodelRef(m.getMetamodelRef());
			
			copy = enumt;
			
			self.setInferredType(enumt);
		} else {
			
		}
		
		// Poors man type propagation...
		if ( copy != null ) {
			if ( self.eContainer() instanceof VariableDeclaration ) {
				((VariableDeclaration) self.eContainer()).setInferredType(copy);
			} else if ( self.eContainer() instanceof anatlyzer.atlext.OCL.CollectionType ) {
				// ???
				
			}
		}
	}

	protected Metaclass renameMetaclass(Metaclass m) {
		EClass newClass = srcMetamodels.getTarget(m.getKlass());
//			System.out.println(self.getName());
		
		// Make a copy
		Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
		metaclass.setKlass( m.getKlass() );
		metaclass.setName( newClass.getName() );
		metaclass.setMetamodelRef( m.getMetamodelRef() );
		metaclass.setModel(m.getModel());
		return metaclass;
	}
	
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		if ( UseReservedWords.isReserved(self.getName()) ) {
			self.setName(UseReservedWords.getReplacement(self.getName()));
		}
	}

	@Override
	public void inOperationCallExp(OperationCallExp self) {
		if ( UseReservedWords.isReserved(self.getOperationName()) ) {
			self.setOperationName(UseReservedWords.getReplacement(self.getOperationName()));
		}
	}
	
	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		if ( UseReservedWords.isReserved(self.getVarName()) ) {
			self.setVarName(UseReservedWords.getReplacement(self.getVarName()));
		}
	}
	
	@Override
	public void inIterator(Iterator self) {
		inVariableDeclaration(self);
	}

	
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		if ( UseReservedWords.isReserved(self.getName()) ) {
			self.setName(UseReservedWords.getReplacement(self.getName()));			
		}
	}
	
}
