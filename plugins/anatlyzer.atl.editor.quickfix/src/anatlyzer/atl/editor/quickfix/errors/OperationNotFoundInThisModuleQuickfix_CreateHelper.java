package anatlyzer.atl.editor.quickfix.errors;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.impl.MetaclassImpl;
import anatlyzer.atl.util.ATLSerializer;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclModel;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;

/**
 * This quickfix proposes adding a new helper or lazy/called rule to the transformation.
 * The heuristics to select each option is the following:
 * <ul>
 * 	<li>Lazy rule.   The call in a collect's body, being the last step in an iterator chain,
 * 				     within the right part of a binding. 
 * 					 Binding's feature type must be non-primitive.
 * 					 Number of arguments must be one.</li>
 * 	<li>Called rule. Same as before, but more than one parameter is passed or just one parameter of
 *                   primitive type.</li>
 *  <li>Helper. Otherwise. </li>
 * </ul>
 * 
 * The heuristics could be more relaxed in favour of lazy/called rules, not requiring the collect
 * iterator be the last step in the iterator chain.
 * 
 * Another option could be to separate this into three different quickfixes, ranking them
 * according the previous heuristics.
 * 
 * <h2>Limitations</h2>
 * The current implementation is not smart enough to recursively quickfix the new rules to avoid
 * "no binding for compulsory feature".
 * 
 * @author jdelara, jesusc
 */
public class OperationNotFoundInThisModuleQuickfix_CreateHelper extends AbstractAtlQuickfix {		// Separate into create helper/create lazy rule
		
	private static Map<Class<? extends Type>, Function<Type, String>> types = new HashMap<>();		// Function<? extends Type, String>
	
