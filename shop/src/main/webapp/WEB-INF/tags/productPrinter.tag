<%@ tag language="java" body-content="empty" description="Prints single product entity as list item"%>
<%@ attribute name="prod" description="Product entity" required="true"
	type="com.epam.hnyp.shop.model.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<c:url var="link_prodImageServlet" value="/productImage"/>

<li><img src="${link_prodImageServlet}?prodId=${prod.id}" />
	<h3>${prod.name}</h3>
	<p>
		<span class="price">Price : ${prod.price / 100}$</span>
	</p>
	<p>Category : ${prod.category.name}</p>
	<p>Manufacturer : ${prod.manufacturer.name}</p>
	<p>Description : ${prod.description}</p>
	<!--  
	<button class="addToCartButton" title="add to cart"></button>
	-->
	<mytags:buyButton cssClass="addToCartButton" productId="${prod.id}" />
</li>
