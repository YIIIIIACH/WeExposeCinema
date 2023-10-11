<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,bean.*,java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<title><c:out value="WeEpose ${cinemaName}場次" default="WeExpose 電影院場次"/></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%! @SuppressWarnings("deprecation")%> 
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div style="padding:50px 15%">
	<form action="SearchShowing" method="get" style="clear:right;width:100%">
	<input type="text" style="display:none" name="movieId" value="${ movieId }">
	<input type="text" style="display:none" name="cinemaId" value="${ cinemaId }">
	<fieldset style="clear:right;width:100%">
	<div class="input-group" style="clear:right;width:100%;padding-bottom:20px">
		<i class="input-group-addon glyphicon glyphicon-user" style="width:20%">放映日期</i>
		<input name="selectDate" class="form-control" type="date" style="width:60%" value="${selectDate }"/><input type="submit" class="btn btn-default" value="搜尋" style="width:20%;height:100%" >
	</div>
	</fieldset>
	</form>
	<div style="display:flex;flex-direction: row;flex-wrap:wrap">
	<div style="margin:0px 0px;width:100%" ><span style="font-size:30px">${cinemaName}</span></div>
	<% 
	String formatStr= "yyyy-MM-dd"; 
	int idx= 0;
	List<TheaterBean> tbList = new ArrayList<TheaterBean>();
	if( request.getAttribute("theaters")!=null){
		tbList = (List<TheaterBean>)request.getAttribute("theaters");
	}
	%>
	<% for( ShowingBean sh : (List<ShowingBean>)request.getAttribute("showes")){ %>
		<div style="background-color:#BBE7F6;padding:0px;height:110px;font-weight:double;margin:16px 16px;width:185px;text-align:center;border:5px solid #8DB4BF;border-radius:8px" >
		<% String tStr= (tbList.size()>0)?tbList.get(idx++).getTheaterName():""; %>
		<h5 ><%=tStr%></h5>
		<a href="/WeExpose/AvailableSeats?showingId=<%=sh.getShowingId() %>" style="color:black;font-weight:bold" target="_blank"><%= sh.getShowingDatetimeFormated() %></a>
		<form method="get" action="/WeExpose/SelectSeat">
			<input type="text" style="display:none" value="<%=sh.getShowingId() %>" name="showingId">
			<% 
			Date d = sh.getShowingDatetime();
			if( d.compareTo(new Date())>=0 ) {
			%>
				<button type="submit" class="btn btn-primary" style="background-color:#518A9D">去訂位</button>
			<%}else{ %>
				<button type="submit" disabled class="btn btn-primary"style="background-color:#66A4B8">去訂位</button>
			<%} %>
		</form><br>
		</div>
	<%} %>
	</div>
	</div>
</body>
</html>