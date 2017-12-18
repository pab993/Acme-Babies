<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->

<security:authorize access="hasRole('CARETAKER')">
	<div>
		<h3>
			<a href="offer/create.do"><spring:message code="offer.create" /></a>
		</h3>
	</div>
</security:authorize>

<display:table name="offers" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="offer.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="offer.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="offer.price" var="priceHeader" />
	<display:column property="price" title="${priceHeader}" />

	<security:authorize access="hasRole('CARETAKER')">

<%-- 		<jstl:choose>
			<jstl:when test="${row.caretaker.id == actor.id}">
				<display:column>
					<a href="offer/edit.do?offerId=${row.id}"> <spring:message
							code="offer.edit"></spring:message></a>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
							--
					</jstl:otherwise>
		</jstl:choose> --%>
		
		
		
			<display:column>
				<jstl:if test="${row.caretaker.id == actor.id}">
					<a href="offer/edit.do?offerId=${row.id}"> <spring:message
							code="offer.edit"></spring:message></a>
				</jstl:if>
			</display:column>
		

	</security:authorize>

	<display:column>
		<a href="offer/display.do?offerId=${row.id}"> <spring:message
				code="offer.display"></spring:message></a>
	</display:column>

	<display:column>
		<a href="actor/display.do?actorId=${row.caretaker.id}"> <spring:message
				code="actor.display2"></spring:message></a>
	</display:column>

</display:table>
