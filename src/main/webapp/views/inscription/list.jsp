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

	<display:table name = "inscriptions" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
			
			<spring:message code = "lesson.title" var = "titleHeader" />
			<display:column property = "lesson.title" title = "${titleHeader}" />
		
			<spring:message code = "lesson.description" var="descriptionHeader" />
			<display:column property="lesson.description" title="${descriptionHeader}" />
			
			<spring:message code = "lesson.startDate" var = "startDateHeader" />
			<display:column property = "lesson.startDate" title = "${startDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "lesson.finishDate" var = "finishDateHeader" />
			<display:column property = "lesson.finishDate" title = "${finishDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "lesson.price" var = "priceHeader" />
			<display:column property = "lesson.price" title = "${priceHeader}"/>
		
	</display:table>
	
	
	
	
