package comp3350.plantr.business;

import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

/**
 * 6/6/2017
 * Raman Maan
 * Purpose: This class is the service manager for the Database
 */

public class DatabaseAccess {
	private static DatabaseInterface _db = null;

	public static DatabaseInterface open() {
		return _db == null ? new StubDatabase() : _db;
	}
}
