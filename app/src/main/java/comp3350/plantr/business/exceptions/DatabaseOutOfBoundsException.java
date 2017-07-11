package comp3350.plantr.business.exceptions;

public class DatabaseOutOfBoundsException extends Exception {
	public DatabaseOutOfBoundsException() {
		super();
	}

	public DatabaseOutOfBoundsException(String message) {
		super(message);
	}

	public DatabaseOutOfBoundsException(Throwable cause) {
		super(cause);
	}

	public DatabaseOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}
}
