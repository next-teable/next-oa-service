package cn.com.starest.nextoa.exception;

/**
 *
 */
public class UserOpinionNotFoundException extends UserOpinionException {

	public UserOpinionNotFoundException() {
	}

	public UserOpinionNotFoundException(String message) {
		super(message);
	}

	public UserOpinionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserOpinionNotFoundException(Throwable cause) {
		super(cause);
	}
}
