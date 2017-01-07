<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<a href="/users">Users</a> |
	<a href="/teams">Teams</a> |
	<a href="/matches">Matches</a> |
	<a href="/footballers">Footballers</a> |
	<c:if test="${user != null}">
		<a href="/logout">Logout</a>
	</c:if>
	
</header>