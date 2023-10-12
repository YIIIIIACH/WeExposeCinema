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
<script>
function validateMyForm(){
	if( document.getElementById('acc').value =="" || document.getElementById('memberName').value =="" || document.getElementById('memberAge').value ==""){
		console.log("lack of data");
		document.getElementById('invalidPwdMessage').style.display= "block";
		event.preventDefault();
		return false;
	}
	
	let p = document.getElementById('pwd').value;
	
	// need number , upper and lower character , at least 4
	if( document.getElementById('acc'))
	if( /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{4,}$/.test(p)){
		return true;
	}
	document.getElementById('invalidPwdMessage').style.display= "block";
	event.preventDefault();
	return false;
}
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 20px 20% 40% 30%;">
<div>
<form action="/WeExpose/Register" onsubmit="validateMyForm()" method="post">
<fieldset >
<legend >註冊資料</legend>
<div class="input-group mb-3">
  	<div class="input-group-prepend">
    	<span class="input-group-text" id="basic-addon1">會員帳號</span>
    	<input type="text" name="acc" id="acc" class="form-control" placeholder="UserAccount" aria-label="UserAccount" aria-describedby="basic-addon1">
 	</div>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">會員密碼</span>
 	 <input type="password" name="pwd" id="pwd" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1">
  </div>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">會員姓名</span>
  	<input type="text" name="memberName" id="memberName" class="form-control" placeholder="Username" aria-label="UserName" aria-describedby="basic-addon1">
  </div>
</div>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">會員年齡</span>
  	<input type="number" name="age" id="memberAge"class="form-control" placeholder="UserAge" aria-label="UserAge" aria-describedby="basic-addon1">
  </div>
</div>
</fieldset>
<label id="invalidPwdMessage" class="warn-message">密碼至少需要一個數字符一個大小寫英文且長度須大於四</label>
<div style="padding-top:30px ">
<button type="reset"class="btn btn-outline-secondary" >重置</button><%="\t\t" %><button class="btn btn-outline-secondary" type="submit">註冊</button>
</div>
</form>
</div>
</div>
</body>
<style>
.warn-message{
	display:none;
}
</style>

</html>