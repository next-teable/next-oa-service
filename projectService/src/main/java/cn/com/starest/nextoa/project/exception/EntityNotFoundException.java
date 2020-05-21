package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException() {
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}
}
