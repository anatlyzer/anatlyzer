package anatlyzer.atl.analyser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import anatlyzer.atl.analyser.namespaces.ClassNamespace;
import anatlyzer.atl.analyser.namespaces.GlobalNamespace;
import anatlyzer.atl.analyser.namespaces.IClassNamespace;
import anatlyzer.atl.analyser.namespaces.PrimitiveTypeNamespace;
import anatlyzer.atl.analyser.namespaces.TransformationNamespace;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.model.TypeUtils;
import anatlyzer.atl.types.Metaclass;
import anatlyzer.atl.types.PrimitiveType;
import anatlyzer.atl.types.ThisModuleType;
import anatlyzer.atl.types.Type;
import anatlyzer.atl.types.UnionType;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.CallableParameter;
import anatlyzer.atlext.ATL.CalledRule;
import anatlyzer.atlext.ATL.ContextHelper;
import anatlyzer.atlext.ATL.Helper;
import anatlyzer.atlext.ATL.InPatternElement;
import anatlyzer.atlext.ATL.LazyRule;
import anatlyzer.atlext.ATL.MatchedRule;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.ATL.RuleVariableDeclaration;
import anatlyzer.atlext.ATL.Unit;
import anatlyzer.atlext.OCL.Attribute;
import anatlyzer.atlext.OCL.CollectionOperationCallExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OclFeature;
import anatlyzer.atlext.OCL.OclModelElement;
import anatlyzer.atlext.OCL.Operation;
import anatlyzer.atlext.OCL.OperationCallExp;
import anatlyzer.atlext.OCL.Parameter;
import anatlyzer.atlext.OCL.PropertyCallExp;
import anatlyzer.atlext.OCL.VariableDeclaration;

/**
 * 
 * @author jesus
 *
 */
public class ComputeResolvers extends AbstractAnalyserVisitor {

	public ComputeResolvers(ATLModel model, GlobalNamespace mm, Unit root) {
		super(model, mm, root);
	}
	
	public void perform(ComputedAttributes attr) {
		this.attr = attr.pushVisitor(this);
		startVisiting(root);
		attr.popVisitor(this);
	}

	// TODO: Decide if this is worth it. This can perfectly go to a static utils.
	@Override
	public void inHelper(Helper self) {
		if ( self.getDefinition().getFeature() instanceof Attribute ) {
			self.setIsAttribute(true);
		} else {
			self.setIsAttribute(false);
		
			List<Parameter> params = ((Operation) self.getDefinition().getFeature()).getParameters();
			for(int i = 0; i < params.size(); i++) {
				self.getCallableParameters().add(createCallableParameter(params.get(i)));
			}
		}
		
		if ( self.getDefinition().getContext_() == null ) {			
			self.setHasContext(false);
		} else {
			self.setHasContext(true);
		}
	}	

	@Override
	public void inLazyRule(LazyRule self) {
		List<InPatternElement> params = self.getInPattern().getElements();
		for(int i = 0; i < params.size(); i++) {
			self.getCallableParameters().add(createCallableParameter(params.get(i)));
		}
	}

	@Override
	public void inCalledRule(CalledRule self) {
		List<Parameter> params = self.getParameters();
		for(int i = 0; i < params.size(); i++) {
			self.getCallableParameters().add(createCallableParameter(params.get(i)));
		}
	}
	
	private CallableParameter createCallableParameter(VariableDeclaration vd) {
		CallableParameter p = ATLFactory.eINSTANCE.createCallableParameter();
		p.setName(vd.getVarName());
		p.setStaticType(vd.getStaticType());
		p.setParamDeclaration(vd);
		return p;
	}

	@Override
	public void inBinding(Binding self) {
		// Type srcType = attr.typeOf(self.getValue());
		Type srcType = self.getValue().getInferredType();
		
		// Type targetVar = attr.typeOf( self.getOutPatternElement() );
		Type targetVar = self.getOutPatternElement().getInferredType();
		IClassNamespace ns = (IClassNamespace) targetVar.getMetamodelRef();
		EStructuralFeature f = ns.getStructuralFeatureInfo(self.getPropertyName());
		
		self.setWrittenFeature(f);
		

		// One problem of the following algorithm is that it looks every subclass
		// of the class(es) of the right part of the binding. The same rule may
		// resolve many of the subclasses (because getResolvingRules returns rules
		// that matches all possible subtypes).
		// 
		// To avoid adding the same rules many times a set is used
		HashSet<MatchedRule> alreadyAdded = new HashSet<MatchedRule>();
	
		for(Metaclass m: typ().getInvolvedMetaclasses(srcType)) {
			IClassNamespace srcNs = (IClassNamespace) m.getMetamodelRef();
			ArrayList<IClassNamespace> nss = new ArrayList<IClassNamespace>(srcNs.getAllSubclasses());
			nss.add(srcNs);
			for(IClassNamespace sub : nss) {
				for(MatchedRule r : sub.getResolvingRules() ) {
					if ( r.isIsAbstract() || alreadyAdded.contains(r) )
						continue;

					alreadyAdded.add(r);
					
					RuleResolutionInfo ruleResolution = createRuleResolutionInfo(r, ATLUtils.allSuperRules(r));
					self.getResolvedBy().add( ruleResolution ) ;					
				}
			}
		}
		
	}

	public RuleResolutionInfo createRuleResolutionInfo(MatchedRule rule, List<MatchedRule> superRules) {
		RuleResolutionInfo rr = ATLFactory.eINSTANCE.createRuleResolutionInfo();
		rr.setRule(rule);
		rr.getAllInvolvedRules().add(rule);
		for (MatchedRule sup : superRules) {
			rr.getAllInvolvedRules().add(sup);			
		}		
		return rr;
	}

