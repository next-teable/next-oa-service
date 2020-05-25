package in.clouthink.nextoa.event.support.email;

import in.clouthink.nextoa.event.support.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dz
 */
@ConfigurationProperties(prefix = Constants.EMAIL_SMTP_PREFIX)
public class SmtpOptions {

    private String host;

    private int port = -1;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
