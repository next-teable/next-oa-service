package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;

import java.util.Date;

/**
 * @author dz
 */
public interface Publishable {

	boolean isPublished();

	Date getPublishAt();

	User getPublishBy();

}
