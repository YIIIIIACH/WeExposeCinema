<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
</style>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/WeExpose/DisplayMovieDesp" style="padding: 6px 10px"><img src="image/weExposeLogo_bw.png" width="37" height="40" style="margin:0px 0px"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a><span>WeExpose 電影院</span></a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="/WeExpose/MemberInfo"><span>會員專區</span></a>
        <li >
        	<a>
        	<span>歡迎</span>
			<c:if test="${sessionScope.memberName==null}">訪客</c:if>${sessionScope.memberName}
			</a>
		</li>
		<c:if test="${sessionScope.memberGrade!=null}">
			<li><a><span>會員等級${sessionScope.memberGrade}</span></a></li>
		</c:if>
        <c:if test="${sessionScope.memberName==null}">
        	<li><a href="/WeExpose/register.jsp"><span>註冊會員</span></a></li>
		</c:if>
        <c:if test="${sessionScope.memberName!=null}">
        	<li>
	        	<a href="/WeExpose/LogOut">
				<span class="glyphicon glyphicon-log-in"></span> 登出</a>
				</a>
			</li>
		</c:if>
      </ul>
    </div>
  </div>
</nav>