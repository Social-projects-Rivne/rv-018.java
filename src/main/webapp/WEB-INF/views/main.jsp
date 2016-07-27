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
	<c:if test="${param.login != null}">
		<div class="alert alert-success">
			<p>You have been logged in ass <strong>${user}</strong>.</p>
			<a href="<c:url value="/logout" />">Logout</a>
		</div>
	</c:if>
	
	<h1>Hi! ${message}</h1>
	<div ng-controller="TestCtrl">
		<p>Check: {{test}} </p>
	</div>
</body>
</html>