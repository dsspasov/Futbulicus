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

		<div class="bs-docs-section" style="margin-top:50px;">

			<div class="row">
				<div class="col-lg-12">
					
					
					<ul class="nav nav-tabs">
					    <li class="${panel == 'match'? 'active' : ''}"><a data-toggle="tab" href="#match">Search By Date</a></li>
					    <li class="${panel == 'fixture'?'active' : ''}"><a data-toggle="tab" href="#fixture">Search by Team name</a></li>
					</ul>


					<div class="tab-content">
						<div id="match" class="tab-pane fade ${panel == 'match'? 'in active' : ''}">
							<div class="page-header">
								<h1 id="tables">Matches</h1>
							</div>
							
							<form action="/matches/search-by-date" methos="GET">
								<select name="competition">
									<option value="436" selected>Primera Division</option>
									<option value="438">Serie A</option>
									<option value="434">Ligue 1</option>
									<option value="430">Bundesliga</option>
									<option value="426">EPL</option>
								</select> 
								<input type="date" name="date" value="${date}"> 
								<input type="submit" value="Search" />
							</form>
							
							<div class="bs-component">
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
								</div>
						</div>
						
						<div id="fixture" class="tab-pane fade ${panel == 'fixture'? 'in active' : ''}">
							<div class="page-header">
								<h1 id="tables">Fixtures</h1>
							</div>
							
							<form method="GET" action="/matches/search-by-team">
								<input name="name" type="text" /> 
								<input type="submit" value="Search" />
							</form>
							
							<div class="bs-component">
								<table class="table table-striped table-hover ">
									<thead>
										<tr>
											<th>Home Team</th>
											<th></th>
											<th></th>
											<th>Away Team</th>
											<th>Date</th>
											<th>Status</th>
											<th>Match Day</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${not empty fixtures}">
											<c:forEach items="${fixtures}" var="fixture">
												<tr>
													<td>${fixture.homeTeamName}</td>
													<td>${fixture.resultGoalsHomeTeam}</td>
													<td>${fixture.resultGoalsAwayTeam}</td>
													<td>${fixture.awayTeamName}</td>
													<td>${fixture.date}</td>
													<td>${fixture.status}</td>
													<td>${fixture.matchday}</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>