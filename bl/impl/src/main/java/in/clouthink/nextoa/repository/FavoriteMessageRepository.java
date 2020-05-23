package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.FavoriteMessage;
import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.repository.custom.FavoriteMessageRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

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
