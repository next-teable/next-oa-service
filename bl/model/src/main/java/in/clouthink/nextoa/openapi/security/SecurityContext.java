package in.clouthink.nextoa.openapi.security;


import in.clouthink.nextoa.model.User;

/**
 */
public interface SecurityContext {

	User currentUser();

	User requireUser();

}
