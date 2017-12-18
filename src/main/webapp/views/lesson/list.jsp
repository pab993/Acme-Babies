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

<display:table name="lessons" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<jstl:choose>
		<jstl:when test="${row.startDate > now}">
			<spring:message code="lesson.title" var="titleHeader" />
			<display:column property="title" title="${titleHeader}" style = "background:orange" />

			<spring:message code="lesson.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" style = "background:orange"/>

			<spring:message code="lesson.startDate" var="startDateHeader" />
			<display:column property="startDate" title="${startDateHeader}"
				format="{0,date,dd/MM/yyyy HH:mm}" style = "background:orange"/>

			<spring:message code="lesson.finishDate" var="finishDateHeader" />
			<display:column property="finishDate" title="${finishDateHeader}"
				format="{0,date,dd/MM/yyyy HH:mm}" style = "background:orange"/>

			<spring:message code="lesson.price" var="priceHeader" />
			<display:column property="price" title="${priceHeader}" style = "background:orange"/>

			<display:column style = "background:orange">
				<a href="lesson/display.do?lessonId=${row.id}"> <spring:message
						code="lesson.display"></spring:message></a>
			</display:column>

			<security:authorize access="hasRole('CUSTOMER')">

				<jstl:if test="${display == true}">
					<jstl:choose>
						<jstl:when test="${row.startDate > now && display == true}">
							<display:column style = "background:orange">
								<a href="shift/list.do?lessonId=${row.id}"> <spring:message
										code="lesson.createInscription"></spring:message></a>
							</display:column>
						</jstl:when>
						<jstl:otherwise>
							<display:column>
								<a><spring:message code="lesson.inscription"></spring:message></a>
							</display:column>
						</jstl:otherwise>
					</jstl:choose>
				</jstl:if>
			</security:authorize>

		</jstl:when>
		<jstl:otherwise>
			<spring:message code="lesson.title" var="titleHeader" />
			<display:column property="title" title="${titleHeader}" />

			<spring:message code="lesson.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
			
			<spring:message code = "lesson.startDate" var = "startDateHeader" />
			<display:column property = "startDate" title = "${startDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "lesson.finishDate" var = "finishDateHeader" />
			<display:column property = "finishDate" title = "${finishDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "lesson.price" var = "priceHeader" />
			<display:column property = "price" title = "${priceHeader}"/>

			<display:column>
				<a href="lesson/display.do?lessonId=${row.id}"> <spring:message
						code="lesson.display"></spring:message></a>
			</display:column>	

			<security:authorize access="hasRole('CUSTOMER')">
			
			<jstl:if test="${display == true}">
				<jstl:choose>
					<jstl:when test="${row.startDate > now && display == true}">
						<display:column>
							<a href="shift/list.do?lessonId=${row.id}"> <spring:message
							code="lesson.createInscription"></spring:message></a>
						</display:column>	
					</jstl:when>
					
					<jstl:otherwise>
						<display:column>
							<a><spring:message code="lesson.inscription"></spring:message></a>					
						</display:column>
					</jstl:otherwise>
				</jstl:choose>
			</jstl:if>	
			</security:authorize>
			</jstl:otherwise>
		</jstl:choose>		
	</display:table>





