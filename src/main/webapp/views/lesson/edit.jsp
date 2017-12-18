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

<form:form action="lesson/edit.do" modelAttribute="lesson">

	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<form:hidden path="kindergarten" />
	<form:hidden path="inscriptions" />	
	<form:hidden path="assessments" />	
	<form:hidden path="shifts" />	


<div>
		<fieldset>
			<b style="color:red"> <spring:message code="information.lessonTax" /></b>: ${lessonTax}
		</fieldset>
</div>
<br/>
	
	<acme:textbox code="lesson.title" path="title" mandatory="true"/>
	<br />

	<acme:textbox code="lesson.description" path="description" mandatory="true"/>
	<br />

	<acme:textbox code="lesson.startDate" path="startDate" mandatory="true"/>
	<br />

	<acme:textbox code="lesson.finishDate" path="finishDate" mandatory="true"/>
	<br />
	
	<acme:textbox code="lesson.price" path="price" mandatory="true"/>
	<br />	
		
	<acme:submit name="save" code="lesson.submit" />
	
	<jstl:if test = "${lesson.id != 0}">
	<input type="submit" name="delete"
		value="<spring:message code="lesson.delete" />" 
		onclick="return confirm('<spring:message code = "lesson.confirm.delete"/>')"/>
	</jstl:if>
	
	<acme:cancel code="lesson.cancel" url="lesson/kindergarten/list.do" />

</form:form>