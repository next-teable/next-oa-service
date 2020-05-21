package cn.com.starest.nextoa.exception;


/**
 *
 */
public class RoleNotFoundException extends RoleException {

	public RoleNotFoundException() {
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
	}
}
