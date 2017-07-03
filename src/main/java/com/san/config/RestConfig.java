package com.san.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.san.config.SwaggerConfig;

@Configuration
@Import({ SwaggerConfig.class })
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@ComponentScan(basePackages = { "com.san.rest", "com.san.handler.rest" })
public class RestConfig extends WebMvcConfigurationSupport {
	Logger logger = Logger.getLogger(RestConfig.class);

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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// will be execute only for matching urls
	}

	// Required for swagger integration
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	// Set a handler to manage above resources
	@Override
	@Bean
	public HandlerMapping resourceHandlerMapping() {
		AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) super.resourceHandlerMapping();
		handlerMapping.setOrder(-1);
		return handlerMapping;
	}
}
