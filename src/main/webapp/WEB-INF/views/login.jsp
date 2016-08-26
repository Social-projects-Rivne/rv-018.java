<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login page</title>
</head>

<body>
	<form action="/login" method="post" >
		<c:if test="${param.error != null}">
			<div>
				<p>Invalid email and/or password.</p>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div>
				<p>You have been logged out successfully.</p>
			</div>
		</c:if>
		<div>
			<label for="username"></label> 
			<input type="text" id="username" name="user_login" placeholder="Enter Email" required>
		</div>
		<div>
			<label for="password"></label>
			<input type="password" id="password" name="password_login" placeholder="Enter Password" required>
		</div>
		<div>
			<label><input type="checkbox" name="remember-me"> Remember Me</label>
		</div>
		<div>
			<input type="submit" value="Log in">
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>