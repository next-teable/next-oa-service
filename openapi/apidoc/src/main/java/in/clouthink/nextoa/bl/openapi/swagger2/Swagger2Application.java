package in.clouthink.nextoa.bl.openapi.swagger2;

import in.clouthink.nextoa.bl.openapi.OpenApiModuleConfiguration;
import in.clouthink.nextoa.bl.ServiceConfiguration;
import in.clouthink.nextoa.event.EventModuleConfiguration;
import in.clouthink.nextoa.security.rbac.RbacConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{ModelConfiguration.class,
                        RepositoryConfiguration.class,
                        ServiceConfiguration.class,
                        OpenApiModuleConfiguration.class,
                        Swagger2Application.class,
                        EventModuleConfiguration.class,
                        RbacConfiguration.class,
                        SpringfoxConfiguration.class},
                args);
    }

}
