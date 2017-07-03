package com.san.util;

public class LiteralParserUtil {

	public static int parseInt(String str) {
		int value = 0;
		try {
			value = Integer.parseInt(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static boolean parseBool(String str) {
		boolean value = false;
		try {
			value = Boolean.parseBoolean(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static long parseLong(String str) {
		long value = 0;
		try {
			value = Long.parseLong(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static float parseFloat(String str) {
		float value = 0;
		try {
			value = Float.parseFloat(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static double parseDouble(String str) {
		double value = 0;
		try {
			value = Double.parseDouble(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static byte parseByte(String str) {
		byte value = 0;
		try {
			value = Byte.parseByte(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static short parseShort(String str) {
		short value = 0;
		try {
			value = Short.parseShort(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static char parseChar(String str) {
		char value = 0;
		try {
			value = (char) Integer.parseInt(str);
		} catch (IllegalArgumentException illegalArgumentException) {
		}
		return value;
	}

	public static Object parseLiteral(String str, Class<?> clazz) {
		Object value = str;
		switch (clazz.getSimpleName()) {
		case "Integer": {
			value = parseInt(str);
			break;
		}
		case "int": {
			value = parseInt(str);
			break;
		}
		case "Float": {
			value = parseFloat(str);
			break;
		}
		case "float": {
			value = parseFloat(str);
			break;
		}
		case "Double": {
			value = parseDouble(str);
			break;
		}
		case "double": {
			value = parseDouble(str);
			break;
		}
		case "Long": {
			value = parseLong(str);
			break;
		}
		case "long": {
			value = parseLong(str);
			break;
		}
		case "Short": {
			value = parseShort(str);
			break;
		}
		case "short": {
			value = parseShort(str);
			break;
		}
		case "Byte": {
			value = parseByte(str);
			break;
		}
		case "byte": {
			value = parseByte(str);
			break;
		}
		case "Boolean": {
			value = parseBool(str);
			break;
		}
		case "boolean": {
			value = parseBool(str);
			break;
		}
		case "Character": {
			value = parseChar(str);
			break;
		}
		case "char": {
			value = parseChar(str);
			break;
		}
		}
		return value;
	}

}
