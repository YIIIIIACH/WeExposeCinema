<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<select name="theaterId">
<option value="1">theater_1</option>
<option value="2">theater_2</option>
</select>
<label>日期</label>
<input type="date" name="searchDate">
<label>時間</label>
<input type="time" name="searchTime">
</fieldset>
<button type="submit">搜尋</button>
</form>
<label>theaterName</label>
<table>
<thead><tr><td>場次<td>日期<td>時間<td>播映電影</thead>
</table>
</div>
</body>
</html>