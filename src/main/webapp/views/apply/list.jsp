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

<display:table name="applies" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<jstl:choose>
		<jstl:when test="${row.status eq 'PENDING'}">
			<spring:message code="apply.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}"
				style="background:orange" />

			<spring:message code="apply.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}"
				style="background:orange" />

			<spring:message code="apply.observations" var="observationsHeader" />
			<display:column property="observations" title="${observationsHeader}"
				style="background:orange" />

			<spring:message code="apply.days" var="daysHeader" />
			<display:column property="days" title="${daysHeader}"
				style="background:orange" />

			<spring:message code="apply.counterOffer" var="counterOfferHeader" />
			<display:column property="counterOffer" title="${counterOfferHeader}"
				style="background:orange" />

			<spring:message code="apply.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:orange" />

			<security:authorize access="hasRole('CUSTOMER')">

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:orange">
							<a href="apply/edit.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:orange">
							--					
						</display:column>
					</jstl:otherwise>
				</jstl:choose>

			</security:authorize>

			<security:authorize access="hasRole('CARETAKER')">

				<display:column style="background:orange">
					<a href="offer/display.do?offerId=${row.offer.id}"> <spring:message
							code="apply.display"></spring:message></a>
				</display:column>

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:orange">
							<a href="apply/editStatus.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:orange">
							--					
						</display:column>
					</jstl:otherwise>
				</jstl:choose>
			</security:authorize>
		</jstl:when>

		<jstl:when test="${row.status eq 'REJECTED'}">
			<spring:message code="apply.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}"
				style="background:red" />

			<spring:message code="apply.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}"
				style="background:red" />

			<spring:message code="apply.observations" var="observationsHeader" />
			<display:column property="observations" title="${observationsHeader}"
				style="background:red" />

			<spring:message code="apply.days" var="daysHeader" />
			<display:column property="days" title="${daysHeader}"
				style="background:red" />

			<spring:message code="apply.counterOffer" var="counterOfferHeader" />
			<display:column property="counterOffer" title="${counterOfferHeader}"
				style="background:red" />

			<spring:message code="apply.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:red" />

			<security:authorize access="hasRole('CUSTOMER')">

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:red">
							<a href="apply/edit.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:red">
							--					
				</display:column>
					</jstl:otherwise>
				</jstl:choose>

			</security:authorize>

			<security:authorize access="hasRole('CARETAKER')">

				<display:column style="background:red">
					<a href="offer/display.do?offerId=${row.offer.id}"> <spring:message
							code="apply.display"></spring:message></a>
				</display:column>

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:red">
							<a href="apply/editStatus.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:red">
							--					
				</display:column>
					</jstl:otherwise>
				</jstl:choose>

			</security:authorize>

		</jstl:when>

		<jstl:otherwise>
			<spring:message code="apply.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}"
				style="background:green" />

			<spring:message code="apply.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}"
				style="background:green" />

			<spring:message code="apply.observations" var="observationsHeader" />
			<display:column property="observations" title="${observationsHeader}"
				style="background:green" />

			<spring:message code="apply.days" var="daysHeader" />
			<display:column property="days" title="${daysHeader}"
				style="background:green" />

			<spring:message code="apply.counterOffer" var="counterOfferHeader" />
			<display:column property="counterOffer" title="${counterOfferHeader}"
				style="background:green" />

			<spring:message code="apply.status" var="statusHeader" />
			<display:column property="status" title="${statusHeader}"
				style="background:green" />

			<security:authorize access="hasRole('CUSTOMER')">

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:green">
							<a href="apply/edit.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:green">
							--					
				</display:column>
					</jstl:otherwise>
				</jstl:choose>

			</security:authorize>

			<security:authorize access="hasRole('CARETAKER')">

				<display:column style="background:green">
					<a href="offer/display.do?offerId=${row.offer.id}"> <spring:message
							code="apply.display"></spring:message></a>
				</display:column>

				<jstl:choose>
					<jstl:when test="${row.status eq 'PENDING'}">
						<display:column style="background:green">
							<a href="apply/editStatus.do?applyId=${row.id}"> <spring:message
									code="apply.edit"></spring:message></a>
						</display:column>
					</jstl:when>
					<jstl:otherwise>
						<display:column style="background:green">
							--					
				</display:column>
					</jstl:otherwise>
				</jstl:choose>

			</security:authorize>

		</jstl:otherwise>

	</jstl:choose>

</display:table>