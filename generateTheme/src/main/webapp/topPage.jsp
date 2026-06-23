<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>お題生成アプリ</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
	
		<div class="top-area">
		
			<div class="title">今日のお題を作ります！</div>
			
			<div class="top-button">
				<form action="difficultyChoice.jsp" method="get">
					<input type="submit" value="お題を生成">
				</form>
				<form action="MakeTheme" method="get">	
					<button type="submit" name="action" value="history">履歴を確認</button>
					<button type="submit" name="action" value="favorite">お気に入りを確認</button>
				</form>
			</div>
			
		</div>
		
	</body>
	
</html>