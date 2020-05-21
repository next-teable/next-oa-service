package cn.com.starest.nextoa.rbac.core.model;



import java.io.Serializable;

/**
 *
 */
public class DefaultRole implements Role, Serializable {

	private String code;

	private String name;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
