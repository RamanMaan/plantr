package comp3350.plantr.tests.business.objects;

import org.junit.Test;

import comp3350.plantr.business.objects.Temperature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 6/4/2017
 * Raman Maan
 * Purpose: Tests the Temperature object
 */

public class TemperatureTest {

	@Test
	public void temperature_testCreation() {
		Temperature t = new Temperature(-10);
		Temperature t2 = new Temperature(10);

		assertNotNull(t);
		assertNotNull(t2);
	}

	@Test
	public void temperature_testValues() {
		Temperature t = new Temperature(-25);
		assertNotNull(t);
		assertFalse(t.getTempFahr() == (t.getTemp()));
	}

	@Test
	public void temperature_testInRange() {
		Temperature min = new Temperature(49.5f);
		Temperature mid = new Temperature(49.6f);
		Temperature max = new Temperature(49.7f);

		assertTrue(mid.inRange(min, max));
		assertFalse(mid.inRange(max, min));

		min = new Temperature(-12);
		mid = new Temperature(-11);
		max = new Temperature(-10);

		assertTrue(mid.inRange(min, max));
		assertFalse(mid.inRange(max, min));
	}

	@Test
	public void temperature_testConversion() {
		Temperature t1 = new Temperature(-25);
		float t1_f = -13;
		Temperature t2 = new Temperature(0);
		float t2_f = 32;
		Temperature t3 = new Temperature(25);
		float t3_f = 77;
		Temperature t4 = new Temperature(16.9f);
		float t4_f = 62.42f;

		assertTrue(t1.getTempFahr() == t1_f);
		assertTrue(t2.getTempFahr() == t2_f);
		assertTrue(t3.getTempFahr() == t3_f);
		assertTrue(t4.getTempFahr() == t4_f);
	}
}
