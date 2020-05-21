package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectSettlementNotFoundException extends ProjectSettlementException {

	public ProjectSettlementNotFoundException() {
	}

	public ProjectSettlementNotFoundException(String message) {
		super(message);
	}

	public ProjectSettlementNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectSettlementNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProjectSettlementNotFoundException(String message,
											  Throwable cause,
											  boolean enableSuppression,
											  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
