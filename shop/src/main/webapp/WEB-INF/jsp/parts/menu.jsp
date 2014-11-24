<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menu">
			<div class="pad">
				<div id="search">
					<form action="" method="get">
						<p>
							<input type="text" name="search" size="20" maxlength="250"
								class="text" value="" /> <input type="submit" value="Go"
								class="txt" />
						</p>
					</form>
				</div>
				<div class="submit">
					<ul>
						<c:url value="/main" var="link_home"></c:url>
						<li><a href="${link_home}"><span>HOME</span></a></li>
						<c:url value="/products" var="link_products"></c:url>
						<li><a href="${link_products}"><span>PRODUCTS</span></a></li>
						<%--  
						<c:url value="/register" var="link_register"></c:url>
						<li><a href="${link_register}"><span>REGISTER</span></a></li>
						--%>
					</ul>
				</div>
			</div>
</div>