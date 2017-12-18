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



<security:authorize access="hasRole('ADMIN')">

	<div>
		<h2>
			<spring:message code="dashboard.minAvgMaxLessonsPerKindergarten" />
			<br>
			<jstl:out value="${minAvgMaxLessonsPerKindergarten}" />
		</h2>
	</div>

<br>

	
	<h2>
		<spring:message code="dashboard.kindergartenWithMoreCustomersInLessons" />
	</h2>
	<jstl:forEach items="${kindergartenWithMoreCustomersInLessons}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
<br>

	
	<h2>
		<spring:message code="dashboard.kindergartenWithLessCustomersInLessons" />
	</h2>
	<jstl:forEach items="${kindergartenWithLessCustomersInLessons}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
<br>

	
	<h2>
		<spring:message code="dashboard.caretakerWithMoreAssessments" />
	</h2>
	<jstl:forEach items="${caretakerWithMoreAssessments}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>

<br>

	
	<h2>
		<spring:message code="dashboard.lessonWithMoreAssessments" />
	</h2>
	<jstl:forEach items="${lessonWithMoreAssessments}" var="item">
		<h4><jstl:out value="${item.title}"/></h4>
	</jstl:forEach>
	
<br>

	
	<h2>
		<spring:message code="dashboard.rankingCaretakersAcceptedOffers" />
	</h2>
	<jstl:forEach items="${rankingCaretakersAcceptedOffers}" var="item">
		<h4><jstl:out value="${item[0].userAccount.username}"/></h4>
	</jstl:forEach>

	<br>

	
	<h2>
		<spring:message code="dashboard.rankingKindergartensCreatedLessons" />
	</h2>
	<jstl:forEach items="${rankingKindergartensCreatedLessons}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>




</security:authorize>
