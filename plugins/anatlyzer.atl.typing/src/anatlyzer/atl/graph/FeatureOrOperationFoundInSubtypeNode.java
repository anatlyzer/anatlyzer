package anatlyzer.atl.graph;

import org.eclipse.emf.ecore.EClass;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.errors.atl_error.FeatureFoundInSubtype;
import anatlyzer.atl.errors.atl_error.FoundInSubtype;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.PropertyCallExp;

public class FeatureOrOperationFoundInSubtypeNode<P extends LocalProblem> extends ExpressionProblemNode<P> {

	public FeatureOrOperationFoundInSubtypeNode(P p, OclExpression expr) {
		super(p, expr);
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// Do not call super, it may make a problematic helper call be included in the path, leading
		// to dependent errors to be included as well, when do not need to, example:
		// * No problematic case (CHIN found in subtype):
		//    helper context KM3!Classifier
		//       def : NOC() : Integer = self.CHIN();
		// * Problematic case (CHIN found in subtype too):
		//    helper context KM3!Classifier
		//       def : NOC() : Integer = self.CHIN() + self.operation_with_problems_inside();
		// 
		//  This is because operation_with_problems_inside will be included in the USE file, 
		//  being problematic and unnecesary for the witness.
		//
		// super.genErrorSlice(slice);
		generatedDependencies(slice);
		//if ( problem instanceof FeatureFoundInSubtype ) {
		if ( problem instanceof FoundInSubtype ) {
			for(EClass c : ((FeatureFoundInSubtype) problem).getPossibleClasses()) {
				slice.addMetaclassNeededInError(c);				
				
				break; 
				// TODO: Adding only one for the moment, I could add more depending on certain conditions... this also affects genCSP, which considers that the first one is used
			}			
		}
	}
	
	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		FoundInSubtype p = (FoundInSubtype) this.problem;
		PropertyCallExp pc = (PropertyCallExp) expr;
		EClass oneSubClass = p.getPossibleClasses().get(0);
		
		return model.negateExpression(model.createKindOf_AllInstancesStyle(model.gen(pc.getSource()), null, oneSubClass));
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		FoundInSubtype p = (FoundInSubtype) this.problem;
		PropertyCallExp pc = (PropertyCallExp) expr;
		EClass oneSubClass = p.getPossibleClasses().get(0);
		
		// TODO: Generate an or with all the classes!
		
		OclExpression receptor = model.gen(pc.getSource());
		String modelName = ((Metaclass) receptor.getInferredType()).getModel().getName();
		return model.createKindOf(receptor, modelName, oneSubClass.getName(), null);
	}
	
	@Override
	public boolean isStraightforward() {
		return false;
	}

	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visitProblem(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
	}
	
}
