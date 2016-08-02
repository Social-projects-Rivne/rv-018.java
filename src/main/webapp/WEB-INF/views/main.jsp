<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
<title>Welcome page</title>
<script
	src="resources/bower_components/materialize/dist/css/materialize.css"></script>
</head>
<body>
	<h1>Hi! ${message}</h1>
	<div ng-controller="TestCtrl">
		<p>Check: {{test}}</p>
	</div>
	<c:choose>
		<c:when test="${user != null && user != 'anonymousUser' && user != 'not authenticated'}">
        	<p>
				You have been logged in as <strong>${user}</strong>. 
				<a href="<c:url value="/logout" />">Logout</a>
			</p>
        <br/>
		</c:when>
		<c:otherwise>
        	<p> 
				Press <a href="<c:url value="/login" />">login</a> to login
			</p>
        <br/>
		</c:otherwise>
	</c:choose>
	
	<script src="resources/bower_components/angular/angular.js"></script>
	<script src="resources/js/controllers.js"></script>
</body>
</html>
