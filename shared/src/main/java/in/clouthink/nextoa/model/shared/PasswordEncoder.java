package in.clouthink.nextoa.model.shared;

/**
 */
public interface PasswordEncoder {

	String encode(String rawPassword, String salt);

}