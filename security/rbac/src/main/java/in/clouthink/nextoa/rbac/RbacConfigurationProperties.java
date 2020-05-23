package in.clouthink.nextoa.rbac;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "in.clouthink.nextoa.rbac")
public class RbacConfigurationProperties {

	private String resourceFile;

	public String getResourceFile() {
		return resourceFile;
	}

	public void setResourceFile(String resourceFile) {
		this.resourceFile = resourceFile;
	}

}
