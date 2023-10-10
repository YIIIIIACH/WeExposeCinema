<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="struct.MemberOrderInfo,bean.*,java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<%! @SuppressWarnings("unchecked") %>
<meta charset="UTF-8">
<title>會員專區</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="margin:20px 20%">
	<div >
		<ul class="nav nav-tabs">
		    <li id="mDataBtn"><a><span>會員基本資料</span></a></li>
		    <li id="mOrderBtn"><a><span>購買紀錄</span></a></li>
	  	</ul>
		<form action="/WeExpose/EditMember" method="post" id='mDataForm'>
		<fieldset>
		<legend>會員資料</legend>
		${requestScope.message }
		<label>會員帳號</label><input name="account" value="${mb.memberAccount }"/>
		<label>會員密碼</label><input name="password" value="" >
		</fieldset>
		<fieldset>
		<label>會員姓名</label><input name="memberName" value="${mb.memberName }"/>
		<label>會員等級</label><input name="memberGrade" disabled value="${mb.memberGrade }">
		</fieldset>
		<fieldset  >
		<button type="submit">修改</button>
		</fieldset>
		</form>
	</div>
	<div id="mOrder" style="display:none">
	<h2>${mb.memberName }的購買紀錄</h2>
	<table>
	<% int subtotalCnt=0; %>
	<!--  movieName cinemaName theaterName showingDatetime showintTypeName  -->
	<% for( MemberOrderInfo of: (List<MemberOrderInfo>)request.getAttribute("orderInfoes") ){
		%>
		<tr><td><%= of.movie.getMovieName() %><td><%= of.theater.getTheaterName()%><td><%= of.showing.getShowingDatetime() %><td><%= of.showingType.getShowingTypeName() %>
		<% subtotalCnt=0; 
		for( int i=0; i< of.seats.size() ; i++){%>
		<tr><td><td>第<%=of.seats.get(i).getSeatRow() %>排<td>第<%=of.seats.get(i).getSeatColumn() %>位<td>單票價格<%= of.productServices.get(i).getProductServicePrice() %> 
		<% subtotalCnt += of.productServices.get(i).getProductServicePrice();
		}%>
		<tr><td><td><td><td><label>小計<%= subtotalCnt %>元</label>
	<% }%>

	</table>
	</div>
	</div>
	<script>
	document.getElementById('mDataBtn').addEventListener('click',()=>{
		document.getElementById('mDataForm').style.display= "block";
		document.getElementById('mOrder').style.display= "none";
	});
	document.getElementById('mOrderBtn').addEventListener('click',()=>{
		document.getElementById('mDataForm').style.display= "none";
		document.getElementById('mOrder').style.display= "block";
	});
	</script>
</body>
</html>