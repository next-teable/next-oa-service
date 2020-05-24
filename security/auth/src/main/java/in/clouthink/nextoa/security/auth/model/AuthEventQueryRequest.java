package in.clouthink.nextoa.security.auth.model;


import in.clouthink.nextoa.shared.domain.request.DateRangedQueryRequest;

/**
 *
 */
public interface AuthEventQueryRequest extends DateRangedQueryRequest {

	String getUsername();

	Boolean getSucceed();
}
