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

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="inscription/edit.do" modelAttribute="inscriptionForm">

	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<form:hidden path="lesson" />
	<form:hidden path="customer" />	
	<form:hidden path="invoice" />	
	<form:hidden path="shiftId"/>

<div>
		<fieldset>
			<b style="color:red"> <spring:message code="information.inscription" /></b>
		</fieldset>
</div>
<br/>
			
	<acme:submit name="save" code="inscription.submit" />
	
	<jstl:if test = "${inscriptionForm.id != 0}">
	<input type="submit" name="delete"
		value="<spring:message code="inscription.delete" />" 
		onclick="return confirm('<spring:message code = "inscription.confirm.delete"/>')"/>
	</jstl:if>
	
	<acme:cancel code="inscription.cancel" url="lesson/list.do" />

</form:form>