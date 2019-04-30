package witness.generator;

import kodkod.engine.Solution.Outcome;
import witness.generator.USESolverMemory.InternallScrollingMV;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.tzi.use.kodkod.UseScrollingKodkodModelValidator;
import org.tzi.use.main.Session;
import org.tzi.use.uml.sys.MSystem;

import anatlyzer.atl.witness.IMetamodelRewrite;
import anatlyzer.atl.witness.IScrollingIterator;
import anatlyzer.atl.witness.IWitnessModel;

public class USEResult {
	private int scope;
	private Outcome outcome;
	private IWitnessModel model;
	private boolean satisfyAllInvariants;
	private long time = -1;
	private ScrollingIterator scrollingIterator;
	
	public USEResult(Outcome outcome, boolean satisfyAllInvariants, IWitnessModel model, int defaultScope) {
		this.outcome   = outcome;
		this.satisfyAllInvariants = satisfyAllInvariants;
		this.model = model;
		this.scope = defaultScope;
	}
	
	public IWitnessModel getModel() {
		return model;
	}
	
	public int getDefaultScope() {
		return scope;
	}
	
	public boolean isSatisfiable() {
		return isSatisfiable(outcome, satisfyAllInvariants);
	}
	
	public static boolean isSatisfiable(Outcome outcome, boolean satisfyAllInvariants) {
		return satisfyAllInvariants && 
				outcome == Outcome.SATISFIABLE ||
				outcome == Outcome.TRIVIALLY_SATISFIABLE;
	}

	public boolean isDiscarded() {
		return satisfyAllInvariants && 
				outcome == Outcome.UNSATISFIABLE ||
				outcome == Outcome.TRIVIALLY_UNSATISFIABLE;
	}
	
	public boolean isUnsupported() {
		return ! satisfyAllInvariants;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
	
	public static class ScrollingIterator implements IScrollingIterator {

		private MSystem system;
		private InternallScrollingMV scrollingValidator;
		private USESolverMemory useSolverMemory;
		private EPackage metamodel;
		private int counter = 0;
		private IMetamodelRewrite rewritingData;
		
		public ScrollingIterator(InternallScrollingMV scrollingValidator, USESolverMemory useSolverMemory) {
			this.scrollingValidator = scrollingValidator;
			this.useSolverMemory = useSolverMemory;
			this.system = useSolverMemory.fSession.system();
			this.metamodel = useSolverMemory.metamodel;
		}

		@Override
		public boolean hasNext() {
			return scrollingValidator != null && ! scrollingValidator.isFinished();
		}

		@Override
		public IWitnessModel next() {
			counter++;
			
			scrollingValidator.nextSolution();
			
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getPackageRegistry().put(metamodel.getNsURI(), metamodel);
			Resource model  = resourceSet.createResource(URI.createFileURI("scrolling" + counter + ".xmi"));

			useSolverMemory.parseOutput2EmfIntoResource(metamodel, model, system.state());
			
			UseFoundWitnessModel m = new UseFoundWitnessModel(metamodel.eResource(), model);
			m.setMetamodelRewritingData(rewritingData);
			return m;
		}

		@Override
		public void setMetamodelRewritingData(IMetamodelRewrite data) {
			this.rewritingData = data;
		}
		
	}

	public void setScrollingIterator(ScrollingIterator scrollingIterator) {
		this.scrollingIterator = scrollingIterator;
	}
	
	public ScrollingIterator getScrollingIterator() {
		return scrollingIterator;
	}
}