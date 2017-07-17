package comp3350.plantr.business.exceptions;

public class DatabaseStartFailureException extends Exception {
	public DatabaseStartFailureException() {
		super();
	}

	public DatabaseStartFailureException(String message) {
		super(message);
	}

	public DatabaseStartFailureException(Throwable cause) {
		super(cause);
	}

	public DatabaseStartFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
