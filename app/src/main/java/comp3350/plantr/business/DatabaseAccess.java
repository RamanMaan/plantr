package comp3350.plantr.business;

import comp3350.plantr.business.exceptions.DatabaseCloseFailureException;
import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.persistence.DataAccessObject;
import comp3350.plantr.persistence.DatabaseInterface;
import comp3350.plantr.persistence.StubDatabase;

/**
 * This class is the service manager for the Database
 */

public class DatabaseAccess {
	public static final String dbName = "PLANT";
	private static String dbPathName = "database/PLANT";

	private static DatabaseInterface _db = null;

	public static String getDBPathName() {
		return dbPathName;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}

	public static void open() throws DatabaseStartFailureException {
		if(_db == null) {
			//_db = new StubDatabase();
			_db = new DataAccessObject(dbName);
			_db.open(dbPathName);
		}
	}

	public static DatabaseInterface getDatabaseAccess() throws DatabaseStartFailureException {
		if(_db == null) {
			open();
		}

		return _db;
	}

	public static void close() throws DatabaseCloseFailureException {
		if (_db != null) {
			_db.close();
			_db = null;
		}
	}
}
