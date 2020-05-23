package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.FavoriteMessage;
import cn.com.starest.nextoa.model.Message;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.custom.FavoriteMessageRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

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
