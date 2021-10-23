package com.mf.smax.common.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket mailApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("SMAX-Restful Webhook").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.mf.smax.web")).paths(PathSelectors.any()).build();
	}
	// 预览地址:swagger-ui.html
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SMAX-Restful Webhook").termsOfServiceUrl("https://www.51cto.com/")
				.contact(new Contact("musaraňa ", "http://gnzhutan.blog.51cto.com/", "gnzhutan@outlook.com")).version("1.1").build();
	}
}
