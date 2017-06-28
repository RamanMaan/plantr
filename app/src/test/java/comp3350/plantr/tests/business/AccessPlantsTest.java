package comp3350.plantr.tests.business;

import org.junit.Test;

import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.persistence.DatabaseInterface;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessPlantsTest {

	@Test
	public void databaseaccess_testOpen() {
		DatabaseInterface db = AccessPlants.open();
		assertNotNull(db);

		DatabaseInterface db2 = AccessPlants.open();
		assertTrue(db.getAllPlants().equals(db2.getAllPlants()));
	}
}
