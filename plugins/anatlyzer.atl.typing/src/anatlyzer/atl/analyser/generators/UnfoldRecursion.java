package anatlyzer.atl.analyser.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.types.Type;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.SimpleInPatternElement;
import anatlyzer.atlext.ATL.StaticHelper;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.IfExp;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LetExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.OclFeatureDefinition;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.OperatorCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;

public class UnfoldRecursion extends AbstractVisitor {

	private EObject root;
	private CSPModel builder = new CSPModel();
	
	private int defaultUnfoldings = 2;
	private List<Helper> newHelpers = new ArrayList<Helper>();
	private ErrorSlice slice;
	
	
	public UnfoldRecursion(EObject root, ErrorSlice slice) {
		this.root    = root;
		this.slice   = slice;
	}	
	
	public List<Helper> perform() {
		startVisiting(root);
		return newHelpers;
	}
	
	@Override
	public void inContextHelper(ContextHelper self) {
		ArrayList<PropertyCallExp> sites = needsUnfolding(self);
		if ( sites != null  ) {
			unfold(self, ATLUtils.getBody(self), sites, defaultUnfoldings);
		}
	}

	@Override
	public void inStaticHelper(StaticHelper self) {
		ArrayList<PropertyCallExp> sites = needsUnfolding(self);
		if ( sites != null  ) {
			unfold(self, ATLUtils.getBody(self), sites, defaultUnfoldings);
		}
	}
	
	private static int numIterates = 0;
	
