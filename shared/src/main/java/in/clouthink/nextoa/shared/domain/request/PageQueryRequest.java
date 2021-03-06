package in.clouthink.nextoa.shared.domain.request;

/**
 *
 */
public interface PageQueryRequest {

	/**
	 * @return 0 as default
	 */
	int getStart();

	/**
	 * @return 20 as default
	 */
	int getLimit();

	/**
	 * @return the attributes order by asc
	 */
	String[] getAttributesOrderByAsc();

	/**
	 * @return the attributes order by desc
	 */
	String[] getAttributesOrderByDesc();

}
