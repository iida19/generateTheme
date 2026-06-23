<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>生成結果</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		
		<%
			String[] chosenTheme = ( String[] )request.getAttribute( "chosenTheme" );
			Integer dif = ( Integer )request.getAttribute( "dif" );
			String themeText = ( String )request.getAttribute( "themeText" );
		%>
		
		今日は、<%=chosenTheme[1] %>色をテーマに、<br>
		<% if ( dif == 2 ) {%>
			<%=chosenTheme[2] %>の距離感で、<%=chosenTheme[3] %>表情の<br>
		<% } %>
		<% if ( dif == 3 || dif == 4 ) {%>
			<%=chosenTheme[2] %>・<%=chosenTheme[4] %>の構図で、<%=chosenTheme[3] %>表情の<br>
		<% } %>
		<%=chosenTheme[0] %>を描いてみよう！<br>
		<% if ( dif == 4 ) {%>
			エクストラお題：
			<% String extra = "";%>
        	<% for ( int i = 5; i < chosenTheme.length; i ++ ) {%>
        		<% extra += chosenTheme[i] + "/";%>
        	<% }%>
        	<% extra = extra.substring(0, extra.length()-1 );%>
        	<%=extra%>
        <% }%>
        
        このお題をお気に入りに登録しますか？
		<form action="MakeTheme" method="post">
		
			<input type="hidden" name="action" value="favorite">
			<input type="hidden" name="difficulty" value="<%=dif %>">
			<input type="hidden" name="themeText" value="<%=themeText %>">
			
			<button name="favo" value="y">はい</button>
			<button name="favo" value="n">いいえ</button>
			
		</form>
        
		
	</body>

</html>