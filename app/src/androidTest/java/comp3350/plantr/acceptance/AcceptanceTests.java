package comp3350.plantr.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(LookupTest.class);
        suite.addTestSuite(ViewInformationOnPlantsTest.class);
        return suite;
    }
}
