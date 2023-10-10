<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.*"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<title><c:out value="WeEpose ${cinemaName}場次" default="WeExpose 電影院場次"/></title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="padding:50px 20%">
	<div style="display:flex;flex-direction: row;flex-wrap:wrap">
	<form action="SearchShowing" method="get"><input type="text" style="display:none" name="movieId" value="${ movieId }">
	<input type="text" style="display:none" name="cinemaId" value="${ cinemaId }">
	<label>搜索放映日期</label><input name="selectDate" type="date"><label>場次類型</label>
	<select name="selectShowingType"><option value="none">NONE</option></select><button type="submit">搜索</button></form>
	<div style="margin:0px 0px;width:100%" ><span style="font-size:30px">${cinemaName}</span></div>
	<c:forEach items="${showes}" var="sh" varStatus="s">
		<div style="background-color:pink;margin:20px 20px;width:180px;text-align:center" >
		<h4>第${s.count}場次</h4>
		<a href="http://localhost:8080/WeExpose/AvailableSeats?showingId=${sh.showingId }" target="_blank">${sh.showingDatetime}</a>
		<form method="get" action="/WeExpose/SelectSeat">
			<input type="text" style="display:none" value="${sh.showingId }" name="showingId">
			<button type="submit">去訂位</button>
		</form><br>
		</div>
	</c:forEach>
	</div>
	</div>
</body>
</html>