package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class CompanyReceivedPaymentException extends RuntimeException {

	public CompanyReceivedPaymentException() {
	}

	public CompanyReceivedPaymentException(String message) {
		super(message);
	}

	public CompanyReceivedPaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyReceivedPaymentException(Throwable cause) {
		super(cause);
	}

	public CompanyReceivedPaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
