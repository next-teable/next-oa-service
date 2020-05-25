package in.clouthink.nextoa.event.support.sms;

import in.clouthink.nextoa.event.support.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dz
 */
@ConfigurationProperties(prefix = Constants.SMS_PREFIX)
public class YunpianOptions {

    private String key;

    private String templateId;

    private String endpoint;

    private String shortUrl;

    private String longUrl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
