package in.clouthink.nextoa.rbac.core.exception;

/**
 *
 */
public class RbacException extends RuntimeException {

	public RbacException() {
	}

	public RbacException(String message) {
		super(message);
	}

	public RbacException(String message, Throwable cause) {
		super(message, cause);
	}

	public RbacException(Throwable cause) {
		super(cause);
	}
}
