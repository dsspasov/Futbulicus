<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	
	<c:import url="header.jsp"></c:import>
	<p>Users</p>
	
	<form method="GET" action="/users/search">
		<input name="searchName" type="text"/>
		<input type="submit" value="Search"/>
	</form>
	
	<ul>
		<c:forEach items="${users}" var="user">
			<li>
				<a href="/users/user/${user.id}">${user.username}</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>