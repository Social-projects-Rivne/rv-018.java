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
	<!-- Angular material -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/angular-material.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/angular-material-calendar.min.css">
	<!--Import Google Icon Font-->
	<link rel="stylesheet" href="http://fonts.googleapis.com/icon?family=Material+Icons">
	<!-- Leaflet styles -->
	<link rel="stylesheet" href="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.css">
	<!-- Custom styles -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/style.css">
	<!-- Supporting mobile devices -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body ng-controller="menuController">
		<!-- Header -->
	<div>
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
		                    <div class="col s5 m4 l3">
		                        <nav class="custom">
		                            <div class="nav-wrapper">
		                              <form>
		                                <div class="input-field">
		                                  <input ng-model="searchLine" class="custom search_custom" id="search" type="search" placeholder="Search" required>
		                                  <label for="search" class="search_custom"><i class="material-icons search_custom">search</i></label>
		                                  <i class="material-icons search_custom">close</i>
		                                </div>
		                              </form>
		                            </div>
		                        </nav>
		                    </div>
		                    <div class="col s1 m1 l1">
		                        <a href="${pageContext.request.contextPath}/#/map/searchplace?name={{searchLine}}" class="btn-flat">Search</a>
		                    </div>
		                    <div class="col s2 m3 l4">
		                      	<div class="user-dropdown" onclick="show()">
									<div class="top-profile-name" ng-bind="name">George</div>
									<img class="top-profile-img" src="https://pp.vk.me/c626416/v626416332/21d46/PqEDVFQjwl0.jpg">
								</div>
		                    </div>
		                </div>
		            </div>    
			   </nav>  
		    </header>  
	    <main>    
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
				<!-- Form for login  -->
				<div ng-include="'${pageContext.request.contextPath}/resources/app/components/login/login.template.html'"></div>
				<!-- Progress bar for map -->
				<div class="progress" ng-controller="markerCtrl" ng-show="progressBarVision">
					<div class="indeterminate"></div>
				</div>
				<!-- Components -->
				<ng-view></ng-view>
				</div>		   
	    </main>

	</div>

	<!-- Leaf map js library -->
	<script src="https://npmcdn.com/leaflet@1.0.0-rc.3/dist/leaflet.js"></script>
	<!-- Bower components -->
	<script src="${pageContext.request.contextPath}/resources/assets/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/materialize.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-material.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-route.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-resource.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-animate.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-aria.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-messages.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-sanitize.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-material-calendar.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/masonry.pkgd.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/imagesloaded.pkgd.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/angular-utf8-base64.js"></script>
	<!-- Core scripts -->
	<script src="${pageContext.request.contextPath}/resources/app/module.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/routes.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/marker.controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/map/datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/menu.controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/login.controller.js"></script>

	<!-- Components -->
	<script src="${pageContext.request.contextPath}/resources/app/components/map/map.component.js"></script>
 	<script src="${pageContext.request.contextPath}/resources/app/components/profile/profile.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/user/user.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/searchPlace/searchplace.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/map/map.service.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/place/place.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/place/edit.place.component.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/place/feedback.modal.controller.js"></script>
	<script src="${pageContext.request.contextPath}/resources/app/components/place/edit.place.modals.controller.js"></script>
	<!-- Supporting sliding menu -->
	<script src="${pageContext.request.contextPath}/resources/app/js/menu.js"></script>
</body>
</html>
