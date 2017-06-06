package comp3350.plantr.objects;

import android.media.Image;

import java.util.Locale;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: This class defines a Plant object
 */

public class Plant {
	private int _plantID;
	private String _plantName;
	private String _plantDesc;
	private String _plantImg;

	private TemperatureRange _optimalTemp;
	private int _hardiness;
	private int _wateringFreq; // the amount of times it needs to be watered per week

	public Plant(int id) {
		_plantID = id;
		_plantName = null;
		_plantDesc = null;
		_plantImg = null;
		_optimalTemp = null;
		_hardiness = -1;
		_wateringFreq = -1;
	}

	public Plant(int id, String name, String desc, String img, TemperatureRange optimalTemp, int hardiness, int wateringFreq) {
		_plantID = id;
		_plantName = name;
		_plantDesc = desc;
		_plantImg = img;
		_optimalTemp = optimalTemp;
		_hardiness = hardiness;
		_wateringFreq = wateringFreq;
	}

	public int getPlantID() {
		return _plantID;
	}

	public String getPlantName() {
		return _plantName;
	}

	public String getPlantDesc() {
		return _plantDesc;
	}

	public String getPlantImg() {
		return _plantImg;
	}

	public TemperatureRange getOptimalTemp() {
		return _optimalTemp;
	}

	public int getHardiness() {
		return _hardiness;
	}

	public int getWateringFreq() {
		return _wateringFreq;
	}

	public boolean equals(Object object) {
		if (object instanceof Plant) {
			Plant plant = (Plant) object;
			return plant._plantID == this._plantID;
		}
		return false;
	}

	public String toString() {
		return String.format(Locale.CANADA, "{id : %d, name : %s, desc : %s, optimalTemp : %s, hardiness : %d, wateringFreq : %d}"
				, _plantID, _plantName, _plantDesc, _optimalTemp, _hardiness, _wateringFreq);
	}
}