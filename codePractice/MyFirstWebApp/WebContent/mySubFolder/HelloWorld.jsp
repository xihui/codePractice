<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hellow JSP</title>
</head>
<body>
<h1>Hello JSP!</h1>
<p>
Nice to meet you, JSP!

The time is now <%= new Date() %>
</p>

<p>
<%
	System.out.println("Evaluating Date Now");
	Date date = new Date();
%>

Hello, the time is now 

<%
	out.println(date);
	out.println("<BR>Your machine's address is ");
	out.println(request.getRemoteHost());
%>
</p>

<p>
<TABLE BORDER=2>
<%
	for(int i=0; i<10; i++){
		%>
		<TR>
		<TD>Number</TD>
		<TD><%= i+1 %></TD>
		</TR>
		<%
	}
%>
</TABLE>

<%! 
	Date theDate = new Date();
	Date getDate() {
        System.out.println( "In getDate() method" );
		return theDate;
	}
%>

Hello!, The time is now <%= getDate() %>

</p>

</body>
</html>