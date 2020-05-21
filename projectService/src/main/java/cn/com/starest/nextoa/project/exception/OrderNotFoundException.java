package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class OrderNotFoundException extends OrderException {

	public OrderNotFoundException() {
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}

	public OrderNotFoundException(String message,
								  Throwable cause,
								  boolean enableSuppression,
								  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
