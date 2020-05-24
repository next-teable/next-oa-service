package in.clouthink.nextoa.openapi.swagger2;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Collections;

@Configuration
@EnableSwagger2
@ComponentScan("in.clouthink.nextoa.openapi")
public class SpringfoxConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("NEXTOA APIs",
                "NEXTOA APIs",
                "1.0.0-SNAPSHOT",
                "",
                new Contact("melthaw", "http://github.com/melthaw", "melthaw@gmail.com"),
                "Apache2",
                "", Collections.emptyList());
    }

	
	/*private ApiKey apiKey() {
		return new ApiKey("apiDocKey", "api_doc_key", "header");
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.buildReceiver();
	}
	
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		
		return Lists.newArrayList(
				new SecurityReference("apiDocKey", authorizationScopes));
	}
	
	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				"app-client-id",
				"app-realm",
				"app",
				"apiKey");
	}
	
	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl");
	}*/

}
