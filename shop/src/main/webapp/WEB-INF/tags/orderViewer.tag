<%@ tag language="java" pageEncoding="cp1251" body-content="empty"
	description="Displays cart content as table"%>

<%@ attribute name="order" required="true"
	description="Cart object to display"
	type="com.epam.hnyp.shop.model.Order"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table id="cart_table" cellspacing="0">
	<thead>
		<tr>
			<th>Product</th>
			<th>Unit price</th>
			<th>Count</th>
			<th>Price</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty order.items}">
			<c:forEach items="${order.items}" var="order_item">
				<tr>
					<td>${order_item.product.name}</td>
					<td>${order_item.actualPrice / 100}$</td>
					<td>${order_item.count}</td>
					<td>${(order_item.count * order_item.actualPrice) / 100}$</td>
					<td></td>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="2">Total</td>
			<td>${order.itemCount}</td>
			<td>${order.totalPrice / 100}$</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">Pay type</td>
			<td>${order.payType.type}</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">Credit card</td>
			<td>${order.creditCardCode}</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">Delivery type</td>
			<td>${order.deliveryType.delivery}</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">Address</td>
			<td>${order.address}</td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>
