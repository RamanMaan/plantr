package comp3350.plantr.tests.Integration;

import junit.framework.TestCase;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.tests.persistence.HSQLDatabaseTest;

/**
 * Created by Austin on 2017-07-10.
 */

public class HSQLDBTest extends TestCase {

	public HSQLDBTest(String arg0) {
		super(arg0);
	}

	public void testDataAccess() {

		System.out.println("\nStarting Integration test DataAccess (using HSQLDB)");

		try {
			DatabaseAccess.getDatabaseAccess();
			HSQLDatabaseTest.testAccess();

		} catch (DatabaseStartFailureException e) {
			System.out.println("Starting DB failed in Integration test");
			e.printStackTrace();
		}

		System.out.println("Finished Integration test DataAccess (using HSQLDB)");
	}
}
