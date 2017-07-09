package comp3350.plantr.business;

import java.sql.SQLException;

import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.User;

public class UserManager {

	//TODO switch this back, for now only creating a default user until login screen implemented
	//private static User _USER = null;
	private static User _USER = new User("defaultuser@plantr.io", "Default Name", "plantr");

	public static User getUser() throws UserLoginException {
		if(_USER == null) {
			throw new UserLoginException();
		}
		return _USER;
	}

	public static void loginUser(String email, String password) throws UserLoginException {
		User u = null;
		try {
			u = DatabaseAccess.getDatabaseAccess().getUser(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseStartFailureException e) {
			e.printStackTrace();
		}

		if (u == null || !u.passwordMatches(password)) {
			throw new UserLoginException();
		}

		_USER = u;
	}
}
