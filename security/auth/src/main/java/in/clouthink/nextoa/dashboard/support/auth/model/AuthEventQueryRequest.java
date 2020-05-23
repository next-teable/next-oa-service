package in.clouthink.nextoa.dashboard.support.auth.model;

import in.clouthink.nextoa.model.dtr.DateRangedQueryRequest;

/**
 *
 */
public interface AuthEventQueryRequest extends DateRangedQueryRequest {

	String getUsername();

	Boolean getSucceed();
}
