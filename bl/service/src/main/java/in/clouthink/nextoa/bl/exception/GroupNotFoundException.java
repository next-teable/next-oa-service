package in.clouthink.nextoa.bl.exception;

/**
 *
 */
public class GroupNotFoundException extends GroupException {

	public GroupNotFoundException() {
	}

	public GroupNotFoundException(String message) {
		super(message);
	}

	public GroupNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupNotFoundException(Throwable cause) {
		super(cause);
	}
}
