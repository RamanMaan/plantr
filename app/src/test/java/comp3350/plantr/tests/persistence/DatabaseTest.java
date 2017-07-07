package comp3350.plantr.tests.persistence;

import org.junit.Test;

import java.util.List;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * 5/30/2017
 * Keaton MacLeod
 * Class Purpose: This class tests the database
 */

public class DatabaseTest {

	@Test
	public void getPlantById() throws Exception {
		DatabaseAccess.openStub();
		DatabaseInterface database = DatabaseAccess.getDatabaseAccess();

		//test plant with existing id
		Plant plant = database.getPlant(1);
		assertNotNull(plant);

		//test plant with invalid id
		Plant invalidP = database.getPlant(-1);
		assertNull(invalidP);

		//test plant with out of bounds id
		Plant outOfBoundsP = database.getPlant(Integer.MAX_VALUE);
		assertNull(outOfBoundsP);
	}//getPlant

	@Test
	public void getPlantByName() throws Exception {
		DatabaseAccess.openStub();
		DatabaseInterface database = DatabaseAccess.getDatabaseAccess();

		//test plant with existing name
		Plant aloe = database.getPlant("aloe");
		assertNotNull(aloe);

		//test case sensitivity
		Plant aloe2 = database.getPlant("ALOE");
		assertTrue(aloe.equals(aloe2));

		//test plant with invalid name
		Plant invalidP = database.getPlant(null);
		assertNull(invalidP);

		//test plant with non-existing name
		Plant outOfBoundsP = database.getPlant("string_to_fail_test");
		assertNull(outOfBoundsP);
	}//getPlant

	@Test
	public void getAllPlants() throws Exception {
		DatabaseAccess.openStub();
		DatabaseInterface database = DatabaseAccess.getDatabaseAccess();

		List<Plant> plants = database.getAllPlants();
		for (int a = 0; a < plants.size(); a++) {
			assertNotNull(plants.get(a));
		}

		for(int i = 0; i < plants.size(); i++) {
			Plant p = database.getPlant(i);
			assertTrue(p.equals(plants.get(i)));
		}
	}//getAllPlants

}//StubDatabaseTest
