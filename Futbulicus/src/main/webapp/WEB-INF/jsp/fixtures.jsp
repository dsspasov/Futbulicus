<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach begin="0" end="${fixtures.size() - 1}" var="index">
		<c:set var="fixture" value="${fixtures.get(index).getAsJsonObject()}" />
		<c:if test="${index == 0}">
			<c:out value="Match day ${fixture.get('matchday')}"></c:out>
		</c:if>
		<c:if
			test="${index != 0 && fixture.get('matchday') != fixtures.get(index - 1).getAsJsonObject().get('matchday')}">
			<br />
			<c:out value="Match day ${fixture.get('matchday').getAsString()}" />
		</c:if>
		<table>
			<tr>
				<td>${fixture.get("homeTeamName")}</td>
				<td>${fixture.get("result").getAsJsonObject().get("goalsHomeTeam")}</td>
				<td>${fixture.get("result").getAsJsonObject().get("goalsAwayTeam")}</td>
				<td>${fixture.get("awayTeamName")}</td>
			</tr>
			<tr>
				<td>${fixture.get("date")}</td>
			</tr>
			<tr>
				<td>${fixture.get("status")}</td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>