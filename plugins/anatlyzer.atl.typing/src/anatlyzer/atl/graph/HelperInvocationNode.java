package anatlyzer.atl.graph;

import java.util.List;
import java.util.stream.Collectors;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.analyser.generators.ErrorSlice;
import anatlyzer.atl.analyser.generators.GraphvizBuffer;
import anatlyzer.atl.analyser.generators.PathId;
import anatlyzer.atl.analyser.generators.TransformationSlice;
import anatlyzer.atl.errors.atl_error.LocalProblem;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.model.TypingModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.BooleanExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class HelperInvocationNode extends AbstractDependencyNode {

	private Helper	helper;

	public HelperInvocationNode(Helper h) {
		this.helper = h;
	}
	
	public Helper getHelper() {
		return helper;
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
	public boolean isProblemInPath(LocalProblem lp) {
		// Problem could be in the rest of the helper structure??
		return problemInExpressionCached(lp, ATLUtils.getBody(helper)) || checkDependenciesAndConstraints(lp);
	}

	@Override
	public boolean isExpressionInPath(OclExpression exp) {
		// Problem could be in the rest of the helper structure??
		return expressionInExpressionCached(exp, ATLUtils.getBody(helper)) || checkDependenciesAndConstraints(exp);
	}

	
	@Override
	public void genGraphviz(GraphvizBuffer gv) {
		super.genGraphviz(gv);
		gv.addNode(this, 
				"def:" + (ATLUtils.isContextHelper(helper) ? ATLUtils.getHelperType(helper).getName() + "." : "") + 
				ATLUtils.getHelperName(helper), leadsToExecution);
	}

	@Override
	public OclExpression genCSP(CSPModel model, GraphNode previous) {
		if ( helper instanceof ContextHelper ) {
			CallExprNode callNode = (CallExprNode) previous;			
			Type srcType = callNode.getCall().getSource().getInferredType();
			Type ctxType = ATLUtils.getHelperContext(helper).getInferredType();
			// ctxType is the same type of srcType => do nothing (careful with unions)
			// ctxType is a subtype of srcType     => check with if/oclIsKindOf and cast
			// ctxType is a supertype of srcType   => do nothing
			
			IfExp lastIf = null;

			// The loop over all possible types is done in case the receptor object is an union type
			// in which case we need to check every possible type
			//
			// TODO: No sure if there must be some ordering of the types in case there
			//       are subtyping relationships between them
			for (Type t : TypingModel.allPossibleTypes(srcType)) {
				if ( TypingModel.equalTypes(t, ctxType) ) {
					// Do nothing
				} else if ( TypingModel.isCompatible(ctxType, t) ) {
					// ctxType is a subtype, so we check. We are thus sure the type is a metaclass
					Metaclass mCtxType = (Metaclass) ctxType;

					// We know that CallExprNode has bound all "self" variable declarations
					// to the same variable, so we pick one
					List<VariableDeclaration> selfRefs = ATLUtils.findSelfReferences((ContextHelper) helper);
					VariableDeclaration aSelfRef  = selfRefs.get(0);
					VariableDeclaration boundSelf = model.getVar(aSelfRef);
					
					// Condition: "genSelf.oclIsKindOf(ContextHelperType)"
					VariableExp refToSelf = model.createVarRef(boundSelf);
					OperationCallExp condition = model.createKindOf(refToSelf, mCtxType.getModel().getName(), mCtxType.getName(), mCtxType);
					
					// True branch: "let genSelf_rebind = genSelf.oclAsType(ContextHelperType) in [dependingNode]"
					model.copyScope();
					LetExp letExp = model.rebind(aSelfRef, (tgtVarDcl) -> model.createCastTo(tgtVarDcl, mCtxType, null));
					letExp.setIn_(getDepending().genCSP(model, this));
					

					if ( lastIf == null ) {
						lastIf = model.createIfExpression(condition, letExp, model.createBooleanLiteral(false));
					} else {
						model.createIfExpression(condition, letExp, lastIf);
					}
					
					model.closeScope();
				} else {
					// is a subtype, so do nothing
				}
			}
			
			if ( lastIf != null )
				return lastIf;			
		}
		
		// Otherwise, just forward
		return getDepending().genCSP(model, this);
	}
	
	@Override
	public OclExpression genWeakestPrecondition(CSPModel model) {
		return getDepending().genWeakestPrecondition(model);
	}

	@Override
	public void genTransformationSlice(TransformationSlice slice) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean isVarRequiredByErrorPath(VariableDeclaration v) {		
		return getDepending().isVarRequiredByErrorPath(v);
	}

	@Override
	public void genIdentification(PathId id) {
		String s = null;
		if ( helper instanceof ContextHelper) {
			s = PathId.typeSig(ATLUtils.getHelperType(helper));			
		} else {
			s = "TM";
		}
		
		s += "." + ATLUtils.getHelperName(helper) + "[";		
		s += ATLUtils.getHelperArguments(helper).stream().map(p -> PathId.typeSig(p.getType())).collect(Collectors.joining(","));
		s += "]";
		id.next(s);
		followDepending(node -> node.genIdentification(id));
	}
	
	@Override
	public void bottomUp(IPathVisitor visitor) {
		boolean b = visitor.visit(this);
		if ( b ) followDepending(node -> node.bottomUp(visitor));
		visitor.visitAfter(this);
	}

}
