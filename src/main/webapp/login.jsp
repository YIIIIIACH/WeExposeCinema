<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
</head>
<body>
<div align="center">
<form method="get" action="/WeExpose/Login" style="margin:80px 200px">
	<input type="text" name="nextPath" style="display:none" value="${nextPath }">
	<input type="text" name="showingId" style="display:none" value="${showingId }"/>
	<div>
	<label>LOG IN</label><input type="text" name="acc">
	</div>
	<div>
	<label>PASSWORD</label><input type="password" name="pwd">
	</div>
	<button type="reset">RESET</button><button type="submit">SUBMIT</button>
</form>
</div>
</body>
</html>