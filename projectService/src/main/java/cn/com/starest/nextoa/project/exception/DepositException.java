package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class DepositException extends RuntimeException {

	public DepositException() {
	}

	public DepositException(String message) {
		super(message);
	}

	public DepositException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepositException(Throwable cause) {
		super(cause);
	}

	public DepositException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
