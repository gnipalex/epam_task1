<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctag" tagdir="/WEB-INF/tags"%>

<fmt:setBundle basename="locale" />

<div id="header">
	<div class="topong">
		<div class="pad">
			<img src="images/cart.gif" alt="My Cart" />
			<h4>
				<c:url value="/cart" var="link_cart" />
				<a href="${link_cart}">
					<fmt:message key="page.header.cart"/>
				</a>
			</h4>
			<br />
			<span class="txt"> <span id="cart_count_id">${SESSION_CART.totalCount}</span>
				<fmt:message key="page.header.cart.items"/>,&nbsp;
				<fmt:message key="page.header.cart.price"/>&nbsp;
				<span id="cart_price_id">${SESSION_CART.totalPrice / 100}</span>&nbsp;$
			</span>
		</div>
	</div>
	<div class="topong wideavablock">
		<ctag:login_small />
	</div>
	<div class="topong smalllangblock">
		<div class="pad">
			<h4>
				<fmt:message key="page.header.lang"/>
			</h4>
			<form action="">
			<select name="lang" onchange="this.form.submit();">
				<c:forEach var="lang" items="${CONTEXT_AVAILABLE_LOCALES}">
					<c:choose>
						<c:when test="${pageContext.request.locale eq lang}">
							<option selected="selected" value="${lang}">${lang}</option>
						</c:when>
						<c:otherwise>
							<option value="${lang}">${lang}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			</form>
		</div>
	</div>
	<h1>
		<span class="green bigl">P</span>roducts<span class="green bigl">S</span>hop
	</h1>
	<!--  <span class="slogan">You are happy - we are happy</span> -->
	<span class="slogan">
	<fmt:message key="page.header.slogan"/>
	</span>
</div>