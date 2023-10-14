<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台 場次管理系統</title>
</head>
<body>
<div>
<form action="/WeExpose/SearchShowingBnd" method="get">
<fieldset>
<legend>場次搜尋</legend>
<label>theater</label>
<select name="movieId">
<c:forEach items="${allMovies }" var="m">
<option value="${m.movieId }">
${m.movieName }</option>
</c:forEach>
</select>
<select name="theaterId">
<c:forEach items="${allTheaters }" var="t">
<option value="${t.theaterId }">
${t.theaterName }
</option>
</c:forEach>
</select>
<label>日期</label>
<input type="date" name="searchDate" value="${dateStr }">
<label>時間</label>
<input type="time" name="searchTime" value="${timeStr }">
</fieldset>
<button type="submit">搜尋</button>
</form>
<label>theaterName</label>
<table style="border:3px solid black" border="3">
<% 
int i=0;
List<String> movieNameList = (List<String>)request.getAttribute("movieNameList");
%>
<thead><tr><td>場次<td>日期<td>時間<td>播映電影</thead>
<c:forEach items="${stEnds }" var="t">
	<tr><td>xx<td>${t[0] }<td>${t[1] }<td><%=movieNameList.get(i) %>
	<% i++; %>
</c:forEach>
</table>
</div>
</body>
</html>