package comp3350.plantr.business;

import java.util.List;

import comp3350.plantr.model.PersonalPlant;
import comp3350.plantr.model.Plant;
import comp3350.plantr.persistence.DataAccessObject;
import comp3350.plantr.persistence.DatabaseInterface;

/**
 * Created by KevinD on 6/29/2017.
 */

public class AccessGarden {
	private static DatabaseInterface _db = null;

	private DatabaseInterface dataAccess;
	private List<PersonalPlant> plants;

	public AccessGarden() {
		dataAccess = Services.getDataAccess(Main.dbName);
		plants = null;
	}

	public List<PersonalPlant> getPlants() {
		return dataAccess.getAllPersonalPlants();
	}

	public PersonalPlant getPersonalPlant(int ID) {
		return dataAccess.getPersonalPlantByID(ID);
	}

	public static DatabaseInterface open() {
		return _db == null ? new DataAccessObject(Main.dbName) : _db;
		//		return _db == null ? new StubDatabase() : _db;
	}
}
