<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header align="right" style="margin:20px 0px;background-color: #33D1FF ">
<a href="/WeExpose/DisplayMovieDesp"><span>首頁</span></a>
<span >歡迎</span>
<c:if test="${sessionScope.memberName==null}">訪客</c:if>${sessionScope.memberName}
<c:if test="${sessionScope.memberGrade!=null}">會員等級${sessionScope.memberGrade}</c:if>
<a href="/WeExpose/MemberInfo"><span>會員專區</span></a>
<c:if test="${sessionScope.memberName!=null}">
<a href="/WeExpose/LogOut"><span>登出</span></a>
</c:if>
<c:if test="${sessionScope.memberName==null}">
<a href="/WeExpose/register.jsp"><span>註冊會員</span></a>
</c:if>
</header>