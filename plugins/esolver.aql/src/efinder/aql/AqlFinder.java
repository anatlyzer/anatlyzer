package efinder.aql;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;

import anatlyzer.atl.errors.ProblemStatus;
import anatlyzer.atl.witness.ConstraintSatisfactionChecker;
import anatlyzer.atl.witness.UseWitnessFinder;
import anatlyzer.atlext.OCL.OclExpression;

public class AqlFinder {

	private AQL2ATL converter;
	private Collection<OclExpression> expressions = new ArrayList<OclExpression>();
	private Collection<? extends EPackage> packages;
	
	public AqlFinder(Collection<? extends EPackage> packages) {
		AQL2ATL converter = new AQL2ATL();
		converter.addMetamodels(packages);
		this.converter = converter;
		this.packages = packages;
	}

	public AqlFinder withExpression(String expression) {
		OclExpression atl = converter.toExpression(expression);
		expressions.add(atl);
		return this;
	}
	
	public ProblemStatus check() {
		ConstraintSatisfactionChecker checker = new ConstraintSatisfactionChecker(expressions);
		for(EPackage pkg : packages) {
			checker.configureMetamodel(pkg.getName(), pkg.eResource());
		}
		
		checker.withFinder(new InternalFinder());
		checker.check();
		
		return checker.getFinderResult();		
	}
	
	public static class InternalFinder extends UseWitnessFinder {

		@Override
		protected void onUSEInternalError(Exception e) {
			e.printStackTrace();
		}

		@Override
		protected String getTempDirectory() {
			return System.getProperty("java.io.tmpdir");
		}
		
	}
}
