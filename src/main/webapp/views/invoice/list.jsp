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

	<display:table name = "invoices" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >
			
			<spring:message code = "invoice.label" var = "labelHeader" />
			<display:column property = "label" title="${labelHeader}" />
		
			<spring:message code = "invoice.concept" var="conceptHeader" />
			<display:column property="concept" title="${conceptHeader}" />
			
			<spring:message code = "invoice.createMoment" var = "createMomentHeader" />
			<display:column property = "createMoment" title="${createMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>
			
			<spring:message code = "invoice.totalPrice" var = "totalPriceHeader" />
			<display:column property = "totalPrice" title="${totalPriceHeader}"/>
			
			
						
	</display:table>
	
	
	
	
