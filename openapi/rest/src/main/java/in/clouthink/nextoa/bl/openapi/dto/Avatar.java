package in.clouthink.nextoa.bl.openapi.dto;

import io.swagger.annotations.ApiModel;

/**
 * 头像
 */
@ApiModel
public class Avatar {

	private String thumbnailFileId;

	private String thumbnailFileName;

	private String imageFileId;

	private String imageFileName;

	public String getThumbnailFileId() {
		return thumbnailFileId;
	}

	public void setThumbnailFileId(String thumbnailFileId) {
		this.thumbnailFileId = thumbnailFileId;
	}

	public String getThumbnailFileName() {
		return thumbnailFileName;
	}

	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}

	public String getImageFileId() {
		return imageFileId;
	}

	public void setImageFileId(String imageFileId) {
		this.imageFileId = imageFileId;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
}
