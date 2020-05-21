package cn.com.starest.nextoa.dashboard.support.auth.model;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;

/**
 *
 */
public interface AuthEventQueryRequest extends DateRangedQueryRequest {

	String getUsername();

	Boolean getSucceed();
}
