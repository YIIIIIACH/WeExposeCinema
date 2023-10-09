<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊會員</title>
</head>
<body>
<div style="padding: 20px 30%">
<div>
<form action="/WeExpose/Register" method="post">
<fieldset>
<legend>註冊資料</legend>
<label>會員帳號</label><input type="text" name="acc" >
<label>會員密碼</label><input type="password" name="pwd" >
<label>會員姓名</label><input type="text" name="memberName" >
<label>年齡</label><input type="number" name="age" >
<button type="reset" >重置</button><button type="submit">註冊</button>
</fieldset>
</form>
</div>
</div>
</body>
</html>