	@Override
	public void inIterateExp(IterateExp self) {
		numIterates++;
		String iterateName = "iterate_" + numIterates;

		Type sourceType = self.getSource().getInferredType();
		Type resultType = self.getResult().getInferredType();
		Type iterateType = self.getInferredType(); // resulType == iterateType
		ArrayList<VariableDeclaration> localVariables = new ArrayList<VariableDeclaration>(getLocalVariables(self));
		
		ArrayList<VariableDeclaration> localVariablesWithOnlyOneSelf = filterOnlyOneSelf(localVariables);
		
		OperationCallExp op = createUnfoldedIterateCall(iterateName + "_" + "0", self.getResult().getInitExpression(), self.getSource(), localVariablesWithOnlyOneSelf);
		EcoreUtil.replace(self, op);
		
		
		// Create iterative helper
		
		for(int i = 0; i < defaultUnfoldings; i++) {
			Operation operation = OCLFactory.eINSTANCE.createOperation();
			operation.setName(iterateName + "_" + i);
			operation.setReturnType( ATLUtils.getOclType(iterateType) );
			// operation.setBody      ( ASTUtils.defaultValue (returnType) );
	
			OclFeatureDefinition def = OCLFactory.eINSTANCE.createOclFeatureDefinition();
			// def.setContext_(ctx);
			def.setFeature (operation);
			
			// Two parameters: iterator variable and collection
			Parameter parameter1 = OCLFactory.eINSTANCE.createParameter();
			parameter1.setType   ( ATLUtils.getOclType(resultType) );
			parameter1.setInferredType(resultType);
			parameter1.setVarName( self.getResult().getVarName() );
			operation.getParameters().add(parameter1);
	
			Parameter parameter2 = OCLFactory.eINSTANCE.createParameter();
			parameter2.setType   ( ATLUtils.getOclType(sourceType) );
			parameter2.getType().setInferredType(sourceType);
			parameter2.setInferredType(sourceType);
			parameter2.setVarName( "col_" );
			operation.getParameters().add(parameter2);
				
			Parameter selfParameter = null;
			HashMap<VariableDeclaration, Parameter> localVarParameters = new HashMap<VariableDeclaration, Parameter>();
			for (VariableDeclaration variableDeclaration : localVariables) {
				if ( variableDeclaration.getVarName().equals("self") ) {
					if ( selfParameter != null ) {
						localVarParameters.put(variableDeclaration, selfParameter);
						continue;
					}
				}
				
				Parameter parameterN = OCLFactory.eINSTANCE.createParameter();
				selfParameter = parameterN;
				parameterN.setType   ( ATLUtils.getOclType(variableDeclaration.getInferredType()) );
				parameterN.getType().setInferredType(variableDeclaration.getInferredType());
				parameterN.setInferredType(variableDeclaration.getInferredType());
				parameterN.setVarName(variableDeclaration.getVarName() + "_"); // To avoid name clashes (e.g., with self)
				operation.getParameters().add(parameterN);
			
				localVarParameters.put(variableDeclaration, parameterN);
			}
			
			StaticHelper helper = ATLFactory.eINSTANCE.createStaticHelper();
			helper.setDefinition(def);
			helper.setInferredReturnType(iterateType);
			
			// Helper body
			IfExp ifexp = OCLFactory.eINSTANCE.createIfExp();
			CollectionOperationCallExp condition = OCLFactory.eINSTANCE.createCollectionOperationCallExp();
			condition.setOperationName("isEmpty");
			condition.setSource(builder.createVarRef(parameter2));
			ifexp.setCondition(condition);
			
			// If isEmpty() then return acc
			ifexp.setThenExpression(builder.createVarRef(parameter1));
			
			// Else
			//
			IteratorExp colAny = builder.createIterator(builder.createVarRef(parameter2), "any");
			colAny.setBody(builder.createBooleanLiteral(true));
			LetExp letExp = builder.createLetScope(colAny, null, "it");
			ifexp.setElseExpression(letExp);
			
			IteratorExp select = builder.createIterator(builder.createVarRef(parameter2), "select");
			OperatorCallExp opDistinct = OCLFactory.eINSTANCE.createOperatorCallExp();
			opDistinct.setOperationName("<>");
			opDistinct.setSource(builder.createVarRef(select.getIterators().get(0)));
			opDistinct.getArguments().add(builder.createVarRef(letExp.getVariable()));
			select.setBody(opDistinct);
			
			LetExp letExp2 = builder.createLetScope(select, null, "rest");
			letExp.setIn_(letExp2);
		
			
			if ( i + 1 == defaultUnfoldings ) {
				// The end
				letExp2.setIn_(OCLFactory.eINSTANCE.createOclUndefinedExp());
			} else {
				ATLCopier copier = new ATLCopier(self.getBody()).
					bind(self.getResult(), parameter1).
					bind(self.getIterators().get(0), letExp.getVariable());
				localVarParameters.forEach((v, p) -> copier.bind(v, p));				
				
				OclExpression body = (OclExpression) copier.copy();
				
				List<VariableDeclaration> localVarsAsParams = filterOnlyOneSelf(localVarParameters.values());
				
				OperationCallExp op2 = createUnfoldedIterateCall(iterateName + "_" + (i + 1), 
						body, 
						builder.createVarRef(letExp2.getVariable()),
						localVarsAsParams);
				letExp2.setIn_(op2);
			}
	
			operation.setBody(ifexp);
			
			newHelpers.add(helper);			
		}
		
	}

	protected ArrayList<VariableDeclaration> filterOnlyOneSelf(Collection<? extends VariableDeclaration> localVariables) {
		boolean selfCreated = false;
		ArrayList<VariableDeclaration> localVariablesWithOnlyOneSelf = new ArrayList<VariableDeclaration>();
		for (VariableDeclaration vd : localVariables) {
			// The "_" check is because this is also used to filter created parameters... 
			if ( vd.getVarName().equals("self") || vd.getVarName().equals("self" + "_")) {
				if ( selfCreated ) continue;
				selfCreated = true;
			}
			
			localVariablesWithOnlyOneSelf.add(vd);
		}
		return localVariablesWithOnlyOneSelf;
	}
	private Set<VariableDeclaration> getLocalVariables(IterateExp iterate) {
		OclExpression body = iterate.getBody();
		
		HashSet<VariableDeclaration> vars = new HashSet<VariableDeclaration>();
		body.eAllContents().forEachRemaining(it -> {
			if ( it instanceof VariableExp ) {
				VariableDeclaration vd = ((VariableExp) it).getReferredVariable();
//				iterate.getIterators().forEach(i -> {
//					System.out.println(i.getVarName() + " - " + vd.getVarName());
//					System.out.println(i + "\n" + vd);
//				});
//				if ( ! vd.getVarName().equals("thisModule") && 
//					 ! (vd == iterate.getResult()) && 
//					 ! iterate.getIterators().contains(vd)) {
//					vars.add(((VariableExp) it).getReferredVariable());
//				} 

				if ( vd.getVarName().equals("self") ||
					 vd.eContainer() instanceof LetExp ||
					 vd instanceof SimpleInPatternElement ||
					 vd instanceof Parameter ) {
					vars.add(((VariableExp) it).getReferredVariable());					
				}
			
			
			}
		});
		return vars;
	}

