package anatlyzer.atl.editor.quickfix.errors;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.errors.atl_error.BindingPossiblyUnresolved;
import anatlyzer.atl.quickfixast.ASTUtils;
import anatlyzer.atl.quickfixast.InDocumentSerializer;
import anatlyzer.atl.quickfixast.QuickfixApplication;
import anatlyzer.atl.types.CollectionType;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;

public class BindingPossiblyUnresolved_FilterBinding extends RuleGeneratingQuickFix {

	@Override
	public boolean isApplicable(IMarker marker) {
		return checkProblemType(marker, BindingPossiblyUnresolved.class);
	}

	protected static EClass getResolvedEClassType(RuleResolutionInfo ri) {
		return getResolvedClassType(ri).getKlass();
	}
	
	protected static Metaclass getResolvedClassType(RuleResolutionInfo ri) {
		return ATLUtils.getInPatternType(ri.getRule());					
	}
	
	@Override
	public QuickfixApplication getQuickfixApplication() throws CoreException {
		BindingPossiblyUnresolved p = (BindingPossiblyUnresolved) getProblem();
		
		final Binding b = (Binding) p.getElement();
		
		final Map<EClass, List<RuleResolutionInfo>> rulesByType = b.getResolvedBy().stream().
				collect(Collectors.groupingBy(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType)); 
		
		final List<EClass> orderedTypes = b.getResolvedBy().stream().
			map(BindingPossiblyUnresolved_FilterBinding::getResolvedEClassType).
			distinct().sorted((c1, c2) -> c1.isSuperTypeOf(c2) ? +1 : -1).
			collect(Collectors.toList());

		Function<VariableDeclaration, OclExpression> genCheck = (bindingValueVar) -> {
			return ASTUtils.generateNestedIfs(orderedTypes, 
					// genCondition: And-concatenation of oclIsKind()
					(EClass eClass) -> {
						List<RuleResolutionInfo> rules = rulesByType.get(eClass);
						OclExpression condition = rules.stream().map(r -> {
							OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
							op.setOperationName("oclIsKindOf");				
							op.getArguments().add( ATLUtils.getOclType( getResolvedClassType(r) ) );	
							VariableExp varRef = OCLFactory.eINSTANCE.createVariableExp();
							varRef.setReferredVariable(bindingValueVar);
							op.setSource(varRef);
							return op;
						}).collect(joining(() -> {
							OperatorCallExp and = OCLFactory.eINSTANCE.createOperatorCallExp();
							and.setOperationName("and");
							return and;
						})) ;
						return condition;
					},
					// genThen: OR-concatenation of all filters
					(EClass eClass) -> {
						List<RuleResolutionInfo> rules = rulesByType.get(eClass);
						OclExpression thenBranch = rules.stream().
								map(r -> copyFilter(bindingValueVar, r.getRule()) ).
								filter(f -> f != null ).
								collect(joining(() -> {
									OperatorCallExp or = OCLFactory.eINSTANCE.createOperatorCallExp();
									or.setOperationName("or");
									return or;							
								}));
						if ( thenBranch == null ) {
							thenBranch = ASTUtils.createBooleanLiteral(true);
						}
						return thenBranch;
					},					
					// genFinalElse: false
					() -> {
						return ASTUtils.createBooleanLiteral(false);
					});
			};		

		QuickfixApplication qa = new QuickfixApplication();
		
		if ( b.getValue().getInferredType() instanceof CollectionType ) {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createIteratorExp, (original, select, trace) -> {
				select.setName("select");
				Iterator it = OCLFactory.eINSTANCE.createIterator();
				it.setVarName("_v");
				select.getIterators().add(it);
				select.setSource(original);
				
				OclExpression check = genCheck.apply(it); // ASTUtils.createBooleanLiteral(true); //
				select.setBody(check);
			});	

		} else {
			qa.change(b.getValue(), OCLFactory.eINSTANCE::createLetExp, (original, let, trace) -> {
				VariableDeclaration varDcl = OCLFactory.eINSTANCE.createIterator();
				varDcl.setVarName("_v");
				varDcl.setType(ATLUtils.getOclType(original.getInferredType()));
				varDcl.setInitExpression(original);
				let.setVariable(varDcl);
				
				OclExpression defaultValueExpr = ASTUtils.defaultValue(original.getInferredType());
				VariableExp refToVar           = OCLFactory.eINSTANCE.createVariableExp();
				refToVar.setReferredVariable(varDcl);
				
				OclExpression check = genCheck.apply(varDcl); 
				
				IfExp ifExp = OCLFactory.eINSTANCE.createIfExp();
				ifExp.setCondition(check);
				ifExp.setThenExpression(refToVar);
				ifExp.setElseExpression(defaultValueExpr);
				let.setIn_(ifExp);
			});	
		}		

		return qa;
	}

	public OclExpression copyFilter(VariableDeclaration var, MatchedRule r) {
		OclExpression filter = r.getInPattern().getFilter();
		if ( filter == null )
			return null;
		
		return (OclExpression) 
				new ATLCopier(filter).
					bind(r.getInPattern().getElements().get(0), var).
					copy();		
	}
	
    public static Collector<OclExpression, ?, OclExpression> joining(Supplier<OperationCallExp> joinOp) {
    	return Collector.<OclExpression, ExpressionJoiner, OclExpression>of(
    			() -> new ExpressionJoiner(joinOp), 
    			ExpressionJoiner::add, 
    			ExpressionJoiner::merge,
    			ExpressionJoiner::toOclExpression, new Collector.Characteristics[0] );
    }
    
    /*
     * 
     *     public static<T, A, R> Collector<T, A, R> of(Supplier<A> supplier,
                                                 BiConsumer<A, T> accumulator,
                                                 BinaryOperator<A> combiner,
                                                 Function<A, R> finisher,
                                                 Characteristics... characteristics) {
     */
    
//    
//    return new CollectorImpl<>(
//            () -> new StringJoiner(delimiter, prefix, suffix),
//            StringJoiner::add, StringJoiner::merge,
//            StringJoiner::toString, CH_NOID);
    
    static class ExpressionJoiner {
    	private OclExpression current;
		private Supplier<OperationCallExp> joinOp;

		public ExpressionJoiner(Supplier<OperationCallExp> joinOp) {
    		this.joinOp  = joinOp;
    	}
		
		public ExpressionJoiner add(OclExpression next) {
			if ( current != null ) {
   				OperationCallExp call = joinOp.get();
				call.setSource(current);
				call.getArguments().add(next);
				this.current = call;
			} else {
				this.current = next;
			}
			return this;
		}
		
	    public ExpressionJoiner merge(ExpressionJoiner other) {
	    	if ( other.current != null ) {
	    		add(other.current);
	    	}
	    	return this;
	    }
	    
	    public OclExpression toOclExpression() {
	    	return current;
	    }
	    
    }
    
	@Override
	public void apply(IDocument document) {
		try {
			QuickfixApplication qfa = getQuickfixApplication();
			new InDocumentSerializer(qfa, document).serialize();		
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getDisplayString() {
		return "Add filter expression to binding";
	}


}
