package anatlyzer.atl.quickfixast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.IDocument;

import anatlyzer.atl.analyser.AnalysisResult;
import anatlyzer.atl.analyser.IAnalyserResult;
import anatlyzer.atl.editor.builder.AnATLyzerBuilder;
import anatlyzer.atl.editor.quickfix.AtlCompletionProposal;
import anatlyzer.atl.editor.quickfix.AtlProblemQuickfix;
import anatlyzer.atl.impact.CallableImpactCalculator;
import anatlyzer.atl.impact.ChangeImpact;
import anatlyzer.atl.impact.IQuickfixSpecificImpactComputation;
import anatlyzer.atl.impact.ImpactFactory;
import anatlyzer.atl.index.AnalysisIndex;
import anatlyzer.atl.model.ATLModel.ITracedATLModel;
import anatlyzer.atl.util.ATLCopier;
import anatlyzer.atl.util.ATLSerializer;
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
	private AtlCompletionProposal qfx;
	private IQuickfixSpecificImpactComputation impact;
	
	public QuickfixApplication(AtlCompletionProposal qfx) {
		this.qfx = qfx;
	}

	public IAnalyserResult getAnalysis() {
		return qfx.getAnalysisResult().getAnalyser();
	}
	
	public List<Action> getActions() {
		return actions;
	}
	
	public List<MMAction> getMetamodelActions() {
		return mmActions;
	}
	
	public <T1 extends EObject, T2 extends EObject> void replace(T1 root, BiFunction<T1, Trace, T2> replacer) {
		// Compute the change impact before anything actually happens
		// new GenericImpactCreator().perform(root, theChangeImpact);
		
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
		EObject copy = ATLCopier.copySingleElement(o);
		EcoreUtil.delete(o, true);
		actions.add(new DeleteAction(o, parent, copy));
	}
	
	/**
	 * This is used when the replaced element is part of the replacement.
	 * 
	 * @param root
	 * @param rootCreator
	 * @param replacer
	 */
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

	public <A extends LocatedElement, B extends LocatedElement> void insertBefore(A anchor, Supplier<B> supplier) {
		B element = supplier.get();
		
		// Add the creted element following the the anchor element
		EStructuralFeature feature = anchor.eContainingFeature();
		if ( ! (feature instanceof EReference) || ! feature.isMany() ) {
			throw new IllegalArgumentException();
		}
		EObject parent = anchor.eContainer();
		@SuppressWarnings("unchecked")
		EList<EObject> list = (EList<EObject>) parent.eGet(feature);
		list.add(list.indexOf(anchor) - 1, element);
		
		actions.add(new InsertBeforeAction(anchor, element));
	}

	@SuppressWarnings("unchecked")
	public void putIn(EObject receptor, EReference feature, Supplier<? extends EObject> creator) {
		EObject newObj = creator.get();
		EObject anchor = null;
		if ( feature.isMany() ) {
			List<EObject> list = ((List<EObject>) receptor.eGet(feature));
			for (int i = list.size() - 1; i >= 0; i--) {
				EObject obj = list.get(i);
				// This is to skip automatically generated elements from the pre-processor
				if ( obj instanceof LocatedElement && ((LocatedElement) obj).getLocation() != null ) {
					anchor = obj;
					break;
				}
			}
//			if ( list.size() > 0 ) {
//				anchor = list.get(list.size() - 1);
//			}
			list.add(newObj);
		} else {
			receptor.eSet(feature, newObj);
		}
		actions.add(new PutInAction(receptor, feature, newObj, anchor));
	}


	public void addCommentBefore(LocatedElement el, Supplier<String> generator) {
		String comment = generator.get();
		el.getCommentsBefore().add(comment);
		actions.add(new AddCommentBefore(el, comment));
	}
	
	public void mmModify(EStructuralFeature feature, String metamodelName, Consumer<EStructuralFeature> modifyer) {
		modifyer.accept(feature);
		mmActions.add(new MMAction(metamodelName));
	}


	public void mmModify(EClass klass, String metamodelName, Consumer<EClass> modifyer) {
		modifyer.accept(klass);
		mmActions.add(new MMAction(metamodelName));
	}
	
	public void mmModify(EPackage pkg, String metamodelName, Consumer<EPackage> modifyer) {
		modifyer.accept(pkg);
		AnalysisResult result = this.qfx.getAnalysisResult();
		result.getNamespace().invalidate();
		
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
	
	public static abstract class Action {
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
		
		public abstract String getText();

		public abstract void fillImpact(ChangeImpact impact) throws UnknownImpact;

		public abstract void updateSpeculativeTrace(ITracedATLModel trace2);
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
		
		public String getText() {
			return comment;
		}
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// TODO: Need a way to indicate that this is pre-condition
			throw new UnknownImpact();
		}
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { }
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

		public String getText() {
			return ATLSerializer.serialize(tgt);
		}
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// new GenericImpactCreator().perform(src, impact);
		}		
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { 
			// This could probably be controlled by a systematic usage
			// of change and replace, where change happens when src is within tgt.
			// As a sanity check, here it is checked that if src is within tgt, no
			// need to update the trace
			TreeIterator<EObject> it = tgt.eAllContents();
			while ( it.hasNext() ) {
				if ( it.next() == src ) {
					return;
				}
			}
			
			// If we are here, src no longer exists...
			// Not sure if another restriction is that src cannot be moved (i.e., two QFA actions at the same time).
			speculativeTrace.updateTarget(src, tgt);			
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

		public String getText() {
			return ATLSerializer.serialize(tgt);
		}
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// TODO: Need a way to indicate that this is pre-condition
			throw new UnknownImpact();
		}
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { 
			// nothing, it is an insertion			
		}
	}

	public static class InsertBeforeAction extends Action {
		private EObject anchor;

		public InsertBeforeAction(EObject anchor, EObject tgt) {
			super(tgt, new Trace());
			this.anchor = anchor;
		}
		
		public EObject getAnchor() {
			return anchor;
		}		

		public String getText() {
			return ATLSerializer.serialize(tgt);
		}
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// TODO: Need a way to indicate that this is pre-condition
			throw new UnknownImpact();
		}
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { 
			// nothing, it is an insertion			
		}
	}

	public static class DeleteAction extends Action {
		private EObject container;
		private EObject copyOfDeleted;

		public DeleteAction(EObject deleted, EObject container, EObject copyOfDeleted) {
			super(deleted, new Trace());
			this.container = container;
			this.copyOfDeleted = copyOfDeleted;
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

		public String getText() {
			return "-- Removed: \n" + ATLSerializer.serialize(copyOfDeleted);
		}
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// TODO: Need a way to indicate that this is pre-condition
			throw new UnknownImpact();
		}
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { 
			// could mark as deleted... but this requires changes in the impact computation??	
		}

	}
	
	public static class PutInAction extends Action {

		private EObject receptor;
		private EReference feature;
		private EObject anchor;

		public PutInAction(EObject receptor, EReference feature, EObject newObj, EObject anchor) {
			super(newObj, new Trace());
			this.receptor = receptor;
			this.feature = feature;
			this.anchor = anchor;
		}
		
		public EObject getReceptor() {
			return receptor;
		}

		public String getText() {
			return ATLSerializer.serialize(tgt);
		}
		
		@SuppressWarnings("unchecked")
		public Action toMockReplacement() {
			if ( anchor != null ) {
				return new InsertAfterAction(anchor, this.tgt);
			}
			
			
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
		
		@Override
		public void fillImpact(ChangeImpact impact) throws UnknownImpact {
			// TODO: Need a way to indicate that this is pre-condition
			throw new UnknownImpact();
		}		
		
		public void updateSpeculativeTrace(ITracedATLModel speculativeTrace) { 
			// Nothing
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


	public void updateSpeculativeTrace(ITracedATLModel trace) {
		this.actions.forEach(a -> {
			a.updateSpeculativeTrace(trace);
		});		
	}
	
	public void updateWorkbench(IDocument doc) {
		// Invalidate the current analysis, to avoid problem in continous mode (when tracking
		// problems that are no longer valid (e.g., they point to an object removed by the quick fix))

//
// 		This is without incremental analysis (safer...)
//	
		
		if ( this.qfx != null ) {
			AnalysisResult result = this.qfx.getAnalysisResult();
			String loc = result.getATLModel().getMainFileLocation();
			IFile f = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(loc));
			AnalysisIndex.getInstance().clean(f);
			new AnATLyzerBuilder().checkFromText(f, doc.get());
		}
		
//		AnalysisResult result = this.qfx.getAnalysisResult();
//
//		impact.perform(result.getATLModel());
//		IncrementalAnalyser analyser = new IncrementalAnalyser(result.getNamespace(), result.getATLModel(), impact);
//		analyser.perform();

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

	public void impactOn(CallableImpactCalculator impact) {
		this.impact = impact;
	}
	
	public static class UnknownImpact extends Exception {
		private static final long serialVersionUID = 1556874133001996117L;
	}
	
	
	private ChangeImpact theChangeImpact = ImpactFactory.eINSTANCE.createChangeImpact();
	public ChangeImpact getImpact() {
		return theChangeImpact;
		/*
		ChangeImpact i = ImpactFactory.eINSTANCE.createChangeImpact();
		
		for (Action action : actions) {
			try {
				action.fillImpact(i);
			} catch (UnknownImpact e) {
				// Impact cannot be computed
				return null;
			}
		}
		
		return i;
		*/
	}


}
