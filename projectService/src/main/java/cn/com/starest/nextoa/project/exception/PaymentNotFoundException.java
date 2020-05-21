package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class PaymentNotFoundException extends PaymentException {

	public PaymentNotFoundException() {
	}

	public PaymentNotFoundException(String message) {
		super(message);
	}

	public PaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentNotFoundException(Throwable cause) {
		super(cause);
	}

	public PaymentNotFoundException(String message,
									Throwable cause,
									boolean enableSuppression,
									boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
