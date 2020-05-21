package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SalaryException extends RuntimeException {

	public SalaryException() {
	}

	public SalaryException(String message) {
		super(message);
	}

	public SalaryException(String message, Throwable cause) {
		super(message, cause);
	}

	public SalaryException(Throwable cause) {
		super(cause);
	}

	public SalaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
