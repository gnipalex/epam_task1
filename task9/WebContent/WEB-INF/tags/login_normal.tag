<%@ tag language="java" pageEncoding="cp1251" body-content="empty"
	description="Shows normal size login block on the page. Uses SESSION_AUT_USER scope var to access authorizes user"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/register" var="link_register" />
<c:url value="/login" var="link_login" />
<c:url value="/account" var="link_account" />
<c:url value="/logout" var="link_logout" />

<c:choose>
	<c:when test="${not empty SESSION_AUT_USER}">
		<h3>You're logged in the system as
			{SESSION_AUT_USER.name}&nbsp;${SESSION_AUT_USER.lastName}, login -
			{SESSION_AUT_USER.login}</h3>
		<form action="${link_logout}">
			<input type="submit" value="Logout" />
		</form>
	</c:when>
	<c:otherwise>
		<form action="${link_login}" method="post">
			<input type="hidden" name="urlReferrer" value="${urlReferrer}" />
			<table>
				<tr>
					<td><h4>Login</h4></td>
					<td><input class="text" type="text" size="30" maxlength="50"
						name="login" value="${login}" />
						<div style="color: red; text-align: right;">${loginError}</div></td>
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
