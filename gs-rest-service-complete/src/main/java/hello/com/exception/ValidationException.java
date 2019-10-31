package hello.com.exception;

public class ValidationException extends Exception {
	private static final long serialVersionUID = -410180507998010368L;

	public ValidationException() {
		super();
	}

	public ValidationException(final String message) {
		super(message);
	}
}
