package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InvoiceService;
import domain.Actor;
import domain.Invoice;

@Controller
@RequestMapping("/invoice")
public class InvoiceController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ActorService actorService;

	// Constructors
	// ============================================================================

	public InvoiceController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Invoice> invoices;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		invoices = this.invoiceService.findAllByActor(actor);

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);
		result.addObject("requestURI", "invoice/list.do");

		return result;
	}

}
