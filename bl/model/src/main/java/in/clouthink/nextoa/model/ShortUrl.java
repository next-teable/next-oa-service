package in.clouthink.nextoa.model;

import in.clouthink.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dz
 */
@Document(collection = "ShortUrls")
public class ShortUrl extends StringIdentifier {

	@Indexed
	private String key;

	private String url;

	private boolean enabled;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
