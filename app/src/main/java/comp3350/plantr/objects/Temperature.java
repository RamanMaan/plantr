package comp3350.plantr.objects;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: This class holds a temperature by default in Celsius and allows for conversion and comparison with Fahrenheit as well
 * Should this class be located somewhere else in our hierarchy? Should we have a "helpers" package?
 * TODO: Should add fahrenheit support
 */

public class Temperature {

	private float _degrees;
	private char _scale;//enum might be better than char

	public Temperature(float degrees, char scale) {
		_degrees = degrees;
		_scale = scale;//TODO shouldn't just accept this blindly, should add validation
	}

	public float getTemp() {
		return _degrees;
	}

	public float getTemp(char scale) {
		switch(scale) {
			case 'c': case 'C':
				return getTemp();
			case 'f': case 'F':
				return celsiusToFahrenheit(_degrees);
			default:
				//TODO throw exception
				return -1;
		}
	}

	private float celsiusToFahrenheit(float degrees) {
		//TODO: should add conversion
		return degrees;
	}
}
