<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello world</title>
</head>
<body>
	
	<c:import url="header.jsp"></c:import>
	<p>Footballers</p>
	<ul>
		<c:forEach items="${footballers}" var="footballer">
			<li><a href="/footballers/footballer/${footballer.id}"> <c:out
						value="${footballer.name}"></c:out>
			</a></li>
		</c:forEach>
	</ul>
</body>
</html>