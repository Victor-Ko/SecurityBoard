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
<a href="/member/mypage">내정보 보기</a><br>
<a href="/member/logout">로그아웃</a>

<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
		<th>등록일</th>
	</tr>
	<c:forEach items="${list}" var="vo">
	<tr>
		<td>${vo.board_seq }</td>
		<td>${vo.board_title }</td>
		<td>${vo.user_id }</td>
		<td>${vo.board_views }</td>
		<td>${vo.board_reg_date }</td>
		<td>${vo}</td>
	</tr>
	</c:forEach>
</table>

<div>${blockSize}</div>

</body>
</html>