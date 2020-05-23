package in.clouthink.nextoa.swagger2;

import in.clouthink.nextoa.event.EventModuleConfiguration;
import in.clouthink.nextoa.model.ModelConfiguration;
import in.clouthink.nextoa.openapi.OpenApiModuleConfigure;
import in.clouthink.nextoa.rbac.RbacConfiguration;
import in.clouthink.nextoa.repository.RepositoryConfiguration;
import in.clouthink.nextoa.service.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{ModelConfiguration.class,
                        RepositoryConfiguration.class,
                        ServiceConfiguration.class,
                        OpenApiModuleConfigure.class,
                        Swagger2Application.class,
                        EventModuleConfiguration.class,
                        RbacConfiguration.class,
                        SpringfoxConfiguration.class},
                args);
    }

}
