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
	$("#insertBtn").click(function(){
		if($("#title").val() == ''){
			alert('제목을 입력하세요');
			$("#title").focus();
			return;
		}
		if($("#contents").val() == ''){
			alert('내용을 입력하세요');
			$("#contents").focus();
			return;
		}
		
		$.ajax({
			url:'/board/insertAction',
			type:'post',
			data:$("#insertForm").serialize(),
			success:function(data){
				if(data.result == 'Y'){
					alert(data.message);
					location.href = data.redirectUrl;
				}else if(data.result='N'){
					alert(data.message);
					location.href = data.redirectUrl;
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
<style type="text/css">
li{
	list-style: none;
}
</style>
</head>
<body>
<div>
	<form action="insertAction" method="post" id="insertForm">
		<ul>
			<li>제목 : <input type="text" id="title" name="board_title"></li>
			<li>내용 : <input type="text" id="contents" name="board_contents"></li>
			<li><input type="button" value="게시글 등록" id="insertBtn"></li>
		</ul>
	</form>
</div>
</body>
</html>