package comp3350.plantr.objects;

/**
 * Created by Michael on 04/06/2017.
 *
 * Purpose: This is a way of measuring the difficulty of taking care of a Plant
 */

public class Difficulty {

	private static String[] _difficultyLevels = {"easy", "medium", "hard"};
	private double _difficulty; //it's a double so that two plants of similar difficulty can still be compared

	public Difficulty(TemperatureRange optimalTemp, int wateringPeriod){

		final int levels = _difficultyLevels.length;

		final double tempSlope = 0.1; //
		final double wateringSlopeConstant = (levels-1.0)/levels; //so that at point weeks = 1, the watering difficulty also = 1

		final int roomTemp = 21; //celsius
		final int day = 24;
		final int week = day*7; //hours in a week.

		final double tempWeight = 0.6;
		final double wateringWeight = 1-tempWeight;

		final double optimalTemperature = optimalTemp.getMean();

		double combinedDifficulty;

		double tempDifficulty = -levels*Math.exp(-tempSlope*Math.pow(optimalTemperature-roomTemp, 2)) + levels; //a reverse bell curve centered on room temp
		double wateringDifficulty;


		wateringDifficulty = Math.max(
				-((levels * (wateringPeriod - 1)) / week) * wateringSlopeConstant + levels
				, 0); //linear decrease in difficulty as time between watering increases. Anything > 1 week is considered "easy"


		if(wateringPeriod > day*2){ //more than two days between waterings
			combinedDifficulty =
					Math.min(
							Math.max(
									(tempWeight*tempDifficulty) + (wateringWeight*wateringDifficulty)
									,0)
							,levels); //weighted average of the difficulty of temperature and watering. Kept in the bound 0-3
		}
		else {
			combinedDifficulty = 3; //no matter what, if it takes more than two days, it's a tough plant to care for
		}

		_difficulty = combinedDifficulty;
	}

	public double getDifficulty(){
		return _difficulty;
	}

	public String toString(){
		int index = (int)Math.max(Math.ceil(_difficulty),1) - 1 ; 	//math.ceil and math.max make it so the range is 1 to _difficulty.length inclusive.
																	//Then subtracting 1 makes it line up to indices 0 to _difficulty.length - 1
		return _difficultyLevels[index]; //
	}
}
