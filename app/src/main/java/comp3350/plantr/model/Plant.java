package comp3350.plantr.model;

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
		_difficulty = calculateDifficulty(optimalTemp, wateringPeriod); //this is a derived attribute
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
		return "{id : " + _plantID + ", name : " + _plantName + "}";
	}

	//This is a way of measuring the difficulty of taking care of a Plant
	private DifficultyType calculateDifficulty(TemperatureRange optimalTemp, int wateringPeriod){
		final int levels = DifficultyType.values().length;
		final double tempSlope = 0.1;
		final double wateringSlopeConstant = (levels - 1.0) / levels; //so that at point weeks = 1, the watering difficulty also = 1
		final Temperature roomTemp = new Temperature(21);
		final int day = 24; // hours in a day
		final int week = day * 7; // hours in a week.
		final double tempWeight = 0.6; //the weight we apply to temperature
		final double wateringWeight = 1 - tempWeight; //the weight we apply to watering frequency

		if (optimalTemp == null || wateringPeriod < 0) {
			return null;
		}

		//if the watering period is less than 2 days, it's automatically max difficulty
		if (wateringPeriod <= day * 2) {
			return DifficultyType.HARD;
		}

		double temperatureDifficulty = -levels * Math.exp(-tempSlope * Math.pow(optimalTemp.getMean() - roomTemp.getTemp(), 2)) + levels; //a reverse bell curve centered on room temp

		double wateringDifficulty = Math.max(-((levels * (wateringPeriod - 1)) / week) * wateringSlopeConstant + levels, 0);//linear decrease in difficulty as time between watering increases. Anything > 1 week is considered "easy"

		double weightedAverage = Math.min(Math.max((tempWeight * temperatureDifficulty) + (wateringWeight * wateringDifficulty), 0), levels); //weighted average of the difficulty of temperature and watering. Kept in the bound 0-3

		return DifficultyType.getType((int) weightedAverage);
	}
}