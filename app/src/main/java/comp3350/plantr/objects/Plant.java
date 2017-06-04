package comp3350.plantr.objects;

import android.media.Image;

/**
 * Created: 5/28/2017
 * Raman Maan
 *
 * Purpose: This class defines a Plant object
 */

public class Plant {
	private int _plantID;
	private String _plantName;
	private String _plantDesc;//TODO : should we have a short and long desc, or just the one?
	private Image _plantImg;

	private Temperature _optimalTemp;
	private int _hardiness;//TODO: discuss how we'll store this information
	private int _wateringFreq;

	public Plant(int id) {
		_plantID = id;
		_plantName = null;
		_plantDesc = null;
		_plantImg = null;
		_optimalTemp = null;
		_hardiness = -1;
		_wateringFreq = -1;
	}

	public Plant(int id, String name, String desc, Image img, Temperature optimalTemp, int hardiness, int wateringFreq) {
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

	public Image getPlantImg() {
		return _plantImg;
	}

	public Temperature getOptimalTemp() {
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
		return String.format("{id : %d, name : %s, desc : %s}", _plantID, _plantName, _plantDesc);
	}
}