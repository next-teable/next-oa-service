package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Message;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.repository.custom.MessageRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author dz
 */
public interface MessageRepository extends AbstractRepository<Message>, MessageRepositoryCustom {

	@Deprecated
	Page<Message> findByTitleLikeAndReceiver(String title, User receiver, Pageable pageable);

	List<Message> findByBizRefId(String bizRefId);

	Message findByBizRefIdAndReceiver(String bizRefId, User user);

	Page<Message> findPageByBizRefId(String bizRefId, Pageable pageable);

}
