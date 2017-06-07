package comp3350.plantr.tests.application;

import org.junit.Test;
import static org.junit.Assert.*;
import comp3350.plantr.application.DatabaseAccess;
import comp3350.plantr.persistence.DatabaseInterface;

public class DatabaseAccessTest {

	@Test
	public void databaseaccess_testOpen() {
		DatabaseInterface db = DatabaseAccess.open();
		assertNotNull(db);

		DatabaseInterface db2 = DatabaseAccess.open();
		assertTrue(db.getAllPlants().equals(db2.getAllPlants()));
	}
}
