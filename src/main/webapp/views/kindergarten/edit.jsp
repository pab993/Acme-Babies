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

<form:form action="kindergarten/register.do" modelAttribute="kindergartenForm">
	
	<fieldset > 
	
	<legend><b> <spring:message code="kindergarten.accountData" /> </b></legend>
	
		<acme:textbox code="kindergarten.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="kindergarten.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="kindergarten.repeatPassword" path="repeatPassword" mandatory="true"/>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="kindergarten.personalData" /></b> </legend>
	
	
		<acme:textbox code="kindergarten.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="kindergarten.surname" path="surname" mandatory="true"/>
		<br />
		
		<acme:textbox code="kindergarten.picture" path="picture" mandatory="true"/>
		<br />
		
		<acme:textbox code="kindergarten.address" path="address"  mandatory="true"/>
		<br/>
			
		<acme:textbox code="kindergarten.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="kindergarten.phone" path="phoneNumber"/>
		<br />
	
	</fieldset>
	
	
	<div>
	<form:checkbox id="myCheck" onclick="comprobar();" path="check"/>
		<form:label path="check">
			<spring:message code="kindergarten.accept" />
			<a href="termAndCondition/termAndCondition.do"><spring:message code="kindergarten.lopd"/></a>
		</form:label>
	</div>
	

	<acme:submit id="submitButton" name="save" code="kindergarten.submit"/>

</form:form>


<script type="text/javascript">

document.getElementById("submitButton").disabled = true;
document.getElementById("myCheck").checked = false;

function comprobar() {
	
	var x = document.getElementById("myCheck").checked;
	
	if(x == true){
		document.getElementById("submitButton").disabled = false;
	}
	else{
		document.getElementById("submitButton").disabled = true;
	}
}



</script>