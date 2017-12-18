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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="offer/edit.do" modelAttribute="offer">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:hidden path="createMoment"/>

	<form:hidden path="caretaker" />
	<form:hidden path="applies" />		
	
	
	<acme:textbox code="offer.title" path="title" />
	<br />

	<acme:textbox code="offer.description" path="description" />
	<br />
	
	<acme:textbox code="offer.price" path="price" />
	<br />	
		
	<acme:submit name="save" code="offer.submit" />
	
	<jstl:if test = "${offer.id != 0}">
	<input type="submit" name="delete"
		value="<spring:message code="offer.delete" />" 
		onclick="return confirm('<spring:message code = "offer.confirm.delete"/>')"/>
	</jstl:if>
	
	<acme:cancel code="offer.cancel" url="offer/caretaker/list.do" />

</form:form>