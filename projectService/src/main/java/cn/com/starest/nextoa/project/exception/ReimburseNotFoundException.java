package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ReimburseNotFoundException extends ReimburseException {

	public ReimburseNotFoundException() {
	}

	public ReimburseNotFoundException(String message) {
		super(message);
	}

	public ReimburseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimburseNotFoundException(Throwable cause) {
		super(cause);
	}

	public ReimburseNotFoundException(String message,
									  Throwable cause,
									  boolean enableSuppression,
									  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
