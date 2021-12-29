package com.example.demo.pass.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

// 使用 Swagger3 配置
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				
				// apis、paths 兩個作為過濾器，過濾顯示在螢幕上的 Controller 要有哪些
				.select() 
				
				// RequestHandlerSelectors.any() 顯示所有 Controller
				// RequestHandlerSelectors.basePackage("com.example.demo.pass.controller") 只顯示特定 Package 下的 Controller 
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.pass.controller"))
				
				// PathSelectors.any() 顯示所有 Controller
				// PathSelectors.ant("/test1") 只顯示特定 Path 下的 Controller
				.paths(PathSelectors.any())	
				.build()
		
				
				// 設定文件細節，會顯示在頁面左上角
				.apiInfo(apiInfo())
				
				// 使 Header 可以存入 Token，權限驗證功能
				.securityContexts(Collections.singletonList(securityContext()))			
				.securitySchemes(Arrays.asList(apiKey()));
	}
	
	// 設定存到 header 的參數 
    private ApiKey apiKey() {
    	// 在 Header 裡放一個參數:
    	// key 是 Authorization
    	// value 自己輸入
    	return new ApiKey("Authorization", "Authorization", "header");
    }
    
    // header 裡 Token 設定
    private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any())
				.build();
	}
    private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("web", "access_token"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    List<SecurityReference> securityReferences = new ArrayList<>(); 
	    // "Authorization"要跟 header 參數名稱一樣不能亂改，否則 header 參數不會跟著 Request 送出
	    securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
	    return securityReferences; 
    }
    
    
    // 顯示在左上角的標題，文件細節
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Arp")
				.description("Arp文件")
				.version("1.0")
				.build();
	}
	
	
}
