package in.clouthink.nextoa.bl.exception;

/**
 *
 */
public class NoticeNotFoundException extends NoticeException {
	public NoticeNotFoundException() {
	}

	public NoticeNotFoundException(String message) {
		super(message);
	}

	public NoticeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoticeNotFoundException(Throwable cause) {
		super(cause);
	}
}
