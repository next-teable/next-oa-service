package in.clouthink.nextoa.webhook;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "in.clouthink.nextoa.webhook")
public class WebhookConfigurationProperties {

	private String url = "https://hook.bearychat.com/=bwBrq/incoming/403427090b0555b1804f0160f338ae29";

	private String startupMessage = "NEXTOA:Startup succeed.";

	private String shutdownMessage = "NEXTOA:The server is shutdown or startup failed.";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStartupMessage() {
		return startupMessage;
	}

	public void setStartupMessage(String startupMessage) {
		this.startupMessage = startupMessage;
	}

	public String getShutdownMessage() {
		return shutdownMessage;
	}

	public void setShutdownMessage(String shutdownMessage) {
		this.shutdownMessage = shutdownMessage;
	}
}
