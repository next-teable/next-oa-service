package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ReceiveInvoiceException extends RuntimeException {

	public ReceiveInvoiceException() {
	}

	public ReceiveInvoiceException(String message) {
		super(message);
	}

	public ReceiveInvoiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReceiveInvoiceException(Throwable cause) {
		super(cause);
	}

	public ReceiveInvoiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
