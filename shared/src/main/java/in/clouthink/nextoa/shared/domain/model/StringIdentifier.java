package in.clouthink.nextoa.shared.domain.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public abstract class StringIdentifier implements Serializable {

	public static final String DEFAULT_ID = "1";

	protected static String trim(String target) {
		return target != null ? target.trim() : null;
	}

	@Id
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
