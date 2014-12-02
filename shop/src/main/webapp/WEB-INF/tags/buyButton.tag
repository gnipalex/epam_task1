<%@ tag language="java" pageEncoding="cp1251" body-content="empty" description="Adds 'buy' button to product"%>

<%@ attribute name="cssClass" description="Takes name of css style class to apply to the button" required="true" %>
<%@ attribute name="productId" description="Product id" required="true" rtexprvalue="true" type="java.lang.Integer" %>

<button class="${cssClass}" title="add to cart" onclick="addToCart(${productId});"></button>