package anatlyzer.atl.graph;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableDeclaration;

public class HelperInvocationNode extends AbstractDependencyNode {

	private Helper	helper;

	public HelperInvocationNode(Helper h) {
		this.helper = h;
	}
	
	@Override
	public void genErrorSlice(ErrorSlice slice) {
		if ( helper.getStaticReturnType() instanceof Metaclass )
			slice.addExplicitMetaclass((Metaclass) helper.getStaticReturnType());

		Type[] args = ATLUtils.getArgumentTypes(helper);
		for (Type type : args) {
			if ( type instanceof Metaclass) slice.addExplicitMetaclass((Metaclass) type);
		}
		
		if ( helper instanceof ContextHelper) {
			ContextHelper h = (ContextHelper) helper;
			if ( h.getContextType() instanceof Metaclass ) 
				slice.addExplicitMetaclass((Metaclass) h.getContextType());
		}
		
		
		generatedDependencies(slice);
	}

	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, 
				"def:" + (ATLUtils.isContextHelper(helper) ? ATLUtils.getHelperType(helper).getName() + "." : "") + 
				ATLUtils.getHelperName(helper), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model) {	
		return getDepending().genCSP(model);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return getDepending().isVarRequiredByErrorPath(v);
	}

}
