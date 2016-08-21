<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<title>Welcome page</title>
	<script type="text/javascript">
		var _contextPath = "${pageContext.request.contextPath}";
	</script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/materialize/dist/css/materialize.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/map.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/loader.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/place.css">
 </head>
<body>

 	<nav ng-controller="sideNavMenuController">
		<div id="header" class="nav-wrapper green darken-2">
  
		<a href="#" data-activates="side-menu" class="sideNavMenuButton"><i class="material-icons">menu</i></a>
  
	    <ul class="side-nav" id="side-menu" ng-style="{top: topMarginValue}">
	      <li><a href="about.html"><i class="material-icons">home</i>Home</a></li>
	      <li><a href="#/map"><i class="material-icons">map</i>Map</a></li>
	      <li><a href="terms.html"><i class="material-icons">event</i>Events calendar</a></li>
	      <li><a href="terms.html"><i class="material-icons">account_circle</i>Profile</a></li>
	    </ul>
  
		</div>
	</nav>
	
	<div id="tabsRow" class="green darken-2">
		<div style="margin-left: 35%; margin-right: 35%">
			<div class="row">
				<ul class="tabs">
	        		<li class="tab col m3 green darken-2"><a class="active white-text" href="#test1">Places</a></li>
	        		<li class="tab col m3 green darken-2"><a class="white-text" href="#test2">Tracks</a></li>
	        		<li class="tab col m3 green darken-2"><a class="white-text" href="#test3">Events</a></li>
	      		</ul>
	      	</div>
		</div>
	</div> 
	
	<ng-view></ng-view>
	
<%-- <h1>Hi! ${message}</h1>
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
	</c:choose> --%>

 	<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/bower_components/materialize/dist/js/materialize.js"></script>
	<!-- Leaf map js library -->
	<script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
	<!-- Bower components -->
	<script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-route/angular-route.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bower_components/angular-resource/angular-resource.js"></script>
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
	<script src="${pageContext.request.contextPath}/resources/app/components/place/place.controller.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/app/marker.controller.js"></script>
 </body>
</html>
