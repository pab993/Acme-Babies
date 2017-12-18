<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Babies Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<li><a class="fNiv" href="offer/searchForm.do"><spring:message
					code="master.page.offer.search" /></a></li>
		<li><a class="fNiv" href="lesson/searchForm.do"><spring:message
					code="master.page.lesson.search" /></a></li>


		<security:authorize
			access="isAnonymous() || hasRole('CUSTOMER') || hasRole('ADMIN') || hasRole('KINDERGARTEN')">
			<li><a class="fNiv" href="offer/list.do"><spring:message
						code="master.page.offer.list" /></a></li>
		</security:authorize>


		<security:authorize access="hasRole('ADMIN')">
			<li><a href="administrator/dashboard.do"><spring:message
						code="master.page.administrator.dashboard" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CARETAKER')">
			<li><a class="fNiv" href="offer/caretaker/list.do"><spring:message
						code="master.page.offer.list" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CARETAKER')">
			<li><a class="fNiv" href="offer/myList.do"><spring:message
						code="master.page.listByCustomerOffer" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CARETAKER')">
			<li><a class="fNiv" href="apply/list.do"><spring:message
						code="master.page.apply.list" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
		<li><a class="fNiv" href="apply/customer/list.do"><spring:message code="master.page.apply.list" /></a></li>
		</security:authorize>
		
		
		<security:authorize access="isAnonymous() || hasRole('CUSTOMER') || hasRole('ADMIN') || hasRole('CARETAKER')">
		<li><a class="fNiv" href="lesson/list.do"><spring:message code="master.page.lesson.list" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('KINDERGARTEN')">
			<li><a class="fNiv" href="lesson/kindergarten/list.do"><spring:message
						code="master.page.lesson.list" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('KINDERGARTEN')">
			<li><a class="fNiv" href="customer/list.do"><spring:message
						code="master.page.customer.list" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('KINDERGARTEN')">
			<li><a class="fNiv" href="lesson/myList.do"><spring:message
						code="master.page.listByCustomerLesson" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="lesson/customer/list.do"><spring:message
						code="master.page.listByCustomerLesson" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="offer/customer/list.do"><spring:message
						code="master.page.listByCustomerOffer" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="invoice/list.do"><spring:message
						code="master.page.invoice.list" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href="configurationSystem/edit.do"><spring:message
						code="master.page.configurationSystem" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="creditCard/edit.do"><spring:message
						code="master.page.creditCard.edit" /></a></li>
			<%-- 			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
									
				</ul>
			</li> --%>
		</security:authorize>

		<security:authorize access="hasRole('KINDERGARTEN')">
			<li><a class="fNiv" href="creditCard/edit.do"><spring:message
						code="master.page.creditCard.edit" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('CARETAKER')">
			<li><a class="fNiv" href="creditCard/edit.do"><spring:message
						code="master.page.creditCard.edit" /></a></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
			<li><a class="fNiv" href="customer/register.do"><spring:message
						code="master.page.customer.register" /></a></li>
			<li><a class="fNiv" href="caretaker/register.do"><spring:message
						code="master.page.caretaker.register" /></a></li>
			<li><a class="fNiv" href="kindergarten/register.do"><spring:message
						code="master.page.kindergarten.register" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/seeProfile.do"><spring:message
								code="master.page.actor.profile" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

