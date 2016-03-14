package anatlyzer.examples.api;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.footprint.TrafoMetamodelData;
import anatlyzer.atl.tests.api.AnalysisLoader;
import anatlyzer.atl.tests.api.AtlLoader;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Module;
import anatlyzer.atlext.processing.AbstractVisitor;
import anatlyzer.footprint.EffectiveMetamodelBuilder;

/**
 * This example shows how to traverse the ATL abstract syntax tree.
 * 
 * @author jesus
 */
public class UML2Java_TraverseAST {
	public static final String TRANSFORMATION = "examples/uml2java/trafo/uml2java.atl";

	public static final String UML_METAMODEL  = "examples/uml2java/metamodels/UML2_3_0_0.ecore";
	public static final String JAVA_METAMODEL   = "examples/uml2java/metamodels/java.ecore";
	
	public static void main(String[] args) throws Exception {
		new UML2Java_TraverseAST().run();
	}

	private void run() throws Exception {
		Resource atlTrafo = AtlLoader.load(TRANSFORMATION);
		AnalysisLoader loader = AnalysisLoader.fromResource(atlTrafo, new Object[] { UML_METAMODEL, JAVA_METAMODEL },  new String[] { "UML", "Java" });

		AnalysisResult result = loader.analyse();
				
		MyASTVisitor myASTVisitor = new MyASTVisitor();
		myASTVisitor.traverse(result.getATLModel().getModule());

		System.out.println();
		System.out.println("Transformation stats");
		System.out.println("====================");
		System.out.println("Num. primitive bindings     : " + myASTVisitor.numPrimitiveBindings);
		System.out.println("Num. non-primitive bindings : " + myASTVisitor.numNonPrimitiveBindings);
		System.out.println("Num. matched rules          : " + myASTVisitor.numMatchedRules);
		
	}

	/**
	 * AnATLyzer provides a visitor facility for ATL models. You only need to inherit from the {@link AbstractVisitor}
	 * class to get a default top-down - bottom-up traversal.
	 * 
	 * In this example we use the visitor to extract some metrics from the transformation.
	 */
	protected static class MyASTVisitor extends AbstractVisitor {

		protected int numNonPrimitiveBindings = 0;
		protected int numPrimitiveBindings = 0;	
		protected int numMatchedRules = 0;
		
		public void traverse(Module module) {
			// This method takes any ATL element and start the traveral
			// from there
			startVisiting(module);
		}
		
		@Override
		public void inBinding(Binding self) {
			if ( self.getValue().getInferredType() instanceof PrimitiveType ) {
				numPrimitiveBindings++;
			} else {
				numNonPrimitiveBindings++;
			}
		}
		
		@Override
		public void inMatchedRule(MatchedRule self) {
			numMatchedRules++;
		}
		
	}
	
	
}
