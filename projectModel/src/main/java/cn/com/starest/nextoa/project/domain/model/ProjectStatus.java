package cn.com.starest.nextoa.project.domain.model;

/**
 * 项目状态
 *
 * @author dz
 */
public enum ProjectStatus {

	//草稿
	DRAFT,
	//立项（目前无这个阶段,立项后,直接进入RUNNING状态,以后如果加上立项审批,则需要一个PENDING状态）
	@Deprecated PENDING,
	//进行中
	RUNNING,
	//已竣工
	COMPLETED,
	//已结算
	SETTLED,
	//结项
	CLOSED

}
