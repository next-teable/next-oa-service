package in.clouthink.nextoa.event;

import in.clouthink.daas.edm.email.EmailSender;
import in.clouthink.daas.edm.email.impl.EmailSenderImpl;
import in.clouthink.daas.edm.sms.SmsSender;
import in.clouthink.nextoa.event.support.Constants;
import in.clouthink.nextoa.event.support.email.SmtpOptions;
import in.clouthink.nextoa.event.support.email.SenderOptions;
import in.clouthink.nextoa.event.support.sms.SmsSenderYunpianImpl;
import in.clouthink.nextoa.event.support.sms.YunpianOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 */
@Configuration
@ComponentScan({"in.clouthink.nextoa.event"})
@EnableConfigurationProperties({SmtpOptions.class, SenderOptions.class, YunpianOptions.class})
public class EventModuleConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = Constants.EMAIL_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = false)
    @Autowired
    public JavaMailSender javaMailSender(SmtpOptions smtpOptions) {
        JavaMailSenderImpl result = new JavaMailSenderImpl();
        result.setHost(smtpOptions.getHost());
        result.setPort(smtpOptions.getPort());
        result.setUsername(smtpOptions.getUsername());
        result.setPassword(smtpOptions.getPassword());
        result.getJavaMailProperties().put("mail.smtp.auth", true);
        result.getJavaMailProperties().put("mail.smtp.starttls.enable", true);
        return result;
    }

    @Bean
    @ConditionalOnProperty(prefix = Constants.EMAIL_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = false)
    @Autowired
    public EmailSender emailSender(SmtpOptions smtpOptions) {
        return new EmailSenderImpl(javaMailSender(smtpOptions));
    }

    @Bean
    @ConditionalOnProperty(prefix = Constants.SMS_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = false)
    @Autowired
    public SmsSender yunpianSmsSender(YunpianOptions yunpianOptions) {
        return new SmsSenderYunpianImpl(yunpianOptions);
    }

}
