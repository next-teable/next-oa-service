package in.clouthink.nextoa.event;

import in.clouthink.daas.edm.email.EmailSender;
import in.clouthink.daas.edm.email.impl.EmailSenderImpl;
import in.clouthink.daas.edm.sms.SmsSender;
import in.clouthink.nextoa.event.support.email.EmailOptions;
import in.clouthink.nextoa.event.support.email.SenderOptions;
import in.clouthink.nextoa.event.support.sms.SmsSenderYunpianImpl;
import in.clouthink.nextoa.event.support.sms.YunpianOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 */
@Configuration
@ComponentScan({"in.clouthink.nextoa.event"})
@EnableConfigurationProperties({EmailOptions.class, SenderOptions.class, YunpianOptions.class})
public class EventModuleConfiguration {

	@Bean
	@Autowired
	public JavaMailSender javaMailSender(EmailOptions emailOptions) {
		JavaMailSenderImpl result = new JavaMailSenderImpl();
		result.setHost(emailOptions.getHost());
		result.setPort(emailOptions.getPort());
		result.setUsername(emailOptions.getUsername());
		result.setPassword(emailOptions.getPassword());
		result.getJavaMailProperties().put("mail.smtp.auth", true);
		result.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
		return result;
	}

	@Bean
	@Autowired
	public EmailSender emailSender(EmailOptions emailOptions) {
		return new EmailSenderImpl(javaMailSender(emailOptions));
	}

	@Bean
	@Autowired
	public SmsSender yunpianSmsSender(YunpianOptions yunpianOptions) {
		return new SmsSenderYunpianImpl(yunpianOptions);
	}


}
