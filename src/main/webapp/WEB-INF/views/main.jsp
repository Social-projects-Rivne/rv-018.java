<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<meta charset="UTF-8">
	<title>Welcome page</title>
	<script type="text/javascript">
		var _contextPath = "${pageContext.request.contextPath}";
	</script>
	<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/materialize/dist/css/materialize.min.css">
<link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css">
<!-- Custom styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/map.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/content.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/loader.css">
<!-- Supporting mobile devices -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
 </head>
<body>
<!-- <<<<<<< HEAD

 	<nav ng-controller="sideNavMenuController">
		<div id="header" class="nav-wrapper green darken-2">
  
		<a href="#" data-activates="side-menu" class="sideNavMenuButton"><i class="material-icons">menu</i></a>
  
	    <ul class="side-nav" id="side-menu" ng-style="{top: topMarginValue}">
	      <li><a href="#/home"><i class="small material-icons">home</i>Home</a></li>
	      <li><a href="#/map"><i class="small material-icons">map</i>Map</a></li>
	      <li><a href="terms.html"><i class="small material-icons">event</i>Events calendar</a></li>
	      <li><a href="terms.html"><i class="small material-icons">account_circle</i>Profile</a></li>
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
	
	<ng-view></ng-view> -->
	
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

<!-- ======= -->
<div ng-controller="mainController">
<header id="header">
        <nav role="navigation">
	       <div class="nav-wrapper">
                <div class="row">
                    <div class="col s2 m1 l1">
                        <i id="toggle-button" class="circle waves-effect waves-light material-icons" ng-click="menuButtonClick()">menu</i>
                    </div>
                    <div class="col m3 l2 hide-on-small-only">
                        GreenTourism
                    </div>
                    <div class="col l1 hide-on-med-and-down line">
                       Home
                    </div>
                    <div class="col s8 m5 l4">
                        <nav class="custom">
                            <div class="nav-wrapper">
                              <form>
                                <div class="input-field">
                                  <input class="custom search_custom" id="search" type="search" placeholder="Search" required>
                                  <label for="search" class="search_custom"><i class="material-icons search_custom">search</i></label>
                                  <i class="material-icons search_custom">close</i>
                                </div>
                              </form>
                            </div>
                        </nav>
                    </div>
                    <div class="col s2 m3 l4">
                        <i class="small material-icons right">perm_identity</i>
                    </div>
                </div>
            </div>    
	   </nav>  
    </header>   
    <main>          
            <div>         
                       <div class="collection not-active" ng-style="{top: topMarginValue}">
                            <a href="#" class="collection-item"><i class="material-icons">business</i> Home</a>
                            <a href="#/map" class="collection-item"><i class="material-icons">language</i> Map</a>
                            <a href="#/event" class="collection-item"><i class="material-icons">redeem</i> Events calendar</a>
                            <a href="#/profile" class="collection-item"><i class="material-icons">perm_identity</i>Profile</a>
                        </div>
                        
						<div id="tabsRow">
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
                        
                        <div class="content" ng-style="contentCss">
                            <ng-view></ng-view>
                        </div>
            </div>   
    </main>
</div>
    
<!-- >>>>>>> 70311d87cc9ec59a17e3eb59631d72979f3ccfb1 -->
 	<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/bower_components/materialize/dist/js/materialize.js"></script>
	<!-- Leaf map js library -->
	<script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
	<!--  -->
	<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bower_components/materialize/dist/js/materialize.min.js"></script>
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
	<!-- Supporting sliding menu -->
	<script src="${pageContext.request.contextPath}/resources/app/js/menu.js"></script>
</body>
</html>
