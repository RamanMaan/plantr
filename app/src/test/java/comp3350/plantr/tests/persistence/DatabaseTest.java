package comp3350.plantr.tests.persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.Plant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

	@Before
	public void startUp() throws DatabaseStartFailureException {
		DatabaseAccess.openStub();
	}

	@Test
	public void getPlantById() throws Exception {
		//test plant with existing id
		Plant plant = AccessPlants.getPlant(1);
		assertNotNull(plant);

		//test plant with invalid id
		Plant invalidP = AccessPlants.getPlant(-1);
		assertNull(invalidP);

		//test plant with out of bounds id
		Plant outOfBoundsP = AccessPlants.getPlant(Integer.MAX_VALUE);
		assertNull(outOfBoundsP);
	}//getPlant

	@Test
	public void getAllPlants() throws Exception {
		List<Plant> plants = AccessPlants.getAllPlants();
		for (int a = 0; a < plants.size(); a++) {
			assertNotNull(plants.get(a));
		}

		for (int i = 0; i < plants.size(); i++) {
			Plant p = AccessPlants.getPlant(i);
			assertTrue(p.equals(plants.get(i)));
		}
	}//getAllPlants
}//StubDatabaseTest
