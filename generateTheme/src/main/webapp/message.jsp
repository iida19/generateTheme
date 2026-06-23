<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>メッセージ</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
	
		<%
			String favoChoice = ( String )request.getAttribute( "favoChoice" );
			Integer choice = ( Integer )request.getAttribute( "choice" );
		%>
		
		<% if ( favoChoice.equals( "y" ) ) {%>
				登録しました！
		<% } else if ( favoChoice.equals( "n" ) ) {%>
				承知しました！
		<% } else if ( favoChoice.equals( "d" ) ) {%>
				<%=choice + 1%>番を削除して、最新のお題をお気に入りに追加しました！
		<% } else if ( favoChoice.equals( "c" ) ) {%>
				お気に入り登録をキャンセルしました！
		<% } %>				
		
		<form action="topPage.jsp" method="get">
        	<input type="submit" value="メニューへ戻る">
        </form>		

	</body>

</html>