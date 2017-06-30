package anatlyzer.ocl.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atlext.OCL.OclExpression;

public interface IConstraintCheckerBridge {

	String getName();

	TranslationResult translate(String text, Resource mmResource);

	
	public static class TranslationResult {
		private OclExpression exp;
		private List<String> errors = new ArrayList<String>();
		private boolean hasErrors;
		
		public TranslationResult(OclExpression exp) {
			this.exp = exp;
			this.hasErrors = false;
		}

		public TranslationResult(List<String> errors) {
			this.errors.addAll(errors);
			this.hasErrors = true;
		}

		public boolean hasErrors() {
			return hasErrors;
		}
		
		public OclExpression getExpression() {
			return exp;
		}
		
		public List<String> getErrors() {
			return errors;
		}
		
	}
}