	static {
		types.put(anatlyzer.atl.types.IntegerType.class, t -> "Integer");
		types.put(anatlyzer.atl.types.StringType.class, t -> "String");
		types.put(anatlyzer.atl.types.FloatType.class, t -> "Real");
		//types.put(anatlyzer.atl.types.VariableExp.class, t -> "Real");
		types.put(MetaclassImpl.class, t -> {
			anatlyzer.atl.types.Metaclass typ = (anatlyzer.atl.types.Metaclass)t;
			return typ.getModel().getName()+"!"+typ.getName();	
		});
	}
	
	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, OperationNotFoundInThisModule.class);
	}
	
	private OperationCallExp getElement() {
		try {
			OperationNotFoundInThisModule p = (OperationNotFoundInThisModule) getProblem();
		
						
			// p.getOperationName() is null at this point 
			return (OperationCallExp)p.getElement();
		} catch (CoreException e) {
			
		}
		return null;
	}
	
	private Rule getContainerRule(OperationCallExp exp) {
		return ATLUtils.getContainer(exp, Rule.class);
//		EObject res = exp; 
//		
//		do {
//			res = res.eContainer();
//		} while (res != null && !(res instanceof Rule));
//		
//		if (res!=null) return (Rule)res;
//		return null;
	}
	
	private String getTypeName(Type inferredType) {		// This should be somewhere else...
		if (types.get((Class<? extends Type>)inferredType.getClass()) != null)
			return types.get((Class<? extends Type>)inferredType.getClass()).apply(inferredType);
		else return "OclAny";
	}
	
	private String buildNewHelper(OperationCallExp exp) {		
		String typeName = this.getTypeName(exp.getSource().getInferredType());
		
		String newHelper = "\nhelper def: "+exp.getOperationName()+"(";
		
		int idx = 0;
		for (OclExpression e : exp.getArguments()) {
			if ( idx > 0 ) newHelper+= ","; 
			newHelper += "arg"+idx+" : "+this.getTypeName(e.getInferredType());			
			idx++;
		}
		
		newHelper+= " ) : "+typeName+" =\n body;\n";
		
		return newHelper;
	}
	

	private String buildNewLazyRule(OperationCallExp exp, Binding b) {		
		LazyRule r = ATLFactory.eINSTANCE.createLazyRule();
		
		InPattern p = ATLFactory.eINSTANCE.createInPattern();
		SimpleInPatternElement ipe = ATLFactory.eINSTANCE.createSimpleInPatternElement();		
		p.getElements().add(ipe);
		r.setInPattern(p);
		
		Metaclass argType = (Metaclass) exp.getArguments().get(0).getInferredType();
		OclModelElement ome = ASTUtils.createOclModelElement(argType);
		ipe.setType(ome);
		ipe.setVarName(argType.getName().substring(0, 1).toLowerCase());
		
		return buildImperativeRule(r, exp, b);	
	}

	private String buildNewCalledRule(OperationCallExp exp, Binding b) {		
		CalledRule r = ATLFactory.eINSTANCE.createCalledRule();

		// Infer parameter types from the types of the actual arguments
		int i = 0;
		for(OclExpression arg : exp.getArguments()) {
			Parameter p = OCLFactory.eINSTANCE.createParameter();
			p.setVarName("arg" + i);
			
			if ( arg.getInferredType() instanceof PrimitiveType ) {
				p.setType(Conversions.convertPType((PrimitiveType) arg.getInferredType()));
			} else if ( arg.getInferredType() instanceof Metaclass ){
				p.setType(ASTUtils.createOclModelElement((Metaclass) arg.getInferredType()));
			} else {
				// Several cases to consider here:
				//		- Union type => ??
				//		- ErrorType?
				throw new UnsupportedOperationException();
			}
			
			r.getParameters().add(p);
			i++;
		}

		return buildImperativeRule(r, exp, b);
	}
	
	private String buildImperativeRule(StaticRule r, OperationCallExp exp, Binding b) {		
		// 1. Name of the rule : the operation name
		r.setName(exp.getOperationName());
		
		// 2. Infer parameter types from the types of the actual arguments
		// Done by the callee
		// 3. Return type of the rule from the binding's expected type
		Metaclass m = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
		OclModelElement ome = ASTUtils.createOclModelElement(m);
		
		OutPattern p = ATLFactory.eINSTANCE.createOutPattern();
		r.setOutPattern(p);
		SimpleOutPatternElement ope = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
		ope.setVarName(m.getName().substring(0, 1).toLowerCase());
		ope.setType(ome);
		p.getElements().add(ope);
		
		return "\n" + ATLSerializer.serialize(r) + "\n";
	}
	
	
	@Override
	public void apply(IDocument document) {
		OperationCallExp res = this.getElement();
		
		try {
			String newCode = applyHeuristic(res, 
					this::buildNewLazyRule,
					this::buildNewCalledRule,
					this::buildNewHelper);
			
			Rule r = this.getContainerRule(res);
			
			int[] sourceOffset = getElementOffset(r, document);
			int sourceOffsetEnd = sourceOffset[1];

			// Setting length to 0 means "insert", that is, replacing 0 characters
			document.replace(sourceOffsetEnd + 1, 0, newCode);
			
		} catch ( BadLocationException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Operation not found in thisModule: create helper or lazy/called rule");
	}

	private String applyHeuristic(OperationCallExp op, 
			BiFunction<OperationCallExp, Binding, String> onNewLazyRule,
			BiFunction<OperationCallExp, Binding, String> onNewCalledRule,
			Function<OperationCallExp, String> onNewHelper) {

		IteratorExp it = ATLUtils.getContainer(op, IteratorExp.class);
		if ( op.getArguments().size() > 0 && it != null && it.getName().equals("collect") ) {
			EObject c = it.eContainer();
			if ( c instanceof Binding && ATLUtils.isReferenceBinding((Binding) c) ) {
				if ( op.getArguments().size() > 1 || op.getArguments().get(0).getInferredType() instanceof PrimitiveType ) {
					return onNewCalledRule.apply(op, (Binding) c);
				} else {
					return onNewLazyRule.apply(op, (Binding) c);
				}
			}
		}
		return onNewHelper.apply(op);
	}

	@Override
	public Point getSelection(IDocument document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Operation not found in thisModule: create helper or lay rule";
	}

	@Override
	public String getDisplayString() {
		return "Operation not found in thisModule: create helper or lazy rule";
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IContextInformation getContextInformation() {
		// TODO Auto-generated method stub
		return null;
	}


}
