package comp3350.plantr.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.plantr.objects.Difficulty;
import comp3350.plantr.objects.Temperature;
import comp3350.plantr.objects.TemperatureRange;


/**
 * Created by Michael on 06/06/2017.
 */

public class DifficultyTest {

	Difficulty _difficultyInstance = new Difficulty(new TemperatureRange(new Temperature(21), new Temperature(24)), 48);

	@Test
	public void createDifficulty(){
		assertNotNull(_difficultyInstance);
		assertEquals("hard", _difficultyInstance.toString());


	}
}
