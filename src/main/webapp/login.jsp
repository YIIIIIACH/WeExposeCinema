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
<style>
.warn-message{
	display:none;
}</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 40px 30% 10px;">
	<form class="bs-example bs-example-form" role="form" method="post" action="/WeExpose/Login" onsubmit="validateMyForm()">
	<h3>${ requestScope.message }</h3>
	<input type="text" name="nextPath" style="display:none" value="${requestScope.nextPath}">
	<input type="text" name="showingId" style="display:none" value="${requestScope.showingId}"/>
	
	<fieldset>
	<legend>帳號登入</legend>
		<div class="input-group">
			<span class="input-group-addon glyphicon glyphicon-user">帳號</span>
			<input type="text" id="account"class="form-control" name="acc">
		</div><br>
		<div class="input-group">
			<span class=" input-group-addon glyphicon glyphicon-lock">密碼</span>
			<input type="password" class="form-control" name="pwd" id="password">
		</div>
		<label id="invalidPwdMessage" class="warn-message">請輸入帳號 或者 密碼至少需要一個數字符一個大小寫英文且長度須大於四</label>
		<div class="button-group" style="margin:20px 15%">
			<button type="reset" class="btn btn-default" style="width:50%">重置</button><button type="submit" class="btn btn-default" style="width:50%">登入</button>		
		</div>
	</fieldset>
	</form>
</div>
</body>
<script>
function validateMyForm(){
	let p = document.getElementById('password').value;
	let a = document.getElementById('account').value;
	// need number , upper and lower character , at least 4
	if( /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{4,}$/.test(p) && a!=''){
		return true;
	}
	document.getElementById('invalidPwdMessage').style.display= "block";
	event.preventDefault();
	return false;
}</script>
</html>