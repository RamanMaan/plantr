package comp3350.plantr.tests.Integration;

/**
 * Created by Austin on 2017-07-10.
 */

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("Integration tests");
		suite.addTestSuite(BusinessPersistenceSeamTest.class);
		suite.addTestSuite(HSQLDBTest.class);
		return suite;
	}
}
