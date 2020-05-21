package cn.com.starest.nextoa.project.exception;

/**
 * @author dz
 */
public class LicenseNotFoundException extends LicenseException {

	public LicenseNotFoundException() {
	}

	public LicenseNotFoundException(String message) {
		super(message);
	}

	public LicenseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public LicenseNotFoundException(Throwable cause) {
		super(cause);
	}

	public LicenseNotFoundException(String message,
									Throwable cause,
									boolean enableSuppression,
									boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
