<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Cart</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="stylesheet" href="products.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/products.js"></script>
<style type="text/css">
#cart_table {
	font-size: 10pt;
	margin: 0 auto;
	border-radius: 4px;
	text-align: right;
}


#cart_table th, td {
	padding: 10px;
}

#cart_table tr {
	height: 50px;
	background-color: #D3E8DE;
}

#cart_table th {
	width: 150px;
}

#cart_table th+th {
	width: 50px;
}

#cart_table th+th+th {
	width: 200px;
}

#cart_table th+th+th+th{
	width: 50px;
}

#cart_table th+th+th+th+th {
	width: 10px;
}

.spanbtn {
	font-weight: bold;
	font-size: 11pt;
	color: red;
}
.spanbtn:HOVER {
	font-size: 10pt;
	cursor: pointer;
}
.table_button {
	font-weight: bold;
	font-size: 11pt;
	color: red;
	width:25px; height: 25px;
}
</style>
</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<%--  <jsp:include page="/WEB-INF/jsp/parts/modules.jsp" /> --%>
		<div id="maincontent">
			<div id="introduction">
				<h2>Cart</h2>
			</div>
			<div style="margin-bottom: 50px;">
				<c:choose>
					<c:when test="${not empty SESSION_CART.items}">
						<table id="cart_table" cellspacing="0">
							<tr>
								<th>Product</th>
								<th>Unit price</th>
								<th>Count</th>
								<th>Price</th>
								<th></th>
							</tr>
							<c:forEach items="${SESSION_CART.items}" var="cart_item">
								<tr>
									<td>${cart_item.item.name}</td>
									<td>${cart_item.item.price / 100}$</td>
									<td>
										<button class="table_button" title="decrement">&lt;</button>
										<span class="cart_item_count">&nbsp;${cart_item.count}&nbsp;</span>
										<button class="table_button" title="increment">&gt;</button>
									</td>
									<td><span class="cart_item_total_price">${(cart_item.count * cart_item.item.price) / 100}</span>$</td>
									<td><button class="table_button" title="remove">X</button></td>
								</tr>
							</c:forEach>
							<tr id="id_total_row">
								<td colspan="2">Total</td>
								<td><span class="cart_item_count">${SESSION_CART.totalCount}</span></td>
								<td><span class="cart_item_total_price">${SESSION_CART.totalPrice / 100}</span>$</td>
								<td></td>
							</tr>

						</table>
					</c:when>
					<c:otherwise>
						<h3>You have no items in cart</h3>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
	<c:url value="/cartManipulate" var="link_cart_manipulate" />
	<input type="hidden" id="add_to_cart_url"
		value="${link_cart_manipulate}" />
</body>
</html>