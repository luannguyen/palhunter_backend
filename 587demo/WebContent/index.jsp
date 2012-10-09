<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.io.*"%>
<%@ page import="edu.usc.cs587.examples.*"%>
<%@ page import="com.std.princeton.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
This is the body.<br />
<%
	String userDir = System.getProperty("user.dir");
	out.println("user.dir="+userDir);
	Constants.testJAR();
%><br />
<%= new String(Constants.getOS()) %><br />
<%= new String(Constants.getCurrentTime()) %><br />
<%= new String(Constants.getUserDir()) %><br />
<%= new String(Constants.getUserHome()) %><br />
<%= new String(Constants.getPath()) %><br />
<%= new String(String.valueOf(Constants.testJAR())) %><br />
<%= new String(String.valueOf(StdRandom.uniform(100))) %><br />

</body>
</html>