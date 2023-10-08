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
	<div>
	<c:forEach items="${showes}" var="sh" varStatus="s">
		<h4>第${s.count}場次</h4>
		<a href="http://localhost:8080/WeExpose/AvailableSeats?showingId=${sh.showingId }" target="_blank">${sh.showingDatetime}</a>
		<form method="get" action="/WeExpose/SelectSeat">
			<input type="text" style="display:none" value="${sh.showingId }" name="showingId">
			<button type="submit">GO Select Seats</button>
		</form><br>
	</c:forEach>
	</div>
</body>
</html>