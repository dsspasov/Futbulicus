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
	<h4>Club</h4>
	<table>
		<tr>
			<td><img src='${team.get("crestUrl").getAsString()}' width="50" height="80" />Name:${team.get("name").getAsString()}</td>
			<td>Value: ${team.get("squadMarketValue").getAsString()}</td>
		</tr>
	</table>
	<br/>
	<h4>Players</h4>
	<table>
		<tr>
			<td>Kit # |</td>
			<td>Name |</td>
			<td>Nationality |</td>
			<td>Position |</td>
			<td>Date of birth |</td>
			<td>Market value |</td>
		</tr>
	</table>
	<c:forEach begin="0" end="${players.size() - 1}" var="index">
		<c:set var="player" value="${players.get(index).getAsJsonObject()}"></c:set>
		<table>
			<tr>
				<td>${player.get("jerseyNumber").getAsString()} |</td>
				<td>${player.get("name").getAsString()} |</td>
				<td>${player.get("nationality").getAsString()} |</td>
				<td>${player.get("position").getAsString()} |</td>
				<td>${player.get("dateOfBirth").getAsString()} |</td>
				<td>${player.get("marketValue").getAsString()} |</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>