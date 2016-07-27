<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login page</title>
</head>

<body>
	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="post" >
		<c:if test="${param.error != null}">
			<div>
				<p>Invalid username and password.</p>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div>
				<p>You have been logged out successfully.</p>
			</div>
		</c:if>
		<div>
			<label for="username"></label> 
			<input type="text" id="username" name="ssoId" placeholder="Enter Username" required>
		</div>
		<div>
			<label for="password"></label>
			<input type="password" id="password" name="password" placeholder="Enter Password" required>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div>
			<input type="submit" value="Log in">
		</div>
	</form>
</body>
</html>