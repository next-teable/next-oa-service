package cn.com.starest.nextoa.event.support.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by dz on 16/5/12.
 */
@ConfigurationProperties(prefix = "cn.com.starest.nextoa.email.smtp")
public class EmailOptions {

	private String host;

	private int port;

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
