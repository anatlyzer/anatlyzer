package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.MetamodelNamespace;
import anatlyzer.atl.editor.quickfix.QuickfixImages;
import anatlyzer.atl.editor.quickfix.ui.AskClasses;
import anatlyzer.atl.errors.Problem;
import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.NullQuickfixApplication;
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
public class BindingPossiblyUnresolved_AddRuleInteractive extends BindingProblemQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) throws CoreException {
		return checkProblemType(marker, BindingPossiblyUnresolved.class) && isSourceType(marker);
	}

	@Override public void resetCache() {};
	
	@Override
	public boolean requiresUserIntervention() {
		return true;
	}
	
	protected static EClass getResolvedEClassType(MatchedRule mr) {
		return ATLUtils.getInPatternType(mr).getKlass();
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		Binding b = (Binding) getProblematicElement();
		Rule bindingRule = ATLUtils.getRule(b);

		BindingPossiblyUnresolved problem = (BindingPossiblyUnresolved) getProblem();
		EList<EClass> problematicClasses = problem.getProblematicClasses();
		
		
		QuickfixApplication qfa = new QuickfixApplication(this);
		Metaclass selectedOutputType = (Metaclass) ATLUtils.getUnderlyingBindingLeftType(b);
		// List<Metaclass> sources = getSourceTypes(b);
		
		final EClass selectedClass;
		AskClasses a = new AskClasses(null, "Select input type for the new rule");
		// a.setClasses(getSourceTypes(b).stream().map(m -> m.getKlass()).collect(Collectors.toList()));
		a.setClasses(problematicClasses);
		if ( a.open() == Window.OK ) {
			selectedClass = a.getSelectedClass();
		} else {
			selectedClass = null;
		}
		if ( selectedClass == null )
			return NullQuickfixApplication.NullInstance;
		
		
		GlobalNamespace mm = getAnalysisResult().getNamespace();
		
		// TODO: Allow selecting a target class
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
		

		MetamodelNamespace metamodel = mm.getMetamodels().stream().filter(m -> m.belongsTo(selectedClass)).findAny().orElse(null);
		// Metaclass src = metamodel.getMetaclass(selectedClass);
		Metaclass src = TypesFactory.eINSTANCE.createMetaclass();
		src.setKlass(selectedClass);
		src.setName(selectedClass.getName());
		MetaModel model = TypesFactory.eINSTANCE.createMetaModel();
		model.setName(metamodel.getName());					
		src.setModel(model);
		
		Metaclass tgt = selectedOutputType;
			qfa.insertAfter(bindingRule, () -> {
				MatchedRule mr =  ATLFactory.eINSTANCE.createMatchedRule();
				String ruleName = selectedClass.getName() + "2" + tgt.getKlass().getName();
				mr.setName(ruleName);
		
				ASTUtils.completeRule(mr, src, tgt, null);	

				getATLModel().getTyping().add(tgt);
				
				// Same as original: BindingPossiblyUnresolved_AddRule! factorize
				
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
		return "Add rule...";
	}
	
	@Override
	public Image getImage() {
		return QuickfixImages.create_matched_rule.createImage();
	}
	
	/** 
	 * Return the list of source types for which rules will be created.
	 * 
	 * This may be actually unnecessary since anATLyzer generates a BindingPossiblyUnresolved problem
	 * for each different source type... 
	 */
	protected List<Metaclass> getSourceTypes(Binding b) {
		List<Metaclass> sources = ATLUtils.getUnderlyingBindingRightMetaclasses(b);
		Set<String> srcModels = ATLUtils.getSourceMetamodelNames(getAnalysisResult().getATLModel());		
		return sources.stream().filter(m -> srcModels.contains( m.getModel().getName()) ).collect(Collectors.toList());
	}
	
}
