package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectNotFoundException extends ProjectException {

	public ProjectNotFoundException() {
	}

	public ProjectNotFoundException(String message) {
		super(message);
	}

	public ProjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProjectNotFoundException(String message,
									Throwable cause,
									boolean enableSuppression,
									boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
