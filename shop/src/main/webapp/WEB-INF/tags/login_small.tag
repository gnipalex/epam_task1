<%@ tag language="java"	body-content="empty"
 	description="Shows small login block on the page. Uses SESSION_AUT_USER scope var to access authorizes user"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="locale" />

<c:url value="/register" var="link_register" />
<c:url value="/login" var="link_login" />
<c:url value="/account" var="link_account" />
<c:url value="/logout" var="link_logout" />

<c:choose>
	<c:when test="${not empty SESSION_AUT_USER}">
	<c:url value="/avatar" var="link_ava"/>
		<img src="${link_ava}" alt="" class="imageAVAsmall">
		<div class="pad">
		<img src="images/account.gif" alt="Account" />
		<h4>
			<a href="${link_account}" title="View my personal account">
				<fmt:message key="page.header.login.myaccount"/>
			</a>
		</h4>
		<br />
		<span class="txt">${SESSION_AUT_USER.name}&nbsp;${SESSION_AUT_USER.lastName},&nbsp;
			<a href="${link_logout}" title="Logout from the system">logout</a>
		</span>
		</div>
	</c:when>
	<c:otherwise>
		<div class="pad">
		<img src="images/account.gif" alt="Account" />
		<h4>
			<a href="${link_login}" title="View login page">
				<fmt:message key="page.header.login"/>
			</a>
		</h4>
		<br />
		<span class="txt">
			<fmt:message key="page.header.login.unathorized"/>,&nbsp;
			<a href="${link_login}">login</a></span>
		</div>
	</c:otherwise>
</c:choose>

