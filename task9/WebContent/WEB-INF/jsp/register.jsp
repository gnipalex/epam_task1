<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
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
		<jsp:include page="parts/header.jsp" />
		<jsp:include page="parts/menu.jsp" />
		<div id="maincontent">
			<div class="introduction">
				<h2>Register</h2>
				<div class="register">
					<c:url value="/register" var="link"></c:url>
					<form id="register_form_id" action="${link}" method="post"
						onsubmit=""> <!-- return validateJS(this); -->
						<table>
							<tr>
								<td><h4>Your name</h4></td>
								<td><input class="text" type="text" value="${userFormBean.name}" maxlength="50"
									size="30" name="name"></input>
									<div id="name_err_id" class="error_message">${errorMessages["nameError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your last name</h4></td>
								<td><input class="text" type="text" value="${userFormBean.lastName}" maxlength="50"
									size="30" name="lastName"></input>
									<div id="lastName_err_id" class="error_message">${errorMessages["lastNameError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your login(email)</h4></td>
								<td><input class="text" type="text" value="${userFormBean.login}" maxlength="50"
									size="30" name="login"></input>
									<div id="login_err_id" class="error_message">${errorMessages["loginError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your password</h4></td>
								<td><input class="text" type="password" value=""
									maxlength="50" size="30" name="password"></input>
									<div id="password_err_id" class="error_message">${errorMessages["passwordError"]}</div></td>
							</tr>
							<tr>
								<td><h4>Your password again</h4></td>
								<td><input class="text" type="password" value=""
									maxlength="50" size="30" name="rePassword"></input></td>
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
										<input type="checkbox" ${checkbox_checked} name="receiveLetters" value="on"></input>&nbsp;receive
										letters
									</div>
								</td>
							</tr>
							<tr>
								<td><h4>Robot protection</h4></td>
								<td>
									<%--  
									<div class="capchaimg"><img src="capchaDrawer" alt="capcha" /></div>
									<input class="text" type="text" value="" maxlength="50" size="30" name="capcha"></input>
									<div id="capcha_err_id" class="error_message">${errorMessages["capchaError"]}</div> 
									<input type="hidden" name="capchaUuid" value=""></input>
									--%>
										
									<ctag:capcha url="capchaDrawer" divCss="capchaimg"/>
									
									<div id="capcha_err_id" class="error_message">${errorMessages["capchaError"]}</div> 
								</td>
							</tr>
						</table>
						<br></br>
						<input class="txt" type="submit" value="Register"></input>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="parts/footer.jsp" />
	</div>
	<input type="hidden" id="res_error_name_empty" value="specify name" />
	<input type="hidden" id="res_error_email_empty" value="specify email" />
	<input type="hidden" id="res_error_lastName_empty"
		value="specify last name" />
	<input type="hidden" id="res_error_email_incorrect"
		value="email is incorrect" />
	<input type="hidden" id="res_error_pwd_empty" value="specify password" />
	<input type="hidden" id="res_error_pwd_mismatch"
		value="passwords don't match" />
	<input type="hidden" id="res_error_capcha_empty" value="specify capcha" />
</body>
</html>