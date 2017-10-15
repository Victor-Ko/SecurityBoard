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
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#deleteBtn').click(function(){
			
			$.ajax({
				url : '/member/deleteAction',
				type : 'POST',
				data : {
					id : $('#id').val()
				},
				success : function(data) {
					if(data.result == "Y") { 
						alert("회원탈퇴에 성공하였습니다.");
					}else {
						alert("회원탈퇴에 실패하였습니다.");
					}
					
					// 이동 url이 없을시에는 해당페이지에 머문다.
					if(data.redirectUrl != '') {
						location.href = data.redirectUrl;
					}
				},
				error : function(err) {
					alert('Ajax Error.');
					return;
				}
			});
			
		});
		
	});
</script>
<body>
<div>
	<form>
		<input type="hidden" name="id" id="id" value="${member.id}" />
	</form>
	
	아이디 : ${member.id }<br>
	이름 : ${member.name }
	<div style="text-align: center;">
		<input type="button" value="정보 수정" id="updateBtn" onclick="location.href='/member/update'">
		<input type="button" value="회원 탈퇴" id="deleteBtn">
		<input type="button" value="홈으로" onclick="location.href='/home'">
	</div>
</div>
</body>
</html>