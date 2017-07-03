package com.san.constants;

public class AppConstants {
	public static final boolean DEVELOPER_MODE_ENABLED = true;

	static {
		if (System.getProperty("env").equalsIgnoreCase("dev")) {
			// DEVELOPER_MODE_ENABLED = true;
		} else {
			// DEVELOPER_MODE_ENABLED = false;
		}
	}
}
