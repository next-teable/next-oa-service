package in.clouthink.nextoa.exception;

/**
 *
 */
public class PaperNotFoundException extends PaperException {

	public PaperNotFoundException() {
	}

	public PaperNotFoundException(String message) {
		super(message);
	}

	public PaperNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaperNotFoundException(Throwable cause) {
		super(cause);
	}
}
