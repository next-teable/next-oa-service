package cn.com.starest.nextoa.model.dtr;

/**
 * Named PageQueryRequest
 */
public interface NamedPageQueryRequest extends PageQueryRequest {

	NamedPageQueryRequest DUMMY = new NamedPageQueryRequest() {

		@Override
		public int getStart() {
			return 0;
		}

		@Override
		public int getLimit() {
			return 20;
		}

		@Override
		public String[] getAttributesOrderByAsc() {
			return new String[0];
		}

		@Override
		public String[] getAttributesOrderByDesc() {
			return new String[0];
		}

		@Override
		public String getName() {
			return null;
		}
	};

	/**
	 */
	String getName();

}
