package comp3350.plantr.objects;

/**
 * Created by Raman on 2017-06-07.
 */

public enum DifficultyType {
	EASY, MEDIUM, HARD;

	public static DifficultyType getType(int difficulty) {
		switch(difficulty) {
			case 1: return EASY;
			case 2: return MEDIUM;
			case 3: return HARD;
			default:
				System.out.println("Invalid difficulty input to enum");
				return null;
		}
	}
}
