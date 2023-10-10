<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeExpose 會員登入</title>
</head>
<body>
<div align="center">
<form method="post" action="/WeExpose/Login" style="margin:80px 30%">
	<h3>${ requestScope.message }</h3>
	<input type="text" name="nextPath" style="display:none" value="${requestScope.nextPath}">
	<input type="text" name="showingId" style="display:none" value="${requestScope.showingId}"/>
	<div>
	<label>帳號</label><input type="text" name="acc">
	</div>
	<div>
	<label>密碼</label><input type="password" name="pwd">
	</div>
	<button type="reset">重置</button><button type="submit">登入</button>
</form>
</div>
</body>
</html>