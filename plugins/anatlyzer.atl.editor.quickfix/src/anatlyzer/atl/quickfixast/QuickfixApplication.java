package anatlyzer.atl.quickfixast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.ATL.ModuleElement;
import anatlyzer.atlext.OCL.OclExpression;

/**
 * This class represents a modification in the AST, allowing
 * to keep track of the changes for a proper subsequent handling,
 * for instance layout-preserving serialization.
 * @author jesus
 *
 * @param <T>
 */
public class QuickfixApplication {

	private ArrayList<Action> actions = new ArrayList<QuickfixApplication.Action>();
	
	public List<Action> getActions() {
		return actions;
	}
	
	public <T extends EObject> void replace(T originalRoot, BiFunction<T, Trace, T> replacer) {
		@SuppressWarnings("unchecked")
		T root = (T) ATLCopier.copySingleElement(originalRoot);
		
		Trace trace = new Trace();
		T r = replacer.apply(root, trace);
		ReplacementAction action = new ReplacementAction(root, r, trace);
		
		actions.add(action);
		
		EcoreUtil.replace(root, r);
		// now r is the new root...
	}
	
	public <A extends LocatedElement, B extends LocatedElement> void insertAfter(A anchor, Supplier<B> supplier) {
		B element = supplier.get();
		
		// Add the creted element following the the anchor element
		EStructuralFeature feature = anchor.eContainingFeature();
		if ( ! (feature instanceof EReference) || ! feature.isMany() ) {
			throw new IllegalArgumentException();
		}
		EObject parent = anchor.eContainer();
		@SuppressWarnings("unchecked")
		EList<EObject> list = (EList<EObject>) parent.eGet(feature);
		list.add(list.indexOf(anchor) + 1 , element);
		
		actions.add(new InsertAfterAction(anchor, element));
	}
	
	public static class Trace {
		LinkedList<Object> preservedElements = new LinkedList<Object>();
		
		@SuppressWarnings("unchecked")
		public void preserve(Object obj) {
			preservedElements.add(obj);
			if ( obj instanceof Collection ) {
				preservedElements.addAll((Collection<? extends Object>) obj);
			}
		}

		public boolean isPreserved(EObject obj) {
			return preservedElements.contains(obj);
		}
	}

	public static class Action {
		protected EObject tgt;
		protected Trace trace;

		public Action(EObject tgt, Trace trace) {
			this.tgt = tgt;
			this.trace = trace;
		}
		
		public EObject getTgt() {
			return tgt;
		}

		public Trace getTrace() {
			return trace;
		}
	}
	
	public static class ReplacementAction extends Action {
		private EObject src;

		public ReplacementAction(EObject src, EObject tgt, Trace trace) {
			super(tgt, trace);
			this.src = src;
			this.tgt = tgt;
		}
		
		public EObject getSrc() {
			return src;
		}		
	}
	
	public static class InsertAfterAction extends Action {

		private EObject anchor;

		public InsertAfterAction(EObject anchor, EObject tgt) {
			super(tgt, new Trace());
			this.anchor = anchor;
		}
		
		public EObject getAnchor() {
			return anchor;
		}
		
	}

	public void move(Consumer<EObject> setter, Supplier<EObject> getter) {
		EObject src =getter.get();
		setter.accept(src);
	}

	public void apply() {
		// For the moment nothing... but should be called to ensure everything is in sync
	}

}
