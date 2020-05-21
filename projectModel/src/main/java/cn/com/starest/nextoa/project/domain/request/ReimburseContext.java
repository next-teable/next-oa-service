package cn.com.starest.nextoa.project.domain.request;

/**
 * @author dz
 */
public interface ReimburseContext {

	/**
	 * @return 报销类型（通过快文还是通过任务）
	 */
	ReimburseRequestRefer getRequestReferType();

	/**
	 * @return 报销申请id（对应到报销类型分别是快文id和任务id）
	 */
	String getRequestReferId();

}
