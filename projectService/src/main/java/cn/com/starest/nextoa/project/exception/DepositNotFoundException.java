package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class DepositNotFoundException extends DepositException {

	public DepositNotFoundException() {
	}

	public DepositNotFoundException(String message) {
		super(message);
	}

	public DepositNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepositNotFoundException(Throwable cause) {
		super(cause);
	}

	public DepositNotFoundException(String message,
									Throwable cause,
									boolean enableSuppression,
									boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
