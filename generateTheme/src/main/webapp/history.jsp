<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>履歴</title>
	</head>

	<body>
	
		<%
			List<String> savedTheme = ( List<String> )request.getAttribute( "savedTheme" );
		%>
		
		<% if ( savedTheme == null || savedTheme.isEmpty() ) {%>
    			まだないよ
    			
    	<% } else {%>
    		<% for ( String s : savedTheme ) {%>
    			<%=s%>
    		<% }%>
    	<% }%>

	</body>

</html>