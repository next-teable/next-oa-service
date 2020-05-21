package cn.com.starest.nextoa.webhook.notify;

import cn.com.starest.nextoa.webhook.WebhookConfigurationProperties;
import cn.com.starest.nextoa.webhook.client.WebhookClient;
import cn.com.starest.nextoa.webhook.client.impl.WebhookClientBearyChatImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author dz
 */
public class StartupNotification implements ApplicationListener<ApplicationStartedEvent> {

	@Autowired
	private WebhookConfigurationProperties webhookConfigurationProperties = new WebhookConfigurationProperties();

	@Autowired
	private WebhookClient webhookClient = new WebhookClientBearyChatImpl();

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		webhookClient.sendMessage(webhookConfigurationProperties.getUrl(),
								  webhookConfigurationProperties.getStartupMessage());
	}

}
