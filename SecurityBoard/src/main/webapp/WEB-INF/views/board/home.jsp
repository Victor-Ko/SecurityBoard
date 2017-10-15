<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시판</title>
</head>
<body>
어서오십시오<br>
${member }<br>
<a href="/member/mypage">내정보 보기</a><br>
<a href="/member/logout">로그아웃</a>
</body>
</html>