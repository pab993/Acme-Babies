<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset>

	<legend>
		<b> <spring:message code="offer.info" /></b>
	</legend>

	<spring:message code="offer.createMoment" />
	:
	<jstl:out value="${offer.createMoment}"></jstl:out>
	<br>
	<spring:message code="offer.title" />
	:
	<jstl:out value="${offer.title}"></jstl:out>
	<br>
	<spring:message code="offer.description" />
	:
	<jstl:out value="${offer.description}"></jstl:out>
	<br>
	<spring:message code="offer.price" />
	:
	<jstl:out value="${offer.price}"></jstl:out>
	<br>
	
</fieldset>
<br />
