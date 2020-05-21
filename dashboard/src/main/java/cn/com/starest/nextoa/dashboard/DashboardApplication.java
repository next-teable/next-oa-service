package cn.com.starest.nextoa.dashboard;

import cn.com.starest.nextoa.dashboard.support.audit.security.SecurityContextImpl;
import cn.com.starest.nextoa.dashboard.support.audit.spiImpl.AuditEventPersisterImpl;
import cn.com.starest.nextoa.event.EventModuleConfiguration;
import cn.com.starest.nextoa.model.ModelConfiguration;
import cn.com.starest.nextoa.openapi.OpenApiModuleConfigure;
import cn.com.starest.nextoa.project.ProjectRestApiModuleConfigure;
import cn.com.starest.nextoa.project.ProjectServiceModuleConfiguration;
import cn.com.starest.nextoa.rbac.RbacConfiguration;
import cn.com.starest.nextoa.repository.RepositoryConfiguration;
import cn.com.starest.nextoa.service.ServiceConfiguration;
import cn.com.starest.nextoa.webhook.WebhookModuleConfiguration;
import cn.com.starest.nextoa.webhook.notify.ShutdownNotification;
import cn.com.starest.nextoa.webhook.notify.StartupNotification;
import in.clouthink.daas.audit.annotation.EnableAudit;
import in.clouthink.daas.audit.configure.AuditConfigurer;
import in.clouthink.daas.audit.spi.AuditEventPersister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.ErrorPageFilter;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ComponentScan({"cn.com.starest.nextoa.dashboard"})
@Import({DashboardSecurityConfigurer.class, DashboardWebMvcConfigurer.class})
@EnableConfigurationProperties(DashboardConfigurationProperties.class)
@EnableMongoRepositories({"cn.com.starest.nextoa.dashboard.support.audit.repository",
						  "cn.com.starest.nextoa.dashboard.support.auth.repository"})
@EnableScheduling
@EnableAsync
@EnableAudit
public class DashboardApplication extends SpringBootServletInitializer implements SchedulingConfigurer {

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("task-");
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(taskScheduler());
	}

	@Bean
	public AuditEventPersister auditEventPersisterImpl() {
		return new AuditEventPersisterImpl();
	}

	@Bean
	public AuditConfigurer auditConfigurer() {
		return result -> {
			result.setSecurityContext(new SecurityContextImpl());
			result.setAuditEventPersister(auditEventPersisterImpl());
			result.setErrorDetailRequired(true);
		};
	}

	@Override
	protected WebApplicationContext run(SpringApplication application) {
		application.getSources().remove(ErrorPageFilter.class);
		return super.run(application);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(new Object[]{ModelConfiguration.class,
																   RepositoryConfiguration.class,
																   ServiceConfiguration.class,
																   EventModuleConfiguration.class,
																   OpenApiModuleConfigure.class,
																   ProjectServiceModuleConfiguration.class,
																   ProjectRestApiModuleConfigure.class,
																   RbacConfiguration.class,
																   WebhookModuleConfiguration.class,
																   DashboardApplication.class});
//		app.addListeners(new ShutdownNotification(), new StartupNotification());
		app.run(args);
	}

}
