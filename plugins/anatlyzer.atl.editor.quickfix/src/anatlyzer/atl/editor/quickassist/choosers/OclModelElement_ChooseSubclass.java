package anatlyzer.atl.editor.quickassist.choosers;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.editor.quickassist.AbstractQuickAssistSet;
import anatlyzer.atl.editor.quickfix.AtlQuickAssist;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclModelElement;

public class OclModelElement_ChooseSubclass extends AbstractQuickAssistSet  {
		
	@Override
	public boolean isApplicable() {
		return element instanceof OclModelElement;
	}

	@Override
	public AtlQuickAssist[] getPossibleAssists() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<AtlQuickAssist> getQuickAssists() {
		OclModelElement m = (OclModelElement) element;
		Metaclass c = (Metaclass) m.getInferredType();
		return ASTUtils.getMetamodelClasses(c).stream().filter(aClass -> {
			return aClass != c.getKlass() && (aClass.isSuperTypeOf(c.getKlass()) || c.getKlass().isSuperTypeOf(aClass));
		}).map(aClass -> {
			return new ChooseClass(aClass);
		}).collect(Collectors.toList());
	}

	public class ChooseClass extends anatlyzer.atl.editor.quickassist.AbstractAtlQuickAssist {

		private EClass klass;

		public ChooseClass(EClass c) {
			this.klass = c;
		}
		
		@Override
		public boolean isApplicable() {
			return true;
		}

		@Override
		public String getDisplayString() {
			return "Replace with " + klass.getName();
		}

		@Override
		public void resetCache() { }

		@Override
		public QuickfixApplication getQuickfixApplication() throws CoreException {
			QuickfixApplication qa = new QuickfixApplication(this);
			OclModelElement e = (OclModelElement) element;			
			qa.replace(e, (original, trace) -> {
				OclModelElement t = OCLFactory.eINSTANCE.createOclModelElement();
				t.setName(klass.getName());
				t.setModel(original.getModel());
				
				return t;
			});
			return qa;
		}
		
	}

}
