<%@ tag language="java" pageEncoding="ISO-8859-1" body-content="empty" description="Prints single product entity as list item"%>
<%@ attribute name="prod" description="Product entity" required="true"
	type="com.epam.hnyp.task9.model.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<li><img src="" />
	<h3>${prod.name}</h3>
	<p>
		<span class="price">price : ${prod.price / 100}$</span>
	</p>
	<p>${prod.description}</p>
	<button title="add to cart"></button>
</li>
