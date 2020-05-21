package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.DefaultFileObjectQueryParameter;
import in.clouthink.daas.fss.mongodb.model.FileObject;
import org.springframework.data.domain.Page;

/**
 */
public interface AdvancedFileObjectQueryRestSupport {

	void deleteById(String id,User user);

	Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter,User user);
}
