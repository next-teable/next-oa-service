package cn.com.starest.nextoa.model.dtr;


public interface UsernameQueryRequest extends DateRangedQueryRequest {

	/**
	 * 用户名
	 *
	 * @return
	 */
	String getUsername();

	/**
	 * 用户名的拼音形式
	 *
	 * @return
	 */
	String getPinyin();

}
