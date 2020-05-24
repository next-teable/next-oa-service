package in.clouthink.nextoa.bl.request;

/**
 * @author dz
 */
public interface SaveSystemSettingRequest {

	/**
	 * @return
	 */
	String getName();

	/**
	 * @return
	 */
	String getFileObjectId();

	/**
	 * @return
	 */
	String getContactEmail();

	/**
	 * @return
	 */
	String getContactPhone();

	/**
	 * @return
	 */
	String[] getCompanySupervisorIds();

	/**
	 * @return
	 */
	String[] getBizDepartmentSupervisorIds();

	/**
	 * @return
	 */
	String[] getProjectSupervisorIds();

	/**
	 * @return
	 */
	String[] getProfitSupervisorIds();

	/**
	 *
	 * @return
	 */
	String getProfitAccountSubjectName();

	/**
	 *
	 * @return
	 */
	String getOutsourceAccountSubjectName();
}
