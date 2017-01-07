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
						<h1 id="tables">
							<img src='${team.get("crestUrl").getAsString()}' width=50 " />
							${team.get("name").getAsString()}
						</h1>
						<h3>Market Value
							${team.get("squadMarketValue").getAsString()}</h3>
						<h2>Players</h2>
					</div>
					<div class="bs-component">
						<table class="table table-striped table-hover ">
							<thead>
								<tr>
									<th>Kit #</th>
									<th>Name</th>
									<th>Nationality</th>
									<th>Position</th>
									<th>Date of birth</th>
									<th>Market value</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach begin="0" end="${players.size() - 1}" var="index">
									<c:set var="player"
										value="${players.get(index).getAsJsonObject()}"></c:set>
									<c:set var="kitNo" value='${player.get("jerseyNumber")}'></c:set>
									<tr>
										<c:if test="${player.get('jerseyNumber') != 'null'}">
											<td>${player.get("jerseyNumber")}</td>
										</c:if>
										<c:if test="${player.get('jerseyNumber') == 'null'}">
											<td>- |</td>
										</c:if>
										<td>${player.get("name").getAsString()}</td>
										<td>${player.get("nationality").getAsString()}</td>
										<td>${player.get("position").getAsString()}</td>
										<td>${player.get("dateOfBirth").getAsString()}</td>
										<td>${player.get("marketValue").getAsString()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="source-button" class="btn btn-primary btn-xs"
							style="display: none;">&lt; &gt;</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>