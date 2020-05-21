package cn.com.starest.nextoa.model.dtr;

import java.util.Date;

/**
 *
 */
public interface AttachmentQueryRequest extends DateRangedQueryRequest {

	String getTitle();

	String getCategory();

	Boolean getPublished();

	Date getCreatedAtBegin();

	Date getCreatedAtEnd();

}
