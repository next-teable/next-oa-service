package in.clouthink.nextoa.repository.custom;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.model.dtr.AttachmentQueryRequest;
import org.springframework.data.domain.Page;

public interface AttachmentRepositoryCustom {

	Page<Attachment> queryPage(AttachmentQueryRequest queryRequest);

	void updateDownloadCounter(String id, int downloadCounter);
}
