package comp3350.plantr.business;

import comp3350.plantr.persistence.DataAccessObject;
import comp3350.plantr.persistence.DatabaseInterface;
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

	public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}

	public static String getDBPathName() {
		if (dbPathName == null)
			return dbName;
		else
			return dbPathName;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}

	public static DatabaseInterface open() {
		return _db == null ? new DataAccessObject(dbName) : _db;
//		return _db == null ? new StubDatabase() : _db;
	}
}
