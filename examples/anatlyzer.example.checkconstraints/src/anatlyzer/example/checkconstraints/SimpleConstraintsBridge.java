package anatlyzer.example.checkconstraints;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.example.checkconstraints.SimpleConstraintsParser.ParseError;
import anatlyzer.example.sconstraints.ConstraintModel;
import anatlyzer.ocl.ui.IConstraintCheckerBridge;

public class SimpleConstraintsBridge implements IConstraintCheckerBridge {

	@Override
	public String getName() {
		return "Simple constraints";
	}

	@Override
	public TranslationResult translate(String text, Resource mmResource) {
		SimpleConstraintsParser parser = new SimpleConstraintsParser();
		ConstraintModel model;
		try {
			model = parser.parse(text);
		} catch (ParseError e) {
			return TranslationResult.error(e.getMessage());
		}
		
		SimpleConstraints2ATL translator = new SimpleConstraints2ATL();
		List<OclExpression> expressions = translator.translate(model);
		return new TranslationResult(expressions);
	}
}
