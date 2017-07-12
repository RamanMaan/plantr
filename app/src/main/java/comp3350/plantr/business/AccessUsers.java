package comp3350.plantr.business;

import java.sql.SQLException;

import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.model.User;

public class AccessUsers {
	public static User getUser(String email) throws DatabaseStartFailureException, SQLException {
		return DatabaseAccess.getDatabaseAccess().getUser(email);
	}
}
