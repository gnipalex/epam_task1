<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ctag" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Register</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<style type="text/css">
.login {
	margin: 50px auto;
	width: 300px;
}

.login input {
	height: 20px;
}

.login table td {
	vertical-align: top;
}
</style>

</head>
<body>
	<div id="content">
		<jsp:include page="parts/header.jsp" />
		<jsp:include page="parts/menu.jsp" />
		<div id="maincontent">
			<div class="introduction">
				<h2>Login</h2>
				<div class="login">
					<c:url value="/register" var="link_register"/>
					<c:url value="/login" var="link_login"/>
					<c:url value="/account" var="link_account"/>
					<c:url value="/logout" var="link_logout"/>
					<ctag:login registerUrl="${link_register}" loginFieldName="login" accountUrl="${link_account}"
						passwordFieldName="password" logoutUrl="${link_logout}" sessionUser="${SESSION_AUT_USER}" loginUrl="${link_login}"
						useSmallSize="false" errorColor="red" errorMessage="${loginError}" loginFieldValue="${login}"/>
				</div>
			</div>
		</div>
		<jsp:include page="parts/footer.jsp" />
	</div>
</body>
</html>