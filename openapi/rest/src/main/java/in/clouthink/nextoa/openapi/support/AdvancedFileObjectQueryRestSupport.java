package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.DefaultFileObjectQueryParameter;
import in.clouthink.daas.fss.mongodb.model.FileObject;
import org.springframework.data.domain.Page;

/**
 */
public interface AdvancedFileObjectQueryRestSupport {

	void deleteById(String id, User user);

	Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter,User user);
}
