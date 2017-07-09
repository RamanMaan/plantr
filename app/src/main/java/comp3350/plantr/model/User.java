package comp3350.plantr.model;

/**
 * A plantr user
 */

public class User {
	private String _name;
	private String _email;
	private String _password;

	public User(String email, String name, String password) {
		this._name = name;
		this._email = email;
		this._password = password;
	}

	public String getName() {
		return _name;
	}

	public String getEmail() {
		return _email;
	}

	public boolean passwordMatches(String p) {
		return _password.equals(p);
	}
}
