<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
x
</header>

<div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="../home" class="navbar-brand">Futbulicus</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" href="#" id="themes">Leagues <span class="caret"></span></a>
              <ul class="dropdown-menu" aria-labelledby="themes">
                <li><a href="/standings/436">Primera Division</a></li>
				<li><a href="/standings/438">Serie A</a></li>
				<li><a href="/standings/434">Ligue 1</a></li>
				<li><a href="/standings/430">Bundesliga</a></li>
				<li><a href="/standings/426">EPL</a></li>					
              </ul>
            </li>
            <li>
				<a href="/teams">Teams</a>
			</li>
            <li>
				<a href="/matches">Matches</a>
			</li>
            <li>
				<a href="/footballers">Footballers</a>
			</li>
            <li>
				<c:if test="${user != null}">
					<a href="/logout">Logout</a>
				</c:if>
			</li>
          </ul>
        </div>
      </div>
    </div>