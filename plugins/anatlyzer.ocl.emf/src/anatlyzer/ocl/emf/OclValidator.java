package anatlyzer.ocl.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.util.AnalyserUtils;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.IWitnessModel;
import anatlyzer.atl.witness.WitnessUtil;
import anatlyzer.atlext.ATL.Library;

/**
 * This class implements the complete workflow to validate
 * meta-models and OCL expressions.
 * 
 * @author jesus
 */
public class OclValidator {

	protected List<EPackage> packages = new ArrayList<>();
	private List<ConstraintCS> constraints = new ArrayList<>();
	private ValidationResult result;
	
	public OclValidator addMetamodel(EPackage metamodel) {
		packages.add(metamodel);
		return this;
	}

	public OclValidator addConstraint(ConstraintCS constraint) {
		constraints.add(constraint);
		return this;
	}
	
	public OclValidator invoke() {
		Library lib = new ResourceToLibrary().translate("MM", constraints);
		
		if ( packages.size() != 1 )
			throw new UnsupportedOperationException();
		
		ConstraintSatisfactionChecker checker = ConstraintSatisfactionChecker.
				withLibrary(lib).
				withFinder(WitnessUtil.getFirstWitnessFinder()).
				configureMetamodel("MM", packages.get(0).eResource()).
				check();
		
		this.result = new ValidationResult(checker.getFinderResult(), checker.getWitnessModel());
		
		return this;
	}
	
	public ValidationResult getResult() {
		return result;
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
}
