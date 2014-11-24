<%@ tag language="java" pageEncoding="cp1251"%>
<%@ tag body-content="empty"
	description="Places picture of capcha. Uses IMG html tag inside of DIV, input field located out of DIV"%>

<%@ attribute name="url"
	description="Url to capcha source (servlet drawing capcha)"
	required="true"%>
<%@ attribute name="divCss" description="DIV element css class"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="${divCss}">
	<c:if test="${not empty capchaUid}">
		<c:set value="?capchaUid=${capchaUid}" var="img_hidden"/>
		<input type="hidden" name="capchaUid" value="${capchaUid}"></input>
	</c:if>
	<img src="${url}${img_hidden}" alt="capcha" />
</div>
<input class="text" type="text" value="" maxlength="50" size="30"
	name="capcha"></input>

