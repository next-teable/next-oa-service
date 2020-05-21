package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SalaryNotFoundException extends SalaryException {

	public SalaryNotFoundException() {
	}

	public SalaryNotFoundException(String message) {
		super(message);
	}

	public SalaryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SalaryNotFoundException(Throwable cause) {
		super(cause);
	}

	public SalaryNotFoundException(String message,
								   Throwable cause,
								   boolean enableSuppression,
								   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
