package com.jincomp.jintest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/*
 * @EnableWebMvc : swagger 3.0 버전
 * @EnableSwagger2 : swagger 2.0 버전
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig {
	
	@Bean
    public Docket swaggerAPI() {
        //Docket : swagger Bean
		
		/*
		 *  return new Docket(DocumentationType.OAS_30) //3.0
 		 *	return new Docket(DocumentationType.SWAGGER_2) //2.0
		 */
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(true) // Swagger 에서 제공해주는 기본 응답 코드를 표시할 것이면 true
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jincomp.jintest.web.jin.controller")) //swagger 탐색 대상 패키지
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
	}

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("BTONE Swagger")
                .description("도서 프로그램")
                .version("1.0")
                .build();
    }
}
