package comp3350.plantr.business;

import java.util.List;

import comp3350.plantr.application.Main;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DataAccessObject;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.application.Services;
import comp3350.plantr.persistence.StubDatabase;

/**
 * 6/6/2017
 * Raman Maan
 * Purpose: This class is the service manager for the Database
 */

public class DatabaseAccess {
	private static DatabaseInterface _db = null;

	public static final String dbName = "PLANT";
	private static String dbPathName = "database/PLANT";

	private DatabaseInterface dataAccess;
	private List<Plant> plants;

	public DatabaseAccess() {
		dataAccess = Services.getDataAccess(Main.dbName);
		plants = null;
	}

	public List<Plant> getPlants() {
//		plants.clear();
		return dataAccess.getAllPlants();
	}

	public Plant getPlant(int ID) {
		return dataAccess.getPlant(ID);
	}

	public static DatabaseInterface open() {
		return _db == null ? new DataAccessObject(dbName) : _db;
//		return _db == null ? new StubDatabase() : _db;
	}
}
