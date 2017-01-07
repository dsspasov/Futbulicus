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
	<div class="container">
		<c:import url="header.jsp"></c:import>

		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables">${leagueCaption} Table</h1>
					</div>
					<div class="bs-component">
						<table class="table table-striped table-hover ">
							<thead>
								<tr>
									<th>#</th>
									<th>Team</th>
									<th>GP</th>
									<th>W</th>
									<th>D</th>
									<th>L</th>
									<th>GF</th>
									<th>GA</th>
									<th>GD</th>
									<th>PTS</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="0" end="${teams.size() - 1}" var="index">
									<c:set var="team" value="${teams.get(index).getAsJsonObject()}"></c:set>
									<c:set var="team_href"
										value='${team.get("_links").getAsJsonObject().get("team").getAsJsonObject().get("href").getAsString()}'></c:set>
									<tr
										class="${team.get('position').getAsInt() < 4 ? 'info' : ''} ${team.get('position').getAsInt() > 17 ? 'danger' : ''}">
										<td>${team.get("position").getAsInt()}</td>
										<td><a
											href='/teams/team/${team_href.substring(team_href.lastIndexOf("/") + 1)}'><img
												src='${team.get("crestURI").getAsString()}' width="14"
												height="20" /> ${team.get("teamName").getAsString()}</a></td>
										<td>${team.get("playedGames").getAsInt()}</td>
										<td>${team.get("wins").getAsInt()}</td>
										<td>${team.get("draws").getAsInt()}</td>
										<td>${team.get("losses").getAsInt()}</td>
										<td>${team.get("goals").getAsInt()}</td>
										<td>${team.get("goalsAgainst").getAsInt()}</td>
										<td>${team.get("goalDifference").getAsInt()}</td>
										<td>${team.get("points").getAsInt()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="source-button" class="btn btn-primary btn-xs"
							style="display: none;">&lt; &gt;</div>
					</div>
					<!-- /example -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>