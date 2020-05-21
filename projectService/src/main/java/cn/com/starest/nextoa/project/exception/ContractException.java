package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ContractException extends RuntimeException {
	public ContractException() {
	}

	public ContractException(String message) {
		super(message);
	}

	public ContractException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContractException(Throwable cause) {
		super(cause);
	}

	public ContractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
