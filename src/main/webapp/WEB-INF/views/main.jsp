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
	<h1>message: ${message }</h1>
	<div ng-controller="TestCtrl">
		<p>Check: {{test}} </p>
		<p>1 + 8 = {{1 + 8}} </p>
	</div>
</body>
</html>
