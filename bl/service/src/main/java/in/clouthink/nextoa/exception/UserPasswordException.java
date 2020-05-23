package in.clouthink.nextoa.exception;

/**
 *
 */
public class UserPasswordException extends BizException {

	public UserPasswordException() {
	}

	public UserPasswordException(String message) {
		super(message);
	}

	public UserPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserPasswordException(Throwable cause) {
		super(cause);
	}
}
