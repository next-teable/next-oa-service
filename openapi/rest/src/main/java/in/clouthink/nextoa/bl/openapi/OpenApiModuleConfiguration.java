package in.clouthink.nextoa.bl.openapi;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"in.clouthink.nextoa.bl.openapi"})
@EnableConfigurationProperties(OpenApiConfigurationProperties.class)
public class OpenApiModuleConfiguration {

}
