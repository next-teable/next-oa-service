package in.clouthink.nextoa.webhook;

import in.clouthink.nextoa.webhook.notify.ShutdownNotification;
import in.clouthink.nextoa.webhook.notify.StartupNotification;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.webhook.client"})
@EnableConfigurationProperties(WebhookConfigurationProperties.class)
public class WebhookModuleConfiguration {

	@Bean
	public ApplicationListener<ApplicationStartedEvent> startupNotification() {
		return new StartupNotification();
	}

	@Bean
	public ApplicationListener<ApplicationFailedEvent> shutdownNotificatioin() {
		return new ShutdownNotification();
	}

}
