package comp3350.plantr.model;

import comp3350.plantr.business.Difficulty;

/**
 * Created: 5/28/2017
 * Raman Maan
 * Purpose: This class defines a Plant object
 */

public class Plant {
	private int _plantID;
	private String _plantName;
	private String _plantDesc;
	private String _plantImg;

	private TemperatureRange _optimalTemp;
	private DifficultyType _difficulty;
	private int _wateringPeriod;

	public Plant(int id) {
		_plantID = id;
		_plantName = null;
		_plantDesc = null;
		_plantImg = null;
		_optimalTemp = null;
		_difficulty = null;
		_wateringPeriod = -1;
	}

	public Plant(int id, String name, String desc, String img, TemperatureRange optimalTemp, int wateringPeriod) {
		_plantID = id;
		_plantName = name;
		_plantDesc = desc;
		_plantImg = img;
		_optimalTemp = optimalTemp;
		_difficulty = Difficulty.calculateDifficulty(optimalTemp, wateringPeriod); //this is a derived attribute
		_wateringPeriod = wateringPeriod;
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

	public DifficultyType getDifficulty() {
		return _difficulty;
	}

	public int getWateringFreq() {
		return _wateringPeriod;
	}

	public boolean equals(Object object) {
		if (object instanceof Plant) {
			Plant plant = (Plant) object;
			return plant._plantID == this._plantID;
		}
		return false;
	}

	public String toString() {
		return String.format("{id : %d, name : %s, desc : %s, optimalTemp : %s, hardiness : %d, wateringFreq : %d}"
				, _plantID, _plantName, _plantDesc, _optimalTemp, _difficulty, _wateringPeriod);
	}
}