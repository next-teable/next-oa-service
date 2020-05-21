package cn.com.starest.nextoa.event.support.impl;

import cn.com.starest.nextoa.event.support.EmailService;
import cn.com.starest.nextoa.event.support.email.SenderOptions;
import cn.com.starest.nextoa.model.ShortUrl;
import cn.com.starest.nextoa.model.SystemSetting;
import cn.com.starest.nextoa.repository.ShortUrlRepository;
import cn.com.starest.nextoa.service.SystemSettingService;
import cn.com.starest.nextoa.shared.util.ShortenUrlUtils;
import in.clouthink.daas.edm.email.EmailMessage;
import in.clouthink.daas.edm.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	private static final String contentTemplate = "亲爱的用户, <br/><br/>" +
												  "有一条新的快文等你处理哦 。<br/>" +
												  "标题: %s。<br/>" +
												  "发送人: %s <br/>" +
												  "快文地址: <a href='%s' target='_blank'>%s</a> <br/>" +
												  "祝您工作顺利." +
												  "<br/><br/>--------------<br/> %s.";

	@Autowired
	private SenderOptions senderOptions;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private SystemSettingService systemSettingService;

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Override
	public void sendPendingPaperEmail(String username, String messageId, String title, String paperSender) {
		String appName = "NEXTOA办公网";
		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		if (systemSetting != null) {
			appName = systemSetting.getName();
		}

		String longUrlTemplate = senderOptions.getLongUrl();
		String shortUrlTemplate = senderOptions.getShortUrl();
		String shortUrl = generateShortUrl(longUrlTemplate, shortUrlTemplate, messageId);

		EmailMessage emailMessage = new EmailMessage();
		emailMessage.setFrom(senderOptions.getFrom());
		emailMessage.setSender(senderOptions.getFromAlias());
		emailMessage.setTo(username);
		emailMessage.setSubject(String.format("[%s]新的快文等待您处理", appName));
		emailMessage.setMessage(String.format(contentTemplate, title, paperSender, shortUrl, shortUrl, appName));

		emailSender.send(emailMessage);
	}

	private String generateShortUrl(String longUrlTemplate, String shortUrlTemplate, String messageId) {
		String longUrl = String.format(longUrlTemplate, messageId);
		String shortKey = ShortenUrlUtils.nextoa(longUrl);
		String result = String.format(shortUrlTemplate, shortKey);

		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setKey(shortKey);
		shortUrl.setUrl(longUrl);
		shortUrl.setEnabled(true);
		shortUrlRepository.save(shortUrl);

		return result;
	}

}
