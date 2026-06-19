<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>お題生成アプリ</title>
	</head>
	
	<body>
		今日のお題を作ります！
		<form action="difficultyChoice.jsp" method="get">
			<input type="submit" value="お題を生成">
		</form>
		<form action="MakeTheme" method="get">	
			<button type="submit" name="action" value="history">履歴を確認</button>
			<button type="submit" name="action" value="favorite">お気に入りを確認</button>
		</form>
	</body>
	
</html>