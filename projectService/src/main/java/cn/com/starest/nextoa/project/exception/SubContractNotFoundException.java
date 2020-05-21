package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class SubContractNotFoundException extends SubContractException {

	public SubContractNotFoundException() {
	}

	public SubContractNotFoundException(String message) {
		super(message);
	}

	public SubContractNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubContractNotFoundException(Throwable cause) {
		super(cause);
	}

	public SubContractNotFoundException(String message,
										Throwable cause,
										boolean enableSuppression,
										boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
