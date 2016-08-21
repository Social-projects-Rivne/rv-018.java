<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<title>Welcome page</title>
	<script type="text/javascript">
		var _contextPath = "${pageContext.request.contextPath}";
	</script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/materialize.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/map.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/loader.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/place.css">
 </head>
<body>
	
<h1>Hi! ${message}</h1>
	<h4>New angular code:</h4>
	<a href="#/profile">Edit profile</a><br>
	
	<a href="#/map">Map component</a>
	
	<div class="progress" ng-controller="markerCtrl" ng-show="progressBarVision">
      <div class="indeterminate"></div>
	</div>

	<ng-view></ng-view>

	<br>
	<br>
	<h4>Old jsp code:</h4>
	<c:choose>
		<c:when
			test="${user != null && user != 'anonymousUser' && user != 'not authenticated'}">
			<p>
				You have been logged in as <strong>${user}</strong>. <a
					href="<c:url value="/logout" />">Logout</a>
			</p>
			<br />
		</c:when>
		<c:otherwise>
			<p>
				Press <a href="<c:url value="/login" />">login</a> to login
			</p>
			<br />
		</c:otherwise>
	</c:choose>

 	<script src="${pageContext.request.contextPath}/resources/plugins/jquery.min.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/plugins/materialize.min.js"></script>
	<!-- Leaf map js library -->
	<script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
	<!-- Bower components -->
	<script src="${pageContext.request.contextPath}/resources/plugins/angular.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/angular-route.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/angular-resource.min.js"></script>
	<!-- Core scripts -->
	<script src="${pageContext.request.contextPath}/resources/app/module.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/routes.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/controller.js"></script>
	<!-- Map component -->
	<script src="${pageContext.request.contextPath}/resources/app/components/map/map.component.js"></script>
	<!-- Login component -->
	<script src="${pageContext.request.contextPath}/resources/app/login/login.component.js"></script>
	<!-- Profile component -->
 	<script src="${pageContext.request.contextPath}/resources/app/components/profile/profile.component.js"></script>
	<!--  User component -->
	<script src="${pageContext.request.contextPath}/resources/app/components/user/user.component.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/components/place/place.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/marker.controller.js"></script>
 </body>
</html>
