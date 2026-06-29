<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>履歴</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
	
		<div class="card h-area">
			
			<%
				List<String> savedTheme = ( List<String> )request.getAttribute( "savedTheme" );
			%>
		
			<% if ( savedTheme == null || savedTheme.isEmpty() ) {%>
    				<div class="title">まだないよ</div>
    			
    		<% } else {%>
    			<div class="title">履歴一覧</div>
    			
    			<div class="hl-area">
    				<% for ( String s : savedTheme ) {%>
    						<div class="h-list">
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