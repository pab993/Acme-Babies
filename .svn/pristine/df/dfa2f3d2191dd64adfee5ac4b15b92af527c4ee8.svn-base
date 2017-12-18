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

<form:form action="apply/edit.do" modelAttribute="apply">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:hidden path="status" />

	<form:hidden path="offer" />
	<form:hidden path="customer" />

	<fieldset>
		<legend>
			<b style="color: red"> <spring:message code="information.apply" /></b>
		</legend>
	</fieldset>
	<br />

	<acme:textbox code="apply.name" path="name" mandatory="true" />
	<br />

	<acme:textbox code="apply.surname" path="surname" mandatory="true" />
	<br />

	<acme:textarea code="apply.observations" path="observations"
		mandatory="false" />
	<br />

	<acme:textbox code="apply.days" path="days" mandatory="true" />
	<br />

	<acme:textbox code="apply.counterOffer" path="counterOffer"
		mandatory="true" />
	<br />
	
	<jstl:choose>
			<jstl:when test="${apply.id != 0}">
			<acme:submit name="save" code="apply.submit" />
			<input type="submit" name="delete"
			value="<spring:message code="apply.delete" />"
			onclick="return confirm('<spring:message code = "apply.confirm.delete"/>')" />
			<acme:cancel code="apply.cancel" url="apply/customer/list.do" />
			
			</jstl:when>
			<jstl:otherwise>
				<acme:submit name="create" code="apply.create" />
				<acme:cancel code="apply.cancel" url="offer/list.do" />
				
			</jstl:otherwise>
	</jstl:choose>

</form:form>