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
	<div class="container">
		<c:import url="header.jsp"></c:import>


		<div class="bs-docs-section">

			<div class="row">
				<div class="col-lg-12">
					<div class="page-header">
						<h1 id="tables">${leagueCaption}Fixtures</h1>
					</div>
					<div class="bs-component">
						<table class="table table-striped table-hover ">
							<thead>
								<tr>
									<th>Home Team</th>
									<th>Result</th>
									<th></th>
									<th>Away Team</th>
									<th>Date</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="0" end="${fixtures.size() - 1}" var="index">
									<c:set var="fixture"
										value="${fixtures.get(index).getAsJsonObject()}" />
									<c:if
										test="${index == 0 || fixture.get('matchday') != fixtures.get(index - 1).getAsJsonObject().get('matchday')}">
										<tr class="info">
											<td><h3>
													<c:out
														value="Match day ${fixture.get('matchday').getAsString()}" />
												</h3></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr></tr>
									</c:if>
									<tr>
										<td>${fixture.get("homeTeamName")}</td>
										<td>${fixture.get("result").getAsJsonObject().get("goalsHomeTeam")}</td>
										<td>${fixture.get("result").getAsJsonObject().get("goalsAwayTeam")}</td>
										<td>${fixture.get("awayTeamName")}</td>
										<td>${fixture.get("date")}</td>
										<td>${fixture.get("status")}</td>
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