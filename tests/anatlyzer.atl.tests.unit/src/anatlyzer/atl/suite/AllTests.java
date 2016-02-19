package anatlyzer.atl.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import anatlyzer.atl.api.atlmodel.TestATLModel;
import anatlyzer.atl.bindingresolution.TestBindingResolution;
import anatlyzer.atl.constraintgen.TestConstraintGeneration;
import anatlyzer.atl.helpers.TestHelpers;
import anatlyzer.atl.imperative.TestImperative;
import anatlyzer.atl.implicitcasting.TestImplicitCasting;
import anatlyzer.atl.operators.TestOperators;
import anatlyzer.atl.resolvetemp.TestResolveTemp;
import anatlyzer.atl.retyping.TestRetyping;
import anatlyzer.atl.ruleconflicts.TestRuleConflicts;
import anatlyzer.atl.twovaluedlogic.TestUndefinedBoolean;
import anatlyzer.atl.typing.TestTyping;
import anatlyzer.atl.unions.TestUnions;

@RunWith(Suite.class)
@SuiteClasses({
	// API tests
	TestATLModel.class,
	
	// Basic typing
	TestImplicitCasting.class, 
	TestUnions.class,
	TestTyping.class,
	TestOperators.class,
	TestImperative.class,
	
	// Constraint solving
	TestBindingResolution.class,
	TestResolveTemp.class,
	TestRuleConflicts.class,
	TestRetyping.class,
	TestUndefinedBoolean.class,

	TestConstraintGeneration.class,
	TestHelpers.class})
public class AllTests {

}
