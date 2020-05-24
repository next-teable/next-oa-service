package in.clouthink.nextoa.bl.repository.custom;

import in.clouthink.nextoa.bl.model.Attachment;
import in.clouthink.nextoa.bl.request.AttachmentQueryRequest;
import org.springframework.data.domain.Page;

public interface AttachmentRepositoryCustom {

	Page<Attachment> queryPage(AttachmentQueryRequest queryRequest);

	void updateDownloadCounter(String id, int downloadCounter);
}
