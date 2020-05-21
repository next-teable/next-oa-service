package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class CompanyReceivedPaymentNotFoundException extends CompanyReceivedPaymentException {

	public CompanyReceivedPaymentNotFoundException() {
	}

	public CompanyReceivedPaymentNotFoundException(String message) {
		super(message);
	}

	public CompanyReceivedPaymentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyReceivedPaymentNotFoundException(Throwable cause) {
		super(cause);
	}

	public CompanyReceivedPaymentNotFoundException(String message,
												   Throwable cause,
												   boolean enableSuppression,
												   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
