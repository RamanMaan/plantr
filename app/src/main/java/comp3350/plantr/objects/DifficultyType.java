package comp3350.plantr.objects;

/**
 * 5/28/2017
 * Raman Maan
 *
 * Purpose: This enum contains the different types of difficulty a plant can be to grow
 */

public enum DifficultyType {
	EASY, MEDIUM, HARD;

	public static DifficultyType getType(int difficulty) {
		switch (difficulty) {
			case 1:
				return EASY;
			case 2:
				return MEDIUM;
			case 3:
				return HARD;
			default:
				return null;
		}
	}
}
