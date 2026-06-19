<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>生成結果</title>
	</head>

	<body>
		
		<%
			String[] chosenTheme = ( String[] )request.getAttribute( "chosenTheme" );
			Integer dif = ( Integer )request.getAttribute( "dif" );
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
		
	</body>

</html>