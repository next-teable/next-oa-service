package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class IssueInvoiceException extends RuntimeException {

	public IssueInvoiceException() {
	}

	public IssueInvoiceException(String message) {
		super(message);
	}

	public IssueInvoiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public IssueInvoiceException(Throwable cause) {
		super(cause);
	}

	public IssueInvoiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
