package com.san.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Import({})
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@ComponentScan(basePackages = { "com.san.controller", "com.san.handler.web" })
public class WebConfig extends WebMvcConfigurationSupport {
	Logger logger = Logger.getLogger(WebConfig.class);

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// Add different resources path
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		int cachePeriod = 31556926;
		cachePeriod = 0;
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

		registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/views/**").addResourceLocations("/views/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/data/**").addResourceLocations("/data/").setCachePeriod(cachePeriod);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(cachePeriod);

	}

	// Set a handler to manage above resources
	@Override
	@Bean
	public HandlerMapping resourceHandlerMapping() {
		AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) super.resourceHandlerMapping();
		handlerMapping.setOrder(-1);
		return handlerMapping;
	}

	// Manage mapping of requests and handlers paths
	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		final RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
		// disable the truncation after .
		handlerMapping.setUseSuffixPatternMatch(false);
		// disable the truncation after ;
		handlerMapping.setRemoveSemicolonContent(false);
		return handlerMapping;
	}

	// Add different interceptors for controllers
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}
}
