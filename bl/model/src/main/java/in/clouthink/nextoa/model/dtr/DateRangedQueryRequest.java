package in.clouthink.nextoa.model.dtr;


import java.util.Date;

/**
 */
public interface DateRangedQueryRequest extends MutablePageQueryRequest {

    Date getBeginDate();

    Date getEndDate();

}
