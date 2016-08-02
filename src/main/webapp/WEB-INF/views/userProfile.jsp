<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>User profile</title>
<script
	src="resources/bower_components/materialize/dist/css/materialize.css"></script>
</head>
<body>
	<h1>Hello!</h1>
	<c:if test="${user != null && user != 'anonymousUser' && user != 'not authenticated'}">
		<div class="alert alert-success">
			<p>
				The profile of <strong>${user}</strong>.
			</p>
		</div>
	</c:if>
</body>
</html>
