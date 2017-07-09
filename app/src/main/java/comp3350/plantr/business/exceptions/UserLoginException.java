package comp3350.plantr.business.exceptions;

public class UserLoginException  extends Exception {
	public UserLoginException() {
		super();
	}

	public UserLoginException(String message) {
		super(message);
	}

	public UserLoginException(Throwable cause) {
		super(cause);
	}

	public UserLoginException(String message, Throwable cause) {
		super(message, cause);
	}
}
