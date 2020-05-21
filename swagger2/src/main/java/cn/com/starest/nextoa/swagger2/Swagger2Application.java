package cn.com.starest.nextoa.swagger2;

import cn.com.starest.nextoa.event.EventModuleConfiguration;
import cn.com.starest.nextoa.model.ModelConfiguration;
import cn.com.starest.nextoa.openapi.OpenApiModuleConfigure;
import cn.com.starest.nextoa.rbac.RbacConfiguration;
import cn.com.starest.nextoa.repository.RepositoryConfiguration;
import cn.com.starest.nextoa.service.ServiceConfiguration;
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
