package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.MetaModel;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.TypesFactory;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atl.util.ExpressionJoiner;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.Rule;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableExp;

/**
 * This quick fix adds a new rule that make the binding resolvable. 
 * It uses the type of the right-hand side of the binding to determine the input
 * pattern element. It also generates a filter to avoid rule conflicts with other
 * rules that were resolving the binding before.  
 *    
 * @qfxName Add new rule
 * @qfxError {@link anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved}
 * 
 * @author jesusc
 */
public class BindingPossiblyUnresolved_AddRule extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingPossiblyUnresolved.class) && isSourceType(marker);
	}

	@Override public void resetCache() {};
	
	protected static EClass getResolvedEClassType(MatchedRule mr) {
		return ATLUtils.getInPatternType(mr).getKlass();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		Binding b = (Binding) getProblematicElement();
		Rule bindingRule = ATLUtils.getRule(b);
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		Metaclass selectedOutputType = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
		List<Metaclass> sources = ATLUtils.getUnderlyingBindingRightMetaclasses(b);
		
		GlobalNamespace mm = getAnalysisResult().getNamespace();
		
		if ( selectedOutputType.getKlass().isAbstract() ) {
			IClassNamespace ns = (IClassNamespace) selectedOutputType.getMetamodelRef();
			for (ClassNamespace subclass : ns.getAllSubclasses(mm)) {
				if ( ! subclass.getKlass().isAbstract() ) {					
					// selectedOutputType = subclass.getType();
					Metaclass metaclass = TypesFactory.eINSTANCE.createMetaclass();
					metaclass.setKlass(subclass.getKlass());
					metaclass.setName(subclass.getKlass().getName());
					MetaModel model = TypesFactory.eINSTANCE.createMetaModel();
					model.setName(subclass.getMetamodelName());					
					metaclass.setModel(model);
					
					selectedOutputType = metaclass;
					break;
				}
			}			
		}
		
		Metaclass tgt = selectedOutputType;
		for (final Metaclass src : sources) {
			qfa.insertAfter(bindingRule, () -> {
				MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
				String ruleName = src.getKlass().getName() + "2" + tgt.getKlass().getName();
				mr.setName(ruleName);
				ASTUtils.completeRule(mr, src, tgt, null);	

				getATLModel().getTyping().add(tgt);
				
				
				
				// Get all rules that resolve elements of type "src"
				OclExpression expr = b.getResolvedBy().stream().
					filter(r -> ATLUtils.isCompatible(ATLUtils.getInPatternType(r.getRule()), src)).
					// filter(r -> r.getRule().getInPattern().getFilter() != null ).
					map(r -> {
						VariableExp ref = OCLFactory.eINSTANCE.createVariableExp();
						ref.setReferredVariable(mr.getInPattern().getElements().get(0));							
						Metaclass resolvedRuleType = (Metaclass) r.getRule().getInPattern().getElements().get(0).getInferredType();
						OperationCallExp checkOclIsKindOf = ASTUtils.createOclIsKindOf(resolvedRuleType, ref);
						
						OclExpression filter = r.getRule().getInPattern().getFilter();
						if ( filter == null ) {
							return ASTUtils.negate(checkOclIsKindOf);
						} else {						
							VariableExp vexp = ATLUtils.findStartingVarExp(filter);
							if ( ! ( vexp.getReferredVariable() instanceof InPatternElement ) ) {
								throw new IllegalStateException();
							}
							OclExpression value = (OclExpression) new ATLCopier(filter).
									bind(vexp.getReferredVariable(), mr.getInPattern().getElements().get(0)).
									copy();	
							
							IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
							ifexp.setCondition(checkOclIsKindOf);
							ifexp.setThenExpression(value);
							ifexp.setElseExpression(ASTUtils.createBooleanLiteral(false));
							
							return ASTUtils.negate( ifexp );
						}						
					}).
					collect(ExpressionJoiner.joining(() -> {
						OperatorCallExp or = OCLFactory.eINSTANCE.createOperatorCallExp();
						or.setOperationName("and");
						return or;							
					}));
				
				if ( expr != null ) {
					// expr = ASTUtils.negate(expr);					
					mr.getInPattern().setFilter(expr);
				}

				return mr;
			});
			
		}
		
		return qfa;
	}
    
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayString() {
		Binding b = (Binding) getProblematicElement();
		List<Metaclass> sources = ATLUtils.getUnderlyingBindingRightMetaclasses(b);
		if ( sources.size() == 1 ) {
			return "Add rule";
		} else {
			return "Add rules: " + sources.stream().map(m -> m.getName()).collect(Collectors.joining(", "));			
		}
		
	}


}
