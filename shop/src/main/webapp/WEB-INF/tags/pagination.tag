<%@ tag language="java" pageEncoding="cp1251"%>
<%@ attribute name="filterBean"
	type="com.epam.hnyp.shop.form.ProductFilterBean" required="true"%>

<%@ taglib prefix="mylib" uri="http://epam.com/hnyp/shop/mylib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="paginationBlock">
	<c:url var="link_products_url" value="/products"/>
	<c:set var="link_first_page">
		<mylib:prepareUrl filterBean="${filterBean}" url="${link_products_url}" pageNum="1"/>
	</c:set>
	<c:set var="link_last_page">
		<mylib:prepareUrl filterBean="${filterBean}" url="${link_products_url}" pageNum="${filterBean.pagesCount}"/>
	</c:set>
	
	<a href="${link_first_page}" title="to page 1">&lt;&lt;</a>
	<!--  <a href="">1</a> <a href="">2</a> -->
	<c:choose>
		<c:when test="${filterBean.currentPage lt 3}">
			<c:forEach begin="1" 
					   end="${filterBean.pagesCount < 3 ? filterBean.pagesCount : 3}"
					   var="index">
				<c:set var="link_tmp">
					<mylib:prepareUrl filterBean="${filterBean}" url="${link_products_url}" pageNum="${index}"/>
				</c:set>
				<a class="pgnNumber ${index eq filterBean.currentPage ? 'current' : ''}"  href="${link_tmp}" title="page ${index}">${index}</a>
			</c:forEach>
			<c:if test="${filterBean.pagesCount > 3}">
				<a>...</a>
			</c:if>
		</c:when>
		<c:when test="${filterBean.currentPage >= 3 && filterBean.currentPage <= (filterBean.pagesCount - 3)}">
			<a>...</a>
			<c:forEach begin="${filterBean.currentPage - 1}" 
					   end="${filterBean.currentPage + 1}"
					   var="index">
				<c:set var="link_tmp">
					<mylib:prepareUrl filterBean="${filterBean}" url="${link_products_url}" pageNum="${index}"/>
				</c:set>
				<a class="pgnNumber ${index eq filterBean.currentPage ? 'current' : ''}"  href="${link_tmp}" title="page ${index}">${index}</a>
			</c:forEach>
			<a>...</a>			
		</c:when>
		<c:otherwise>
			<a>...</a>
			<c:forEach begin="${filterBean.pagesCount - 2}" 
					   end="${filterBean.pagesCount}"
					   var="index">
				<c:set var="link_tmp">
					<mylib:prepareUrl filterBean="${filterBean}" url="${link_products_url}" pageNum="${index}"/>
				</c:set>
				<a class="pgnNumber ${index eq filterBean.currentPage ? 'current' : ''}"  href="${link_tmp}" title="page ${index}">${index}</a>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<a href="${link_last_page}" title="to page ${filterBean.pagesCount}">&gt;&gt;</a>
</div>
