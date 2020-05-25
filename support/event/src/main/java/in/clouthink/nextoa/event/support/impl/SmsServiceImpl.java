package in.clouthink.nextoa.event.support.impl;

import in.clouthink.daas.edm.sms.AdvancedSmsMessage;
import in.clouthink.daas.edm.sms.SmsSender;
import in.clouthink.nextoa.bl.model.ShortUrl;
import in.clouthink.nextoa.bl.model.SmsHistory;
import in.clouthink.nextoa.bl.repository.ShortUrlRepository;
import in.clouthink.nextoa.bl.repository.SmsHistoryRepository;
import in.clouthink.nextoa.event.support.SmsService;
import in.clouthink.nextoa.event.support.sms.YunpianOptions;
import in.clouthink.nextoa.shared.util.ShortenUrlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;

/**
 *
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Log logger = LogFactory.getLog(SmsServiceImpl.class);

    @Autowired(required = false)
    private SmsSender smsSender;

    @Autowired(required = false)
    private YunpianOptions yunpianOptions;

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Override
    public void sendPendingPaperMessage(String cellphone, String messageId, String sender, String title) {
        if (smsSender == null) {
            logger.warn(String.format("The sms sender is disabled. No sms will be sent to (mobile=%s,title=%s, sender=%s)",
                    cellphone, title, sender));
            return;
        }

        SmsHistory smsHistory = new SmsHistory();
        smsHistory.setCellphone(cellphone);
        smsHistory.setCreatedAt(new Date());
        smsHistory.setCategory(SmsHistory.SmsCategory.PENDING_PAPER_MESSAGE);

        try {
            AdvancedSmsMessage smsMessage = new AdvancedSmsMessage();
            smsMessage.setCellphone(cellphone);
            smsMessage.setOptions(yunpianOptions);

            String longUrlTemplate = yunpianOptions.getLongUrl();
            String shortUrlTemplate = yunpianOptions.getShortUrl();
            String shortUrl = generateShortUrl(longUrlTemplate, shortUrlTemplate, messageId);

            String message = String.format("sender=%s&title=%s&address=%s", sender, title, shortUrl);
            smsHistory.setMessage(message);
            String encodedMessage = new StringBuilder().append(URLEncoder.encode("#sender#", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(sender, "UTF-8"))
                    .append("&")
                    .append(URLEncoder.encode("#title#", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(title, "UTF-8"))
                    .append("&")
                    .append(URLEncoder.encode("#address#", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode(shortUrl, "UTF-8"))
                    .toString();
            smsMessage.setMessage(encodedMessage);
            smsSender.send(smsMessage);
        } catch (Exception e) {
            smsHistory.setStatus(SmsHistory.SmsStatus.FAILED);
            smsHistory.setFailureReason(e + "");
            logger.error(e, e);
        } finally {
            smsHistoryRepository.save(smsHistory);
        }
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


//	public static void main(String[] args) {
//		System.out.print(new SmsServiceImpl().generateShortUrl(
//				"http://117.177.38.55:9999/portal#!//static/app/mobile/paper/detail.html?messageId=%s",
//				"http://117.177.38.55:9999/s/%s",
//				"dsuiorewi149324fdsafkh234879"));
//	}

}
