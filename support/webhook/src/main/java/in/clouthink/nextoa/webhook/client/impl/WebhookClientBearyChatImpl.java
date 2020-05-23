package in.clouthink.nextoa.webhook.client.impl;

import in.clouthink.nextoa.webhook.client.WebhookClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author dz
 */
@Component
public class WebhookClientBearyChatImpl implements WebhookClient {

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void sendMessage(String url, String message) {
		BearyChatMessage bearyChatMessage = BearyChatMessage.from(message);
		restTemplate.postForEntity(url, bearyChatMessage, Map.class);
	}

	@Override
	public void sendMessage(String url, String message, String body) {
		BearyChatMessage bearyChatMessage = BearyChatMessage.from(message);
		bearyChatMessage.getAttachments().add(BearyChatMessageAttachment.error(body));
		restTemplate.postForEntity(url, bearyChatMessage, Map.class);
	}
}
