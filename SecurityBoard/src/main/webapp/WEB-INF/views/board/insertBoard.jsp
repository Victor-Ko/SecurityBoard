<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>게시글 등록</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#insertForm").submit(function(){
		if($("#title").val() == ''){
			alert('제목을 입력하세요');
			$("#title").focus();
			return false;
		}
		if($("#contents").val() == ''){
			alert('내용을 입력하세요');
			$("#contents").focus();
			return false;
		}
		
		$.ajax({
			url:'board/insertAction',
			type:'post',
			data:$("#insertForm").serialize(),
			success:function(data){
				if(data.result == 'Y'){
					alert(data.message);
					loaction.href = data.redirectUrl;
				}else{
					alert(data.message);
					loaction.href = data.redirectUrl;
				}
			},
			error:function(){
				alert('네트워크 오류');
				location.href='/board/list';
			}
		});
	});
});
</script>
</head>
<body>
<div>
	<form action="insertAction" method="post" id="insertForm">
		<ul>
			<li>제목 : <input type="text" id="title" name="title"></li>
			<li>내용 : <input type="text" id="contents" name="contents"></li>
		</ul>
	</form>
</div>
</body>
</html>