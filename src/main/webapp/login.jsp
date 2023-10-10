<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeExpose 會員登入</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 40px 30% 10px;">
	<form class="bs-example bs-example-form" role="form" method="post" action="/WeExpose/Login">
	<h3>${ requestScope.message }</h3>
	<input type="text" name="nextPath" style="display:none" value="${requestScope.nextPath}">
	<input type="text" name="showingId" style="display:none" value="${requestScope.showingId}"/>
	<fieldset>
	<legend>帳號登入</legend>
		<div class="input-group">
			<span class="input-group-addon glyphicon glyphicon-user">帳號</span>
			<input type="text" class="form-control" name="acc">
		</div><br>
		<div class="input-group">
			<span class=" input-group-addon glyphicon glyphicon-lock">密碼</span>
			<input type="password" class="form-control" name="pwd">
		</div>
		<div class="button-group" style="margin:20px 15%">
			<button type="reset" class="btn btn-default" style="width:50%">重置</button><button type="submit" class="btn btn-default" style="width:50%">登入</button>		
		</div>
	</fieldset>
	</form>
</div>
</body>
</html>