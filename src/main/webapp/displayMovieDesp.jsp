<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Movies</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<c:forEach items="${movies}" var="m">
	<div align="center" style="width:600px">
	 <h3>${ m.movieName }</h3><br>
	 <p align="left" > ${ m.movieDescription }</p><br>
	 <img src="${ m.movieImagePath }">
	 <form method="get" action="/WeExpose/SearchShowing">
	 <input name="movieId" value="${m.movieId }" style="display:none">
	 <select name="cinemaId">
		<c:forEach items="${cinemas}" var="c">
			 <option value="${c.cinemaId }" > ${c.cinemaName }</option>
		</c:forEach>
	</select>
	<input type="submit" value="搜尋場次"/>
	</form>
	 
 	</div>
</c:forEach>
</body>
</html>