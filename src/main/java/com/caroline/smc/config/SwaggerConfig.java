package com.caroline.smc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caroline.smc.security.constants.SecurityConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		ParameterBuilder ticketPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		ticketPar.name(SecurityConstants.TOKEN_HEADER).description("user token").modelRef(new ModelRef("string"))
				.parameterType("header").required(false).build();
		pars.add(ticketPar.build());

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .securitySchemes(Collections.singletonList(apiKey()))
//                .securityContexts(Collections.singletonList(securityContext()))
//                .useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.caroline.smc")).paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Stock Market Charting APIs Guide")
				.description("Stock Market Charting APIs Guide")
				.termsOfServiceUrl("http://localhost:8082/smc/swagger-ui.html")
				.contact(new Contact("Caroline", "", "fuyanbin2009@163.com")).version("1.0.0").build();
	}
	
//	private ApiKey apiKey() {
//        return new ApiKey("apiKey", SecurityConstants.TOKEN_HEADER, "header");
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Collections.singletonList(new SecurityReference("apiKey", authorizationScopes));
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/smc/api/*"))
//                .build();
//    }
//
//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId("api-client-id")
//                .clientSecret("api-client-secret")
//                .realm("api-realm")
//                .appName("api-app")
//                .scopeSeparator(",")
//                .additionalQueryStringParams(null)
//                .useBasicAuthenticationWithAccessCodeGrant(false)
//                .build();
//    }
}