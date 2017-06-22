package comp3350.plantr.tests.business.objects;

import org.junit.Test;
import static org.junit.Assert.*;
import comp3350.plantr.business.objects.Plant;
import comp3350.plantr.business.objects.Temperature;
import comp3350.plantr.business.objects.TemperatureRange;

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
}