	/*
	private void createControlFlowGraph(BindingAnn ann) {
		ControlFlow cflow = typ.createControlFlow();
		ann.setControlFlow(cflow);		
		
		// ann.getValue()
	}
	*/
	
	@Override
	public void inRuleVariableDeclaration(RuleVariableDeclaration self) {
		// Ignored for the moment, not sure if needed
	}

	@Override
	public void inVariableDeclaration(VariableDeclaration self) {
		// Ignored for the moment
	}
	
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		// Type srcType = attr.typeOf(self.getSource());
		Type srcType = self.getSource().getInferredType();
		
		self.setReceptorType( srcType );
		if ( srcType instanceof Metaclass ) {
			setMetaclassResolvers(self, (Metaclass) srcType);
		} else if ( srcType instanceof ThisModuleType ) {
			computeResolvers(self, self.getName());			
		} else if ( srcType instanceof UnionType ) {
			Type t = typ().compactUnion((UnionType) srcType);
			if ( t instanceof Metaclass ) {
				setMetaclassResolvers(self, (Metaclass) t);
			} else {
				System.err.println("TODO: How to deal with this in createannotations... setUsedFeature...");
			}
		} 
		
		
	}

	protected void setMetaclassResolvers(NavigationOrAttributeCallExp self, Metaclass srcType) {
		IClassNamespace cn = (IClassNamespace) srcType.getMetamodelRef();
		EStructuralFeature f = cn.getStructuralFeatureInfo(self.getName());
		if ( f != null  ) {
			self.setUsedFeature(f);
		} else {
			computeResolvers(srcType, self, self.getName());				
		}
	}
	
	@Override
	public void inOperationCallExp(OperationCallExp self) {
		// CallExprAnn ann = annotationOperationCall(self, self.getArguments());
		computeResolvers(self, self.getOperationName());
	}

	private void computeResolvers(PropertyCallExp self, String featureOrOperationName) {			
		computeResolvers(self.getSource().getInferredType(), self, featureOrOperationName);
	}
	
	private void computeResolvers(Type srcType, PropertyCallExp self, String featureOrOperationName) {			
		if ( srcType instanceof Metaclass && !(srcType instanceof OclModelElement) ) {
			self.setIsStaticCall(false);
			IClassNamespace cn = (IClassNamespace) srcType.getMetamodelRef();
			if ( cn.getAttachedOclFeature(featureOrOperationName) != null ) {
			
				//System.out.println(TypeUtils.typeToString(ann.getSource().getType()) + "." + self.getOperationName() + " - " + self.getLocation());
				OclFeature op = cn.getAttachedOclFeature(featureOrOperationName);
			
				Helper h = (Helper) op.eContainer().eContainer();
				ATLUtils.setStaticResolverBidirectional(self, h);
				// self.setStaticResolver( h );
				self.getDynamicResolvers().add( (ContextHelper) h ) ;
			} else {
				// Must be in the supertype, otherwise was signalled as an error
				for(ClassNamespace sup : cn.getAllSuperClasses()) {
					if ( sup.getAttachedOclFeature(featureOrOperationName) != null ) {
						OclFeature op = sup.getAttachedOclFeature(featureOrOperationName);
						
						Helper h = (Helper) op.eContainer().eContainer();
						// self.setStaticResolver( h );
						ATLUtils.setStaticResolverBidirectional(self, h);
						self.getDynamicResolvers().add( (ContextHelper) h ) ;
						break;
					}
				}
			}
			
			for(ClassNamespace sub : cn.getAllSubclasses()) {
				if ( sub.getAttachedOclFeature(featureOrOperationName) != null ) {
					OclFeature op =  sub.getAttachedOclFeature(featureOrOperationName);
					Helper h = (Helper) op.eContainer().eContainer();
					self.getDynamicResolvers().add( (ContextHelper) h ) ;					
				}
			}
			
		} else if ( srcType instanceof ThisModuleType ) {
			self.setIsStaticCall(true);
			TransformationNamespace tn = (TransformationNamespace) srcType.getMetamodelRef();
			if ( tn.getAttachedOclFeature(featureOrOperationName) != null ) {
				OclFeature op = tn.getAttachedOclFeature(featureOrOperationName);
				Helper h = (Helper) op.eContainer().eContainer();

				//ModuleCallableAnn x = attr.<ModuleCallableAnn>annotationOf(h);
				// self.setStaticResolver( h );
				ATLUtils.setStaticResolverBidirectional(self, h);
			} else if ( tn.hasLazyRule(featureOrOperationName) ) {
				LazyRule r = tn.getLazyRule(featureOrOperationName);
				// self.setStaticResolver( r  );
				ATLUtils.setStaticResolverBidirectional(self, r);
			} else if ( tn.hasCalledRule(featureOrOperationName) ) {
				CalledRule r = tn.getCalledRule(featureOrOperationName);
				// self.setStaticResolver( r );
				ATLUtils.setStaticResolverBidirectional(self, r);
			}
		} else if ( srcType instanceof PrimitiveType ) {
			self.setIsStaticCall(false);
			PrimitiveTypeNamespace tn = (PrimitiveTypeNamespace) srcType.getMetamodelRef();
			OclFeature op = tn.getAttachedOclFeature(featureOrOperationName);
			if ( op != null ) {
				Helper h = (Helper) op.eContainer().eContainer();
				ATLUtils.setStaticResolverBidirectional(self, h);				
			}
		}
	}
	
	@Override
	public void inCollectionOperationCallExp(CollectionOperationCallExp self) {
		computeResolvers(self, self.getOperationName());
	}

}
