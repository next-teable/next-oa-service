package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class LendingNotFoundException extends LendingException {

	public LendingNotFoundException() {
	}

	public LendingNotFoundException(String message) {
		super(message);
	}

	public LendingNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public LendingNotFoundException(Throwable cause) {
		super(cause);
	}

	public LendingNotFoundException(String message,
									Throwable cause,
									boolean enableSuppression,
									boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
