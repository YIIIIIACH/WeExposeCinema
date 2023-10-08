<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>congratulation you booked successfully</h2>
<!--  cinema theater movie product seatList -->
<div><span>${cinema.cinemaName }</span><span>${theater.theaterName }</span><span>${movie.movieName }</span></div>
<c:forEach items="${seatList}" var="seat">
	<div><span>第${seat.seatRow }排</span><span>第${seat.seatColumn }列</span><span>小計${product.productPricing }</span></div>
</c:forEach>
</body>
</html>