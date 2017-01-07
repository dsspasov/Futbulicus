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
	<p>Matches</p>
		
	<form action="/matches/search" methos="GET">
		<select name="competition">
			<option value="436" selected>Primera Division</option>
			<option value="438">Serie A</option>
			<option value="434">Ligue 1</option>
			<option value="430">Bundesliga</option>
			<option value="426">EPL</option>
		</select>
		<input type="date" name="date" value="${date}">
		<input type="submit" value="Search"/>
		
		<c:if test="${ not empty matches }">
			<c:if test="${matches.size()>0}">
				<table>
					<tr>
						<th>Status</th>
						<th>Home team</th>
						<th>Away team</th>
					</tr>
					<c:forEach begin="0" end="${matches.size() - 1}" var="index">
					<tr>
					<c:set var="match" value="${matches.get(index).getAsJsonObject()}"></c:set>
						<td>${match.get("status").getAsString()}|</td>
						<td>${match.get("homeTeamName").getAsString()}|</td>
						<td>${match.get("awayTeamName").getAsString()}|</td>
						<c:set var="odds" value='${match.get("odds").getAsJsonObject()}'></c:set>
						<td>
						
							<span>homeWin: </span> ${odds.get("homeWin").getAsString()}
							<span>draw: </span> ${odds.get("draw").getAsString()}
							<span>awayWin: </span> ${odds.get("awayWin").getAsString()}
						</td>
					</tr>		
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${matches.size()==0}">
				<p>No matches</p>
			</c:if>
		</c:if>
		
	</form>
	
</body>
</html>