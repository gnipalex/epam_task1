<%@ tag language="java" body-content="empty"
	description="Displays cart content as table" %>

<%@ attribute name="showButtons" type="java.lang.Boolean"
	required="true"%>
<%@ attribute name="cart" required="true" description="Cart object to display" type="com.epam.hnyp.shop.cart.Cart" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		<c:if test="${not empty cart.items}">
			<c:forEach items="${cart.items}" var="cart_item">
				<tr>
					<c:choose>
						<c:when test="${showButtons}">
							<td>${cart_item.item.name}</td>
							<td>${cart_item.item.price / 100}$</td>
							<td>
								<button onclick="decrementItem(this, ${cart_item.item.id});"
									class="table_button" title="decrement">&lt;</button>&nbsp; <span
								class="cart_item_count">${cart_item.count}</span>&nbsp;
								<button onclick="incrementItem(this, ${cart_item.item.id});"
									class="table_button" title="increment">&gt;</button>
							</td>
							<td><span class="cart_item_total_price">${(cart_item.count * cart_item.item.price) / 100}</span>$</td>
							<td><button onclick="removeItem(this, ${cart_item.item.id})"
									class="table_button" title="remove">X</button></td>
						</c:when>
						<c:otherwise>
							<td>${cart_item.item.name}</td>
							<td>${cart_item.item.price / 100}$</td>
							<td>${cart_item.count}</td>
							<td>${(cart_item.count * cart_item.item.price) / 100}$</td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</c:if>
		<tr id="id_total_row">
			<td colspan="2">Total</td>
			<td><span class="cart_item_total_count">${cart.totalCount}</span></td>
			<td><span class="cart_item_total_price">${cart.totalPrice / 100}</span>$</td>
			<td></td>
		</tr>
	</tbody>
</table>
