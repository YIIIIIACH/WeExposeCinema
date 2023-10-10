<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking Success</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style="margin:20px 20%" align="center">

<h2>訂位成功</h2>
<!--  cinema theater movie product seatList -->
<table>
<tr><td><span>${cinema.cinemaName }</span><td><span>${theater.theaterName }</span><td><span>${movie.movieName }</span>
<td><form action="/WeExpose/CallOfOrder" method="get"><input type="text" name="orderId" value="${orderId }" style="display:none"><button type="submit">取消交易</button></form>
<c:forEach items="${seatList}" var="seat">
	<tr align="right"><td><span>第${seat.seatRow }排</span><td><span>第${seat.seatColumn }列</span><td><span>小計${product.productPricing }</span>
</c:forEach>
</table>
<a href="http://localhost:8080/WeExpose/DisplayMovieDesp"><div width="80" height="40"><span>回首頁</span></div></a>
<a href="http://localhost:8080/WeExpose/MemberInfo"><div width="80" height="40"><span>會員專區</span></div></a>
</div>
</body>
</html>