<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入り</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
	
		<div class="card f-area">
		
			<%
				List<String> favoriteTheme = ( List<String> )request.getAttribute( "favoriteTheme" );
			%>
		
			<% if ( favoriteTheme == null || favoriteTheme.isEmpty() ) {%>
   	 				<div class="title">まだないよ</div>
    			
    		<% } else {%>
    			<div class="title">お気に入り一覧</div>
    		
    			<div class="fl-area">
    				<% for ( String s : favoriteTheme ) {%>
    						<div class="f-list">
    							<%=s%>
    						</div>	
    				<% }%>
    			</div>
    				
    		<% }%>
    	
    		<form action="topPage.jsp" method="get">
        		<input type="submit" value="メニューへ戻る">
        	</form>
        
        </div>

	</body>

</html>