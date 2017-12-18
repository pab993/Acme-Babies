
package controllers;

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
import domain.Actor;
import domain.Assessment;
import forms.ActorForm;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	// Services
	// =============================================================================

	@Autowired
	private ActorService	actorService;


	// Constructors
	// ==============================================================================

	public ActorController() {
		super();
	}

	// Profile
	// ==============================================================================

	@RequestMapping(value = "/seeProfile", method = RequestMethod.GET)
	public ModelAndView verProfile(@RequestParam(required = false) final Integer actorId) {
		ModelAndView result;
		Actor principal;

		if (actorId != null) {
			final Actor actor = this.actorService.findOne(actorId);

			if (actor == null)
				result = new ModelAndView("redirect:/panic/misc.do");
			else {

				principal = this.actorService.findByPrincipal();
				result = new ModelAndView("actor/seeProfile");

				result.addObject("principal", principal);
				result.addObject("actor", actor);
			}
		} else {
			principal = this.actorService.findByPrincipal();
			result = new ModelAndView("redirect:/actor/seeProfile.do?actorId=" + principal.getId());
		}

		return result;
	}

	@RequestMapping(value = "/seeProfileUnregistered", method = RequestMethod.GET)
	public ModelAndView verProfileUnregistred(@RequestParam(required = false) final Integer actorId) {
		ModelAndView result;
		Actor principal;

		if (actorId != null) {
			final Actor actor = this.actorService.findOne(actorId);

			result = new ModelAndView("actor/seeProfile");

			result.addObject("actor", actor);
		} else {
			principal = this.actorService.findByPrincipal();
			result = new ModelAndView("redirect:/actor/seeProfile.do?actorId=" + principal.getId());
		}

		return result;
	}

	// Edition
	// ================================================================================

	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public ModelAndView editProfile() {
		ModelAndView result;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		final ActorForm actorForm = this.actorService.reconstructToForm(principal);
		result = new ModelAndView("actor/editProfile");
		result.addObject("actorForm", actorForm);

		return result;
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {
		ModelAndView result;
		Actor actor;

		try {
			actor = this.actorService.reconstruct1(actorForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(actorForm, "actor.save.error");
			else {
				actor = this.actorService.reconstruct2(actorForm, binding);
				result = new ModelAndView("redirect:/actor/seeProfile.do");
				actor = this.actorService.save(actor);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(actorForm, "actor.save.error");
		}

		return result;
	}

	// Display
	// ============================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int actorId) {

		ModelAndView result;
		Actor actor;
		try {
			actor = this.actorService.findOne(actorId);
			Assert.notNull(actor);

			final Collection<Assessment> assessments = actor.getAssessments();

			result = new ModelAndView("actor/display");
			result.addObject("actor", actor);
			result.addObject("assessments", assessments);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/panic/misc.do");
		}

		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/editProfile");

		result.addObject("actorForm", actorForm);
		result.addObject("message", message);

		return result;
	}

}
