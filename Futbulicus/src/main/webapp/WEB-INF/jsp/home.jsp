<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<p>Home</p>
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
		<table>
			<tr>
				<td>${team.get("position").getAsInt()}|</td>
				<td><a href='${team.get("_links").getAsJsonObject().get("team").getAsJsonObject().get("href").getAsString()}'>${team.get("teamName").getAsString()}</a>|</td>
				<td><img
					src='${team.get("crestURI").getAsString()}'
					width="14" height="20" />|</td>
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
	<div id="disqus_thread"></div>
	<script>
		var disqus_config = function() {
			// The generated payload which authenticates users with Disqus
			this.page.remote_auth_s3 = 'c0660c8924a67ba22260b4960e209da78cd0a29c';
			this.page.api_key = 'YLyDKkEyE4ezVawHoTDsyssEfAMxJEV2PHEIpOyH42PYVAg3DGGTT9YpmzM0iyvZ';
		}
		/**
		 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
		 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
		var disqus_config = function() {
			this.page.url = PAGE_URL; // Replace PAGE_URL with your page's canonical URL variable
			this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
		};
		(function() { // DON'T EDIT BELOW THIS LINE
			var d = document, s = d.createElement('script');
			s.src = '//futbulicus.disqus.com/embed.js';
			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
	<noscript>
		Please enable JavaScript to view the <a
			href="https://disqus.com/?ref_noscript#disqus_thread">comments
			powered by Disqus.</a>
	</noscript>
	<script id="dsq-count-scr" src="//futbulicus.disqus.com/count.js" async></script>
</body>
</html>