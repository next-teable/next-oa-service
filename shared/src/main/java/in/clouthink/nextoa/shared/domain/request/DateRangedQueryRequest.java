package in.clouthink.nextoa.shared.domain.request;


import java.util.Date;

/**
 */
public interface DateRangedQueryRequest extends MutablePageQueryRequest {

    Date getBeginDate();

    Date getEndDate();

}
