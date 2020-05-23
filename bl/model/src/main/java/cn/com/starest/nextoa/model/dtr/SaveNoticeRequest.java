package cn.com.starest.nextoa.model.dtr;

/**
 *
 */
public interface SaveNoticeRequest {

	String getCategory();

	String getTitle();

	//不超过140个字
	String getSummary();

	String getContent();

}
