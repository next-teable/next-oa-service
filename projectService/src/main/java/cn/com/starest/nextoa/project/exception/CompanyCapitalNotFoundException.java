package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class CompanyCapitalNotFoundException extends CompanyCapitalException {

	public CompanyCapitalNotFoundException() {
	}

	public CompanyCapitalNotFoundException(String message) {
		super(message);
	}

	public CompanyCapitalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyCapitalNotFoundException(Throwable cause) {
		super(cause);
	}

	public CompanyCapitalNotFoundException(String message,
										   Throwable cause,
										   boolean enableSuppression,
										   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
