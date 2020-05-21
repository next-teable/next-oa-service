package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class PendingPaymentException extends RuntimeException {

	public PendingPaymentException() {
	}

	public PendingPaymentException(String message) {
		super(message);
	}

	public PendingPaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PendingPaymentException(Throwable cause) {
		super(cause);
	}

	public PendingPaymentException(String message,
								   Throwable cause,
								   boolean enableSuppression,
								   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
