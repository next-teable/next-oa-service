package in.clouthink.nextoa.bl.exception;

/**
 *
 */
public class UserException extends BizException {

	public UserException() {
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
}
