<%@ page language="java" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mylib" uri="http://epam.com/hnyp/shop/mylib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Main</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="stylesheet" href="products.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/products.js"></script>
<script type="text/javascript" src="js/cartViewer.js"></script>
</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<%--  <jsp:include page="/WEB-INF/jsp/parts/modules.jsp" /> --%>
		<div id="maincontent">
			<div id="introduction">
				<h2>Products</h2>
			</div>
			<c:url var="link_products"  value="/products"/>
			<form method="get" action="${link_products}">
				<div id="prodConfig">
					<div class="item">
						<p>Elements on page</p>
						<select id="elementsOnPage" name="elementsOnPage">
							<c:forEach items="${elementsOnPageModes}" var="em">
								<c:choose>
									<c:when test="${em eq filterBean.elementsOnPage}">
										<option value="${em}" selected="selected">${em.count}</option>
									</c:when>
									<c:otherwise>
										<option value="${em}">${em.count}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="item">
						<p class="label">Sort by</p>
						<select id="sortBy" name="sortBy">
							<c:forEach items="${sortModes}" var="sm">
								<c:choose>
									<c:when test="${sm eq filterBean.sortMode}">
										<option value="${sm}" selected="selected">${sm.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${sm}">${sm.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div id="filter">
					<div class="f" id="priceFilter">
						<div class="title">
							<span class="text">Price</span> <span class="btn" onclick="resetPrice();">(reset)</span>
						</div>
						<input class="price" type="text" maxlength="10" name="priceLow"
							value="${filterBean.priceLow}" /> low <input class="price" type="text" maxlength="10"
							name="priceHigh" value="${filterBean.priceHigh}" /> high
					</div>
					<div class="f" id="categoriesFilter">
						<div class="title">
							<span class="text">Categories</span> <span class="btn" onclick="resetCategories();">(reset)</span>
						</div>
						<ul>
							<c:forEach items="${categories}" var="cat">
								<c:choose>
									<c:when test="${not empty filterBean.categoryIds && mylib:contains(filterBean.categoryIds,cat.id)}">
										<li><input type="checkbox" checked="checked" name="cat" value="${cat.id}" />${cat.name}</li>
									</c:when>
									<c:otherwise>
										<li><input type="checkbox" name="cat" value="${cat.id}" />${cat.name}</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<div class="f" id="manufacturersFilter">
						<div class="title">
							<span class="text">Manufacturers</span> <span class="btn" onclick="resetManufacturers();">(reset)</span>
						</div>
						<ul>
							<c:forEach items="${manufacturers}" var="man">
								<c:choose>
									<c:when test="${not empty filterBean.manufacturerIds && mylib:contains(filterBean.manufacturerIds,man.id)}">
										<li><input type="checkbox" checked="checked" name="manuf" value="${man.id}" />${man.name}</li>
									</c:when>
									<c:otherwise>
										<li><input type="checkbox" name="manuf" value="${man.id}" />${man.name}</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<div class="f">
						<input type="submit" value="submit"/>
					</div>
				</div>
			</form>
			<div id="leftside" class="productside">
				<mytags:pagination filterBean="${filterBean}" />
				<div class="products_holder">
					<c:choose>
						<c:when test="${not empty products}">
							<ul>
								<c:forEach var="p" items="${products}">
									<mytags:productPrinter prod="${p}" />
								</c:forEach>
							</ul>
						</c:when>
						<c:otherwise>
							<p>nothing found</p>
						</c:otherwise>
					</c:choose>
				</div>
				<div style="clear:both;"></div>
				<mytags:pagination filterBean="${filterBean}" />
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
	<c:url value="/cartManipulate" var="link_cart_manipulate" />
	<input type="hidden" id="add_to_cart_url" value="${link_cart_manipulate}" />
</body>
</html>