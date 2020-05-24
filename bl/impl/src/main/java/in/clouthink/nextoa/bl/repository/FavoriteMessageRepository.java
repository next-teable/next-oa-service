package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.FavoriteMessage;
import in.clouthink.nextoa.bl.model.Message;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.repository.custom.FavoriteMessageRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

/**
 *
 */
public interface FavoriteMessageRepository extends
        AbstractRepository<FavoriteMessage>,
        FavoriteMessageRepositoryCustom {
    
    FavoriteMessage findByMessageAndCreatedBy(Message message, User user);
    
    // Page<FavoriteMessage> findByCreatedBy(User user, Pageable pageable);
    
    long countByCreatedBy(User user);
    
}
