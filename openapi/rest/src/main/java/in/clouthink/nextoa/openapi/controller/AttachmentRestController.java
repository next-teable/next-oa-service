package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.AttachmentRestSupport;
import in.clouthink.nextoa.shared.util.ImageUtils;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.core.FileStorageException;
import in.clouthink.daas.fss.repackage.org.apache.commons.io.FilenameUtils;
import in.clouthink.daas.fss.rest.UploadFileRequest;
import in.clouthink.daas.fss.util.HttpMultipartUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Api("附件发布管理")
@RestController
@RequestMapping("/api")
public class AttachmentRestController {

	@Autowired
	private AttachmentRestSupport attachmentsRestSupport;

	/**
	 * CKEDITOR support （针对ckeditor进行特殊处理和支持）
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/attachments/upload", method = RequestMethod.POST)
	@ResponseBody
	public void upload4CkEditor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		MultipartFile multipartFile = HttpMultipartUtils.resolveMultipartFile(request);
		if (multipartFile == null || multipartFile.isEmpty()) {
			throw new FileStorageException("上传的文件不能为空");
		}
		String originalFileName = multipartFile.getOriginalFilename();
		String fileSuffix = FilenameUtils.getExtension(originalFileName);
		if (!ImageUtils.isSupported(fileSuffix)) {
			out.println("<script type=\"text/javascript\">alert('格式错误，仅支持jpg|jpeg|bmp|gif|png格式');</script>");
			out.flush();
			out.close();
			return;
		}

		UploadFileRequest uploadFileRequest = new UploadFileRequest();
		uploadFileRequest.setName("CKEDITOR插入图片");
		uploadFileRequest.setUploadedBy(SecurityContexts.getContext().requireUser().getUsername());
		FileObject fileObject = attachmentsRestSupport.upload4CkEditor(uploadFileRequest, request, response);

		// 组装返回url，以便于ckeditor定位图片
		String fileUrl = "/api/files/" + fileObject.getId();
		fileUrl = request.getContextPath() + fileUrl;

		// 将上传的图片的url返回给ckeditor
		String tabIndex = request.getParameter("CKEditorFuncNum");
		String callback = " var callback = function(){ " +
						  " var element, dialog = window.parent.CKEDITOR.dialog.getCurrent();console.info('dialog:' + dialog); " +
						  " element = dialog.getContentElement( 'info', 'txtWidth' ); " +
						  " console.info('element:' + element); " +
						  "	if ( element ) {\n " +
						  // " dialog.setValueOf('info', 'txtWidth', '768');" +
						  "	} " +
						  "};";
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + tabIndex + ",'" + fileUrl + "',''" + ")");
		out.println(callback);
		out.println("setTimeout(callback,300);");
		out.println("</script>");
	}


	/**
	 * 头像特殊处理,上传后,对头像进行crop和zoom操作
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/attachments/avatar", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAvatar(UploadFileRequest uploadFileRequest,
							   HttpServletRequest request,
							   HttpServletResponse response) throws IOException {
		User user = SecurityContexts.getContext().requireUser();
		uploadFileRequest.setUploadedBy(user.getUsername());
		FileObject fileObject = attachmentsRestSupport.uploadAvatar(uploadFileRequest, request, response);

		return fileObject.getId();
	}

	@ApiOperation(value = "附件列表,支持分页,支持动态查询(按名称,分类查询)")
	@RequestMapping(value = "/attachments", method = RequestMethod.GET)
	public Page<AttachmentSummary> listAttachmentSummaryPage(AttachmentQueryParameter queryRequest) {
		return attachmentsRestSupport.listAttachment(queryRequest);
	}

	@ApiOperation(value = "查看附件详情")
	@RequestMapping(value = "/attachments/{id}", method = RequestMethod.GET)
	public AttachmentDetail getAttachmentDetail(@PathVariable String id) {
		return attachmentsRestSupport.getAttachmentDetail(id);
	}

	@ApiOperation(value = "创建附件（前提已经调用daas-fss上传文件得到文件的metadata,然后和业务数据放到一起）")
	@RequestMapping(value = "/attachments", method = RequestMethod.POST)
	public String createAttachment(@RequestBody SaveAttachmentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return attachmentsRestSupport.createAttachment(request, user);
	}

	@ApiOperation(value = "修改附件信息（名称,分类等）,已发布的附件不能修改")
	@RequestMapping(value = "/attachments/{id}", method = RequestMethod.POST)
	public void updateNew(@PathVariable String id, @RequestBody SaveAttachmentParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		attachmentsRestSupport.updateAttachment(id, request, user);
	}

	@ApiOperation(value = "删除附件（已发布的附件不能删除）")
	@RequestMapping(value = "/attachments/{id}", method = RequestMethod.DELETE)
	public void deleteAttachment(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		attachmentsRestSupport.deleteAttachment(id, user);
	}

	@ApiOperation(value = "发布附件（重复发布自动忽略）")
	@RequestMapping(value = "/attachments/{id}/publish", method = RequestMethod.POST)
	public void publishAttachment(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		attachmentsRestSupport.publishAttachment(id, user);
	}

	@ApiOperation(value = "取消发布附件（重复取消自动忽略）")
	@RequestMapping(value = "/attachments/{id}/unpublish", method = RequestMethod.POST)
	public void unpublishAttachment(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		attachmentsRestSupport.unpublishAttachment(id, user);
	}

	@ApiOperation(value = "下载附件")
	@RequestMapping(value = {"/attachments/{id}/download",
							 "/portal/attachments/{id}/download"}, method = RequestMethod.GET)
	public void downloadAttachment(@PathVariable String id, HttpServletResponse response) throws IOException {
		User user = SecurityContexts.getContext().requireUser();
		attachmentsRestSupport.downloadAttachment(id, user, response);
	}

	@ApiOperation(value = "查看附件的下载历史记录")
	@RequestMapping(value = "/attachments/{id}/downloadHistory", method = RequestMethod.GET)
	public Page<DownloadSummary> listDownloadHistory(@PathVariable String id, PageQueryParameter queryParameter) {
		return attachmentsRestSupport.listDownloadHistory(id, queryParameter);
	}

}
