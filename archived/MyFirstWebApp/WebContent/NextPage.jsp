<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="MyFirstWebApp.user.UserData" scope="session"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NextPage</title>
</head>
<body>
Hello, <%= user.getUsername()%>
Your age is <%= user.getAge() %>
</body>
</html>