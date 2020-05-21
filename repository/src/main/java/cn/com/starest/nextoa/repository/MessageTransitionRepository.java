package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Message;
import cn.com.starest.nextoa.model.MessageTransition;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface MessageTransitionRepository extends AbstractRepository<MessageTransition> {

	List<MessageTransition> findByMessage(Message message);

}