<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String e = ( String )request.getAttribute( "error" );
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>難易度選択</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>

		<div class="card dc-area">
		
			<div class="title">お題の難易度を選んでください！</div>
			
			<form action="MakeTheme" method="post">
		
				<input type="hidden" name="action" value="generate">
			
				<div class="dcr-area">
					<div class="dc-radio">
						<input type="radio" id="easy" name="difficulty" value="1" required>
						<label for="easy">かんたん</label>
					</div>	
					<div class="dc-radio">
						<input type="radio" id="normal" name="difficulty" value="2">
						<label for="normal">ふつう</label>
					</div>
					<div class="dc-radio">
						<input type="radio" id="hard" name="difficulty" value="3">
						<label for="hard">むずかしい</label>
					</div>
					<div class="dc-radio">
						<input type="radio" id="veryhard" name="difficulty" value="4">
						<label for="veryhard">とてもむずかしい</label>
					</div>
				</div>
				<input type="submit" value="決定">
			
			</form>
			
			<% if ( e != null && !e.isEmpty() ) { %>
					<%=e %>
			<% } %>		
			
		</div>
	</body>

</html>