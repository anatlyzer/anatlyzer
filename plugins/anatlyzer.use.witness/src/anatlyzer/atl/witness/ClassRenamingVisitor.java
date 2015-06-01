package anatlyzer.atl.witness;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.IObjectVisitor;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclModelElement;
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
		if ( self.getInferredType() instanceof Metaclass ) {
			Metaclass m = (Metaclass) self.getInferredType();
			EClass newClass = srcMetamodels.getTarget(m.getKlass());
			self.setName(newClass.getName());
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
