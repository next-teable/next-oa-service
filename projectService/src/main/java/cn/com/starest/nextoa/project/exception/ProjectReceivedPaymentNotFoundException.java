package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectReceivedPaymentNotFoundException extends ProjectReceivedPaymentException {

	public ProjectReceivedPaymentNotFoundException() {
	}

	public ProjectReceivedPaymentNotFoundException(String message) {
		super(message);
	}

	public ProjectReceivedPaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectReceivedPaymentNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProjectReceivedPaymentNotFoundException(String message,
												   Throwable cause,
												   boolean enableSuppression,
												   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
