package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.TypedElement;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.processing.AbstractVisitor;

public class ClassRenamingVisitor extends AbstractVisitor implements IObjectVisitor {

	private SourceMetamodelsData srcMetamodels;

	public ClassRenamingVisitor(SourceMetamodelsData srcMetamodels) {
		this.srcMetamodels = srcMetamodels;
	}
	
	@Override
	public void perform(EObject root) {
		startVisiting(root);
	}
	
	@Override
	public void inOclModelElement(OclModelElement self) {
		Type copy = null;
		if ( self.getInferredType() instanceof Metaclass ) {
			Metaclass m = (Metaclass) self.getInferredType();
			EClass newClass = srcMetamodels.getTarget(m.getKlass());
			self.setName(newClass.getName());
//			System.out.println(self.getName());
			
			// Make a copy
			Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
			metaclass.setKlass( m.getKlass() );
			metaclass.setName( newClass.getName() );
			metaclass.setMetamodelRef( m.getMetamodelRef() );
			metaclass.setModel(m.getModel());
		
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
		}
		
		// Poors man type propagation...
		if ( copy != null && self.eContainer() instanceof VariableDeclaration ) {
			((VariableDeclaration) self.eContainer()).setInferredType(copy);
		}
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
	
}
