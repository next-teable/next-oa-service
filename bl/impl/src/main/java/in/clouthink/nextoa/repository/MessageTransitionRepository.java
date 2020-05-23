package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.MessageTransition;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface MessageTransitionRepository extends AbstractRepository<MessageTransition> {

	List<MessageTransition> findByMessage(Message message);

}
