package anatlyzer.atl.quickfixast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.actions.WorkspaceAction;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atlext.ATL.LocatedElement;

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
	private ArrayList<MMAction> mmActions = new ArrayList<QuickfixApplication.MMAction>();
	private AtlProblemQuickfix qfx;
	
	public QuickfixApplication(AtlProblemQuickfix qfx) {
		this.qfx = qfx;
	}

	public List<Action> getActions() {
		return actions;
	}
	
	public List<MMAction> getMetamodelActions() {
		return mmActions;
	}
	
	public <T1 extends EObject, T2 extends EObject> void replace(T1 root, BiFunction<T1, Trace, T2> replacer) {
		//@SuppressWarnings("unchecked")
		//T root = (T) ATLCopier.copySingleElement(originalRoot);
		
		Trace trace = new Trace();
		T2 r = replacer.apply(root, trace);
		ReplacementAction action = new ReplacementAction(root, r, trace);
		
		actions.add(action);
		
		EcoreUtil.replace(root, r);
		// now r is the new root...
	}
	
	public void remove(EObject o) {
		EObject parent = o.eContainer();
		// EcoreUtil.delete(o);
		EcoreUtil.delete(o, true);
		actions.add(new DeleteAction(o, parent));
	}
	
	public <T1 extends EObject, T2 extends EObject> void change(T1 root, 
			Supplier<T2> rootCreator,
			TriConsumer<T1, T2, Trace> replacer) {	
		Trace trace = new Trace();
		
		T2 newRoot = rootCreator.get();
		EcoreUtil.replace(root, newRoot);
		
		replacer.accept(root, newRoot, trace);
		ReplacementAction action = new ReplacementAction(root, newRoot, trace);
		
		actions.add(action);
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

	@SuppressWarnings("unchecked")
	public void putIn(EObject receptor, EReference feature, Supplier<? extends EObject> creator) {
		EObject newObj= creator.get();
		if ( feature.isMany() ) {
			((List<EObject>) receptor.eGet(feature)).add(newObj);
		} else {
			receptor.eSet(feature, newObj);
		}
		actions.add(new PutInAction(receptor, feature, newObj));
	}


	public void addCommentBefore(LocatedElement el, Supplier<String> generator) {
		String comment = generator.get();
		el.getCommentsBefore().add(comment);
		actions.add(new AddCommentBefore(el, comment));
	}
	
	public void mmModify(EStructuralFeature feature, String metamodelName, Consumer<EStructuralFeature> modifyer) {
		modifyer.accept(feature);
		System.out.println("==========> MODIFY FEATURE: " + feature.getName());
		mmActions.add(new MMAction(metamodelName));
	}


	public void mmModify(EClass klass, String metamodelName, Consumer<EClass> modifyer) {
		modifyer.accept(klass);
		mmActions.add(new MMAction(metamodelName));
	}
	
	public void mmModify(EPackage pkg, String metamodelName, Consumer<EPackage> modifyer) {
		modifyer.accept(pkg);
		mmActions.add(new MMAction(metamodelName));
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

	public static class MMAction {
		protected String metamodelName;
		public MMAction(String metamodelName) {
			this.metamodelName = metamodelName;
		}
		
		public String getMetamodelName() {
			return metamodelName;
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

	public static class AddCommentBefore extends Action {
		private LocatedElement src;
		private String comment;

		public AddCommentBefore(LocatedElement src, String comment) {
			super(null, new Trace());
			this.comment = comment;
			this.src = src;
		}

		public LocatedElement getElement() {
			return src;
		}
		
		public String getComment() {
			return comment;
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

	public static class DeleteAction extends Action {
		private EObject container;

		public DeleteAction(EObject deleted, EObject container) {
			super(deleted, new Trace());
			this.container = container;
		}

		@SuppressWarnings("unchecked")
		public Action toMockReplacement() {
			Trace mockTrace = new Trace();
			EList<EReference> refs = container.eClass().getEAllReferences();
			for (EReference ref : refs) {
				mockTrace.preserve(container.eGet(ref));
			}
			return new ReplacementAction(container, container, mockTrace);			
		}
	}
	
	public static class PutInAction extends Action {

		private EObject receptor;
		private EReference feature;

		public PutInAction(EObject receptor, EReference feature, EObject newObj) {
			super(newObj, new Trace());
			this.receptor = receptor;
			this.feature = feature;
		}
		
		public EObject getReceptor() {
			return receptor;
		}

		@SuppressWarnings("unchecked")
		public Action toMockReplacement() {
			Trace mockTrace = new Trace();
			EList<EReference> refs = receptor.eClass().getEAllReferences();
			for (EReference ref : refs) {
				if ( ref != feature ) {
					mockTrace.preserve(receptor.eGet(ref));
				} else if ( ref.isMany() ) {
					for(EObject o: (EList<EObject>) receptor.eGet(feature)) {
						if ( o != tgt ) { 
							mockTrace.preserve(o);
						}
					}
					
				}
			}
			return new ReplacementAction(receptor, receptor, mockTrace);
		}
		
		
	}
	
	public void move(Consumer<EObject> setter, Supplier<EObject> getter) {
		EObject src =getter.get();
		setter.accept(src);
	}

	public void apply() {
		// For the moment nothing... but should be called to ensure everything is in sync
		// 
	}

	public void updateWorkbench(IDocument doc) {
		// Invalidate the current analysis, to avoid problem in continous mode (when tracking
		// problems that are no longer valid (e.g., they point to an object removed by the quick fix))
		AnalysisResult result = this.qfx.getAnalysisResult();
		String loc = result.getATLModel().getMainFileLocation();
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(loc));
		AnalysisIndex.getInstance().clean(f);
		new AnATLyzerBuilder().checkFromText(f, doc.get());
		
		
//		try {
//			f.touch(null);
//		} catch (CoreException e) {
//			e.printStackTrace();
//		}
	}
	
	public void saveMetamodels(AnalysisResult r) {
		mmActions.stream().map(a -> a.getMetamodelName()).distinct().forEach(name -> {
			Resource resource = r.getNamespace().getLogicalNamesToMetamodels().get(name);
			try {
				resource.save(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

}
