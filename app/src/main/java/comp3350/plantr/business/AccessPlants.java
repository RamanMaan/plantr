package comp3350.plantr.business;

import java.util.List;

import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DataAccessObject;
import comp3350.plantr.persistence.DatabaseInterface;

/**
 * 6/6/2017
 * Raman Maan
 * Purpose: This class is the service manager for the Database
 */

public class AccessPlants {
	private static DatabaseInterface _db = null;

	private DatabaseInterface dataAccess;
	private List<Plant> plants;

	public AccessPlants() {
		dataAccess = Services.getDataAccess(Main.dbName);
		plants = null;
	}

	public List<Plant> getPlants() {
		return dataAccess.getAllPlants();
	}

	public Plant getPlant(int ID) {
		return dataAccess.getPlant(ID);
	}

	public static DatabaseInterface open() {
		return _db == null ? new DataAccessObject(Main.dbName) : _db;
//		return _db == null ? new StubDatabase() : _db;
	}
}
