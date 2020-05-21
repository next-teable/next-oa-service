package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ReimburseException extends RuntimeException {
	public ReimburseException() {
	}

	public ReimburseException(String message) {
		super(message);
	}

	public ReimburseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimburseException(Throwable cause) {
		super(cause);
	}

	public ReimburseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
