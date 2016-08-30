<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en" ng-app="greenApp">
<head>
	<meta charset="UTF-8">
	<title>Welcome page</title>
	<script type="text/javascript">
		var _contextPath = "${pageContext.request.contextPath}";
	</script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/materialize.min.css">
	<!--Import Google Icon Font-->
	<link rel="stylesheet" href="http://fonts.googleapis.com/icon?family=Material+Icons">
	<!-- Leaflet styles -->
	<link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css">
	<!-- Custom styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/style.css">
	<!-- Supporting mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<div ng-controller="menuController">
		<!-- Header -->
		<header id="header">
		        <nav role="navigation">
			       <div class="nav-wrapper">
		                <div class="row">
		                    <div class="col s2 m1 l1">
		                        <i id="toggle-button" class="circle waves-effect waves-light material-icons">menu</i>
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
				<!-- Hiding menu -->
					<div class="collection not-active">
						<a href="#" class="collection-item" ng-click="hideTabs();"><i class="material-icons">business</i> Home</a>
						<a href="#/map" class="collection-item" ng-click="showTabs();"><i class="material-icons">language</i> Map</a>
						<a href="#/event" class="collection-item" ng-click="hideTabs();"><i class="material-icons">redeem</i> Events calendar</a>
						<a href="#/profile" class="collection-item" ng-click="hideTabs();"><i class="material-icons">perm_identity</i>Profile</a>
					</div>
				<!-- Tabs -->
				<div id="tabsRow" class="tabsBackgroundColor" ng-show="checkTabs">
					<div class="tabsIndent">
						<div class="row">
							<ul class="tabs" ng-show="checkTabs">
								<li class="tab col m3 tabsBackgroundColor"><a class="active white-text" href="#test1">Places</a></li>
								<li class="tab col m3 tabsBackgroundColor"><a class="white-text" href="#test2">Tracks</a></li>
								<li class="tab col m3 tabsBackgroundColor"><a class="white-text" href="#test3">Events</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- Actual content of the page -->
				<div class="content">
					 <div class="progress" ng-controller="markerCtrl" ng-show="progressBarVision">
						<div class="indeterminate"></div>
					</div>
					<ng-view></ng-view>
				</div>		
			</div>   
	    </main>
	</div>
    
	<!-- Leaf map js library -->
	<script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
	<!-- Bower components -->
	<script src="${pageContext.request.contextPath}/resources/assets/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/materialize.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-route.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-resource.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-animate.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-messages.min.js"></script>
	<!-- Core scripts -->
	<script src="${pageContext.request.contextPath}/resources/app/module.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/routes.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/marker.controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/menu.controller.js"></script>
	<!-- Components -->
	<script src="${pageContext.request.contextPath}/resources/app/components/map/map.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/login/login.component.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/app/components/profile/profile.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/user/user.component.js"></script>
	<!-- Supporting sliding menu -->
	<script src="${pageContext.request.contextPath}/resources/app/js/menu.js"></script>
</body>
</html>
