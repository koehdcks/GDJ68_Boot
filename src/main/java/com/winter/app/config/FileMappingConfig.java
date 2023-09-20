package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration //***-context.xml 과 같은 역할
public class FileMappingConfig implements WebMvcConfigurer {
	
	//local 파일의 위치
	@Value("${app.upload.mapping}")
	private String filePath;
	//요청 URL 경로
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(urlPath) //요청 URL 주소
				.addResourceLocations(filePath);//Local 파일 위치
	}
}
