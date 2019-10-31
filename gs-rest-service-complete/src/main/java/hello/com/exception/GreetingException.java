package hello.com.exception;

public class GreetingException extends Exception {

	private static final long serialVersionUID = -470180507998010368L;

	public GreetingException() {
		super();
	}

	public GreetingException(final String message) {
		super(message);
	}

}