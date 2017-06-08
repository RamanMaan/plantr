package comp3350.plantr.tests.business;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.plantr.application.DatabaseAccess;
import comp3350.plantr.business.Difficulty;
import comp3350.plantr.objects.DifficultyType;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.objects.Temperature;
import comp3350.plantr.objects.TemperatureRange;


/**
 * Created by Michael on 06/06/2017.
 */

public class DifficultyTest {
	@Test
	public void difficulty_testGettingFromPlant() {
		//test if parameters are null
		Plant p = null;
		assertNull(Difficulty.calculateDifficulty(p));

		//test if plant parameters are invalid
		p = new Plant(0);
		assertNull(Difficulty.calculateDifficulty(p));

		//test an actual plant
		p = DatabaseAccess.open().getAllPlants().get(0);
		assertNotNull(Difficulty.calculateDifficulty(p));
	}

	@Test
	public void difficulty_testGettingWithDirect() {
		//test if parameters are invalid
		TemperatureRange tempRange = null;
		int wateringFreq = -1;
		assertNull(Difficulty.calculateDifficulty(tempRange, wateringFreq));

		//test if parameters are valid
		tempRange = new TemperatureRange(new Temperature(21), new Temperature(24));
		wateringFreq = 48;
		assertTrue(Difficulty.calculateDifficulty(tempRange, wateringFreq) == DifficultyType.HARD);
	}
}
