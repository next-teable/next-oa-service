package in.clouthink.nextoa.shared.domain.request;

/**
 *
 */
public interface MutablePageQueryRequest extends PageQueryRequest {

    /**
     * @param start
     */
    void setStart(int start);

    /**
     * @param limit
     */
    void setLimit(int limit);

}
