<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>내 정보 보기</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<div>
	아이디 : ${member.id }<br>
	비밀번호 : ${member.pw }<br>
	이름 : ${member.name }
	<div style="text-align: center;">
		<input type="button" value="정보 수정" id="updateBtn" onclick="location.href='/member/update'">
		<input type="button" value="회원 탈퇴" id="deleteBtn">
	</div>
</div>
</body>
</html>