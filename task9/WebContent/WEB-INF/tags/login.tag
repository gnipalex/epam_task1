<%@ tag language="java" pageEncoding="cp1251"%>
<%@ tag body-content="empty"
	description="Shows login block on the page. If uses small size shows only : links login/logout, info about authorized user"%>

<%@ attribute name="useSmallSize" required="true"
	description="If true than tag will show small login area containing only link to login page or logout otherwise inputs to login"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/register" var="link_register" />
<c:url value="/login" var="link_login" />
<c:url value="/account" var="link_account" />
<c:url value="/logout" var="link_logout" />


<c:choose>
	<c:when test="${not empty SESSION_AUT_USER}">
		<c:choose>
			<c:when test="${useSmallSize}">
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
				<h3>You're logged in the system as
					{SESSION_AUT_USER.name}&nbsp;${SESSION_AUT_USER.lastName}, login -
					{SESSION_AUT_USER.login}</h3>
				<form action="${link_logout}">
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
					<a href="${link_login}" title="View login page">LOGIN</a>
				</h4>
				<br />
				<span class="txt">You're unauthorized,&nbsp;<a
					href="${link_login}">login</a></span>
			</c:when>
			<c:otherwise>
				<form action="${link_login}" method="post">
					<input type="hidden" name="urlReferrer" value="${urlReferrer}"/>
					<table>
						<tr>
							<td><h4>Login</h4></td>
							<td><input class="text" type="text" size="30" maxlength="50"
								name="login" value="${login}" />
								<div style="color:red;text-align:right;">${loginError}</div></td>
						</tr>
						<tr>
							<td><h4>Password</h4></td>
							<td><input class="text" type="password" size="30"
								maxlength="50" name="password" value="" /></td>
						</tr>
						<tr>
							<td><input type="submit" value="Login" /></td>
							<td></td>
						</tr>
							<tr>
								<td colspan="2">You also can <a href="${link_register}">register</a>,
									if you haven't got account yet.
								</td>
							</tr>						
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

