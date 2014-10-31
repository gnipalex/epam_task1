<%@ tag language="java" pageEncoding="cp1251"
 	body-content="empty"
 	description="Shows small login block on the page. Uses SESSION_AUT_USER scope var to access authorizes user"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/register" var="link_register" />
<c:url value="/login" var="link_login" />
<c:url value="/account" var="link_account" />
<c:url value="/logout" var="link_logout" />

<c:choose>
	<c:when test="${not empty SESSION_AUT_USER}">
		<img src="images/account.gif" alt="Account" />
		<h4>
			<a href="${link_account}" title="View my personal account">MY
				ACCOUNT</a>
		</h4>
		<br />
		<span class="txt">${SESSION_AUT_USER.name}&nbsp;${SESSION_AUT_USER.lastName},&nbsp;
			<a href="${link_logout}" title="Logout from the system">logout</a>
		</span>

	</c:when>
	<c:otherwise>
		<img src="images/account.gif" alt="Account" />
		<h4>
			<a href="${link_login}" title="View login page">LOGIN</a>
		</h4>
		<br />
		<span class="txt">You're unauthorized,&nbsp;<a
			href="${link_login}">login</a></span>

	</c:otherwise>
</c:choose>

