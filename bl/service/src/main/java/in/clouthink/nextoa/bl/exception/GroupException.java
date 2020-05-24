package in.clouthink.nextoa.bl.exception;

/**
 *
 */
public class GroupException extends BizException {

	public GroupException() {
	}

	public GroupException(String message) {
		super(message);
	}

	public GroupException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupException(Throwable cause) {
		super(cause);
	}
}
