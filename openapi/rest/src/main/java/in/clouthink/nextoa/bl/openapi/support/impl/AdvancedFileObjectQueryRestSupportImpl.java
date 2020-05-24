package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.daas.fss.mongodb.repository.FileObjectRepository;
import in.clouthink.daas.fss.spi.FileObjectService;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.DefaultFileObjectQueryParameter;
import in.clouthink.nextoa.bl.openapi.support.AdvancedFileObjectQueryRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 */
@Service
public class AdvancedFileObjectQueryRestSupportImpl implements AdvancedFileObjectQueryRestSupport {

	@Autowired
	private FileObjectService fileObjectService;

	@Autowired
	private FileObjectRepository fileObjectRepository;

	@Override
	public void deleteById(String id, User user) {
		in.clouthink.daas.fss.core.FileObject fileObject = fileObjectService.findById(id);
		if (fileObject == null) {
			return;
		}

//		if (user.getAuthorities().contains(SysRole.ROLE_ADMIN) || user.getAuthorities().contains(SysRole.ROLE_MGR)) {
//			try {
//				fileObjectService.deleteById(id);
//			}
//			catch (Exception e) {
//			}
//			return;
//		}
//
//		if (!user.getUsername().equalsIgnoreCase(fileObject.getUploadedBy())) {
//			throw new AttachmentException("只能删除自己上传的附件.");
//		}

		try {
			fileObjectService.deleteById(id);
		}
		catch (Exception e) {
		}
	}

	@Override
	public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter, User user) {
		String sortedBy = queryParameter.getSortedBy();
		if (StringUtils.isEmpty(sortedBy)) {
			sortedBy = "uploadedAt";
		}
		return fileObjectRepository.findPage(queryParameter,
											 new PageRequest(queryParameter.getStart(),
															 queryParameter.getLimit(),
															 new Sort(Sort.Direction.ASC, sortedBy)));
	}
}
