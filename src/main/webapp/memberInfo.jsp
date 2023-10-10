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
		<legend ><label style="margin:25px auto 10px">會員資料</label></legend>
		<c:if test="${ requestScope.message !=null }">${requestScope.message }</c:if>
		<div class="input-group">
			<span class="input-group-addon glyphicon glyphicon-user" > 帳號</span>
			<input type="text" class="form-control" name="account" value="${mb.memberAccount }">
		</div><br>
		<div class="input-group">
			<span class=" input-group-addon glyphicon glyphicon-lock" > 密碼</span>
			<input type="password" class="form-control"  name="password">
		</div><br>
		<div class="input-group">
			<span class="input-group-addon glyphicon glyphicon-sunglasses" > 姓名</span>
			<input type="text" class="form-control" name="memberName" value="${mb.memberName }">
		</div><br>
		<div class="input-group">
			<span class=" input-group-addon glyphicon glyphicon-king" > 等級</span>
			<input type="text" class="form-control" name="memberGrade" disabled value="${mb.memberGrade }">
		</div>
		<div class="button-group" style="margin:20px 75% 0px 0px">
			<button type="submit" class="btn btn-primary" style="width:100%">修改</button>		
		</div>
		</fieldset>
		</form>
	</div>
	<div id="mOrder" style="display:none">
	<h2>${mb.memberName }的購買紀錄</h2>
	<% int subtotalCnt=0; 
		int cnt=0;%>
	<!--  movieName cinemaName theaterName showingDatetime showintTypeName  -->
	<% for( MemberOrderInfo of: (List<MemberOrderInfo>)request.getAttribute("orderInfoes") ){
		%>
		<div><a href="#_<%=cnt %>_seats" data-toggle="collapse"><label><%= of.movie.getMovieName() %><%= of.theater.getTheaterName()%><%= of.showing.getShowingDatetime() %><%= of.showingType.getShowingTypeName() %></label></a></div>
		<table id="_<%=cnt %>_seats" class="collapse in">
		<% subtotalCnt=0; 
		for( int i=0; i< of.seats.size() ; i++){%>
		<tr><td><td>第<%=of.seats.get(i).getSeatRow() %>排<td>第<%=of.seats.get(i).getSeatColumn() %>位<td>單票價格<%= of.productServices.get(i).getProductServicePrice() %> 
		<% subtotalCnt += of.productServices.get(i).getProductServicePrice();
		}%>
		</table>
		<div align="right"><label align="right">小計<%= subtotalCnt %>元</label></div>
	<% 
	cnt++;
	}%>

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