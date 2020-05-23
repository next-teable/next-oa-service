package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.model.Receiver;
import in.clouthink.nextoa.openapi.dto.ReceiverParameter;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParameter parameter);

	List<Receiver> buildReceivers(List<ReceiverParameter> parameter);

}
