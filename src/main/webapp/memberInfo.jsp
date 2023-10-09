<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="struct.MemberOrderInfo,bean.*,java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<%! @SuppressWarnings("unchecked") %>
<meta charset="UTF-8">
<title>會員專區</title>
</head>
<body>
	<div style="padding:20px 100px">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div >
		<form action="/WeExpose/EditMember" method="post">
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
	<div>
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
</body>
</html>