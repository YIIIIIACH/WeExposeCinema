<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊會員</title>
<link rel="icon" type="image/x-icon" href="image/weExposeFavicon.ico">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 20px 30%">
<div>
<form action="/WeExpose/Register" onsubmit="validateMyForm()" method="post">
<fieldset>
<legend>註冊資料</legend>
<label>會員帳號</label><input type="text" name="acc" >
<label>會員密碼</label><input id="pwd" type="password" name="pwd" >
<label>會員姓名</label><input type="text" name="memberName" >
<label>年齡</label><input type="number" name="age" >
<label id="invalidPwdMessage" class="warn-message">密碼至少需要一個數字符一個大小寫英文且長度須大於四</label>
<button type="reset" >重置</button><button type="submit">註冊</button>
</fieldset>
</form>
</div>
</div>
</body>
<style>
.warn-message{
	display:none;
}
</style>
<script>
function validateMyForm(){
	let p = document.getElementById('pwd').value;
	// need number , upper and lower character , at least 4
	if( /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{4,}$/.test(p)){
		return true;
	}
	document.getElementById('invalidPwdMessage').style.display= "block";
	event.preventDefault();
	return false;
}
</script>
</html>