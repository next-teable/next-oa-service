package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.ShortUrl;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

/**
 * @author dz
 */
public interface ShortUrlRepository extends AbstractRepository<ShortUrl> {

	ShortUrl findFirstByKey(String key);

}
