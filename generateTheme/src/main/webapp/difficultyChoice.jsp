<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>難易度選択</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		お題の難易度を選んでください！
		<form action="MakeTheme" method="post">
		
			<input type="hidden" name="action" value="generate">
			
			<input type="radio" name="difficulty" value="1">かんたん</button>
			<input type="radio" name="difficulty" value="2">ふつう</button>
			<input type="radio" name="difficulty" value="3">むずかしい</button>
			<input type="radio" name="difficulty" value="4">とてもむずかしい</button>
			<input type="submit" value="決定">
			
		</form>
	</body>

</html>