<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="assessment/postAssessment.do" modelAttribute="assessmentForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="idComentable" />
	

	<fieldset >
		<legend><b> <spring:message code="assessment.post" /></b> </legend>

		<acme:textbox code="assessment.title" path="title" mandatory="true"/>
		<br />
		
		<acme:textbox code="assessment.text" path="text" mandatory="true"/>
		<br />
		
<%-- 		<acme:textbox code="assessment.rate" path="rate" mandatory="true"/>
		<br />	 --%>	
		
		<form:label path="rate">
			<spring:message code="assessment.rate"/>(*)
		</form:label>
		<form:select path="rate">
	
			<form:option value="0">0</form:option>
			<form:option value="1">1</form:option>
			<form:option value="2">2</form:option>
			<form:option value="3">3</form:option>
			<form:option value="4">4</form:option>
			<form:option value="5">5</form:option>
	
		</form:select>	
		<br/>
	</fieldset>
	
	<p>
		<acme:submit name="save" code="assessment.submit"/>
		<acme:cancel url="welcome/index.do" code="assessment.cancel"/>
	</p>
</form:form>