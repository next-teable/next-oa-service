package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SubOrderNotFoundException extends SubOrderException {

	public SubOrderNotFoundException() {
	}

	public SubOrderNotFoundException(String message) {
		super(message);
	}

	public SubOrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubOrderNotFoundException(Throwable cause) {
		super(cause);
	}

	public SubOrderNotFoundException(String message,
									 Throwable cause,
									 boolean enableSuppression,
									 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
