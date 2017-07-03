<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HelloWorld page</title>
</head>
<body>Greeting : ${greeting} This is a welcome page.
<input type="hidden"
	name="${_csrf.parameterName}"
	value="${_csrf.token}"/>
</body>
</html>