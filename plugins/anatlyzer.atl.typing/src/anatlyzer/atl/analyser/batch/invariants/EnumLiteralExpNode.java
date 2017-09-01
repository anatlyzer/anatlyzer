package anatlyzer.atl.analyser.batch.invariants;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;

import anatlyzer.atl.analyser.generators.CSPModel2;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.types.EnumType;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.OutPatternElement;
import anatlyzer.atlext.OCL.EnumLiteralExp;
import anatlyzer.atlext.OCL.IntegerExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.StringExp;

public class EnumLiteralExpNode extends AbstractInvariantReplacerNode {

	private EnumLiteralExp exp;

	public EnumLiteralExpNode(AbstractInvariantReplacerNode parent, EnumLiteralExp exp) {
		super(null);
		this.exp = exp;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		// nothing to do
	}
	
	@Override
	public OclExpression genExpr(CSPModel2 builder) {
		return genExprNormalized(builder);
	}
	
	@Override
	public OclExpression genExprNormalized(CSPModel2 builder) {
		EnumType t  = (EnumType) exp.getInferredType();
		EEnum enum_ = (EEnum) t.getEenum();

//		EList<EEnumLiteral> literals = enum_.getELiterals();
//		String value = null;
//		for (int i = 0; i < literals.size(); i++) {
//			if ( literals.get(i).getLiteral().equals(exp.getName()) ) {
//				value = literals.get(i).getLiteral();
//				break;
//			}
//		}
//		
//		if ( value == null ) {
//			throw new IllegalStateException();
//		}
		
		// Do we need to generate a constraint about the size
		// of the property?
		
//		IntegerExp iexp = OCLFactory.eINSTANCE.createIntegerExp();
//		iexp.setIntegerSymbol(value);
//		return iexp;
		
		StringExp sexp = OCLFactory.eINSTANCE.createStringExp();
		sexp.setStringSymbol(enum_.getName() + "_" + exp.getName());
		return sexp;
	}
	
	@Override
	public void genGraphviz(GraphvizBuffer<IInvariantNode> gv) {				
		gv.addNode(this, "Enum literal node: " + ATLSerializer.serialize(exp), true);		
	}
	
	@Override
	public void getTargetObjectsInBinding(java.util.Set<OutPatternElement> elems) { }	

	@Override
	public boolean isUsed(InPatternElement e) {
		return false;
	}

}
