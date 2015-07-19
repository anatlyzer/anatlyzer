package anatlyzer.atl.witness;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.TypedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.processing.AbstractVisitor;

public class ClassRenamingVisitor extends AbstractVisitor implements IObjectVisitor {

	private SourceMetamodelsData srcMetamodels;
	private HashSet<EObject> renamed = new HashSet<EObject>();
	
	public ClassRenamingVisitor(SourceMetamodelsData srcMetamodels) {
		this.srcMetamodels = srcMetamodels;
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
			m.setName(newClass.getName());
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
//			if ( newClass == null ) {
//				System.out.println(m.getName());
//			}
				
			self.setName(newClass.getName());
			m.setName(newClass.getName());

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
		if ( self.getUsedFeature() != null ) {
			if ( UseReservedWords.isReserved(self.getName()) ) {
				self.setName(UseReservedWords.getReplacement(self.getName()));
			}
		} else {
			// Is a helper
		}
	}
	
	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		if ( UseReservedWords.isReserved(self.getVarName()) ) {
			self.setVarName(UseReservedWords.getReplacement(self.getVarName()));
		}
	}
	
	@Override
	public void inEnumLiteralExp(EnumLiteralExp self) {
		if ( UseReservedWords.isReserved(self.getName()) ) {
			self.setName(UseReservedWords.getReplacement(self.getName()));			
		}
	}
	
}
