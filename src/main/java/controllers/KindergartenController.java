
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.KindergartenService;
import domain.Kindergarten;
import forms.KindergartenForm;

@Controller
@RequestMapping("/kindergarten")
public class KindergartenController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public KindergartenController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private KindergartenService	kindergartenService;


	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("kindergarten/edit");

		result.addObject("kindergartenForm", new KindergartenForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final KindergartenForm kindergartenForm, final BindingResult binding) {
		ModelAndView result;
		Kindergarten kindergarten;

		try {
			kindergarten = kindergartenService.reconstruct(kindergartenForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(kindergartenForm, "kindergarten.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				kindergarten = kindergartenService.save(kindergarten);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(kindergartenForm, "kindergarten.save.error");
			System.out.print(oops.getMessage());
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(KindergartenForm kindergartenForm, String message) {

		ModelAndView resul = new ModelAndView("kindergarten/edit");

		resul.addObject("kindergartenForm", kindergartenForm);
		resul.addObject("message", message);
		return resul;
	}

}
