<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Shop - Cart</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="stylesheet" href="products.css" type="text/css" />
<link rel="stylesheet" href="cart.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/products.js"></script>

</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<%--  <jsp:include page="/WEB-INF/jsp/parts/modules.jsp" /> --%>
		<div id="maincontent">
			<div id="introduction">
				<h2>Order preparing</h2>
			</div>
			<div id="cart_wraper">
				<p>
					<mytags:cartViewer cart="${SESSION_CART}" showButtons="false"/>
				</p>
				<hr />
				<p>
					
				</p>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
	<c:url value="/cartManipulate" var="link_cart_manipulate" />
	<input type="hidden" id="add_to_cart_url"
		value="${link_cart_manipulate}" />
</body>
</html>