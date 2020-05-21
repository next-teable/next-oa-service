package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class PendingPaymentNotFoundException extends PendingPaymentException {

	public PendingPaymentNotFoundException() {
	}

	public PendingPaymentNotFoundException(String message) {
		super(message);
	}

	public PendingPaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PendingPaymentNotFoundException(Throwable cause) {
		super(cause);
	}

	public PendingPaymentNotFoundException(String message,
										   Throwable cause,
										   boolean enableSuppression,
										   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
