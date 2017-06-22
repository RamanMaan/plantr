package comp3350.plantr.tests.business;

import org.junit.Test;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.persistence.DatabaseInterface;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DatabaseAccessTest {

	@Test
	public void databaseaccess_testOpen() {
		DatabaseInterface db = DatabaseAccess.open();
		assertNotNull(db);

		DatabaseInterface db2 = DatabaseAccess.open();
		assertTrue(db.getAllPlants().equals(db2.getAllPlants()));
	}
}
