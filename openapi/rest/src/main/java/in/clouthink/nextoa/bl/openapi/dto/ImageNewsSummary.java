package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.News;
import io.swagger.annotations.ApiModel;

/**
 */
@ApiModel
public class ImageNewsSummary extends NewsSummary {

	static void convert(News news, ImageNewsSummary result) {
		NewsSummary.convert(news, result);
	}

	public static ImageNewsSummary from(News news, String fileObjectId) {
		if (news == null) {
			return null;
		}
		ImageNewsSummary result = new ImageNewsSummary();
		convert(news, result);
		result.setFileObjectId(fileObjectId);
		return result;
	}


	private String fileObjectId;

	public String getFileObjectId() {
		return fileObjectId;
	}

	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}

}
