<%@ tag language="java" pageEncoding="cp1251" %>

<%@ attribute name="sessionUser" required="true" type="com.epam.hnyp.task9.model.User" %>
<%@ attribute name="useSmallSize" required="true" description="If true than tag will show small login area containing only link to login page or logout otherwise inputs to login"%>
<%@ attribute name="loginField" required="true" %>
<%@ attribute name="passwordField" required="true" %>
<%@ attribute name="loginUrl" required="true" %>
<%@ attribute name="logoutUrl" required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${useSmallSize}">
		<c:choose>
			<c:when test="$"></c:when>
		</c:choose>
		<img src="images/account.gif" alt="Account" />
		<h4>
				<a href="">MY ACCOUNT</a>
		</h4>
		<br /> <span class="txt">Show my account</span>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>

