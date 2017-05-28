package comp3350.plantr.application;

import comp3350.plantr.presentation.TempGUI;

/**
 * 5/28/2017
 * Raman Maan
 *
 * Purpose: This class contains our main application code
 */
public class Main {

	public static final String _dbName = "apolloDB";

	public static void main(String args[]) {
		startUpProcess();
		TempGUI.run();
		shutDownProcess();
	}

	/**
	 * This contains any start up activities before app launches
	 */
	public static void startUpProcess() {
		DBServiceManager.createDB(_dbName);
	}

	/**
	 * This contains any shut down activities as app closes
	 */
	public static void shutDownProcess() {
		DBServiceManager.closeDB();
	}
}
