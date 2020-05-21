package cn.com.starest.nextoa.model.shared;

/**
 */
public interface PasswordEncoder {

	String encode(String rawPassword, String salt);

}
