<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<div id="header">
			<div class="topong">
				<div class="pad">
					<img src="images/cart.gif" alt="My Cart" />
					<h4>
						<a href="">VIEW CART</a>
					</h4>
					<br /> <span class="txt">0 items</span>
				</div>
			</div>
			<div class="topong">
				<div class="pad">
					<img src="images/account.gif" alt="Account" />
					<h4>
						<a href="">MY ACCOUNT</a>
					</h4>
					<br /> <span class="txt">Show my account</span>
				</div>
			</div>
			<h1>
				<span class="green bigl">P</span>roducts<span class="green bigl">S</span>hop
			</h1>
			<span class="slogan">You are happy - we are happy</span>
		</div>
		<div id="menu">
			<div class="pad">
				<div id="search">
					<form action="" method="get">
						<p>
							<input type="text" name="search" size="20" maxlength="250"
								class="text" value="" /> <input type="submit" value="Go"
								class="txt" />
						</p>
					</form>
				</div>
				<div class="submit">
					<ul>
						<li><a href=""><span>HOME</span></a></li>
						<li><a href=""><span>PRODUCTS</span></a></li>
						<li><a href=""><span>REGISTER</span></a></li>
					</ul>
				</div>
			</div>
		</div>
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
									<div class="capchaimg"><img src="capchaDrawer" alt="capcha" /></div>
									<input class="text" type="text" value="" maxlength="50" size="30" name="capcha"></input>
									<div id="capcha_err_id" class="error_message">${errorMessages["capchaError"]}</div> 
									<input type="hidden" name="capchaUuid" value=""></input>
								</td>
							</tr>
						</table>
						<br></br>
						<input class="txt" type="submit" value="Register"></input>
					</form>
				</div>
			</div>
		</div>
		<div id="footer">
			<p class="right">
				Copyright &copy; 2014 ProductsShop, Design: <a
					href="http://www.free-css-templates.com">free-css-templates.com</a>
			</p>
			<p>
				<a href="">Home</a> &middot; <a href="">Products</a> &middot; <a
					href="">About</a>
			</p>
		</div>
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