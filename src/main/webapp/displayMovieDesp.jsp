<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeExpose 電影院</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<div style="margin:20px 20%">
<c:forEach items="${movies}" var="m">
	<div align="center" style="width:600px">
	 <a href="#_${m.movieName }_desp" class="btn btn-info" data-toggle="collapse">${ m.movieName }</a><br>
	 <p align="left" id="_${m.movieName }_desp" class="collapse"> ${ m.movieDescription }</p><br>
	 <img src="${ m.movieImagePath }">
	 <form method="get" action="/WeExpose/SearchShowing">
	 <input name="movieId" value="${m.movieId }" style="display:none">
	 <select name="cinemaId"class="form-select" aria-label="Default select example">
		<c:forEach items="${cinemas}" var="c">
			 <option value="${c.cinemaId }" > ${c.cinemaName }</option>
		</c:forEach>
	</select>
	<input type="submit" value="搜尋場次" class="btn btn-info"/>
	</form>
	 
 	</div>
</c:forEach>
</div>
</body>
</html>