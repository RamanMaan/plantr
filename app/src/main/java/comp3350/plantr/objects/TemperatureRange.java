package comp3350.plantr.objects;

import java.util.Locale;

/**
 * 6/4/2017
 * Raman Maan
 *
 * Purpose: Holds a range of temperatures as a max and min value
 */

public class TemperatureRange {
	private Temperature _min;
	private Temperature _max;

	public TemperatureRange(Temperature min, Temperature max) {
		_min = min;
		_max = max;
	}

	public boolean inRange(Temperature t) {
		return t.inRange(_min, _max);
	}

	public String toString() {
		return String.format(Locale.CANADA, "{min: %.1fC, max: %.1fF}", _min.getTemp(), _max.getTemp());
	}
}
