package in.clouthink.nextoa.security.rbac;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "in.clouthink.nextoa.security.rbac")
public class RbacConfigurationProperties {

	private String resourceFile;

	public String getResourceFile() {
		return resourceFile;
	}

	public void setResourceFile(String resourceFile) {
		this.resourceFile = resourceFile;
	}

}
