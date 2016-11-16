package anatlyzer.atl.editor.quickfix.errors;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.editor.quickfix.AbstractAtlQuickfix;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.QuickfixUtil;
import anatlyzer.atl.editor.quickfix.util.Conversions;
import anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.types.impl.MetaclassImpl;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.InPattern;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.ATL.OutPattern;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.SimpleOutPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.ATL.StaticRule;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
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
 * @qfxName  Operation not found in thisModule: create helper or lazy rule
 * @qfxError {@link anatlyzer.atl.errors.atl_error.OperationNotFoundInThisModule}
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
	
	@Override public void resetCache() { }
	
	protected StaticHelper buildNewHelper(OperationCallExp op) {		
		Type returnType = ASTUtils.findExpectedTypeInExpressionPosition(op, false); // QuickfixUtil.findPossibleTypeOfFaultyExpression(op);
		StaticHelper helper = ASTUtils.buildNewThisModuleOperation(op.getOperationName(), 
				returnType,
				op.getArguments());
		
		return helper;
	}
	

	protected StaticRule buildNewLazyRule(OperationCallExp exp, Binding b) {		
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

	protected StaticRule buildNewCalledRule(OperationCallExp exp, Binding b) {		
		CalledRule r = ATLFactory.eINSTANCE.createCalledRule();

		// Infer parameter types from the types of the actual arguments
		int i = 0;
		for(OclExpression arg : exp.getArguments()) {
			Parameter p = OCLFactory.eINSTANCE.createParameter();
			p.setVarName(ASTUtils.getNiceParameterName(arg, r.getParameters()));
			
			if ( arg.getInferredType() instanceof PrimitiveType ) {
				p.setType(Conversions.convertPType((PrimitiveType) arg.getInferredType()));
			} else if ( arg.getInferredType() instanceof Metaclass ){
				p.setType(ASTUtils.createOclModelElement((Metaclass) arg.getInferredType()));
			} else if ( arg.getInferredType() instanceof CollectionType ) {
				p.setType( ATLUtils.getOclType(arg.getInferredType()) );
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
	
	private StaticRule buildImperativeRule(StaticRule r, OperationCallExp exp, Binding b) {		
		// 1. Name of the rule : the operation name
		r.setName(exp.getOperationName());
		
		// 2. Infer parameter types from the types of the actual arguments
		// Done by the callee
		// 3. Return type of the rule from the binding's expected type
		Metaclass m = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
		OclModelElement ome = ASTUtils.createOclModelElement(selectConcreteType(m));
		
		OutPattern p = ATLFactory.eINSTANCE.createOutPattern();
		r.setOutPattern(p);
		SimpleOutPatternElement ope = ATLFactory.eINSTANCE.createSimpleOutPatternElement();
		ope.setVarName(m.getName().substring(0, 1).toLowerCase() + "Tgt");
		ope.setType(ome);
		p.getElements().add(ope);
		
		return r;
	}
	
	
	private Metaclass selectConcreteType(Metaclass m) {
		if ( m.getKlass().isAbstract() ) {
			ClassNamespace ns = (ClassNamespace) m.getMetamodelRef();
			m = ns.getAllSubclasses(getAnalysisResult().getNamespace()).stream().filter(m2 -> ! m2.getKlass().isAbstract() ).
				map(subclass -> {
					// This is also done in BindingPossiblyUnresolved_AddRule quickfix, due
					// to the same problem with the creation of objects outsie the analyser thread
					Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
					metaclass.setKlass(subclass.getKlass());
					metaclass.setName(subclass.getKlass().getName());
					MetaModel model = TypesFactory.eINSTANCE.createMetaModel();
					model.setName(subclass.getMetamodelName());					
					metaclass.setModel(model);
					return metaclass;
				}).
				findAny().orElse(m);
		}
		return m;
	}

	@Override
	public void apply(IDocument document) {
		QuickfixApplication qfa = getQuickfixApplication();	
		new InDocumentSerializer(qfa, document).serialize();

		/*
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
		*/
	}

	private ModuleElement applyHeuristic(OperationCallExp op, 
			BiFunction<OperationCallExp, Binding, ModuleElement> onNewLazyRule,
			BiFunction<OperationCallExp, Binding, ModuleElement> onNewCalledRule,
			Function<OperationCallExp, ModuleElement> onNewHelper) {

		IteratorExp it = ATLUtils.getContainer(op, IteratorExp.class);
		if ( op.getArguments().size() > 0 && it != null && it.getName().equals("collect") ) {
			EObject c = it.eContainer();
			if ( c instanceof Binding && ATLUtils.isReferenceBinding((Binding) c) ) {
				if ( op.getArguments().size() > 1 || 
						op.getArguments().get(0).getInferredType() instanceof PrimitiveType ||
						op.getArguments().get(0).getInferredType() instanceof CollectionType ) {
					return onNewCalledRule.apply(op, (Binding) c);
				} else {
					return onNewLazyRule.apply(op, (Binding) c);
				}
			}
		}
		
		// If the call is located just in the binding, then we need a rule call to
		// to avoid hepers with target types!
		if ( op.eContainer() instanceof Binding ) {
			// Same code as above
			EObject c = op.eContainer();
			if ( c instanceof Binding && ATLUtils.isReferenceBinding((Binding) c) ) {
				if ( op.getArguments().size() > 1 || 
						op.getArguments().get(0).getInferredType() instanceof PrimitiveType ||
						op.getArguments().get(0).getInferredType() instanceof CollectionType ) {
					return onNewCalledRule.apply(op, (Binding) c);
				} else {
					return onNewLazyRule.apply(op, (Binding) c);
				}
			}
		}
		
		return onNewHelper.apply(op);
	}

	@Override
	public String getAdditionalProposalInfo() {
		return "Create helper or lazy rule (heuristic)";
	}

	@Override
	public String getDisplayString() {
		return "Create helper or lazy rule (heuristic)";
	}

	@Override
	public Image getImage() {
		return QuickfixImages.create_helper.createImage();
	}
	@Override
	public QuickfixApplication getQuickfixApplication() {
		OperationCallExp res = (OperationCallExp) this.getProblematicElement();
	
		ModuleElement anchor = ATLUtils.getContainer(res, ModuleElement.class);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		qfa.insertAfter(anchor, () -> { 
			ModuleElement newCode = applyHeuristic(res, 
					this::buildNewLazyRule,
					this::buildNewCalledRule,
					this::buildNewHelper);
			return newCode;
		});
		
		return qfa;
	}

}
