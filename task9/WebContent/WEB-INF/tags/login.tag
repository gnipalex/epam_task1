<%@ tag language="java" pageEncoding="cp1251"%>
<%@ tag body-content="empty"
	description="Shows login block on the page. If uses small size shows only : links login/logout, info about authorized user"%>
<%@ attribute name="sessionUser" required="true"
	type="com.epam.hnyp.task9.model.User" %>
<%@ attribute name="useSmallSize" required="true"
	description="If true than tag will show small login area containing only link to login page or logout otherwise inputs to login"%>
<%@ attribute name="loginFieldName" %>
<%@ attribute name="loginFieldValue" %>
<%@ attribute name="passwordFieldName" %>
<%@ attribute name="errorMessage"%>
<%@ attribute name="errorColor"%>
<%@ attribute name="loginUrl" required="true" %>
<%@ attribute name="logoutUrl" required="true"%>
<%@ attribute name="accountUrl" required="true"%>
<%@ attribute name="registerUrl"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${not empty sessionUser}">
		<c:choose>
			<c:when test="${useSmallSize}">
				<img src="images/account.gif" alt="Account" />
				<h4>
					<a href="${accountUrl}" title="View my personal account">MY
						ACCOUNT</a>
				</h4>
				<br />
				<span class="txt">${sessionUser.name}&nbsp;${sessionUser.lastName},&nbsp;
					<a href="${logoutUrl}" title="Logout from the system">logout</a>
				</span>
			</c:when>
			<c:otherwise>
				<h3>You're logged in the system as
					{sessionUser.name}&nbsp;${sessionUser.lastName}, login -
					{sessionUser.login}</h3>
				<form action="${logoutUrl}">
					<input type="submit" value="Logout" />
				</form>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${useSmallSize}">
				<img src="images/account.gif" alt="Account" />
				<h4>
					<a href="${accountUrl}" title="View my personal account">LOGIN</a>
				</h4>
				<br />
				<span class="txt">You're unauthorized,&nbsp;<a
					href="${loginUrl}">login</a></span>
			</c:when>
			<c:otherwise>
				<form action="${loginUrl}" method="post">
					<table>
						<tr>
							<td><h4>Login</h4></td>
							<td><input class="text" type="text" size="30" maxlength="50"
								name="${loginFieldName}" value="${loginFieldValue}" />
								<div id="name_err_id" style="color : ${errorColor};">${errorMessage}</div>
							</td>
						</tr>
						<tr>
							<td><h4>Password</h4></td>
							<td><input class="text" type="password" size="30"
								maxlength="50" name="${passwordFieldName}" value=""/></td>
						</tr>
						<tr>
							<td><input type="submit" value="Login" /></td>
							<td></td>
						</tr>
						<c:if test="${not empty registerUrl}">
							<tr>
								<td colspan="2">You also can <a href="${registerUrl}">register</a>, if
									you haven't got account yet.
								</td>
							</tr>
						</c:if>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

