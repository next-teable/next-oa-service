package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectCompletionNotFoundException extends ProjectCompletionException {

	public ProjectCompletionNotFoundException() {
	}

	public ProjectCompletionNotFoundException(String message) {
		super(message);
	}

	public ProjectCompletionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectCompletionNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProjectCompletionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
