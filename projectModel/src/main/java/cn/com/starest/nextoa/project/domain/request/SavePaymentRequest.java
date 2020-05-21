package cn.com.starest.nextoa.project.domain.request;

import java.util.Date;

/**
 * @author dz
 */
public interface SavePaymentRequest extends AbstractSavePaymentRequest {

	/**
	 * @return 付款时间
	 */
	Date getPayAt();

}
