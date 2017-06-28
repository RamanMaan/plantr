package comp3350.plantr.tests.model;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by Michael on 27/06/2017
 *
 * Class that currently only tests plant object creation.
 */

public class PersonalPlantTest {


	@Test
	public void personalPlant_testEquals(){

		Plant dummyPlantOne = new Plant(1);
		Plant dummyPlantTwo = new Plant(2);

		PersonalPlant firstPlant = new PersonalPlant(null, "myTestPlant", null);
		PersonalPlant secondPlant = new PersonalPlant(dummyPlantOne, "myTestPlant2", null);

		int first = firstPlant.getID();
		int second = first+1;

		//testing the equals function
		assertTrue(firstPlant.equals(first));
		assertTrue(secondPlant.equals(second));

		assertTrue(!firstPlant.equals(second));
		assertTrue(!secondPlant.equals(first));

		assertNotEquals(firstPlant, secondPlant);

		//same everything
		firstPlant = new PersonalPlant(dummyPlantOne, "sameString", null);
		secondPlant = new PersonalPlant(dummyPlantOne, "sameString", null, firstPlant.getID());

		assertEquals(firstPlant,secondPlant);

		//different ID, same plant and string though
		firstPlant = new PersonalPlant(dummyPlantOne, "sameString", null);
		secondPlant = new PersonalPlant(dummyPlantOne, "sameString", null);

		assertNotEquals(firstPlant, secondPlant);

		//different ID, different plant, same string
		firstPlant = new PersonalPlant(dummyPlantOne, "I am a string", null);
		secondPlant = new PersonalPlant(dummyPlantOne, "I am a string that is different", null);

	}
}
