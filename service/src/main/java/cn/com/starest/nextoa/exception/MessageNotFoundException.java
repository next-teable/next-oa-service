package cn.com.starest.nextoa.exception;

/**
 *
 */
public class MessageNotFoundException extends MessageException {

	public MessageNotFoundException() {
	}

	public MessageNotFoundException(String message) {
		super(message);
	}

	public MessageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageNotFoundException(Throwable cause) {
		super(cause);
	}
}
