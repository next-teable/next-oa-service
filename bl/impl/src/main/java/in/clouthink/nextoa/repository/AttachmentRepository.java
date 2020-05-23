package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.repository.custom.AttachmentRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface AttachmentRepository extends AbstractRepository<Attachment>, AttachmentRepositoryCustom {

	Page<Attachment> findByPublished(boolean published, Pageable pageable);

}
