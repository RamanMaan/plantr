package comp3350.plantr.tests.model;

import org.junit.Before;
import org.junit.Test;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Michael on 27/06/2017
 *
 * Class that currently only tests plant object creation.
 */

public class PersonalPlantTest {

	@Before
	public void startUp() throws DatabaseStartFailureException {
		DatabaseAccess.openStub();
	}

	@Test
	public void personalPlant_testEquals(){
		Plant dummyPlantOne = new Plant(1);
		Plant dummyPlantTwo = new Plant(2);

		PersonalPlant firstPlant = new PersonalPlant(null, "myTestPlant", 1, null, null);
		PersonalPlant secondPlant = new PersonalPlant(dummyPlantOne, "myTestPlant2", 2, null, null);

		//testing the equals function
		assertTrue(firstPlant.equals(firstPlant.getID()));
		assertTrue(secondPlant.equals(secondPlant.getID()));

		assertTrue(!firstPlant.equals(secondPlant));
		assertTrue(!secondPlant.equals(firstPlant));

		assertNotEquals(firstPlant, secondPlant);

		//same everything
		firstPlant = new PersonalPlant(dummyPlantOne, "sameString", 1, null, null);
		secondPlant = new PersonalPlant(dummyPlantOne, "sameString", 1, null, null);

		assertEquals(firstPlant, secondPlant);

		//different ID, same plant and string though
		firstPlant = new PersonalPlant(dummyPlantOne, "sameString", 1, null, null);
		secondPlant = new PersonalPlant(dummyPlantOne, "sameString", 2, null, null);

		assertNotEquals(firstPlant, secondPlant);

		//different ID, diff plant, diff string
		firstPlant = new PersonalPlant(dummyPlantOne, "I am a string", 1, null, null);
		secondPlant = new PersonalPlant(dummyPlantTwo, "I am a string that is different", 2, null, null);

		assertNotEquals(firstPlant, secondPlant);
	}
}
