package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.ShortUrl;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * @author dz
 */
public interface ShortUrlRepository extends AbstractRepository<ShortUrl> {

	ShortUrl findFirstByKey(String key);

}
