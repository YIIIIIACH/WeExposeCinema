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
<style>
  body {
    position: relative;
  }
  ul.nav-pills {
    top: 100px;
    left:70px;
    position: fixed;
  }
  #section1 {color: #fff; background-color: #1E88E5;}
  #section2 {color: #fff; background-color: #673ab7;}
  #section3 {color: #fff; background-color: #ff9800;}
  #section41 {color: #fff; background-color: #00bcd4;}
  #section42 {color: #fff; background-color: #009688;}
  
  @media screen and (max-width: 810px) {
    #section1, #section2, #section3, #section41, #section42  {
      margin-left: 150px;
    }
  }
  </style>
</head>
<body data-spy="scroll" data-target="#myScrollspy" data-offset="10">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
<div class="row"> <!-- style="margin:20px 20%" -->
	<nav class="col-sm-3" id="myScrollspy">
      <ul class="nav nav-pills nav-stacked">
       <li class="active"><a href="#_${movies[0].movieId }">${movies[0].movieName }</a></li>
        <c:forEach items="${movies }" var="m" begin="1">
        	<li><a href="#_${m.movieId }">${m.movieName }</a></li>
        </c:forEach>
      </ul>
    </nav>
    <div class="col-sm-9">
<c:forEach items="${movies}" var="m">
	<div align="center" style="width:600px" id="_${m.movieId }">
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
</div>
</div>
</body>
</html>