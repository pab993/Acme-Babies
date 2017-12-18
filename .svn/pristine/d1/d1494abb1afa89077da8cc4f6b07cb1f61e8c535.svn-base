
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CaretakerService;
import domain.Caretaker;
import forms.CaretakerForm;

@Controller
@RequestMapping("/caretaker")
public class CaretakerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CaretakerController() {
		super();
	}


	//Services
	// ============================================================================

	@Autowired
	private CaretakerService	caretakerService;


	//Edition
	//=============================================================================

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		result = new ModelAndView("caretaker/edit");

		result.addObject("caretakerForm", new CaretakerForm());

		return result;
	}

	// Save
	// ====================================================================

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CaretakerForm caretakerForm, final BindingResult binding) {
		ModelAndView result;
		Caretaker caretaker;

		try {
			caretaker = caretakerService.reconstruct(caretakerForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(caretakerForm, "caretaker.save.error");
			else {
				result = new ModelAndView("redirect:/welcome/index.do");
				caretaker = caretakerService.save(caretaker);

			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(caretakerForm, "caretaker.save.error");
		}

		return result;
	}

	// Ancilliary methods
	// =================================================================================================

	private ModelAndView createEditModelAndView(CaretakerForm caretakerForm, String message) {

		ModelAndView resul = new ModelAndView("caretaker/edit");

		resul.addObject("caretakerForm", caretakerForm);
		resul.addObject("message", message);
		return resul;
	}

}
