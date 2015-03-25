package anatlyzer.atl.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import anatlyzer.atl.bindingresolution.TestBindingResolution;
import anatlyzer.atl.constraintgen.TestConstraintGeneration;
import anatlyzer.atl.helpers.TestHelpers;
import anatlyzer.atl.implicitcasting.TestImplicitCasting;
import anatlyzer.atl.retyping.TestRetyping;
import anatlyzer.atl.ruleconflicts.TestRuleConflicts;

@RunWith(Suite.class)
@SuiteClasses({
	TestImplicitCasting.class, 
	
	// Constraint solving
	TestBindingResolution.class,
	TestRuleConflicts.class,
	TestRetyping.class,

	TestConstraintGeneration.class,
	TestHelpers.class})
public class AllTests {

}
