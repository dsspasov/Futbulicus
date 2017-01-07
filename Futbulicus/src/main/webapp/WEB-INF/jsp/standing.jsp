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
	<h2>${leagueCaption}</h2>
	<table>
		<tr>
			<td>position</td>
			<td>team name</td>
			<td>logo</td>
			<td>played games</td>
			<td>points</td>
			<td>goals</td>
			<td>goals against</td>
			<td>goal difference</td>
			<td>wins</td>
			<td>draws</td>
			<td>losses</td>
		</tr>
	</table>
	<c:forEach begin="0" end="${teams.size() - 1}" var="index">
		<c:set var="team" value="${teams.get(index).getAsJsonObject()}"></c:set>
		<c:set var="team_href"
			value='${team.get("_links").getAsJsonObject().get("team").getAsJsonObject().get("href").getAsString()}'></c:set>
		<table>
			<tr>
				<td>${team.get("position").getAsInt()}|</td>
				<td><a
					href='/teams/team/${team_href.substring(team_href.lastIndexOf("/") + 1)}'>${team.get("teamName").getAsString()}</a>|</td>
				<td><img src='${team.get("crestURI").getAsString()}' width="14"
					height="20" />|</td>
				<td>${team.get("playedGames").getAsInt()}|</td>
				<td>${team.get("points").getAsInt()}|</td>
				<td>${team.get("goals").getAsInt()}|</td>
				<td>${team.get("goalsAgainst").getAsInt()}|</td>
				<td>${team.get("goalDifference").getAsInt()}|</td>
				<td>${team.get("wins").getAsInt()}|</td>
				<td>${team.get("draws").getAsInt()}|</td>
				<td>${team.get("losses").getAsInt()}|</td>
			</tr>
		</table>
		<br />
	</c:forEach>
	
	<a href="/fixtures/${leagueId}">Fixtures</a>

	
</body>
</html>