package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Message;
import in.clouthink.nextoa.bl.model.MessageTransition;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface MessageTransitionRepository extends AbstractRepository<MessageTransition> {

	List<MessageTransition> findByMessage(Message message);

}
