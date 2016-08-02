<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<title>Welcome page</title>
	<script src="resources/bower_components/materialize/dist/css/materialize.css"></script>
	<script src="resources/bower_components/angular/angular.js"></script>
	<script src="resources/js/controllers.js"></script>
</head>
<body>
	<h1>Hi! ${message}</h1>
	<div ng-controller="TestCtrl">
		<p>Check: {{test}} </p>
	</div>
	<c:if test="${user != 'anonymousUser' && user != 'not authenticated'}">
		<div class="alert alert-success">
			<p>
				You have been logged in as <strong>${user}</strong>.
				<a href="<c:url value="/logout" />">Logout</a>
			</p>
		</div>
	</c:if>
</body>
</html>
