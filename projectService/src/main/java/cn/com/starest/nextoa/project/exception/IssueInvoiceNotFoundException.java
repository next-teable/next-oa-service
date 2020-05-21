package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class IssueInvoiceNotFoundException extends LendingException {

	public IssueInvoiceNotFoundException() {
	}

	public IssueInvoiceNotFoundException(String message) {
		super(message);
	}

	public IssueInvoiceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public IssueInvoiceNotFoundException(Throwable cause) {
		super(cause);
	}

	public IssueInvoiceNotFoundException(String message,
										 Throwable cause,
										 boolean enableSuppression,
										 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
