<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="user" class="MyFirstWebApp.user.UserData" scope="session"/>
<jsp:setProperty name="user" property="*"/> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Save Name</title>
</head>
<body>
<%
    String tgtPage = null;
    if ( user.getUsername().equals( "xihui" ))
        tgtPage = "NextPage.jsp";
    else if ( user.getUsername().equals( "Evan" ))
        tgtPage = "HelloWorld.jsp";
    else
        tgtPage = "Hello2.jsp";
     response.sendRedirect( tgtPage );
%>
</body>
</html>