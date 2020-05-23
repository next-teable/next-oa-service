package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.Receiver;
import cn.com.starest.nextoa.openapi.dto.ReceiverParameter;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParameter parameter);

	List<Receiver> buildReceivers(List<ReceiverParameter> parameter);

}
