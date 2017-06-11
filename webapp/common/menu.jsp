<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
	<title>menu</title>
</head>
<body>
	<div class="navbar navbar-default" id="subnav">
	    <div class="col-md-12">
	        <div class="navbar-header">
	            <a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home <small><i class="glyphicon glyphicon-chevron-down"></i></small></a>
	            <ul class="nav dropdown-menu">
	                <li><a href="/user/profile.jsp"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> Profile</a></li>
	                <li class="nav-divider"></li>
	                <li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>
	            </ul>
	        </div>
	        <div class="collapse navbar-collapse" id="navbar-collapse2">
	            <ul class="nav navbar-nav navbar-right">
	                <li class="active"><a href="../index.jsp">Posts</a></li>
<c:if test="${not empty sessionScope.user}">
	                <li><a href="/user/logout" role="button">로그아웃</a></li>
	                <li><a href="/user/updateForm" role="button">개인정보수정</a></li>
</c:if>
<c:if test="${empty sessionScope.user}">
		            <li><a href="../user/login.jsp" role="button">로그인</a></li>
	                <li><a href="../user/form.jsp" role="button">회원가입</a></li>
</c:if>
	                <!--
	                <li><a href="#loginModal" role="button" data-toggle="modal">ë¡ê·¸ì¸</a></li>
	                <li><a href="#registerModal" role="button" data-toggle="modal">íìê°ì</a></li>
	                -->
	            </ul>
	        </div>
	    </div>
	</div>
</body>
</html>