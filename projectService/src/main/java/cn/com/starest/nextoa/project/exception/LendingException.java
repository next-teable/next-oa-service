package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class LendingException extends RuntimeException {
	public LendingException() {
	}

	public LendingException(String message) {
		super(message);
	}

	public LendingException(String message, Throwable cause) {
		super(message, cause);
	}

	public LendingException(Throwable cause) {
		super(cause);
	}

	public LendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
