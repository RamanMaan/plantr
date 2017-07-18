package comp3350.plantr.tests.persistence;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import comp3350.plantr.business.AccessGarden;
import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.PersonalPlantManager;
import comp3350.plantr.business.UserManager;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DatabaseTest {

	@Before
	public void startUp() throws DatabaseStartFailureException, UserLoginException {
		DatabaseAccess.openStub();
		UserManager.loginUser("ramanmaan@plantr.io", "plantr");
	}

	@Test
	public void getPlantById() throws SQLException, DatabaseStartFailureException {
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
	public void getAllPlants() throws SQLException, DatabaseStartFailureException {
		List<Plant> plants = AccessPlants.getAllPlants();
		for (int a = 0; a < plants.size(); a++) {
			assertNotNull(plants.get(a));
		}

		for (int i = 0; i < plants.size(); i++) {
			Plant p = AccessPlants.getPlant(i);
			assertTrue(p.equals(plants.get(i)));
		}
	}//getAllPlants

	@Test
	public void getPersonalPlantByID() throws SQLException, DatabaseStartFailureException {
		//test plant with existing id
		PersonalPlant plant = AccessGarden.getPersonalPlantByID(1);
		assertNotNull(plant);

		//test plant with invalid id
		PersonalPlant invalidP = AccessGarden.getPersonalPlantByID(-1);
		assertNull(invalidP);

		//test plant with out of bounds id
		PersonalPlant outOfBoundsP = AccessGarden.getPersonalPlantByID(Integer.MAX_VALUE);
		assertNull(outOfBoundsP);
	}

	@Test
	public void getAllPersonalPlants() throws SQLException, DatabaseStartFailureException, UserLoginException {
		User u = UserManager.getUser();

		List<PersonalPlant> plants = AccessGarden.getAllPersonalPlants();
		for (int a = 0; a < plants.size(); a++) {
			assertNotNull(plants.get(a));
		}

		//ensure every plant belongs to user
		for (int i = 0; i < plants.size(); i++) {
			assertTrue(plants.get(i).getOwner().equals(u));
		}
	}

	@Test
	public void updatePersonalPlant() throws UserLoginException, SQLException, DatabaseStartFailureException {
		Date currentDate = new Date();
		PersonalPlant p = AccessGarden.getAllPersonalPlants().get(0);
		assertTrue(p.getLastWatered().before(currentDate));

		Date oldDate = p.getLastWatered();

		//water the plant
		p.setLastWatered(currentDate);
		//update it
		AccessGarden.updatePersonalPlant(p);

		//get the plant again
		p = AccessGarden.getPersonalPlantByID(p.getID());

		//ensure it was updated
		assertTrue(p.getLastWatered().equals(currentDate));
		assertFalse(p.getLastWatered().equals(oldDate));
	}

	@Test
	public void removePersonalPlant() throws SQLException, DatabaseStartFailureException {
		PersonalPlant plant = AccessGarden.getPersonalPlantByID(1);
		//test existing plant
		AccessGarden.removePersonalPlant(plant);
		assertNull(AccessGarden.getPersonalPlantByID(1));

		//test plant that didn't ever exist
		assertNull(AccessGarden.getPersonalPlantByID(Integer.MAX_VALUE));

		//test plant with out of bounds id
		assertNull(AccessGarden.getPersonalPlantByID(-100));
	}
}//StubDatabaseTest
