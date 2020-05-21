package cn.com.starest.nextoa.repository.custom;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.dtr.AttachmentQueryRequest;
import org.springframework.data.domain.Page;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public interface AttachmentRepositoryCustom {

	Page<Attachment> queryPage(AttachmentQueryRequest queryRequest);

	void updateDownloadCounter(String id, int downloadCounter);
}
