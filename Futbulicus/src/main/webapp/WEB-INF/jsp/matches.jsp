<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matches</title>
</head>
<body>
	<div class="container">
		<c:import url="header.jsp"></c:import>

		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables">Matches</h1>
					</div>
					<div class="bs-component">

						<form action="/matches/search" methos="GET">
							<select name="competition">
								<option value="436" selected>Primera Division</option>
								<option value="438">Serie A</option>
								<option value="434">Ligue 1</option>
								<option value="430">Bundesliga</option>
								<option value="426">EPL</option>
							</select> <input type="date" name="date" value="${date}"> <input
								type="submit" value="Search" />

							<c:if test="${ not empty matches }">
								<c:if test="${matches.size()>0}">
									<table class="table table-striped table-hover ">
										<thead>
											<tr>
												<th>Home Team</th>
												<th></th>
												<th></th>
												<th>Away Team</th>
												<th>Date</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach begin="0" end="${matches.size() - 1}" var="index">
												<tr>
													<c:set var="match"
														value="${matches.get(index).getAsJsonObject()}"></c:set>
													<c:set var="result"
														value='${match.get("result").getAsJsonObject()}'></c:set>
													<c:set var="odds"
														value='${match.get("odds").getAsJsonObject()}'></c:set>

													<td>${match.get("homeTeamName").getAsString()}</td>
													<td>${result.get("goalsHomeTeam").getAsInt()}</td>
													<td>${result.get("goalsAwayTeam").getAsInt()}</td>
													<td>${match.get("awayTeamName").getAsString()}</td>
													<td>${match.get("date").getAsString()}</td>
													<td>${match.get("status").getAsString()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
								<c:if test="${matches.size()==0}">
									<p>No matches</p>
								</c:if>
							</c:if>

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>