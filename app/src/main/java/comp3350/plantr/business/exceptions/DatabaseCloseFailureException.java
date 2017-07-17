package comp3350.plantr.business.exceptions;

public class DatabaseCloseFailureException extends Exception {
	public DatabaseCloseFailureException() {
		super();
	}

	public DatabaseCloseFailureException(String message) {
		super(message);
	}

	public DatabaseCloseFailureException(Throwable cause) {
		super(cause);
	}

	public DatabaseCloseFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
