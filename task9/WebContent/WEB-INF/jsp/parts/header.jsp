<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctag" tagdir="/WEB-INF/tags"%>
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
			<ctag:login useSmallSize="true"/>
		</div>
	</div>
	<h1>
		<span class="green bigl">P</span>roducts<span class="green bigl">S</span>hop
	</h1>
	<span class="slogan">You are happy - we are happy</span>
</div>