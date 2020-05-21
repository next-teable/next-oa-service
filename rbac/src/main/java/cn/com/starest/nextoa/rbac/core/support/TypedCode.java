package cn.com.starest.nextoa.rbac.core.support;

/**
 *
 */
public class TypedCode {

	private String type;

	private String code;

	public TypedCode(String type, String code) {
		this.type = type;
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

}
