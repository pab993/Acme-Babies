<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->

	<display:table name = "shifts" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
			
			<spring:message code = "shift.title" var = "titleHeader" />
			<display:column property = "title" title = "${titleHeader}" />
		
			<spring:message code = "shift.attendance" var="attendanceHeader" />
			<display:column property="attendance" title="${attendanceHeader}" />

			<security:authorize access="hasRole('CUSTOMER')">
			
			<jstl:if test="${display == true}">
				<jstl:choose>
					<jstl:when test="${display == true && row.lesson.startDate > now && row.attendance >= 1}">
						<display:column>
							<a href="inscription/create.do?shiftId=${row.id}"> <spring:message
							code="lesson.createInscription"></spring:message></a>
						</display:column>	
					</jstl:when>
					
					<jstl:otherwise>
						<display:column>
							<a><spring:message code="shift.inscription"></spring:message></a>					
						</display:column>
					</jstl:otherwise>
					
				</jstl:choose>
			</jstl:if>	
			</security:authorize>		
	</display:table>
	
	
	
	
