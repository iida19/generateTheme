<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
	String e = ( String )request.getAttribute( "error" );
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>選択画面</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
	
		<%
			List<String> favoriteTheme = ( List<String> )request.getAttribute( "favoriteTheme" );
			String themeText = ( String )request.getAttribute( "themeText" );
		%>
		
		<div class="card fc-area">
		
			お気に入りの件数が上限いっぱいです！<br>
			削除するお題を選んでください！
		
			<form action="MakeTheme" method="post">
		
				<input type="hidden" name="action" value="deleteFavorite">
				<input type="hidden" name="themeText" value="<%=themeText %>">
			
				<div class="fcr-area">
				
					<% for ( int i = 0; i < favoriteTheme.size(); i ++ ) { %>
							<div class="fc-radio">
							<input type="radio" id="<%=i %>" name="deleteChoice" value="<%= i %>">
							<label for="<%=i %>"><%=favoriteTheme.get( i ) %></label>
							</div>
					<% } %>
					<div class="fc-radio">
						<input type="radio" id="<%=favoriteTheme.size() %>" name="deleteChoice" value="<%=favoriteTheme.size() %>" required>
							<label for="<%=favoriteTheme.size() %>">上のお題は残して、お気に入り登録をキャンセル</label>
					</div>
				
				</div>
				
				<input type="submit" value="削除">
						
			</form>
			
			<% if ( e != null && !e.isEmpty() ) { %>
					<%=e %>
			<% } %>
		
		</div>

	</body>

</html>