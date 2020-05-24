package in.clouthink.nextoa.security;


import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 */
public interface SecurityContext<T extends UserDetails> {

    T currentUser();

    T requireUser();

}
