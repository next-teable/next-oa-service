package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class CompanyCapitalException extends RuntimeException {

	public CompanyCapitalException() {
	}

	public CompanyCapitalException(String message) {
		super(message);
	}

	public CompanyCapitalException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyCapitalException(Throwable cause) {
		super(cause);
	}

	public CompanyCapitalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
