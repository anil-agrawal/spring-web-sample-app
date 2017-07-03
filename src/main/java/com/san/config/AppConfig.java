package com.san.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.san.util.PropertyHelperUtil;

@Configuration
@Import({ CassandraConfig.class, SecurityConfig.class })
@ComponentScan({ "com.san.service", "com.san.handler" })
public class AppConfig {

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:i18/api/error/messages", "classpath:i18/api/success/messages");
		messageSource.setDefaultEncoding("UTF-8");
		PropertyHelperUtil.messageSource = messageSource;
		return messageSource;
	}

}
