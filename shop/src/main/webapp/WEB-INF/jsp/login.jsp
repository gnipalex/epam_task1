<%@ page language="java" %>
<%@ taglib prefix="ctag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Register</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="stylesheet" href="login.css" type="text/css" />
</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<div id="maincontent">
			<div class="introduction">
				<h2>Login</h2>
				<div class="login">
					<ctag:login_normal />
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
</body>
</html>