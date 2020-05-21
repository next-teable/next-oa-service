package cn.com.starest.nextoa.webhook.notify;

import cn.com.starest.nextoa.webhook.WebhookConfigurationProperties;
import cn.com.starest.nextoa.webhook.client.WebhookClient;
import cn.com.starest.nextoa.webhook.client.impl.WebhookClientBearyChatImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author dz
 */
public class ShutdownNotification implements ApplicationListener<ApplicationFailedEvent> {

	@Autowired
	private WebhookConfigurationProperties webhookConfigurationProperties = new WebhookConfigurationProperties();

	@Autowired
	private WebhookClient webhookClient = new WebhookClientBearyChatImpl();

	@Override
	public void onApplicationEvent(ApplicationFailedEvent event) {
		webhookClient.sendMessage(webhookConfigurationProperties.getUrl(),
								  webhookConfigurationProperties.getShutdownMessage(),
								  event.getException().getMessage());
	}


}
