package in.clouthink.nextoa.openapi;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.openapi", "in.clouthink.daas.fss"})
@EnableConfigurationProperties(OpenApiConfigurationProperties.class)
public class OpenApiModuleConfigure {

}
