package com.san.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;

public class PropertyHelperUtil {

	public static Environment env;
	public static MessageSource messageSource;
	private static Logger logger = Logger.getLogger(PropertyHelperUtil.class);
	private static int processorCount = Runtime.getRuntime().availableProcessors();
	public static final Locale DEFAULT_LOCALE = Locale.getDefault();

	public static String getEnvProperty(String key) {
		return env.getProperty(key);
	}

	public static int getEnvPropertyAsInt(String key) {
		return LiteralParserUtil.parseInt(env.getProperty(key));
	}

	public static boolean getEnvPropertyAsBoolean(String key) {
		return LiteralParserUtil.parseBool(env.getProperty(key));
	}

	public static float getEnvPropertyAsFloat(String key) {
		return LiteralParserUtil.parseFloat(env.getProperty(key));
	}

	public static int fetchThreadPoolSize(Class<?> clazz) {
		return fetchThreadPoolSize(clazz.getName());
	}

	public static int fetchThreadPoolSize(String name) {
		String propName = name + ".threadPool.size";
		int poolSize = LiteralParserUtil.parseInt(env.getProperty(propName));
		if (poolSize < 1) {
			logger.debug("Property value for key [" + propName + "] not found in properties file. Applying default value : " + 1);
			poolSize = 1;
		} else {
			poolSize = Math.min(poolSize, processorCount);
		}
		return poolSize;
	}

	public static String getMessage(HttpServletRequest request, String code) throws NoSuchMessageException {
		Locale locale = new Locale(acceptLanguage(request));
		locale = (locale != null) ? locale : DEFAULT_LOCALE;
		return getMessage(code, locale);
	}

	public static String getMessage(String code) throws NoSuchMessageException {
		return messageSource.getMessage(code, null, DEFAULT_LOCALE);
	}

	public static String getMessage(String code, String locale) throws NoSuchMessageException {
		return messageSource.getMessage(code, null, new Locale(locale));
	}

	public static String getMessage(String code, Locale locale) throws NoSuchMessageException {
		return messageSource.getMessage(code, null, locale);
	}

	public static String getMessage(String code, Object[] arguments) throws NoSuchMessageException {
		return messageSource.getMessage(code, arguments, DEFAULT_LOCALE);
	}

	public static String getMessage(String code, String locale, Object[] arguments) throws NoSuchMessageException {
		return messageSource.getMessage(code, arguments, new Locale(locale));
	}

	public static String getMessage(String code, Locale locale, Object[] arguments) throws NoSuchMessageException {
		return messageSource.getMessage(code, arguments, locale);
	}

	private static String acceptLanguage(HttpServletRequest request) {
		String language = request.getHeader("Accept-Language");
		if (language == null) {
			language = "en_IN";
		}
		return language;
	}

}
