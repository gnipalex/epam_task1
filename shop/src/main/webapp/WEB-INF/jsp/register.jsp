<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctag" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Register</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
<link rel="stylesheet" href="register.css" type="text/css" />
<script type="text/javascript" src="js/register_validator.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<div id="maincontent">
			<div class="introduction">
				<h2>Register</h2>
				<div class="register">
					<c:url value="/register" var="link_register" />
					<form id="register_form_id" action="${link_register}" method="post" enctype="multipart/form-data"
						onsubmit="return validateJS(this);"> 
						<table>
							<tr>
								<td><h4>Your name</h4></td>
								<td><input class="text" type="text" value="${userFormBean.name}" maxlength="50"
									size="30" name="name" />
									<div id="name_err_id" class="error_message">${errorMessages["nameError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your last name</h4></td>
								<td><input class="text" type="text" value="${userFormBean.lastName}" maxlength="50"
									size="30" name="lastName" />
									<div id="lastName_err_id" class="error_message">${errorMessages["lastNameError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your login(email)</h4></td>
								<td><input class="text" type="text" value="${userFormBean.login}" maxlength="50"
									size="30" name="login" onblur="checkLogin(this);" />
									<div id="login_err_id" class="error_message">${errorMessages["loginError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your password</h4></td>
								<td><input class="text" type="password" value=""
									maxlength="50" size="30" name="password" />
									<div id="password_err_id" class="error_message">${errorMessages["passwordError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your password again</h4></td>
								<td><input class="text" type="password" value=""
									maxlength="50" size="30" name="rePassword" /></td>
							</tr>
							<tr>
								<td><h4>
										Receive our <br /> newsletters
									</h4></td>
								<td>
									<div class="checkboxwraper">
										<c:if test="${not empty userFormBean.receiveLetters }">
											<c:set var="checkbox_checked" value="checked=\"checked\""></c:set>
										</c:if>
										<input type="checkbox" ${checkbox_checked} name="receiveLetters" value="on" />&nbsp;receive
										letters
									</div>
								</td>
							</tr>
							<tr>
								<td><h4>Your avatar</h4></td>
								<td><input class="text" type="file" value="" accept="image/jpeg" size="0"
									 name="avatar" />
									 <div id="avatar_err_id" class="error_message">${errorMessages["avatarError"]}</div>
								</td>
							</tr>
							<tr>
								<td><h4>Robot protection</h4></td>
								<td>
									<c:url value="/capchaDrawer" var="link_capcha" />	
									<ctag:capcha url="${link_capcha}" divCss="capchaimg"/>
									<div id="capcha_err_id" class="error_message">${errorMessages["capchaError"]}</div> 
								</td>
							</tr>
						</table>
						<br />
						<input class="txt" type="submit" value="Register" />
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
	
	<c:url value="/loginCheck" var="link_login_check" />
	<input type="hidden" id="login_check_url" value="${link_login_check}" />
	
	<input type="hidden" id="res_error_name_empty" value="specify name" />
	<input type="hidden" id="res_error_lastName_empty"
		value="specify last name" />
		
	<input type="hidden" id="res_error_email_incorrect"
		value="email is incorrect" />	
	<input type="hidden" id="res_error_email_empty" value="specify email" />
	<input type="hidden" id="res_error_email_already_in_use" value="this email is already in use" />
	<input type="hidden" id="res_error_email_check_fail" value="email check failed"/>
	
	<input type="hidden" id="res_error_pwd_empty" value="specify password" />
	<input type="hidden" id="res_error_pwd_mismatch"
		value="passwords don't match" />
	<input type="hidden" id="res_error_capcha_empty" value="specify capcha" />
	<input type="hidden" id="res_error_avatar_bad_format" value="select a '.jpg' file instead" />
</body>
</html>