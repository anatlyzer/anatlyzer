package anatlyzer.atl.analyser.batch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import anatlyzer.atl.analyser.generators.CSPModel;
import anatlyzer.atl.model.ATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLUtils;
import anatlyzer.atlext.ATL.ATLFactory;
import anatlyzer.atlext.ATL.Binding;
import anatlyzer.atlext.ATL.RuleResolutionInfo;
import anatlyzer.atlext.OCL.IterateExp;
import anatlyzer.atlext.OCL.Iterator;
import anatlyzer.atlext.OCL.IteratorExp;
import anatlyzer.atlext.OCL.LoopExp;
import anatlyzer.atlext.OCL.NavigationOrAttributeCallExp;
import anatlyzer.atlext.OCL.OCLFactory;
import anatlyzer.atlext.OCL.OclExpression;
import anatlyzer.atlext.OCL.VariableExp;
import anatlyzer.atlext.processing.AbstractVisitor;
import anatlyzer.atlext.processing.AbstractVisitor.VisitingActions;

public class InvariantReplacerVisitor extends AbstractVisitor {

	private ATLModel model;
	private HashMap<EObject, List<Info>> translation = new HashMap<>();
	private CSPModel builder = new CSPModel();
	
	public InvariantReplacerVisitor(ATLModel model) {
		this.model = model;
	}
	
	@Override
	public VisitingActions preIteratorExp(IteratorExp self) {
		return actions("source", "iterators", "body");
	}
	
	@Override
	public void inIterator(Iterator self) {
		LoopExp loop = (LoopExp) self.eContainer();
		store1(loop.getSource(), (info) -> 	{
			Iterator copy = OCLFactory.eINSTANCE.createIterator();
			copy.setVarName(self.getVarName());
			return info.propagate(copy);
		});
	}
	
	@Override
	public void inVariableExp(VariableExp self) {
		store1(self.getReferredVariable(), (info) -> {
			VariableExp copy = OCLFactory.eINSTANCE.createVariableExp();
			// TODO: Do variable re-assignment well
			copy.setReferredVariable(self.getReferredVariable());
			return info.propagate(copy);
		});
	}
	
	@Override
	public void inNavigationOrAttributeCallExp(NavigationOrAttributeCallExp self) {
		touch(self);

		// Is it a true navigation, not a helper call
		if ( self.getUsedFeature() != null ) {
			if ( ! (self.getSource() instanceof VariableExp) ) {
				throw new UnsupportedOperationException();
			}
			
			List<Binding> allBindings = model.allObjectsOf(Binding.class).stream().
				filter(b -> b.getWrittenFeature() == self.getUsedFeature()).
				collect(Collectors.toList());
			
			if ( allBindings.size() == 0 ) {
				throw new UnsupportedOperationException();
			}
			
			if ( allBindings.size() > 1 ) {
				throw new UnsupportedOperationException();
			}
			
			Binding b = allBindings.get(0);
			
			List<Info> resolutions = new ArrayList<Info>();
			if ( isPrimitiveBinding(b) ) {
				resolutions.add(new Info(copy(b.getValue()), null));
			} else {			
				for(RuleResolutionInfo rri : b.getResolvedBy()) {
					OclExpression src = copy(b.getValue());
					if ( rri.getRule().getInPattern().getFilter() != null ) {
						IteratorExp select = builder.createIterator(src, "select", rri.getRule().getInPattern().getElements().get(0).getVarName());
						select.setBody( copy(rri.getRule().getInPattern().getFilter()) );
						src = select;
					}
					
					// NavigationOrAttributeCallExp nav = OCLFactory.eINSTANCE.createNavigationOrAttributeCallExp();
					// nav.setSource( copy(self.getSource()) );
					
					resolutions.add(new Info(src, rri.getRule()));				
				}
			}
			store(resolutions);				
		}
	}

	@Override
	public void inIteratorExp(IteratorExp self) {
		store1(self.getSource(), (infoSource) -> { 
			IteratorExp newIterator = OCLFactory.eINSTANCE.createIteratorExp();
			newIterator.setName(self.getName());
			Iterator it = OCLFactory.eINSTANCE.createIterator();
			it.setVarName(self.getIterators().get(0).getVarName());
			newIterator.getIterators().add(it);
			
			newIterator.setSource((OclExpression) infoSource.target);			
			newIterator.setBody( composeCopy(self.getBody()) );
			
			return infoSource.propagate(newIterator);
		});
	}
	
	// Predicates

	private OclExpression composeCopy(OclExpression self) {
		List<Info> infos = get(self);
		
		// Analyse the expression to decide!
		if ( self instanceof NavigationOrAttributeCallExp ) {
			NavigationOrAttributeCallExp nav = (NavigationOrAttributeCallExp) self;
			if ( nav.getUsedFeature() instanceof EAttribute ) {
				check(infos.size() == 1);
				
				return (OclExpression) copy(infos.get(0).target);
			}
		}
		throw new IllegalStateException();
	}

	private void check(boolean b) {
		if ( ! b ) throw new IllegalStateException();
	}

	private boolean isPrimitiveBinding(Binding b) {
		return b.getWrittenFeature() instanceof EAttribute;
	}
	
	
	// Facilities

	private void store(List<Info> resolutions) {
		translation.put(this.current, resolutions);
	}

	private List<Info> get(EObject obj) {
		return translation.get(obj);
	}
	
	private void storeN(EObject obj, Function<Info, List<Info>> f) {
		store( get(obj).stream().flatMap(e -> f.apply(e).stream()).collect(Collectors.toList()) );
	}
	
	private void store1(EObject obj, Function<Info, Info> f) {
		storeN(obj, (passedExpr) -> Collections.singletonList(f.apply(passedExpr)));
	}
	
	private EObject copy(EObject target) {
		return ATLCopier.copySingleElement(target);
	}

	private OclExpression copy(OclExpression target) {
		return (OclExpression) ATLCopier.copySingleElement(target);
	}

	private HashSet<EObject> touched = new HashSet<EObject>();
	private void touch(EObject self) {
		touched.add(self);
	}
	
	// Extra classes
	public class Info {
		private EObject target;
		private EObject propagated;

		public Info(EObject target, EObject propagated) {
			this.target = target;
			this.propagated = propagated;
		}

		public Info propagate(EObject copy) {
			return new Info(copy, propagated);
		}
	}
	
}
