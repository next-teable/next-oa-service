package in.clouthink.nextoa.shared.domain.params;

import in.clouthink.nextoa.shared.domain.request.MutablePageQueryRequest;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class PageQueryParameter implements MutablePageQueryRequest {

    int start = 0;

    int limit = 20;

    String[] attributesOrderByAsc;

    String[] attributesOrderByDesc;

    public PageQueryParameter() {
    }

    public PageQueryParameter(int start, int limit) {
        setStart(start);
        setLimit(limit);
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public void setStart(int start) {
        if (start < 0) {
            start = 0;
        }
        this.start = start;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        if (limit <= 0) {
            limit = 20;
        }
        if (limit > 100) {
            limit = 100;
        }
        this.limit = limit;
    }

    @Override
    public String[] getAttributesOrderByAsc() {
        return attributesOrderByAsc;
    }

    public void setAttributesOrderByAsc(String[] attributesOrderByAsc) {
        this.attributesOrderByAsc = attributesOrderByAsc;
    }

    @Override
    public String[] getAttributesOrderByDesc() {
        return attributesOrderByDesc;
    }

    public void setAttributesOrderByDesc(String[] attributesOrderByDesc) {
        this.attributesOrderByDesc = attributesOrderByDesc;
    }
}
