package com.san;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

import com.san.config.AppConfig;
import com.san.config.CassandraConfig;
import com.san.service.TestService;
import com.san.util.CommonUtil;

public class App {
	public static AnnotationConfigApplicationContext appContext;

	public static void main(String[] args) {
		System.out.println("Initializing Application");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, CassandraConfig.class);
		appContext = ctx;
		CommonUtil.ctx = ctx;
		System.out.println("Application Initialized");
		test();
		exit();
	}

	public static void test() {
		try {
			TestService testService = appContext.getBean(TestService.class);
			testService.testApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exit() {
		CassandraClusterFactoryBean cluster = appContext.getBean(CassandraClusterFactoryBean.class);
		try {
			System.out.println("Destroying Application");
			cluster.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
