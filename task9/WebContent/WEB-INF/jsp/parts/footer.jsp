<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="footer">
	<p class="right">
		Copyright &copy; 2014 ProductsShop, Design: <a
			href="http://www.free-css-templates.com">free-css-templates.com</a>
	</p>
	<p>
		<c:url value="/main" var="link_home"></c:url>
		<c:url value="/products" var="link_products"></c:url>
		<c:url value="/about" var="link_about"></c:url>
		<a href="${link_home}">Home</a> &middot; <a href="${link_products}">Products</a> &middot; <a
			href="${link_about}">About</a>
	</p>
</div>