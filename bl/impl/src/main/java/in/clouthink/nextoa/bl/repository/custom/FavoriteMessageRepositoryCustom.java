package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.FavoriteMessage;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.MessageQueryRequest;
import org.springframework.data.domain.Page;

public interface FavoriteMessageRepositoryCustom {
    
    Page<FavoriteMessage> queryPage(User createdBy,
                                    MessageQueryRequest queryRequest);

}
