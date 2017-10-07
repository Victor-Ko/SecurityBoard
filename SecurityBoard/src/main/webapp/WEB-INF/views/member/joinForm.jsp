<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#joinForm").submit(function(){
		if($("#id").val() == ''){
			alert('아이디를 입력하세요');
			$("#id").focus();
			return false;
		}
		if($("#pw").val() == ''){
			alert('비밀번호를 입력하세요');
			$("#pw").focus();
			return false;
		}
		if($("#name").val() == ''){
			alert('이름을 입력하세요');
			$("#name").focus();
			return false;
		}
	});
});
</script>
<style type="text/css">
li{
	list-style: none;
}
</style>
</head>
<body>
<div>
	<form action="/joinActoin" id="joinForm" method="post">
		<ul>
			<li>아이디 : <input type="text" id="id" name="id"></li>
			<li>비밀번호 : <input type="password" id="pw" name="pw"></li>
			<li>이름 : <input type="text" id="name" name="name"></li>
			<li><input type="submit" value="회원가입"></li>
		</ul>
	</form>
</div>
</body>
</html>