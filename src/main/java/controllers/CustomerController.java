/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import domain.Actor;
import domain.Customer;
import domain.Kindergarten;
import forms.CustomerForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private CustomerService	customerService;
	
	@Autowired
	private ActorService	actorService;
	
	


	
	// List
		// =============================================================================

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Customer> customers;
			Actor principal = actorService.findByPrincipal();
			
			customers = this.customerService.findAllByKindergarten((Kindergarten)principal);

			result = new ModelAndView("customer/list");
			result.addObject("customers", customers);
			result.addObject("requestURI", "customer/list.do");

			return result;
		}
	
	
	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("customer/edit");

		result.addObject("customerForm", new CustomerForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CustomerForm customerForm, final BindingResult binding) {
		ModelAndView result;
		Customer customer;

		try {
			customer = customerService.reconstruct(customerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(customerForm, "customer.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				customer = this.customerService.save(customer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(customerForm, "customer.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(CustomerForm customerForm, String message) {

		ModelAndView resul = new ModelAndView("customer/edit");

		resul.addObject("customerForm", customerForm);
		resul.addObject("message", message);
		return resul;
	}

}
