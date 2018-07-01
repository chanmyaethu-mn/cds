package com.chan.cds.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.chan.cds.**.mapper")
public class WebMVCConfig implements WebMvcConfigurer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
	 * addResourceHandlers(org.springframework.web.servlet.config.annotation.
	 * ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/image/**").addResourceLocations("file:C:\\image\\");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
