package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectException extends RuntimeException {
	public ProjectException() {
	}

	public ProjectException(String message) {
		super(message);
	}

	public ProjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectException(Throwable cause) {
		super(cause);
	}

	public ProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
