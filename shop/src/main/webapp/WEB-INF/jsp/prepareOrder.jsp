<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>

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
					<mytags:cartViewer cart="${SESSION_CART}" showButtons="false" />
				</p>
				<hr />
				<c:url value="/prepareOrder" var="link_prepareOrder" />
				<form action="aaa" method="post">
					<table>
						<tr>
							<td>Payment type</td>
							<td><select name="payType">
									<c:forEach items="${paymentTypes}" var="ps">
										<c:set value="" var="tmp" />
										<c:if test="${ps == paymentType}">
											<c:set value="selected=\"selected\"" var="tmp" />
										</c:if>
										<option ${tmp} value="${ps}">${ps.type}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Credit card code</td>
							<td><input type="text" name="creditCard"
								value="${creditCard}" /></td>
						</tr>
						<tr>
							<td>Delivery type</td>
							<td><select name="deliveryType">
									<c:forEach items="${deliveryTypes}" var="ds">
										<c:set value="" var="tmp" />
										<c:if test="${ds == deliveryType}">
											<c:set value="selected=\"selected\"" var="tmp" />
										</c:if>
										<option ${tmp} value="${ds}">${ds.delivery}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Address</td>
							<td><input type="text" name="address" value="${address}" /></td>
						</tr>
					</table>
					<c:url value="/cart" var="link_cart" />
					<a href="$link_cart"><button>back to cart</button></a> <input
						type="submit" value="continue" />
				</form>

			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
	<c:url value="/cartManipulate" var="link_cart_manipulate" />
	<input type="hidden" id="add_to_cart_url"
		value="${link_cart_manipulate}" />
</body>
</html>