<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Admin Page</h1>

<p><sec:authentication property="principal.member.username"/></p>
<p>MemberVO : <sec:authentication property="principal.member"/></p>
</body>
</html>