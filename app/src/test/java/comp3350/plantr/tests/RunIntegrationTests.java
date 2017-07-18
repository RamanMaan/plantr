package comp3350.plantr.tests;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import comp3350.plantr.tests.Integration.IntegrationTests;

/**
 * Created by Austin on 2017-07-10.
 */

public class RunIntegrationTests extends TestCase{
	public static TestSuite suite;

	public static Test suite() {
		suite = new TestSuite("Integration tests");
		suite.addTest(IntegrationTests.suite());
		return suite;
	}

}
