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
		<b> <spring:message code="lesson.info" /></b>
	</legend>

	<spring:message code="lesson.title" />
	:
	<jstl:out value="${lesson.title}"></jstl:out>
	<br>
	<spring:message code="lesson.description" />
	:
	<jstl:out value="${lesson.description}"></jstl:out>
	<br>
	<spring:message code="lesson.startDate" />
	:
	<jstl:out value="${lesson.startDate}"></jstl:out>
	<br>
	<spring:message code="lesson.finishDate" />
	:
	<jstl:out value="${lesson.finishDate}"></jstl:out>
	<br>
	<spring:message code="lesson.price" />
	:
	<jstl:out value="${lesson.price}"></jstl:out>
	<br>

</fieldset>
<br />

<security:authorize access="hasRole('CUSTOMER')"> 
	<h3>
		<a href="assessment/postAssessment.do?comentableId=${lesson.getId() }"> <spring:message
				code="postAssessment" />
		</a>
	</h3>
</security:authorize>

<display:table name="assessments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="assessment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="assessment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="assessment.rate" var="rateHeader" />
	<display:column property="rate" title="${rateHeader}" />

	<spring:message code="assessment.actorName" var="customerNameHeader" />
	<display:column property="customer.name" title="${customerNameHeader}" />
	
</display:table>