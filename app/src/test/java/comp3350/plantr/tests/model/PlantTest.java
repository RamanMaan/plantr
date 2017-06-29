package comp3350.plantr.tests.model;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.plantr.business.DatabaseAccess;
import comp3350.plantr.model.DifficultyType;
import comp3350.plantr.model.Plant;
import comp3350.plantr.model.Temperature;
import comp3350.plantr.model.TemperatureRange;

/**
 * Created by Austin on 2017-06-06.
 *
 * Class that currently only tests plant object creation.
 */

public class PlantTest {

	@Test
	public void plant_testCreate() {
		Plant pidOnly = new Plant(1);
		Plant altPlant = new Plant(2, "Plant2", "Generic Plant", "Any plant", new TemperatureRange(new Temperature(3), new Temperature(5)), 3);

		assertNotNull(pidOnly);
		assertNotNull(altPlant);
	}

	@Test
	public void plant_testPlantDifficultyNull() {

		//test if plant parameters are invalid
		Plant p = new Plant(0);
		assertNull(p.getDifficulty());

		//test an actual plant
		p = DatabaseAccess.open().getAllPlants().get(0);
		assertNotNull(p.getDifficulty());
	}

	@Test
	public void difficulty_testDifficultiesCorrect() {

		//test if parameters are invalid
		TemperatureRange tempRange = null;
		int wateringFreq = -1;
		Plant p = new Plant(0, "Bob", "A test Plant", null, tempRange, wateringFreq);
		assertNull(p.getDifficulty());

		tempRange = new TemperatureRange(new Temperature(21), new Temperature(24));
		wateringFreq = 170;
		p = new Plant(0, "Bob", "A test Plant", null, tempRange, wateringFreq);
		assertTrue(p.getDifficulty() == DifficultyType.EASY);

		tempRange = new TemperatureRange(new Temperature(24), new Temperature(27));
		wateringFreq = 170;
		p = new Plant(0, "Bob", "A test Plant", null, tempRange, wateringFreq);
		assertTrue(p.getDifficulty() == DifficultyType.MEDIUM);

		//test if parameters are valid
		tempRange = new TemperatureRange(new Temperature(21), new Temperature(24));
		wateringFreq = 48;
		p = new Plant(0, "Bob", "A test Plant", null, tempRange, wateringFreq);
		assertTrue(p.getDifficulty() == DifficultyType.HARD);
	}
}
