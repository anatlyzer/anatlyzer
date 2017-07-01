package anatlyzer.example.checkconstraints;

import java.util.ArrayList;
import java.util.List;

import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.example.sconstraints.ConstraintModel;
import anatlyzer.example.sconstraints.NumClassesConstraint;
import anatlyzer.example.sconstraints.NumConstraint;
import anatlyzer.example.sconstraints.NumRefsConstraint;

/**
 * This class implements a translator from the simple
 * constraint language to ATL.
 * 
 * @author jesus
 *
 */
public class SimpleConstraints2ATL {
	public static final String METAMODEL_NAME = "MM";
	
	public List<OclExpression> translate(ConstraintModel model) {
		ArrayList<OclExpression> expressions = new ArrayList<>();
		
		for (NumConstraint constraint : model.getConstraints()) {
			if ( constraint instanceof NumClassesConstraint ) {
				expressions.add(translate((NumClassesConstraint) constraint));
			} else if ( constraint instanceof NumRefsConstraint ) {
				expressions.add(translate((NumRefsConstraint) constraint));
			}
		}
		
		return expressions;
	}


	private OclExpression translate(NumClassesConstraint constraint) {
		OperationCallExp allInstances = createAllInstances(METAMODEL_NAME, constraint.getClassName());
		CollectionOperationCallExp sizeOp = createSizeOperation(allInstances);
		
		IntegerExp integerLiteral = createIntegerLiteral(constraint.getNumber());
		
		OperatorCallExp gte = OCLFactory.eINSTANCE.createOperatorCallExp();
		gte.setOperationName(">=");
		gte.setSource(sizeOp);
		gte.getArguments().add(integerLiteral);
		
		return gte;
	}
	
	private CollectionOperationCallExp createSizeOperation(OclExpression source) {
		CollectionOperationCallExp colOp = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
		colOp.setOperationName("size");
		colOp.setSource(source);
		return colOp;
	}


	private OclExpression translate(NumRefsConstraint constraint) {
		OperationCallExp allInstances = createAllInstances(METAMODEL_NAME, constraint.getClassName());
		
		// Create the forAll: Type.allInstances()->forAll(obj | ...)
		IteratorExp forAll = OCLFactory.eINSTANCE.createIteratorExp();
		forAll.setName("forAll");
		forAll.setSource(allInstances);
		Iterator it = OCLFactory.eINSTANCE.createIterator();
		it.setVarName("obj");
		forAll.getIterators().add(it);
		
		// Create the navigation: obj.referenceName
		NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
		nav.setName(constraint.getRefName());
		VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
		varRef.setReferredVariable(it);
		nav.setSource(varRef);

		// Create size operation for the reference: obj.referenceName->size()
		CollectionOperationCallExp sizeOp = createSizeOperation(nav);
		
		IntegerExp integerLiteral = createIntegerLiteral(constraint.getNumber());
		
		OperatorCallExp gte = OCLFactory.eINSTANCE.createOperatorCallExp();
		gte.setOperationName(">=");
		gte.setSource(sizeOp);
		gte.getArguments().add(integerLiteral);
		
		forAll.setBody(gte);
		
		return forAll;
	}
	
	private OperationCallExp createAllInstances(String mmName, String className) {
		OclModelElement classRef = OCLFactory.eINSTANCE.createOclModelElement();
		classRef.setName(className);
		
		OclModel model = OCLFactory.eINSTANCE.createOclModel();
		model.setName(mmName);
		classRef.setModel(model);
		
		OperationCallExp allInstancesCall = OCLFactory.eINSTANCE.createOperationCallExp();
		allInstancesCall.setOperationName("allInstances");
		allInstancesCall.setSource(classRef);
		
		return allInstancesCall;
	}

	private IntegerExp createIntegerLiteral(int num) {
		IntegerExp numLiteral = OCLFactory.eINSTANCE.createIntegerExp();
		numLiteral.setIntegerSymbol(num);
		return numLiteral;
	}

}
