package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.DefCS;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.IInputPartialModel;
import anatlyzer.atl.witness.IWitnessFinder;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atl.witness.XMIPartialModel;
import anatlyzer.atlext.ATL.Library;
import anatlyzer.ocl.emf.scope.Bounds;
import anatlyzer.ocl.emf.scope.ExplicitScopeCalculator;

/**
 * This class implements the complete workflow to validate
 * meta-models and OCL expressions.
 * 
 * @author jesus
 */
public class OclValidator {

	protected List<EPackage> packages = new ArrayList<>();
	private List<ConstraintCS> constraints = new ArrayList<>();
	private List<Constraint> constraintsEcore = new ArrayList<>();
	private ValidationResult result;
	private List<Bounds> bounds = new ArrayList<Bounds>();
	private IInputPartialModel partialModel;
	private IWitnessFinder wf;
	private List<DefCS> operations = new ArrayList<DefCS>();
	
	public OclValidator addMetamodel(EPackage metamodel) {
		packages.add(metamodel);
		return this;
	}

	public OclValidator addConstraint(ConstraintCS constraint) {
		constraints.add(constraint);
		return this;
	}

	public OclValidator addConstraint(Constraint constraint) {
		constraintsEcore.add(constraint);
		return this;
	}

	public OclValidator withWitnessFinder(IWitnessFinder wf) {
		this.wf = wf;
		return this;
	}
	
	public void setPartialModel(IInputPartialModel partialModel) {
		this.partialModel = partialModel;
	}

	public OclValidator invoke() {
		Library lib = new ResourceToLibrary().translate("MM", constraints, constraintsEcore, operations);
		
		if ( packages.size() != 1 )
			throw new UnsupportedOperationException();
		
		IWitnessFinder finder = this.wf != null ? this.wf : WitnessUtil.getFirstWitnessFinder();
		finder.setScopeCalculator(new ExplicitScopeCalculator().withBounds(bounds));
		finder.setInputPartialModel(partialModel);
		finder.setCheckAllCompositeConstraints(true);
		
		ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withLibrary(lib).
				withFinder(finder).
				configureMetamodel("MM", packages.get(0).eResource()).
				check();
		
		this.result = new ValidationResult(checker.getFinderResult(), checker.getWitnessModel());
		
		return this;
	}
	
	public ValidationResult getResult() {
		return result;
	}

	public void addBounds(String className, int min, int max) {
		EClassifier klass = packages.stream().
			filter(p -> p.getEClassifier(className) != null).
			map(p -> p.getEClassifier(className)).
			findFirst().orElseThrow(() -> new RuntimeException("Cannot find : " + className));

		
		bounds.add(new Bounds(klass, min, max));
	}
	
	public static class ValidationResult {
		private ProblemStatus status;
		private IWitnessModel witnessModel;

		public ValidationResult(ProblemStatus status) {
			this.status = status;
		}
		
		public ValidationResult(ProblemStatus status, IWitnessModel witnessModel) {
			this(status);
			this.witnessModel = witnessModel;
		}

		public ProblemStatus getStatus() {
			return status;
		}
		
		public IWitnessModel getWitnessModel() {
			return witnessModel;
		}

		public boolean sat() {
			return AnalyserUtils.isConfirmed(status);
		}
		
		public boolean unsat() {
			return AnalyserUtils.isDiscarded(status);
		}
		
		public boolean isError() {
			return AnalyserUtils.isErrorStatus(status);
		}
		
	}

	public void addOperation(DefCS defCS) {
		this.operations .add(defCS);
	}

}
