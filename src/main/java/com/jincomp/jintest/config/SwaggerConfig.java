//package com.jincomp.jintest.config;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
///*
// * @EnableWebMvc : swagger 3.0 버전
// * @EnableSwagger2 : swagger 2.0 버전
// */
//@Configuration
//public class SwaggerConfig {
//	
//	private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//            .title("Test Open API")
//            .description("Test Swagger Demo")
//            .version("1.0")
//            .build();
//    }
//	
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.OAS_30)
//                .consumes(getConsumeContentTypes())
//                .produces(getProduceContentTypes())
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.jincomp.jintest.web.jin.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private Set<String> getConsumeContentTypes() {
//        Set<String> consumes = new HashSet<>();
//        consumes.add("application/json;charset=UTF-8");
//        consumes.add("application/x-www-form-urlencoded");
//        return consumes;
//    }
//
//    private Set<String> getProduceContentTypes() {
//        Set<String> produces = new HashSet<>();
//        produces.add("application/json;charset=UTF-8");
//        return produces;
//    	} 
//    }
//
