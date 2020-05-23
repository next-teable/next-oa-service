package in.clouthink.nextoa.model.dtr;

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
