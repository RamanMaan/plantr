package comp3350.plantr.objects;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: This class holds a temperature in Celsius
 */

public class Temperature {

	private float _degrees;

	public Temperature(float degrees) {
		_degrees = degrees;
	}

	public float getTemp() {
		return _degrees;
	}

	public float getTempFahr() {
		return celsiusToFahrenheit(getTemp());
	}

	public boolean inRange(Temperature min, Temperature max) {
		return min._degrees <= this._degrees && this._degrees <= max._degrees;
	}

	public boolean equals(Temperature other) {
		return this._degrees == other._degrees;
	}

	private float celsiusToFahrenheit(float degrees) {
		return (9f/5f) * degrees + 32;
	}
}
