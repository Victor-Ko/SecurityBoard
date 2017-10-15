<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>내정보 수정</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#updateForm").submit(function(){
		if($("#pw").val() == ''){
			alert('비밀번호를 입력하세요');
			$("#pw").focus();
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
	<form action="updateAction" id="updateForm" method="post">
		<input type="hidden" id="id" name="id" value="${member.id }">
		<ul>
			<li>비밀번호 : <input type="password" id="pw" name="pw"></li>
			<li><input type="submit" value="정보 수정"></li>
		</ul>
	</form>
</div>
</body>
</html>