package anatlyzer.ocl.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atlext.OCL.OclExpression;

public interface IConstraintCheckerBridge {

	String getName();

	TranslationResult translate(String text, Resource mmResource);

	
	public static class TranslationResult {
		private List<OclExpression> expressions = new ArrayList<>();
		private List<String> errors = new ArrayList<String>();
		private boolean hasErrors = false;

		public TranslationResult() { }

		public TranslationResult(OclExpression exp) {
			this.expressions.add(exp);
		}

		public TranslationResult(List<OclExpression> exp) {
			this.expressions.addAll(exp);
		}

		public void setErrors(List<String> errors) {
			this.errors.addAll(errors);
			this.hasErrors = true;
		}
		
		public boolean hasErrors() {
			return hasErrors;
		}
		
		public List<OclExpression> getExpressions() {
			return expressions;
		}
		
		public List<String> getErrors() {
			return errors;
		}

		public static TranslationResult error(String message) {
			TranslationResult r = new TranslationResult();
			r.setErrors(Collections.singletonList(message));
			return r;
		}

		public static TranslationResult error(List<String> errors) {
			TranslationResult r = new TranslationResult();
			r.setErrors(errors);
			return r;
		}
		
	}
}
