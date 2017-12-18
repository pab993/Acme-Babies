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

<form:form action="lesson/search.do">

	<input type="text" name="keyword" />
	<input type="submit" name="search"
		value="<spring:message code = "lesson.search" />" />

</form:form>

<jstl:if test="${!firstTime}">
		
		<display:table name = "lessons" id = "row" requestURI="lesson/searchForm.do" pagesize = "10" class = "displaytag" >

			<spring:message code = "lesson.title" var = "titleHeader" />
			<display:column property = "title" title = "${titleHeader}" />
		
			<spring:message code = "lesson.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
			
			<spring:message code = "lesson.startDate" var = "startDateHeader" />
			<display:column property = "startDate" title = "${startDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "lesson.finishDate" var = "finishDateHeader" />
			<display:column property = "finishDate" title = "${finishDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
						
			<spring:message code = "lesson.price" var = "priceHeader" />
			<display:column property = "price" title = "${priceHeader}"/>
			
	</display:table>
</jstl:if>