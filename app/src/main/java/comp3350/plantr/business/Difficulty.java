package comp3350.plantr.business;

import comp3350.plantr.objects.DifficultyType;
import comp3350.plantr.objects.Plant;
import comp3350.plantr.objects.Temperature;
import comp3350.plantr.objects.TemperatureRange;

/**
 * Created by Michael on 04/06/2017.
 * <p>
 * Purpose: This is a way of measuring the difficulty of taking care of a Plant
 */

public class Difficulty {

	static final int levels = DifficultyType.values().length;

	static final double tempSlope = 0.1; //
	static final double wateringSlopeConstant = (levels - 1.0) / levels; //so that at point weeks = 1, the watering difficulty also = 1

	static final Temperature roomTemp = new Temperature(21);
	static final int day = 24;
	static final int week = day * 7; //hours in a week.

	static final double tempWeight = 0.6;
	static final double wateringWeight = 1 - tempWeight;

	public static DifficultyType calculateDifficulty(Plant p) {
		return calculateDifficulty(p.getOptimalTemp(), p.getWateringFreq());
	}

	public static DifficultyType calculateDifficulty(TemperatureRange optimalTemps, int wateringPeriod) {
		//if the watering period is less than 2 days, it's automatically max difficulty
		if(wateringPeriod <= day * 2) {
			return DifficultyType.HARD;
		}

		double combinedDifficulty = calculateCombinedDifficulty(optimalTemps, wateringPeriod);

		return getDifficultyType(combinedDifficulty);
	}

	private static double calculateTemperatureDifficulty(double optimalTemperatureMean) {
		return -levels * Math.exp(-tempSlope * Math.pow(optimalTemperatureMean - roomTemp.getTemp(), 2)) + levels; //a reverse bell curve centered on room temp
	}

	private static double calculateWateringDifficulty(int wateringPeriod) {
		return Math.max(-((levels * (wateringPeriod - 1)) / week) * wateringSlopeConstant + levels, 0);//linear decrease in difficulty as time between watering increases. Anything > 1 week is considered "easy"
	}

	private static double calculateCombinedDifficulty(TemperatureRange optimalTemps, int wateringPeriod) {
		double tempDifficulty = calculateTemperatureDifficulty(optimalTemps.getMean());
		double wateringDifficulty = calculateWateringDifficulty(wateringPeriod);
		double weightedAverage = Math.min(Math.max((tempWeight * tempDifficulty) + (wateringWeight * wateringDifficulty), 0), levels); //weighted average of the difficulty of temperature and watering. Kept in the bound 0-3
		return weightedAverage;
	}

	private static DifficultyType getDifficultyType(double difficulty) {
		int diff = (int)Math.max(Math.ceil(difficulty), 1) - 1;//math.ceil and math.max make it so the range is 1 to _difficulty.length inclusive.
		return DifficultyType.getType(diff);
	}
}
