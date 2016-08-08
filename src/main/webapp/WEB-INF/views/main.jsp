<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<title>Welcome page</title>
	<link rel="stylesheet" href="/resources/bower_components/materialize/dist/css/materialize.css">
</head>
<body>
	<h1>Hi! ${message}</h1>
	<h4>New angular code:</h4>
	<a href="#/profile">Edit profile</a>
	
	<ng-view></ng-view>
	
	<br>
	<br>
	<h4>Old jsp code:</h4>
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
	<script src="resources/bower_components/angular-route/angular-route.js"></script>
	<script src="resources/bower_components/angular-resource/angular-resource.js"></script>
	
	<script src="resources/app/module.js"></script>
	<script src="resources/app/routes.js"></script>
	<script src="resources/app/controller.js"></script>
	
	<script src="resources/app/login/login.component.js"></script>
	<script src="resources/app/components/profile/profile.component.js"></script>
	<script src="resources/app/components/user/user.component.js"></script>
</body>
</html>
