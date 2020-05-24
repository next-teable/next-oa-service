package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.DefaultFileObjectQueryParameter;
import org.springframework.data.domain.Page;

/**
 */
public interface AdvancedFileObjectQueryRestSupport {

	void deleteById(String id, User user);

	Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter,User user);
}
