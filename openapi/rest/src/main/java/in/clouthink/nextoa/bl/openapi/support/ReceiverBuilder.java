package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.Receiver;
import in.clouthink.nextoa.bl.openapi.dto.ReceiverParameter;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParameter parameter);

	List<Receiver> buildReceivers(List<ReceiverParameter> parameter);

}
