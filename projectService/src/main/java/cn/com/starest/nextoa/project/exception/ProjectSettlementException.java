package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectSettlementException extends RuntimeException {

	public ProjectSettlementException() {
	}

	public ProjectSettlementException(String message) {
		super(message);
	}

	public ProjectSettlementException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectSettlementException(Throwable cause) {
		super(cause);
	}

	public ProjectSettlementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
