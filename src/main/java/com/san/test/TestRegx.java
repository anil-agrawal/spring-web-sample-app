package com.san.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegx {
	public static void main(String args[]) {
		String[] lines = { "/rest/v1/api1/index/{name}", "/api/v1/test/admin/{name}", "/rest/v1/api2/index", "/rest/v1/api3/index", "/rest/v1/api4/index", "/api/v1/test/operate", "/rest/v1/api5/index" };
		String pattern = "^(?!/rest/v1/api3/index$).*$";
		pattern = "^(?!/api/v1/test/admin.*$)(?!/api/v1/test/operate$).*$";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		for (String line : lines) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				System.out.println("Found value: " + m.group(0));
			} else {
				System.out.println("NO MATCH");
			}
		}

	}
}
