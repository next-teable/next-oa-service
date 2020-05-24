package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Attachment;
import in.clouthink.nextoa.bl.repository.custom.AttachmentRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface AttachmentRepository extends AbstractRepository<Attachment>, AttachmentRepositoryCustom {

	Page<Attachment> findByPublished(boolean published, Pageable pageable);

}
