
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.OfferService;
import domain.Actor;
import domain.Offer;

@Controller
@RequestMapping("/offer")
public class OfferController extends AbstractController {

	// Services
	// ============================================================================

	@Autowired
	private OfferService	offerService;

	@Autowired
	private ActorService	actorService;


	// Constructors
	// ============================================================================

	public OfferController() {
		super();
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Offer> offers = new ArrayList<Offer>();

		offers = this.offerService.findAll();

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/list.do");

		return result;
	}

	// List
	// =============================================================================

	@RequestMapping(value = "/caretaker/list", method = RequestMethod.GET)
	public ModelAndView listCaretaker() {
		ModelAndView result;
		Collection<Offer> offers;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		offers = this.offerService.findAll();

		result = new ModelAndView("offer/listCaretaker");
		result.addObject("offers", offers);
		result.addObject("actor", actor);
		result.addObject("requestURI", "offer/caretaker/list.do");

		return result;
	}

	// List customer
	// =============================================================================

	@RequestMapping(value = "/customer/list", method = RequestMethod.GET)
	public ModelAndView listByCustomer() {
		ModelAndView result;
		Collection<Offer> offers;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		offers = this.offerService.findAllByCustomer(actor);

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/customer/list.do");

		return result;
	}

	// List creadas por caretaker
	// =============================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView myList() {
		ModelAndView result;
		Collection<Offer> offers;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		offers = this.offerService.findAllByCaretaker(actor);

		result = new ModelAndView("offer/listCaretaker");
		result.addObject("offers", offers);
		result.addObject("actor", actor);
		result.addObject("requestURI", "offer/myList.do");

		return result;
	}

	// Display
	// ============================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int offerId) {

		ModelAndView result;
		Offer offer;

		try {
			offer = this.offerService.findOne(offerId);
			Assert.notNull(offer);

			result = new ModelAndView("offer/display");
			result.addObject("offer", offer);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;
	}

	// Create
	// =================================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Offer offer = this.offerService.create();

		result = this.createEditModelAndView(offer);

		return result;
	}

	// Edition
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int offerId) {
		ModelAndView result;

		try {
			final Offer offer = this.offerService.findOne(offerId);
			Assert.notNull(offer);
			Assert.isTrue(offer.getCaretaker().equals(this.actorService.findByPrincipal()));

			result = this.createEditModelAndView(offer);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}
		return result;

	}

	// Save
	// =============================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Offer offer, final BindingResult binding) {
		ModelAndView result;

		try {

			if (binding.hasErrors())
				result = this.createEditModelAndView(offer, "offer.save.error");
			else {
				result = new ModelAndView("redirect:/offer/caretaker/list.do");
				this.offerService.save(offer);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(offer, "offer.save.error");
		}

		return result;
	}

	// Deleting
	// -------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Offer offer, final BindingResult binding) {
		ModelAndView result;

		try {
			this.offerService.delete(offer);
			result = new ModelAndView("redirect:/offer/caretaker/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(offer, "offer.delete.error");
		}
		return result;
	}

	// SearchForm
	// ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Offer> offers;
		Boolean firstTime;

		offers = this.offerService.findAll();
		firstTime = true;

		result = new ModelAndView("offer/search");
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	// Search
	// ==============================================================================

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") final String keyword) {
		ModelAndView result;
		Collection<Offer> offers;

		offers = this.offerService.getOfferByKeyWord(keyword);

		result = new ModelAndView("offer/search");
		result.addObject("offers", offers);

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(final Offer offer) {

		return this.createEditModelAndView(offer, null);
	}

	private ModelAndView createEditModelAndView(final Offer offer, final String message) {

		final ModelAndView result = new ModelAndView("offer/edit");

		result.addObject("offer", offer);
		result.addObject("message", message);

		return result;
	}

}
