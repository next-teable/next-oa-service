package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class ProjectCompletionException extends RuntimeException {

	public ProjectCompletionException() {
	}

	public ProjectCompletionException(String message) {
		super(message);
	}

	public ProjectCompletionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProjectCompletionException(Throwable cause) {
		super(cause);
	}

	public ProjectCompletionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
