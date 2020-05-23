package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.ShortUrl;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

/**
 * @author dz
 */
public interface ShortUrlRepository extends AbstractRepository<ShortUrl> {

	ShortUrl findFirstByKey(String key);

}
