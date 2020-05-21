package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ReceiveInvoiceNotFoundException extends ReimburseException {

	public ReceiveInvoiceNotFoundException() {
	}

	public ReceiveInvoiceNotFoundException(String message) {
		super(message);
	}

	public ReceiveInvoiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReceiveInvoiceNotFoundException(Throwable cause) {
		super(cause);
	}

	public ReceiveInvoiceNotFoundException(String message,
										   Throwable cause,
										   boolean enableSuppression,
										   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
