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