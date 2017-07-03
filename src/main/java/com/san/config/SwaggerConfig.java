package com.san.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource(value = { "classpath:swagger.properties" })
@EnableSwagger2
public class SwaggerConfig {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private Environment env;

	@Bean
	public Docket userAPI() {
		ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
		apiSelectorBuilder = apiSelectorBuilder.apis(RequestHandlerSelectors.any());
		apiSelectorBuilder = apiSelectorBuilder.paths(PathSelectors.regex(userAPIPathSelector()));
		Docket docket = apiSelectorBuilder.build();
		docket = docket.apiInfo(apiInfo());
		overrideResponseCodeMessages(docket);
		docket.groupName("user");
		return docket;

	}

	private String userAPIPathSelector() {
		// Show all APIs but below ones
		String[] onlyForDevelopers = { "/v1/test/admin", "/v1/test/operate" };

		// Control API listing by either java or swagger.properties also decide the preference in case both are available
		String apis = env.getProperty("san.swagger.restrictedAPIs");
		if (apis != null && apis.length() > 3) {
			onlyForDevelopers = apis.split("\\,");
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("^");
		for (String api : onlyForDevelopers) {
			stringBuilder.append("(?!" + api.trim() + ".*$)");
		}
		stringBuilder.append(".*$");
		// System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}

	@Bean
	public Docket developerAPI() {
		ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
		apiSelectorBuilder = apiSelectorBuilder.apis(RequestHandlerSelectors.any());
		apiSelectorBuilder = apiSelectorBuilder.paths(PathSelectors.any());
		Docket docket = apiSelectorBuilder.build();
		docket = docket.apiInfo(apiInfo());
		overrideResponseCodeMessages(docket);
		docket.groupName("dev");
		return docket;

	}

	private ApiInfo apiInfo() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo("Public REST API", "Public APIs", "1.0", "", "anilagrawal038@gmail.com", "", "");
		return apiInfo;
	}

	private Docket overrideResponseCodeMessages(Docket docket) {
		List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
		messages.add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error")).build());
		messages.add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());
		docket = docket.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, messages);
		return docket;
	}

	// Url(Rest) for remote swagger : http://localhost:8080/api/v2/api-docs
	// Url(Web) for local swagger UI : http://localhost:8080/api/swagger-ui.html
}
