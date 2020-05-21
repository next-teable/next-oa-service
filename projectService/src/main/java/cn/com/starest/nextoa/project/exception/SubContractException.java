package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SubContractException extends RuntimeException {
	public SubContractException() {
	}

	public SubContractException(String message) {
		super(message);
	}

	public SubContractException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubContractException(Throwable cause) {
		super(cause);
	}

	public SubContractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
