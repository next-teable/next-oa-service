package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectReceivedPaymentException extends RuntimeException {

	public ProjectReceivedPaymentException() {
	}

	public ProjectReceivedPaymentException(String message) {
		super(message);
	}

	public ProjectReceivedPaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectReceivedPaymentException(Throwable cause) {
		super(cause);
	}

	public ProjectReceivedPaymentException(String message,
										   Throwable cause,
										   boolean enableSuppression,
										   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
