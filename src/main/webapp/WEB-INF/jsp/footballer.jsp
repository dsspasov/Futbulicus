<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	
	<c:out value="${footballer.name}"></c:out><br/>
	<c:out value="${footballer.age}"></c:out><br/>
	<c:out value="${footballer.nationality}"></c:out><br/>
	<c:out value="${footballer.kitNumber}"></c:out><br/>
	<c:out value="${footballer.position}"></c:out><br/>
	<a href="/teams/team/${footballer.team.id}">${footballer.team.name}</a>
</body>
</html>