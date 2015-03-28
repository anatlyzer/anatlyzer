package anatlyzer.atl.quickfixast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atlext.ATL.LocatedElement;
import anatlyzer.atlext.OCL.OclExpression;

public class QuickfixScope<T extends LocatedElement> {
	private T root;
	private T originalRoot;

	private ArrayList<ReplacementAction> actions = new ArrayList<QuickfixScope.ReplacementAction>();
	
	@SuppressWarnings("unchecked")
	public QuickfixScope(T root) {
		this.originalRoot = root;
		this.root         = (T) ATLCopier.copySingleElement(root);
	}

	public ArrayList<ReplacementAction> getActions() {
		return actions;
	}
	
	public void replace(BiFunction<T, Trace, T> replacer) {
		Trace trace = new Trace();
		T r = replacer.apply(root, trace);
		ReplacementAction action = new ReplacementAction(root, r, trace);
		
		actions.add(action);
		
		EcoreUtil.replace(root, r);
		// now r is the new root...
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

	public static class ReplacementAction {
		private EObject src;
		private EObject tgt;
		private Trace trace;

		public ReplacementAction(EObject src, EObject tgt, Trace trace) {
			this.src = src;
			this.tgt = tgt;
			this.trace = trace;
		}
		
		public EObject getSrc() {
			return src;
		}
		
		public EObject getTgt() {
			return tgt;
		}

		public Trace getTrace() {
			return trace;
		}
	}

	public void move(Consumer<EObject> setter, Supplier<EObject> getter) {
		EObject src =getter.get();
		setter.accept(src);
	}

}
