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
	public void personalPlant_testCreate() {
		Plant dummyPlant = new Plant(1);

		PersonalPlant myPlant = new PersonalPlant(dummyPlant, "myTestPlant");

		assertNotNull(myPlant);
	}

	@Test
	public void personalPlant_testEquals(){

		Plant dummyPlant = new Plant(1);

		PersonalPlant firstPlant = new PersonalPlant(null, "myTestPlant");
		PersonalPlant secondPlant = new PersonalPlant(dummyPlant, "myTestPlant2");

		int first = firstPlant.getID();
		int second = first+1;

		//testing the equals function
		assertTrue(firstPlant.equals(first));
		assertTrue(secondPlant.equals(second));

		assertTrue(!firstPlant.equals(second));
		assertTrue(!secondPlant.equals(first));

		assertNotEquals(firstPlant, secondPlant);
	}
}
