package anatlyzer.atl.editor.quickfix.errors;

import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.errors.atl_error.NoClassFoundInMetamodel;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.OclModelElement;

public class NoClassFoundInMetamodelQuickFix_ChangeMetamodel extends AbstractMetamodelChangeQuickfix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, NoClassFoundInMetamodel.class);
	}

	@Override public void resetCache() { }
	
	@Override
	public String getDisplayString() {
		return "Create meta-class in meta-model";
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.create_class.createImage();
	}
	
	public String getChangedMetamodel() {
		OclModelElement me = (OclModelElement) getProblematicElement();
		String mmName = me.getModel().getName();
		return mmName;
	};
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		OclModelElement me = (OclModelElement) getProblematicElement();
		String mmName = me.getModel().getName();
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		
		Set<EPackage> packages = getAnalysisResult().getNamespace().getNamespace(mmName).getLoadedPackages();
		EPackage aPackage = packages.stream().filter(p -> p.getESuperPackage() == null ).findAny().orElse(null);
		if ( aPackage != null ) {
			qfa.mmModify(aPackage, mmName, (pkg) -> {
				EClass newClass = EcoreFactory.eINSTANCE.createEClass();
				newClass.setName(me.getName());
				pkg.getEClassifiers().add(newClass);
			});
		}
		
		return qfa;
	}

}
