package comp3350.plantr.tests.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import comp3350.plantr.business.AccessPlants;
import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

	@Test
	public void watering_test() throws SQLException, DatabaseStartFailureException {
		//setup
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -50);
		Date currentDate = new Date();
		Plant plant = new Plant(1);

		//ensure default date is current date and time
		PersonalPlant p = new PersonalPlant(plant, "Name", 1, null, null);
		assertNotNull(p.getLastWatered());
		assertTrue(p.getLastWatered().equals(currentDate));

		//ensure we can set to null if necessary
		p.setLastWatered(null);
		assertNull(p.getLastWatered());

		//ensure past values can be added
		p.setLastWatered(cal.getTime());
		assertTrue(p.getLastWatered().before(currentDate));

		//ensure when past values added through constructer they carry through
		p = new PersonalPlant(plant, "Name", 1, cal.getTime(), null);
		assertTrue(currentDate.after(p.getLastWatered()));

		p.setLastWatered(currentDate);
		assertTrue(currentDate.equals(p.getLastWatered()));

		p.setLastWatered(null);
		assertNull(p.getLastWatered());
	}
}