	protected OperationCallExp createUnfoldedIterateCall(String iterateName, OclExpression acc, OclExpression col, List<VariableDeclaration> localVariables) {
		VariableDeclaration thisModuleVar = OCLFactory.eINSTANCE.createVariableDeclaration();
		thisModuleVar.setVarName(CSPModel.THIS_MODULE_CONTEXT_VAR);

		// Create a call to thisModule.iterate_num
		OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
		op.setOperationName(iterateName);

		// Add thisModule as first argument because Retyping does not work
		VariableExp thisModuleRef2 = OCLFactory.eINSTANCE.createVariableExp();
		thisModuleRef2.setReferredVariable(thisModuleVar);
		op.getArguments().add(thisModuleRef2);

		op.getArguments().add(acc);
		op.getArguments().add(col);
		
		for (VariableDeclaration variableDeclaration : localVariables) {
			VariableExp varexp = OCLFactory.eINSTANCE.createVariableExp();
			varexp.setReferredVariable(variableDeclaration);
			op.getArguments().add(varexp);
		}
		
		VariableExp thisModuleRef = OCLFactory.eINSTANCE.createVariableExp();
		thisModuleRef.setReferredVariable(thisModuleVar);
		op.setSource(thisModuleRef);
		
		return op;
	}

	private void unfold(final Helper helper, final OclExpression body, final List<PropertyCallExp> originalSites, int numUnfoldings) {		
		Helper previousHelper = helper;
		List<PropertyCallExp> sites = originalSites;
		for(int i = 0; i < numUnfoldings; i++) {
			ATLCopier copier = new ATLCopier(helper);
			Helper copy = (Helper) copier.copy();
			String newHelperName = ATLUtils.getHelperName(helper) + "_" + i;
			if ( copy.getDefinition().getFeature() instanceof Attribute ) {
				((Attribute) copy.getDefinition().getFeature()).setName(newHelperName);
			} else {
				((Operation) copy.getDefinition().getFeature()).setName(newHelperName);				
			}
	
			for (final PropertyCallExp s : sites) {		
				if ( s instanceof NavigationOrAttributeCallExp ) {
					((NavigationOrAttributeCallExp) s).setName(newHelperName);
				} else {
					((OperationCallExp) s).setOperationName(newHelperName);
				}
			}
			
			for (final PropertyCallExp s : originalSites) {		
				// The site has polymorphic behaviour, then the static type has to be changed
				// generated new helpers as well
				if ( s.getStaticResolver() != helper && s.getDynamicResolvers().contains(helper) ) {
					// TODO: Check that other unfolding of sibling classes has not done the same operation 
					//       over a common supertype
					Helper overridenHelper = slice.getCopiedHelper((Helper) s.getStaticResolver()); // because staticHelper points to the original, not to the copy made by the error slice					
					System.out.println(ATLUtils.getTypeName(ATLUtils.getHelperType(overridenHelper).getInferredType()) + "." + ATLUtils.getHelperName(overridenHelper));
					Helper newOverridenHelper = (Helper) ATLCopier.copySingleElement(overridenHelper);
					
					if ( newOverridenHelper.getDefinition().getFeature() instanceof Attribute ) {
						((Attribute) newOverridenHelper.getDefinition().getFeature()).setName(newHelperName);
					} else {
						((Operation) newOverridenHelper.getDefinition().getFeature()).setName(newHelperName);				
					}

					newHelpers.add(newOverridenHelper);
					
				}

			}
	
			
//			// Change the operation names that the calls of the previous helper use
//			for (final PropertyCallExp s : originalSites) {		
//				// If it is a polymorphic call, then I need to check if the current helper
//				// is not the static type, and thus we only go in the "unfolding path" if 
//				// the dynamic type is the one of the helper
//				if ( s.getStaticResolver() != helper && s.getDynamicResolvers().contains(helper) ) {
//					LetExp let = OCLFactory.eINSTANCE.createLetExp();
//					PropertyCallExp targetSite = (PropertyCallExp) copier.get(s);
//					EcoreUtil.replace(targetSite, let);
//					
//					VariableDeclaration varDcl = OCLFactory.eINSTANCE.createVariableDeclaration();
//					varDcl.setVarName("_rv");
//					let.setVariable(varDcl);
//
//					varDcl.setInitExpression(targetSite.getSource());
//
//					IfExp ifExp = OCLFactory.eINSTANCE.createIfExp();
//					let.setIn_(ifExp);
//					VariableExp varExp = OCLFactory.eINSTANCE.createVariableExp();
//					varExp.setReferredVariable(varDcl);
//					
//					OclModelElement ome = (OclModelElement) ATLUtils.getHelperType(helper);
//					Metaclass m = (Metaclass) ome.getInferredType();
//					OperationCallExp kindOf = builder.createKindOf(varExp, m.getModel().getName(), ome.getName(), m);
//					
//					ifExp.setCondition(kindOf);
//					
//					// if branch, cast & call
//					OperationCallExp castedVar = builder.createCastTo(varDcl, m);
//					if ( s instanceof NavigationOrAttributeCallExp ) {
//						NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
//						nav.setSource(castedVar);
//						nav.setName(newHelperName);
//						ifExp.setThenExpression(nav);
//					} else {
//						OperationCallExp op = OCLFactory.eINSTANCE.createOperationCallExp();
//						op.setSource(castedVar);
//						op.setOperationName(newHelperName);
//						for (OclExpression arg : ((OperationCallExp) targetSite).getArguments()) {
//							op.getArguments().add((OclExpression) ATLCopier.copySingleElement(arg));
//						}						
//						ifExp.setThenExpression(op);
//					}
//					
//					
//					// else branch, do the regular call
//					VariableExp varExp2 = OCLFactory.eINSTANCE.createVariableExp();
//					varExp2.setReferredVariable(varDcl);					
//					targetSite.setSource(varExp2);
//					ifExp.setElseExpression(targetSite);
//				} else {
//					PropertyCallExp targetSite = (PropertyCallExp) copier.get(s);
//					if ( targetSite instanceof NavigationOrAttributeCallExp ) {
//						((NavigationOrAttributeCallExp) targetSite).setName(newHelperName);
//					} else {
//						((OperationCallExp) targetSite).setOperationName(newHelperName);
//					}
//				}
//			}
//	
			// Compute the call sites of the new element
			sites = originalSites.stream().map(s -> (PropertyCallExp) copier.get(s)).collect(Collectors.toList());
			previousHelper = copy;
	
			// Module m = (Module) helper.eContainer();
			// m.getElements().add(m.getElements().indexOf(helper), copy);
			newHelpers.add(copy);
		}

		// Change the body of the last helper
		if ( previousHelper.getDefinition().getFeature() instanceof Attribute ) {
			((Attribute) previousHelper.getDefinition().getFeature()).setInitExpression(OCLFactory.eINSTANCE.createOclUndefinedExp());
		} else {
			((Operation) previousHelper.getDefinition().getFeature()).setBody(OCLFactory.eINSTANCE.createOclUndefinedExp());
		}

	}

	private ArrayList<PropertyCallExp> needsUnfolding(Helper helper) {
		ArrayList<PropertyCallExp> unfoldingSites = null;
		// Starting from ATLUtils.getBody(helper) is not a good idea, because
		// we need to process the root elements of the body, which is not included in eAllContents
		TreeIterator<EObject> it = helper.eAllContents(); 
		while ( it.hasNext() ) {
			EObject o = it.next();
			if ( o instanceof PropertyCallExp ) {
				PropertyCallExp prop = ((PropertyCallExp) o);
				if ( prop.getStaticResolver() == helper || prop.getDynamicResolvers().contains(helper) ) {
					if ( unfoldingSites == null ) 
						unfoldingSites = new ArrayList<PropertyCallExp>();
					unfoldingSites.add((PropertyCallExp) o);
				}				
			}
		}
		return unfoldingSites;
	}

	
}
