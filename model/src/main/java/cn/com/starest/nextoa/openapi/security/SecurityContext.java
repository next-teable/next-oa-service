package cn.com.starest.nextoa.openapi.security;


import cn.com.starest.nextoa.model.User;

/**
 */
public interface SecurityContext {

	User currentUser();

	User requireUser();

}
