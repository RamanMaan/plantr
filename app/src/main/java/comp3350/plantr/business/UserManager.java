package comp3350.plantr.business;

import java.sql.SQLException;

import comp3350.plantr.business.exceptions.DatabaseStartFailureException;
import comp3350.plantr.business.exceptions.UserLoginException;
import comp3350.plantr.model.User;

public class UserManager {

	private static User _USER = null;

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

	public static boolean invalidEmail(String email) {
		return email == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	public static boolean invalidPassword(String password) {
		return password.length() <= 1;
	}
}
