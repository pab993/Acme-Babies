
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import services.ComentableService;
import domain.Assessment;
import domain.Comentable;
import forms.AssessmentForm;

@Controller
@RequestMapping("/assessment")
public class AssessmentController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private AssessmentService	assessmentService;

	@Autowired
	private ComentableService	comentableService;


	//Create Assessment
	//=======================================================

	@RequestMapping(value = "/postAssessment", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int comentableId) {
		ModelAndView result;

		try {
			final Comentable comentable = this.comentableService.findOneAux(comentableId);
			Assert.notNull(comentable);

			final AssessmentForm assessmentForm = new AssessmentForm();
			assessmentForm.setIdComentable(comentableId);

			result = new ModelAndView("assessment/postAssessment");
			result.addObject("assessmentForm", assessmentForm);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:/panic/misc.do");

		}
		return result;

	}

	@RequestMapping(value = "/postAssessment", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AssessmentForm assessmentForm, final BindingResult binding) {
		ModelAndView result;
		System.out.println(binding.getAllErrors());
		final Assessment assessment = this.assessmentService.reconstruct(assessmentForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(assessmentForm);
		else
			try {

				this.assessmentService.save(assessment);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(assessmentForm, "assessment.commit.error");
			}

		return result;
	}
	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final AssessmentForm assessmentForm) {
		ModelAndView result;

		result = this.createEditModelAndView(assessmentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AssessmentForm assessmentForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("assessment/postAssessment");

		result.addObject("assessmentForm", assessmentForm);
		result.addObject("message", message);

		return result;
	}

}
