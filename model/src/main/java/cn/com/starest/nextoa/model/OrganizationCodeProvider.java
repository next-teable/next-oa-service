package cn.com.starest.nextoa.model;

/**
 * 组织机构编码生成器
 */
@Deprecated
public interface OrganizationCodeProvider {

	/**
	 * @param organization (if the parent of the org is null ,the org is taken as the root node)
	 * @return
	 */
	String generate(Organization organization);

}
