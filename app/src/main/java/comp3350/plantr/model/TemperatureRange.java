package comp3350.plantr.model;

import java.util.Locale;

import comp3350.plantr.model.Temperature;

/**
 * 6/4/2017
 * Raman Maan
 * Purpose: Holds a range of temperatures as a max and min value
 */

public class TemperatureRange {
	private Temperature _lower;
	private Temperature _upper;

	public TemperatureRange(Temperature lower, Temperature upper) {
		_lower = lower;
		_upper = upper;
	}

	public Temperature getLowerTemp() {
		return _lower;
	}

	public Temperature getUpperTemp() {
		return _upper;
	}

	public double getMean() {
		return (_lower.getTemp() + _upper.getTemp()) / 2;
	}

	public String toString() {
		return String.format(Locale.CANADA, "{min: %.1fC, max: %.1fF}", _lower.getTemp(), _upper.getTemp());
	}
}
