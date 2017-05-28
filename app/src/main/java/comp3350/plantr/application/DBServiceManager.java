package comp3350.plantr.application;

import comp3350.plantr.persistence.ApolloDBstub;
import comp3350.plantr.persistence.DatabaseInterface;

/**
 * 5/28/2017
 * Raman Maan
 *
 * Purpose: Manages the database services
 */

public class DBServiceManager {

	private static DatabaseInterface _db;

	public static DatabaseInterface createDB(String dbName) {
		if(_db == null) {
			_db = new ApolloDBstub();
			_db.open(dbName);
		}

		return _db;
	}

	public static DatabaseInterface getDB() {
		if(_db == null) {
			System.out.println("Connection to database has not been established.");
			System.exit(1);
		}

		return _db;
	}

	public static void closeDB() {
		if(_db != null) {
			_db.close();
		}
		_db = null;
	}
}
