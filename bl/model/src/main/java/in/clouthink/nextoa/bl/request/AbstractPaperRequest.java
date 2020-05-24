package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Receiver;

import java.util.List;

/**
 */
public interface AbstractPaperRequest {

	/**
	 * 快文正文内容(对于允许编辑的,可以修改正文内容后再进入下一步)
	 *
	 * @return
	 */
	String getPaperContent();

	/**
	 * 主送
	 */
	List<Receiver> getToReceivers();

	/**
	 * 抄送
	 *
	 * @return
	 */
	List<Receiver> getCcReceivers();

}
