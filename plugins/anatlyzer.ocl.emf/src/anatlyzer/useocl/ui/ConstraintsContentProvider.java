package anatlyzer.useocl.ui;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;

import anatlyzer.ocl.emf.OclEMFUtils;

public class ConstraintsContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() { }

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }

	@Override
	public Object[] getElements(Object inputElement) {
		if ( inputElement instanceof OclDocumentData ) {
			OclDocumentData doc = (OclDocumentData) inputElement;
			List<InvariantData> invs = doc.getInvariants();
			return invs.toArray(new InvariantData[invs.size()]);
		}
		return new Object[0];
	}
	
	public static class InvariantData {
		private ConstraintCS constraint;
		private boolean selected = true;

		public InvariantData(ConstraintCS c) {
			this.constraint = c;
		}
		
		public ConstraintCS getConstraint() {
			return constraint;
		}
		
		public boolean isSelected() {
			return selected;
		}
		
		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public String getInvariantName() {
			return constraint.getName();
		}

		public String getClassName() {
			return ((ClassifierContextDeclCS) constraint.eContainer()).getReferredClass().getName();
		}
	}

	public static class OclDocumentData {
		private CompleteOCLDocumentCS doc;
		private List<InvariantData> invs;
		private List<DefCS> operations;

		public OclDocumentData(CompleteOCLDocumentCS doc) {
			this.doc = doc;
			this.invs = OclEMFUtils.getInvariants(doc).stream().map(InvariantData::new).collect(Collectors.toList());
			this.operations = OclEMFUtils.getOperations(doc);
		}
		
		public List<InvariantData> getInvariants() {
			return invs;
		}

		public List<DefCS> getOperations() {
			return operations;
		}
		
		public CompleteOCLDocumentCS getDoc() {
			return doc;
		}
	}
	
}
