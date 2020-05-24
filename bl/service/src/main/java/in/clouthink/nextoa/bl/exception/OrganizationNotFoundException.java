package in.clouthink.nextoa.bl.exception;

/**
 *
 */
public class OrganizationNotFoundException extends OrganizationException {

	public OrganizationNotFoundException() {
	}

	public OrganizationNotFoundException(String message) {
		super(message);
	}

	public OrganizationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrganizationNotFoundException(Throwable cause) {
		super(cause);
	}
}
