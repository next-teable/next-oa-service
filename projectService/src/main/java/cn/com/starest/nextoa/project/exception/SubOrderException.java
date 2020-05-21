package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SubOrderException extends RuntimeException {
	public SubOrderException() {
	}

	public SubOrderException(String message) {
		super(message);
	}

	public SubOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubOrderException(Throwable cause) {
		super(cause);
	}

	public SubOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
