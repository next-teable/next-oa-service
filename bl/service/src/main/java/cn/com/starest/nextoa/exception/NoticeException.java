package cn.com.starest.nextoa.exception;

/**
 *
 */
public class NoticeException extends BizException {
	public NoticeException() {
	}

	public NoticeException(String message) {
		super(message);
	}

	public NoticeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoticeException(Throwable cause) {
		super(cause);
	}
}
