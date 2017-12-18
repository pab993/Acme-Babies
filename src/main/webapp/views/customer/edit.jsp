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

<form:form action="customer/register.do" modelAttribute="customerForm">
	
	<fieldset > 
	
	<legend><b> <spring:message code="customer.accountData" /> </b></legend>
	
		<acme:textbox code="customer.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="customer.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="customer.repeatPassword" path="repeatPassword" mandatory="true"/>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="customer.personalData" /></b> </legend>
	
	
		<acme:textbox code="customer.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="customer.surname" path="surname" mandatory="true"/>
		<br />
		
		<acme:textbox code="customer.picture" path="picture" mandatory="true"/>
		<br />
		
		<acme:textbox code="customer.address" path="address"  mandatory="true"/>
		<br/>
			
		<acme:textbox code="customer.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="customer.phone" path="phoneNumber"/>
		<br />
	
	</fieldset>
	
	
	<div>
	<form:checkbox id="myCheck" onclick="comprobar();" path="check"/>
		<form:label path="check">
			<spring:message code="customer.accept" />
			<a href="termAndCondition/termAndCondition.do"><spring:message code="customer.lopd"/></a>
		</form:label>
	</div>
	

	<acme:submit id="submitButton" name="save" code="customer.submit"/>

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