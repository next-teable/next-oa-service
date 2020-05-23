package in.clouthink.nextoa.exception;

/**
 *
 */
public class NewsException extends BizException {
	public NewsException() {
	}

	public NewsException(String message) {
		super(message);
	}

	public NewsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsException(Throwable cause) {
		super(cause);
	}
}
