package cn.com.starest.nextoa.repository.custom;

import org.springframework.data.domain.Page;

import cn.com.starest.nextoa.model.FavoriteMessage;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.MessageQueryRequest;

public interface FavoriteMessageRepositoryCustom {
    
    Page<FavoriteMessage> queryPage(User createdBy,
                                    MessageQueryRequest queryRequest);

}
