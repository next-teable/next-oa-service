package cn.com.starest.nextoa.webhook.client;

/**
 * @author dz
 */
public interface WebhookClient {

	/**
	 * The implementation must handle the exception
	 *
	 * @param url
	 * @param message
	 */
	void sendMessage(String url, String message);

	/**
	 * The implementation must handle the exception
	 *
	 * @param url
	 * @param message
	 */
	void sendMessage(String url, String message, String body);

}
