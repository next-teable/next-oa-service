package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class LicenseException extends RuntimeException {

	public LicenseException() {
	}

	public LicenseException(String message) {
		super(message);
	}

	public LicenseException(String message, Throwable cause) {
		super(message, cause);
	}

	public LicenseException(Throwable cause) {
		super(cause);
	}

	public LicenseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
