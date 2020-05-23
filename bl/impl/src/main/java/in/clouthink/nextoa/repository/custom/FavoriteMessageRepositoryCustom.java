package in.clouthink.nextoa.repository.custom;

import org.springframework.data.domain.Page;

import in.clouthink.nextoa.model.FavoriteMessage;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.MessageQueryRequest;

public interface FavoriteMessageRepositoryCustom {
    
    Page<FavoriteMessage> queryPage(User createdBy,
                                    MessageQueryRequest queryRequest);

}
