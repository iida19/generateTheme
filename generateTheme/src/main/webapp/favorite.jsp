<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入り</title>
	</head>

	<body>
	
		<%
			List<String> favoriteTheme = ( List<String> )request.getAttribute( "favoriteTheme" );
		%>
		
		<% if ( favoriteTheme == null || favoriteTheme.isEmpty() ) {%>
    			まだないよ
    			
    	<% } else {%>
    		<% for ( String s : favoriteTheme ) {%>
    			<%=s%>
    		<% }%>
    	<% }%>

	</body>

</html>