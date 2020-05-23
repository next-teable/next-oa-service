package in.clouthink.nextoa.exception;

/**
 *
 */
public class ContactGroupNotFoundException extends ContactGroupException {

	public ContactGroupNotFoundException() {
	}

	public ContactGroupNotFoundException(String message) {
		super(message);
	}

	public ContactGroupNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContactGroupNotFoundException(Throwable cause) {
		super(cause);
	}
}
